package ru.ge.guifx.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.Portfolio;
import ru.ge.data.entities.RatingExternal;
import ru.ge.data.entities.RatingInternal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CounterpartiesController {

    @FXML
    private TableView<Counterparty> table;
    @FXML
    private TableColumn<Counterparty, Integer> idCol;
    @FXML
    private TableColumn<Counterparty, String> nameCol;
    @FXML
    private TableColumn<Counterparty, String> sNameCol;
    @FXML
    private TableColumn<Counterparty, String> sectorCol;
    @FXML
    private TableColumn<Counterparty, String> cRiskCol;
    @FXML
    private TableColumn<Counterparty, String> domCol;
    @FXML
    private TableColumn<Counterparty, Boolean> intraCol;
    @FXML
    private TableColumn<Counterparty, String> portfolioCol;
    @FXML
    private TableColumn<Counterparty, String> donorCol;
    @FXML
    private TableColumn<Counterparty, String> dateCol;
    @FXML
    private TableColumn<Counterparty, String> commentCol;
    @FXML
    private TableColumn<Counterparty, String> innCol;
    @FXML
    private TableColumn<Counterparty, String> swiftCol;
    @FXML
    private TableColumn<Counterparty, String> tickerCol;
    @FXML
    private TableColumn<Counterparty, String> ratingIntCol;
    @FXML
    private TableColumn<Counterparty, String> ratingExtCol;
    @FXML
    private TabPane tabPane;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        setColumnFactories();
        addListiners();
        hideColumns();
    }

    private void getData() {
        String sql = "from " + Counterparty.class.getSimpleName() + " as x order by x.name";
        List<Counterparty> counterpartyList = session.createQuery(sql, Counterparty.class).getResultList();
        ObservableList<Counterparty> list = FXCollections.observableArrayList(counterpartyList);
        table.setItems(list);
    }

    private void hideColumns() {
//        UserSetting settings = FxUtil.userSettings;
//        idCol.setVisible(settings.getTableCounterpartyShowId());
//        sNameCol.setVisible(settings.getTableCounterpartyShowShortName());
//        sectorCol.setVisible(settings.getTableCounterpartyShowSector());
//        cRiskCol.setVisible(settings.getTableCounterpartyShowCountryOfRisk());
//        domCol.setVisible(settings.getTableCounterpartyShowCountryOfDomicile());
//        intraCol.setVisible(settings.getTableCounterpartyShowIntraGroup());
//        portfolioCol.setVisible(settings.getTableCounterpartyShowPortfolio());
//        donorCol.setVisible(settings.getTableCounterpartyShowDonor());
//        dateCol.setVisible(settings.getTableCounterpartyShowStartDate());
//        commentCol.setVisible(settings.getTableCounterpartyShowComment());
//        innCol.setVisible(settings.getTableCounterpartyShowINN());
//        swiftCol.setVisible(settings.getTableCounterpartyShowSWIFT());
//        tickerCol.setVisible(settings.getTableCounterpartyShowTicker());
//        ratingIntCol.setVisible(settings.getTableCounterpartyShowLastIntRating());
//        ratingExtCol.setVisible(settings.getTableCounterpartyShowLastExtRating());
    }

    private void setColumnFactories() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sNameCol.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        sectorCol.setCellValueFactory(new PropertyValueFactory<>("financialSector"));
        cRiskCol.setCellValueFactory(new PropertyValueFactory<>("countryRisk"));
        domCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        donorCol.setCellValueFactory(new PropertyValueFactory<>("ratingDonor"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        intraCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(c.isIntraGroup());
            return booleanProp;
        });
        intraCol.setCellFactory(p -> {
            CheckBoxTableCell<Counterparty, Boolean> cell = new CheckBoxTableCell<Counterparty, Boolean>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        portfolioCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            List<Portfolio> set = c.getPortfolioList();
            String result = "";
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                Portfolio first = (Portfolio) iterator.next();
                result = first.getName();
                if (set.size() > 1) {
                    for (int i = 1; i < set.size(); i++) {
                        Portfolio next = (Portfolio) iterator.next();
                        result += " / " + next.getName();
                    }
                }
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
        dateCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(c.getDateStart().toString());
            return stringProp;
        });
        innCol.setCellValueFactory(new PropertyValueFactory<>("inn"));
        swiftCol.setCellValueFactory(new PropertyValueFactory<>("swift"));
        tickerCol.setCellValueFactory(new PropertyValueFactory<>("ticker"));
        ratingIntCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            List<RatingInternal> set = c.getRatingInternalList();
            String result = "";
            RatingInternal first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (RatingInternal) iterator.next();
                for (RatingInternal next : set) {
                    if (next.getDateStart().compareTo(first.getDateStart()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getRating().getName();
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
        ratingExtCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            List<RatingExternal> set = c.getRatingExternalList();
            String result = "";
            RatingExternal first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (RatingExternal) iterator.next();
                for (RatingExternal next : set) {
                    if (next.getDateStart().compareTo(first.getDateStart()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getRating().getName() + " (" + first.getRatingAgency().getName() + ")";
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
    }

    private void addListiners() {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Counterparty counterparty = table.getSelectionModel().getSelectedItem();
                showDetails(counterparty);
            }
        });
    }

    private void showDetails(Counterparty counterparty) {
        ObservableList<Tab> tabList = tabPane.getTabs();
        if (tabList.size() > 1 && counterparty != null) {
            for (int i = 1; i < tabList.size(); i++) {
                if (tabList.get(i).getText().equals(counterparty.getName())) {
                    tabPane.getSelectionModel().select(tabList.get(i));
                    return;
                }
            }
        }
        Tab tab = new Tab();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/counterpartyForm.fxml"));
            AnchorPane pane = loader.load();
            CounterpartyFormController controller = loader.getController();
            controller.setEntity(counterparty);
            controller.setParentController(this);
            tab.setContent(pane);
            if (counterparty == null) {
                tab.setText("new");
            } else {
                tab.setText(counterparty.getName());
            }
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            System.out.println("can't load counterpartyForm");
            e.printStackTrace();
        }
        tabPane.getTabs().add(tab);
    }

    @FXML
    private void newCounterparty() {
        showDetails(null);
    }

    @FXML
    private void deleteCounterparty() {
        Counterparty c = table.getSelectionModel().getSelectedItem();
        if (c == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Internal Rating");
        alert.setHeaderText("Are you sure want to delete Counterparty?");
        alert.setContentText(c.getName());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            Transaction transaction = null;
            try {
                ObservableList<Tab> tabList = tabPane.getTabs();
                if (tabList.size() > 1) {
                    for (int i = 1; i < tabList.size(); i++) {
                        if (tabList.get(i).getText().equals(c.getName())) {
                            tabPane.getTabs().remove(tabList.get(i));
                            break;
                        }
                    }
                }
                transaction = session.beginTransaction();
                table.getItems().remove(c);
                Counterparty cForDelete = session.get(Counterparty.class, c.getId());
                session.remove(cForDelete);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            } finally {
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    public void update() {
        getData();
        table.refresh();
    }

    public void update(Counterparty counterparty) {
        ObservableList<Counterparty> list = table.getItems();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == counterparty.getId()) {
                list.set(i, counterparty);
                System.out.println("refresh " + counterparty.getName());
                break;
            }
        }
        table.refresh();
    }

}

