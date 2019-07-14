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
import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.FinancialStatement;
import ru.ge.data.entities.FinancialStatementStandard;
import ru.ge.guifx.FxUtil;

import java.util.List;

public class FinancialStatementDialogController {

    @FXML
    private ComboBox<Counterparty> counterpartyBox;
    @FXML
    private ComboBox<FinancialStatementStandard> standardsBox;
    @FXML
    private TextArea commentArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button saveButton;

    private CounterpartyFormController parentController;
    private FinancialStatement entity;
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
        sql = "from " + FinancialStatementStandard.class.getSimpleName() + " as x order by x.name asc";
        List<FinancialStatementStandard> fsList = session.createQuery(sql).list();
        standardsBox.setItems(FXCollections.observableArrayList(fsList));
    }

    public void setParentController(CounterpartyFormController controller) {
        this.parentController = controller;
        counterpartyBox.getSelectionModel().select(controller.getEntity());
    }

    public void setEntity(FinancialStatement entity) {
        if (entity == null) return;
        try {
            this.entity = session.get(FinancialStatement.class, entity.getId());
            if (entity != null) {
                saveButton.setText("Update");
                standardsBox.getSelectionModel().select(entity.getStandard());
                datePicker.setValue(entity.getDateStart());
                commentArea.appendText(entity.getComment());
            }
        } catch (Exception e) {
            System.out.println("can't load financial statement from database");
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
            entity = new FinancialStatement();
        }
        entity.setCounterparty(counterpartyBox.getSelectionModel().getSelectedItem());
        entity.setStandard(standardsBox.getValue());
        entity.setDateStart(datePicker.getValue());
        entity.setComment(commentArea.getText());
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(entity);
        t.commit();
        parentController.refresh();
        parentController.setFS();
        parentController.setInternalRating();
        close(actionEvent);
    }
}
