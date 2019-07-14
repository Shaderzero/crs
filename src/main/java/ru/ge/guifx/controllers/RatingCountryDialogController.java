package ru.ge.guifx.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.hibernate.Session;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Country;
import ru.ge.data.entities.Rating;
import ru.ge.data.entities.RatingAgency;
import ru.ge.data.entities.RatingCountry;
import ru.ge.guifx.FxUtil;

import java.util.List;

public class RatingCountryDialogController {

    @FXML
    private ComboBox<Country> countryBox;
    @FXML
    private ComboBox<Rating> ratingBox;
    @FXML
    private ComboBox<RatingAgency> ratingAgencyBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button saveButton;

    private CountryFormController parentController;
    private RatingCountry entity;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        FxUtil.autoCompleteComboBoxPlus(
                countryBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase())
                                || itemToCompare.getShortName().toLowerCase().contains(typedText.toLowerCase()));
    }

    private void getData() {
        String sql = "from " + Country.class.getSimpleName() + " as x order by x.name asc";
        List<Country> cList = session.createQuery(sql).list();
        countryBox.setItems(FXCollections.observableArrayList(cList));
        sql = "from " + Rating.class.getSimpleName() + " as x order by x.score asc";
        List<Rating> rList = session.createQuery(sql).list();
        ratingBox.setItems(FXCollections.observableArrayList(rList));
        sql = "from " + RatingAgency.class.getSimpleName() + " as x order by x.name asc";
        List<RatingAgency> agencyList = session.createQuery(sql).list();
        ratingAgencyBox.setItems(FXCollections.observableArrayList(agencyList));
    }

    public void setParentController(CountryFormController controller) {
        this.parentController = controller;
        countryBox.getSelectionModel().select(controller.getEntity());
    }

    public void setEntity(RatingCountry entity) {
        if (entity == null) return;
        try {
            this.entity = session.get(RatingCountry.class, entity.getId());
            if (entity != null) {
                saveButton.setText("Update");
                ratingBox.getSelectionModel().select(entity.getRating());
                ratingAgencyBox.getSelectionModel().select(entity.getRatingAgency());
                datePicker.setValue(entity.getDateStart());
            }
        } catch (Exception e) {
            System.out.println("can't load country rating from database");
            e.printStackTrace();
        } finally {
//            session.close();
        }
    }

    @FXML
    private void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveOrUpdate(ActionEvent actionEvent) {
        if (entity == null) {
            entity = new RatingCountry();
        }
        entity.setCountry(countryBox.getSelectionModel().getSelectedItem());
        entity.setRating(ratingBox.getValue());
        entity.setRatingAgency(ratingAgencyBox.getValue());
        entity.setDateStart(datePicker.getValue());
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        parentController.refresh();
        parentController.setRatings();
        close(actionEvent);
    }
}
