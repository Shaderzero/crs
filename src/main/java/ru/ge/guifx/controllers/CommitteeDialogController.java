package ru.ge.guifx.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Committee;
import ru.ge.data.entities.CommitteeLimit;
import ru.ge.data.entities.CommitteeStatus;
import ru.ge.data.entities.Counterparty;
import ru.ge.guifx.FxUtil;

import java.util.List;

public class CommitteeDialogController {

    @FXML
    private ComboBox<Counterparty> counterpartyBox;
    @FXML
    private ComboBox<CommitteeStatus> statusBox;
    @FXML
    private ComboBox<CommitteeLimit> limitBox;
    @FXML
    private TextArea commentArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button saveButton;

    private CounterpartyFormController parentController;
    private Committee entity;
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
        sql = "from " + CommitteeStatus.class.getSimpleName() + " as x order by x.name asc";
        List<CommitteeStatus> csList = session.createQuery(sql).list();
        statusBox.setItems(FXCollections.observableArrayList(csList));
        sql = "from " + CommitteeLimit.class.getSimpleName() + " as x order by x.name asc";
        List<CommitteeLimit> clList = session.createQuery(sql).list();
        limitBox.setItems(FXCollections.observableArrayList(clList));
    }

    public void setParentController(CounterpartyFormController controller) {
        this.parentController = controller;
        counterpartyBox.getSelectionModel().select(controller.getEntity());
    }

    public void setEntity(Committee entity) {
        if (entity == null) return;
        try {
            this.entity = session.get(Committee.class, entity.getId());
            if (entity != null) {
                saveButton.setText("Update");
                limitBox.getSelectionModel().select(entity.getLimit());
                statusBox.getSelectionModel().select(entity.getStatus());
                datePicker.setValue(entity.getDateStart());
                commentArea.appendText(entity.getComment());
            }
        } catch (Exception e) {
            System.out.println("can't load committee from database");
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
            entity = new Committee();
        }
        entity.setCounterparty(counterpartyBox.getSelectionModel().getSelectedItem());
        entity.setLimit(limitBox.getValue());
        entity.setDateStart(datePicker.getValue());
        entity.setComment(commentArea.getText());
        entity.setStatus(statusBox.getValue());
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(entity);
        t.commit();
        parentController.refresh();
        parentController.setCommittee();
        close(actionEvent);
    }
}
