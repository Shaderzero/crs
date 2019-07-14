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
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.Rating;
import ru.ge.data.entities.RatingAgency;
import ru.ge.data.entities.RatingExternal;
import ru.ge.guifx.FxUtil;

import java.util.List;

public class RatingExternalDialogController {

    @FXML
    private ComboBox<Counterparty> counterpartyBox;
    @FXML
    private ComboBox<Rating> ratingBox;
    @FXML
    private ComboBox<RatingAgency> ratingAgencyBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button saveButton;

    private CounterpartyFormController parentController;
    private RatingExternal entity;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        FxUtil.autoCompleteComboBoxPlus(
                counterpartyBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase())
                                || itemToCompare.getShortName().toLowerCase().contains(typedText.toLowerCase()));
    }

    private void getData() {
        String sql = "from " + Counterparty.class.getSimpleName() + " as x order by x.name asc";
        List<Counterparty> cList = session.createQuery(sql).list();
        counterpartyBox.setItems(FXCollections.observableArrayList(cList));
        sql = "from " + Rating.class.getSimpleName() + " as x order by x.score asc";
        List<Rating> rList = session.createQuery(sql).list();
        ratingBox.setItems(FXCollections.observableArrayList(rList));
        sql = "from " + RatingAgency.class.getSimpleName() + " as x order by x.name asc";
        List<RatingAgency> agencyList = session.createQuery(sql).list();
        ratingAgencyBox.setItems(FXCollections.observableArrayList(agencyList));
    }

    public void setParentController(CounterpartyFormController controller) {
        this.parentController = controller;
        counterpartyBox.getSelectionModel().select(controller.getEntity());
    }

    public void setEntity(RatingExternal entity) {
        if (entity == null) return;
        try {
            this.entity = session.get(RatingExternal.class, entity.getId());
            if (entity != null) {
                saveButton.setText("Update");
                ratingBox.getSelectionModel().select(entity.getRating());
                ratingAgencyBox.getSelectionModel().select(entity.getRatingAgency());
                datePicker.setValue(entity.getDateStart());
            }
        } catch (Exception e) {
            System.out.println("can't load rating external from database");
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
            entity = new RatingExternal();
        }
        entity.setCounterparty(counterpartyBox.getSelectionModel().getSelectedItem());
        entity.setRating(ratingBox.getValue());
        entity.setRatingAgency(ratingAgencyBox.getValue());
        entity.setDateStart(datePicker.getValue());
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(entity);
        t.commit();
        parentController.refresh();
        parentController.setExternalRating();
        close(actionEvent);
    }
}
