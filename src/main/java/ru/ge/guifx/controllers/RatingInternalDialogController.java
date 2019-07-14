package ru.ge.guifx.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.*;
import ru.ge.guifx.FxUtil;
import ru.ge.util.Helper;

import java.util.List;

public class RatingInternalDialogController {

    @FXML
    private CheckBox conservativeCheckBox;
    @FXML
    private TextArea commentArea;
    @FXML
    private ComboBox<Counterparty> counterpartyBox;
    @FXML
    private ComboBox<Rating> ratingBox;
    @FXML
    private ComboBox<Rating> ratingWCBox;
    @FXML
    private ComboBox<RiskClass> riskClassBox;
    @FXML
    private ComboBox<FinancialStatement> financialStatementBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField analystField;
    @FXML
    private Button saveButton;

    private CounterpartyFormController parentController;
    private RatingInternal entity;
    private Session session = HibernateUtil.getSession();

    @FXML
    void initialize() {
        getData();
        FxUtil.autoCompleteComboBoxPlus(
                counterpartyBox,
                (typedText, itemToCompare) ->
                        itemToCompare.getName().toLowerCase().contains(typedText.toLowerCase())
                                || itemToCompare.getShortName().toLowerCase().contains(typedText.toLowerCase()));
        analystField.setText(Helper.getUser());
    }

    private void getData() {
        String sql = "from " + Counterparty.class.getSimpleName() + " as x order by x.name asc";
        List<Counterparty> cList = session.createQuery(sql).list();
        counterpartyBox.setItems(FXCollections.observableArrayList(cList));
        sql = "from " + Rating.class.getSimpleName() + " as x order by x.score asc";
        List<Rating> rList = session.createQuery(sql).list();
        ratingBox.setItems(FXCollections.observableArrayList(rList));
        ratingWCBox.setItems(FXCollections.observableArrayList(rList));
        sql = "from " + RiskClass.class.getSimpleName() + " as x order by x.name asc";
        List<RiskClass> riskClassList = session.createQuery(sql).list();
        riskClassBox.setItems(FXCollections.observableArrayList(riskClassList));
    }

    public void setParentController(CounterpartyFormController controller) {
        this.parentController = controller;
        getFinancialStatements(controller.getEntity());
        counterpartyBox.getSelectionModel().select(controller.getEntity());
    }

    private void getFinancialStatements(Counterparty counterparty) {
        String sql = "from " + FinancialStatement.class.getSimpleName() + " as x where x.counterparty = :counterparty order by x.dateStart desc";
        Query query = session.createQuery(sql);
        query.setParameter("counterparty", counterparty);
        List<FinancialStatement> financialStatementList = query.list();
        financialStatementBox.setItems(FXCollections.observableArrayList(financialStatementList));
    }

    public void setEntity(RatingInternal entity) {
        if (entity == null) return;
        try {
            this.entity = session.get(RatingInternal.class, entity.getId());
            if (entity != null) {
                saveButton.setText("Update");
                ratingBox.getSelectionModel().select(entity.getRating());
                ratingWCBox.getSelectionModel().select(entity.getRatingWC());
                riskClassBox.getSelectionModel().select(entity.getRiskClass());
                financialStatementBox.getSelectionModel().select(entity.getFinancialStatement());
                conservativeCheckBox.setSelected(entity.isConservative());
                datePicker.setValue(entity.getDateStart());
                commentArea.appendText(entity.getComment());
            }
        } catch (Exception e) {
            System.out.println("can't load rating internal from database");
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
            entity = new RatingInternal();
        }
        entity.setCounterparty(parentController.getEntity());
        entity.setRating(ratingBox.getValue());
        entity.setRatingWc(ratingWCBox.getValue());
        entity.setRiskClass(riskClassBox.getValue());
        entity.setFinancialStatement(financialStatementBox.getValue());
        entity.setDateStart(datePicker.getValue());
        entity.setAnalyst(analystField.getText());
        entity.setConservative(conservativeCheckBox.isSelected());
        entity.setComment(commentArea.getText());
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(entity);
        t.commit();
        parentController.refresh();
        parentController.setInternalRating();
        close(actionEvent);
    }

}
