package ru.ge.guifx.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.*;
import ru.ge.guifx.FxUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CounterpartyFormController {

    @FXML
    public TableView<RatingInternal> ratingInternalTable;
    public Counterparty entity;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox pBox;
    @FXML
    private VBox pBox1;
    @FXML
    private VBox pBox2;
    @FXML
    private VBox pBox3;
    @FXML
    private VBox pBox4;
    @FXML
    private VBox pBox5;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField shortNameField;
    @FXML
    private ComboBox<FinancialSector> financialSectorBox;
    @FXML
    private ComboBox<Country> countryDomicileBox;
    @FXML
    private ComboBox<Country> countryRiskBox;
    @FXML
    private ComboBox<Counterparty> ratingDonorBox;
    @FXML
    private ComboBox<CounterpartyGroup> groupBox;
    @FXML
    private CheckBox firstPortfolioBox;
    @FXML
    private CheckBox secondPortfolioBox;
    @FXML
    private TextField innField;
    @FXML
    private TextField swiftField;
    @FXML
    private TextField tickerField;
    @FXML
    private TableColumn<RatingInternal, String> irDateCol;
    @FXML
    private TableColumn<RatingInternal, String> irRatingCol;
    @FXML
    private TableColumn<RatingInternal, String> irRWCCol;
    @FXML
    private TableColumn<RatingInternal, String> irClassCol;
    @FXML
    private TableColumn<RatingInternal, Boolean> irConservativeCol;
    @FXML
    private TableColumn<RatingInternal, String> irFSDateCol;
    @FXML
    private TableColumn<RatingInternal, String> irFSStandardCol;
    @FXML
    private TableColumn<RatingInternal, String> irAnalystCol;
    @FXML
    private TableColumn<RatingInternal, String> irCommentCol;
    @FXML
    private TableView<RatingExternal> ratingExternalTable;
    @FXML
    private TableColumn<RatingExternal, String> erDateCol;
    @FXML
    private TableColumn<RatingExternal, String> erAgencyCol;
    @FXML
    private TableColumn<RatingExternal, String> erRatingCol;
    @FXML
    private TableView<RatingCountry> crRatingTable;
    @FXML
    private TableColumn<RatingCountry, String> crDateCol;
    @FXML
    private TableColumn<RatingCountry, String> crAgencyCol;
    @FXML
    private TableColumn<RatingCountry, String> crRatingCol;
    @FXML
    private TableView<RatingCountry> cdRatingTable;
    @FXML
    private TableColumn<RatingCountry, String> cdDateCol;
    @FXML
    private TableColumn<RatingCountry, String> cdAgencyCol;
    @FXML
    private TableColumn<RatingCountry, String> cdRatingCol;
    @FXML
    private TableView<FinancialStatement> fsTable;
    @FXML
    private TableColumn<FinancialStatement, String> fsDateCol;
    @FXML
    private TableColumn<FinancialStatement, String> fsStandardCol;
    @FXML
    private TableView<Committee> committeeTable;
    @FXML
    private TableColumn<Committee, String> committeeDateCol;
    @FXML
    private TableColumn<Committee, String> committeePortfolioCol;
    @FXML
    private TableColumn<Committee, String> committeeStatusCol;
    @FXML
    private TableColumn<Committee, String> committeeLimitationCol;
    @FXML
    private TableColumn<Committee, String> committeeCommentCol;
    @FXML
    private Button btnDeleteCounterparty;
    @FXML
    private Button btnSaveCounterparty;
    @FXML
    private ToggleButton btnEditMode;
    @FXML
    private Label countryRiskLabel;
    @FXML
    private Label countryLabel;
    private List<Portfolio> portfolioList;
    private Boolean editMode = true;
    private CounterpartiesController parentController;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        FxUtil.autoCompleteComboBoxPlus(
                ratingDonorBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase())
                                || itemToCompare.getShortName().toLowerCase().contains(typedText.toLowerCase()));
        FxUtil.autoCompleteComboBoxPlus(
                countryDomicileBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()));
        FxUtil.autoCompleteComboBoxPlus(
                countryRiskBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase()));
        setColumnFactories();
        addListeners();
    }

    private void setColumnFactories() {
        irAnalystCol.setCellValueFactory(new PropertyValueFactory<>("analyst"));
        irCommentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        irConservativeCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(r.isConservative());
            return booleanProp;
        });
        irConservativeCol.setCellFactory(p -> {
            CheckBoxTableCell<RatingInternal, Boolean> cell = new CheckBoxTableCell<RatingInternal, Boolean>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        irDateCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(r.getDateStart().toString());
            return stringProp;
        });
        irRatingCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            Rating rating = r.getRating();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rating != null) {
                stringProp = new SimpleStringProperty(rating.getName());
            }
            return stringProp;
        });
        irRWCCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            Rating rating = r.getRatingWC();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rating != null) {
                stringProp = new SimpleStringProperty(rating.getName());
            }
            return stringProp;
        });
        irClassCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            RiskClass rc = r.getRiskClass();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rc != null) {
                stringProp = new SimpleStringProperty(rc.getName());
            }
            return stringProp;
        });
        irFSDateCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            FinancialStatement fs = r.getFinancialStatement();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (fs != null) {
                stringProp = new SimpleStringProperty(fs.getDateStart().toString());
            }
            return stringProp;
        });
        irFSStandardCol.setCellValueFactory(param -> {
            RatingInternal r = param.getValue();
            FinancialStatement fs = r.getFinancialStatement();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (fs != null) {
                stringProp = new SimpleStringProperty(fs.getStandard().getName());
            }
            return stringProp;
        });
        erDateCol.setCellValueFactory(param -> {
            RatingExternal r = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(r.getDateStart().toString());
            return stringProp;
        });
        erAgencyCol.setCellValueFactory(param -> {
            RatingExternal r = param.getValue();
            RatingAgency a = r.getRatingAgency();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (a != null) {
                stringProp = new SimpleStringProperty(a.getName());
            }
            return stringProp;
        });
        erRatingCol.setCellValueFactory(param -> {
            RatingExternal r = param.getValue();
            Rating rating = r.getRating();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rating != null) {
                stringProp = new SimpleStringProperty(rating.getName());
            }
            return stringProp;
        });
        crDateCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(r.getDateStart().toString());
            return stringProp;
        });
        crAgencyCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            RatingAgency a = r.getRatingAgency();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (a != null) {
                stringProp = new SimpleStringProperty(a.getName());
            }
            return stringProp;
        });
        crRatingCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            Rating rating = r.getRating();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rating != null) {
                stringProp = new SimpleStringProperty(rating.getName());
            }
            return stringProp;
        });

        cdDateCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(r.getDateStart().toString());
            return stringProp;
        });
        cdAgencyCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            RatingAgency a = r.getRatingAgency();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (a != null) {
                stringProp = new SimpleStringProperty(a.getName());
            }
            return stringProp;
        });
        cdRatingCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            Rating rating = r.getRating();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (rating != null) {
                stringProp = new SimpleStringProperty(rating.getName());
            }
            return stringProp;
        });
        fsDateCol.setCellValueFactory(param -> {
            FinancialStatement fs = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(fs.getDateStart().toString());
            return stringProp;
        });
        fsStandardCol.setCellValueFactory(param -> {
            FinancialStatement fs = param.getValue();
            FinancialStatementStandard s = fs.getStandard();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (s != null) {
                stringProp = new SimpleStringProperty(s.getName());
            }
            return stringProp;
        });
        committeeDateCol.setCellValueFactory(param -> {
            Committee c = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(c.getDateStart().toString());
            return stringProp;
        });
        committeePortfolioCol.setCellValueFactory(param -> {
            Committee c = param.getValue();
            List<Portfolio> set = c.getCounterparty().getPortfolioList();
            String result = "";
            if (set != null && set.size() > 0) {
                Iterator iter = set.iterator();
                Portfolio portfolio = (Portfolio) iter.next();
                result = portfolio.getName();
                if (set.size() > 1) {
                    for (int i = 1; i < set.size(); i++) {
                        portfolio = (Portfolio) iter.next();
                        result += " \\ " + portfolio.getName();
                    }
                }
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
        committeeStatusCol.setCellValueFactory(param -> {
            Committee c = param.getValue();
            CommitteeStatus s = c.getStatus();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (s != null) {
                stringProp = new SimpleStringProperty(s.getName());
            }
            return stringProp;
        });
        committeeLimitationCol.setCellValueFactory(param -> {
            Committee c = param.getValue();
            CommitteeLimit l = c.getLimit();
            SimpleStringProperty stringProp = new SimpleStringProperty("");
            if (l != null) {
                stringProp = new SimpleStringProperty(l.getName());
            }
            return stringProp;
        });
        committeeCommentCol.setCellValueFactory(param -> {
            Committee c = param.getValue();
            return new SimpleStringProperty(c.getComment());
        });
    }

    private void addListeners() {
        int targetHeight = 1000;
        int targetWidth = 1000;
        scrollPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal.intValue() < newVal.intValue()
                    && oldVal.intValue() < targetHeight
                    && scrollPane.getWidth() > targetWidth) {
                flatView();
                return;
            }
            if (oldVal.intValue() > newVal.intValue()
                    && oldVal.intValue() >= targetHeight) {
                slimView();
            }
        });
        scrollPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (oldVal.intValue() < newVal.intValue() && oldVal.intValue() < targetWidth && scrollPane.getHeight() > targetHeight) {
                flatView();
                return;
            }
            if (oldVal.intValue() > newVal.intValue() && oldVal.intValue() >= targetWidth) {
                slimView();
            }
        });
    }

    private void slimView() {
        pBox.setMaxHeight(200);
        pBox.setMinHeight(200);
        pBox1.setMaxHeight(200);
        pBox1.setMinHeight(200);
        pBox2.setMaxHeight(200);
        pBox2.setMinHeight(200);
        pBox3.setMaxHeight(200);
        pBox3.setMinHeight(200);
        pBox4.setMaxHeight(200);
        pBox4.setMinHeight(200);
        pBox5.setMaxHeight(200);
        pBox5.setMinHeight(200);
        System.out.println("slim");
    }

    private void flatView() {
        double targetHeight = scrollPane.getHeight() / 2 - 10;
        pBox.setMaxHeight(targetHeight);
        pBox.setMinHeight(targetHeight);
        pBox1.setMaxHeight(targetHeight);
        pBox1.setMinHeight(targetHeight);
        pBox2.setMaxHeight(targetHeight);
        pBox2.setMinHeight(targetHeight);
        pBox3.setMaxHeight(targetHeight);
        pBox3.setMinHeight(targetHeight);
        pBox4.setMaxHeight(targetHeight);
        pBox4.setMinHeight(targetHeight);
        pBox5.setMaxHeight(targetHeight);
        pBox5.setMinHeight(targetHeight);
        System.out.println("flat");
    }

    @FXML
    private void toggleEditMode() {
        editMode = !editMode;
        setEditMode();
    }

    private void getData() {
        String sql = "from " + FinancialSector.class.getSimpleName() + " as x order by x.name asc";
        List<FinancialSector> fsList = session.createQuery(sql).list();
        financialSectorBox.setItems(FXCollections.observableArrayList(fsList));
        sql = "from " + Country.class.getSimpleName() + " as x order by x.name asc";
        List<Country> countryList = session.createQuery(sql).list();
        countryDomicileBox.setItems(FXCollections.observableArrayList(countryList));
        countryRiskBox.setItems(FXCollections.observableArrayList(countryList));
        sql = "from " + Counterparty.class.getSimpleName() + " as x order by x.name asc";
        List<Counterparty> counterpartyList = session.createQuery(sql).list();
        ratingDonorBox.setItems(FXCollections.observableArrayList(counterpartyList));
        sql = "from " + CounterpartyGroup.class.getSimpleName() + " as x order by x.name asc";
        List<CounterpartyGroup> groupList = session.createQuery(sql).list();
        groupBox.setItems(FXCollections.observableArrayList(groupList));
        sql = "from " + Portfolio.class.getSimpleName() + " as x order by x.name asc";
        portfolioList = session.createQuery(sql).list();
        if (portfolioList.size() > 0) firstPortfolioBox.setText(portfolioList.get(0).getName());
        if (portfolioList.size() > 1) secondPortfolioBox.setText(portfolioList.get(1).getName());

    }

    private void setEditMode() {
        btnDeleteCounterparty.setDisable(!editMode);
        btnSaveCounterparty.setDisable(!editMode);

        nameField.setEditable(editMode);
        shortNameField.setEditable(editMode);
        financialSectorBox.setDisable(!editMode);
        financialSectorBox.setStyle("-fx-opacity: 1");
        ratingDonorBox.setDisable(!editMode);
        ratingDonorBox.setStyle("-fx-opacity: 1");
        countryRiskBox.setDisable(!editMode);
        countryRiskBox.setStyle("-fx-opacity: 1");
        countryDomicileBox.setDisable(!editMode);
        countryDomicileBox.setStyle("-fx-opacity: 1");
        groupBox.setDisable(!editMode);
        groupBox.setStyle("-fx-opacity: 1");
        secondPortfolioBox.setDisable(!editMode);
        secondPortfolioBox.setStyle("-fx-opacity: 1");
        firstPortfolioBox.setDisable(!editMode);
        firstPortfolioBox.setStyle("-fx-opacity: 1");
        innField.setEditable(editMode);
        swiftField.setEditable(editMode);
        tickerField.setEditable(editMode);
    }

    public void refresh() {
        session.refresh(entity);
        parentController.update(entity);
    }

    public void setParentController(CounterpartiesController controller) {
        this.parentController = controller;
    }

    private void fillFields() {
        idField.setText(String.valueOf(entity.getId()));
        nameField.setText(entity.getName());
        shortNameField.setText(entity.getShortName());
        financialSectorBox.getSelectionModel().select(entity.getFinancialSector());
        countryDomicileBox.getSelectionModel().select(entity.getCountry());
        countryRiskBox.getSelectionModel().select(entity.getCountryRisk());
        ratingDonorBox.getSelectionModel().select(entity.getRatingDonor());
        groupBox.getSelectionModel().select(entity.getCounterpartyGroup());
        for (Portfolio p : entity.getPortfolioList()) {
            if (p.getName().equals(firstPortfolioBox.getText())) {
                firstPortfolioBox.setSelected(true);
            }
            if (p.getName().equals(secondPortfolioBox.getText())) {
                secondPortfolioBox.setSelected(true);
            }
        }
        innField.setText(entity.getInn());
        swiftField.setText(entity.getSwift());
        tickerField.setText(entity.getTicker());
        countryLabel.setText("Domicile Country " + entity.getCountry().getName());
        countryRiskLabel.setText("Risk Country " + entity.getCountryRisk().getName());
        setInternalRating();
        setExternalRating();
        setCRRating();
        setCDRating();
        setFS();
        setCommittee();
    }

    public void setInternalRating() {
        ObservableList<RatingInternal> list = FXCollections.observableArrayList(entity.getRatingInternalList());
        ratingInternalTable.setItems(list);
//        ratingInternalTable.setFixedCellSize(25);
//        ratingInternalTable.prefHeightProperty().bind(ratingInternalTable.fixedCellSizeProperty().multiply(Bindings.size(ratingInternalTable.getItems()).add(2.5)));
//        ratingInternalTable.minHeightProperty().bind(ratingInternalTable.prefHeightProperty());
//        ratingInternalTable.maxHeightProperty().bind(ratingInternalTable.prefHeightProperty());
        ratingInternalTable.refresh();
    }

    public void setExternalRating() {
        ObservableList<RatingExternal> list = FXCollections.observableArrayList(entity.getRatingExternalList());
        ratingExternalTable.setItems(list);
        ratingExternalTable.refresh();
    }

    private void setCRRating() {
        ObservableList<RatingCountry> list = FXCollections.observableArrayList(entity.getCountryRisk().getRatingCountryList());
        crRatingTable.setItems(list);
    }

    private void setCDRating() {
        ObservableList<RatingCountry> list = FXCollections.observableArrayList(entity.getCountry().getRatingCountryList());
        cdRatingTable.setItems(list);
    }

    public void setFS() {
        ObservableList<FinancialStatement> list = FXCollections.observableArrayList(entity.getFinancialStatementList());
        fsTable.setItems(list);
        fsTable.refresh();
    }

    public void setCommittee() {
        ObservableList<Committee> list = FXCollections.observableArrayList(entity.getCommitteeList());
        committeeTable.setItems(list);
        committeeTable.refresh();
    }

    @FXML
    private void saveCounterparty() {
        if (entity == null) {
            entity = new Counterparty();
        }
        entity.setName(nameField.getText());
        entity.setShortName(shortNameField.getText());
        entity.setFinancialSector(financialSectorBox.getSelectionModel().getSelectedItem());
        System.out.println(countryDomicileBox.getSelectionModel().getSelectedItem());
        Country country = FxUtil.getComboBoxValue(countryDomicileBox);
        if (country == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You can not save entity without country of domicile");
            alert.showAndWait();
            return;
        }
        entity.setCountry(country);
        country = FxUtil.getComboBoxValue(countryRiskBox);
        if (country == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You can not save entity without country of risk");
            alert.showAndWait();
            return;
        }
        entity.setCountryRisk(country);
        List<Portfolio> pList = new ArrayList<>();
        for (Portfolio p : this.portfolioList) {
            if (secondPortfolioBox.isSelected() && p.getName().equals(secondPortfolioBox.getText())) {
                pList.add(p);
            }
            if (firstPortfolioBox.isSelected() && p.getName().equals(firstPortfolioBox.getText())) {
                pList.add(p);
            }
        }
        entity.setPortfolioList(pList);
        entity.setRatingDonor(FxUtil.getComboBoxValue(ratingDonorBox));
        entity.setDateStart(LocalDate.now());
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(entity);
        t.commit();
        refresh();
        if (idField.getText() == "") idField.setText(String.valueOf(entity.getId()));
    }

    @FXML
    private void addRatingInternal() {
        showRatingInternalDialog(null);
    }

    @FXML
    private void editRatingInternal() {
        RatingInternal rating = ratingInternalTable.getSelectionModel().getSelectedItem();
        showRatingInternalDialog(rating);
    }

    private void showRatingInternalDialog(RatingInternal rating) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ratingInternalDialog.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            RatingInternalDialogController dialogController = fxmlLoader.getController();
            dialogController.setEntity(rating);
            dialogController.setParentController(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRatingInternal() {
        RatingInternal rating = ratingInternalTable.getSelectionModel().getSelectedItem();
        if (rating == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Internal Rating");
        alert.setHeaderText("Are you sure want to delete Internal Rating?");
        alert.setContentText(rating.getRating().getName() + " by " + rating.getDateStart());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            try {
//                RatingInternal ratingForDelete = session.get(RatingInternal.class, rating.getId());
                entity.getRatingInternalList().remove(rating);
                session.getTransaction().begin();
                session.remove(rating);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refresh();
                setInternalRating();
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    @FXML
    private void addRatingExternal() {
        showRatingExternalDialog(null);
    }

    @FXML
    private void editRatingExternal() {
        RatingExternal rating = ratingExternalTable.getSelectionModel().getSelectedItem();
        if (rating == null) return;
        showRatingExternalDialog(rating);
    }

    private void showRatingExternalDialog(RatingExternal rating) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ratingExternalDialog.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            RatingExternalDialogController dialogController = fxmlLoader.<RatingExternalDialogController>getController();
            dialogController.setParentController(this);
            dialogController.setEntity(rating);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRatingExternal() {
        RatingExternal rating = ratingExternalTable.getSelectionModel().getSelectedItem();
        if (rating == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete External Rating");
        alert.setHeaderText("Are you sure want to delete External Rating?");
        alert.setContentText(rating.getRating().getName() + " by " + rating.getDateStart());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            try {
                entity.getRatingExternalList().remove(rating);
                session.getTransaction().begin();
                session.remove(rating);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refresh();
                setExternalRating();
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    @FXML
    private void addFinancialStatement() {
        showFinancialStatement(null);
    }

    @FXML
    private void editFinancialStatement() {
        FinancialStatement fs = fsTable.getSelectionModel().getSelectedItem();
        showFinancialStatement(fs);
    }

    private void showFinancialStatement(FinancialStatement fs) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/financialStatementDialog.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            FinancialStatementDialogController dialogController = fxmlLoader.getController();
            dialogController.setEntity(fs);
            dialogController.setParentController(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteFinancialStatement() {
        FinancialStatement fs = fsTable.getSelectionModel().getSelectedItem();
        if (fs == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Internal Rating");
        alert.setHeaderText("Are you sure want to delete Internal Rating?");
        alert.setContentText(fs.getStandard().getName() + " by " + fs.getDateStart());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            try {
                entity.getFinancialStatementList().remove(fs);
                session.getTransaction().begin();
                session.remove(fs);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refresh();
                setFS();
                setInternalRating();
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    @FXML
    private void addCommittee() {
        showCommittee(null);
    }

    @FXML
    private void editCommitte() {
        Committee c = committeeTable.getSelectionModel().getSelectedItem();
        showCommittee(c);
    }

    private void showCommittee(Committee c) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/committeeDialog.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            CommitteeDialogController dialogController = fxmlLoader.getController();
            dialogController.setEntity(c);
            dialogController.setParentController(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCommittee() {
        Committee c = committeeTable.getSelectionModel().getSelectedItem();
        if (c == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Committee");
        alert.setHeaderText("Are you sure want to delete Committee?");
        alert.setContentText(c.getStatus().getName() + " by " + c.getDateStart());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            try {
                entity.getCommitteeList().remove(c);
                session.getTransaction().begin();
                session.remove(c);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refresh();
                setCommittee();
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    public Counterparty getEntity() {
        return this.entity;
    }

    public void setEntity(Counterparty entity) {
        if (entity == null) return;
        this.entity = session.get(Counterparty.class, entity.getId());
        if (entity != null) {
            editMode = false;
            btnEditMode.setDisable(false);
            fillFields();
            setEditMode();
        }
    }
}
