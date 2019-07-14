package ru.ge.guifx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.Country;
import ru.ge.data.entities.RatingCountry;

import java.io.IOException;
import java.util.Optional;

public class CountryFormController {

    @FXML
    public TableView<RatingCountry> ratingCountryTable;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField nameRuField;
    @FXML
    private TextField nameShortField;
    @FXML
    private TextField tickerField;
    @FXML
    private TableColumn<RatingCountry, String> rcDateCol;
    @FXML
    private TableColumn<RatingCountry, String> rcAgencyCol;
    @FXML
    private TableColumn<RatingCountry, String> rcRatingCol;
    @FXML
    private TableView<Counterparty> counterpartyTable;
    @FXML
    private TableColumn<Counterparty, String> cDateCol;
    @FXML
    private TableColumn<Counterparty, String> cNameCol;
    @FXML
    private TableColumn<Counterparty, String> cDonorCol;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private ToggleButton editButton;

    private Country entity;
    private Boolean editMode = true;
    private CountriesController parentController;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        setColumnFactories();
        addListeners();
    }

    private void setColumnFactories() {
        rcDateCol.setCellValueFactory(param -> {
            RatingCountry r = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(r.getDateStart().toString());
            return stringProp;
        });
        rcAgencyCol.setCellValueFactory(new PropertyValueFactory<>("ratingAgency"));
        rcRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        cDateCol.setCellValueFactory(param -> {
            Counterparty c = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(c.getDateStart().toString());
            return stringProp;
        });
        cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cDonorCol.setCellValueFactory(new PropertyValueFactory<>("ratingDonor"));
    }

    private void addListeners() {

    }

    @FXML
    private void toggleEdit() {
        editMode = !editMode;
        setEditMode();
    }

    private void getData() {
    }

    private void setEditMode() {
        deleteButton.setDisable(!editMode);
        saveButton.setDisable(!editMode);

        nameField.setEditable(editMode);
        nameRuField.setEditable(editMode);
        nameShortField.setEditable(editMode);
        tickerField.setEditable(editMode);
    }

    public void refresh() {
        session.refresh(entity);
        parentController.update(entity);
    }

    public void setParentController(CountriesController controller) {
        this.parentController = controller;
    }

    private void fillFields() {
        idField.setText(String.valueOf(entity.getId()));
        nameField.setText(entity.getName());
        nameRuField.setText(entity.getNameRu());
        nameShortField.setText(entity.getShortName());
        tickerField.setText(entity.getTicker());
        setRatings();
        setCounterparties();
    }

    public void setRatings() {
        ObservableList<RatingCountry> list = FXCollections.observableArrayList(entity.getRatingCountryList());
        ratingCountryTable.setItems(list);
        ratingCountryTable.refresh();
    }

    public void setCounterparties() {
        ObservableList<Counterparty> list = FXCollections.observableArrayList(entity.getCounterpartyList());
        counterpartyTable.setItems(list);
        counterpartyTable.refresh();
    }

    @FXML
    private void save() {
        if (entity == null) {
            entity = new Country();
        }
        entity.setName(nameField.getText());
        entity.setNameRu(nameRuField.getText());
        entity.setShortName(nameShortField.getText());
        entity.setTicker(tickerField.getText());
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        refresh();
        if (idField.getText() == "") idField.setText(String.valueOf(entity.getId()));
    }

    @FXML
    private void addRatingCountry() {
        showRatingCountryDialog(null);
    }

    @FXML
    private void editRatingCountry() {
        RatingCountry rating = ratingCountryTable.getSelectionModel().getSelectedItem();
        if (rating == null) return;
        showRatingCountryDialog(rating);
    }

    private void showRatingCountryDialog(RatingCountry rating) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ratingCountryDialog.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            RatingCountryDialogController dialogController = fxmlLoader.<RatingCountryDialogController>getController();
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
    private void deleteRatingCountry() {
        RatingCountry rating = ratingCountryTable.getSelectionModel().getSelectedItem();
        if (rating == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Country Rating");
        alert.setHeaderText("Are you sure want to delete Country Rating?");
        alert.setContentText(rating.getRating().getName() + " by " + rating.getDateStart());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            try {
                entity.getRatingCountryList().remove(rating);
                session.getTransaction().begin();
                session.remove(rating);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                refresh();
                setRatings();
            }
        } else if (option.get() == ButtonType.CANCEL) {
            System.out.println("cancel");
        } else {
            System.out.println("how it can be?");
        }
    }

    public Country getEntity() {
        return this.entity;
    }

    public void setEntity(Country entity) {
        if (entity == null) return;
        this.entity = session.get(Country.class, entity.getId());
        if (entity != null) {
            editMode = false;
            editButton.setDisable(false);
            fillFields();
            setEditMode();
        }
    }
}
