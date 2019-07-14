package ru.ge.guifx.controllers;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Guarantee;
import ru.ge.data.entities.GuaranteeReport;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GuaranteesController {

    @FXML
    private TableView<Guarantee> table;
    @FXML
    private TableColumn<Guarantee, Integer> idCol;
    @FXML
    private TableColumn<Guarantee, String> numberCol;
    @FXML
    private TableColumn<Guarantee, String> dateStartCol;
    @FXML
    private TableColumn<Guarantee, Long> amountInitCol;
    @FXML
    private TableColumn<Guarantee, String> currencyCol;
    @FXML
    private TableColumn<Guarantee, Long> amountLastCol;
    @FXML
    private TableColumn<Guarantee, String> dateEndLastCol;
    @FXML
    private TableColumn<Guarantee, String> counterpartyCol;
    @FXML
    private TableColumn<Guarantee, String> guarantorCol;
    @FXML
    private TableColumn<Guarantee, String> benificiarCol;
    @FXML
    private TableColumn<Guarantee, String> subsidiaryCol;
    @FXML
    private TableColumn<Guarantee, String> typeCol;
    @FXML
    private TableColumn<Guarantee, String> commentCol;
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
        String sql = "from " + Guarantee.class.getSimpleName() + " as x order by x.number";
        List<Guarantee> guaranteeList = session.createQuery(sql, Guarantee.class).getResultList();
        ObservableList<Guarantee> list = FXCollections.observableArrayList(guaranteeList);
        table.setItems(list);
    }

    private void hideColumns() {
    }

    private void setColumnFactories() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        counterpartyCol.setCellValueFactory(new PropertyValueFactory<>("counterparty"));
        guarantorCol.setCellValueFactory(new PropertyValueFactory<>("guarantor"));
        benificiarCol.setCellValueFactory(new PropertyValueFactory<>("beneficiar"));
        subsidiaryCol.setCellValueFactory(new PropertyValueFactory<>("subsidiary"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateStartCol.setCellValueFactory(param -> {
            Guarantee g = param.getValue();
            SimpleStringProperty stringProp = new SimpleStringProperty(g.getDateStart().toString());
            return stringProp;
        });
        amountInitCol.setCellValueFactory(param -> {
            Guarantee g = param.getValue();
            SimpleLongProperty prop = new SimpleLongProperty(g.getAmount());
            return prop.asObject();
        });
        amountLastCol.setCellValueFactory(param -> {
            Guarantee g = param.getValue();
            List<GuaranteeReport> set = g.getReportList();
            Long result = 0l;
            GuaranteeReport first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (GuaranteeReport) iterator.next();
                for (GuaranteeReport next : set) {
                    if (next.getDateReport().compareTo(first.getDateReport()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getAmountEnd();
            }
            SimpleLongProperty prop = new SimpleLongProperty(result);
            return prop.asObject();
        });
//        amountInitCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountInitCol.setCellFactory(tc -> new TableCell<Guarantee, Long>() {
            @Override
            protected void updateItem(Long number, boolean empty) {
                super.updateItem(number, empty);
                if (empty) {
                    setText(null);
                } else {
                    String str = Long.toString(number);
                    if (str.length() == 1) {
                        str = "0.0" + str;
                    } else if (str.length() == 2) {
                        str = "0." + str;
                    } else {
                        str = new StringBuilder(str).insert(str.length() - 2, ".").toString();
                    }
                    setText(str);
                }
            }
        });
        amountLastCol.setCellFactory(tc -> new TableCell<Guarantee, Long>() {
            @Override
            protected void updateItem(Long number, boolean empty) {
                super.updateItem(number, empty);
                if (empty) {
                    setText(null);
                } else {
                    String str = Long.toString(number);
                    if (str.length() == 1) {
                        str = "0.0" + str;
                    } else if (str.length() == 2) {
                        str = "0." + str;
                    } else {
                        str = new StringBuilder(str).insert(str.length() - 2, ".").toString();
                    }
                    setText(str);
                }
            }
        });
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));
        dateEndLastCol.setCellValueFactory(param -> {
            Guarantee g = param.getValue();
            List<GuaranteeReport> set = g.getReportList();
            String result = "";
            GuaranteeReport first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (GuaranteeReport) iterator.next();
                for (GuaranteeReport next : set) {
                    if (next.getDateReport().compareTo(first.getDateReport()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getDateReport().toString();
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });


        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }

    private void addListiners() {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
//                Counterparty counterparty = table.getSelectionModel().getSelectedItem();
//                showDetails(counterparty);
            }
        });
    }

    private void showDetails(Guarantee guarantee) {
        ObservableList<Tab> tabList = tabPane.getTabs();
        if (tabList.size() > 1 && guarantee != null) {
            for (int i = 1; i < tabList.size(); i++) {
                if (tabList.get(i).getText().equals(guarantee.getNumber())) {
                    tabPane.getSelectionModel().select(tabList.get(i));
                    return;
                }
            }
        }
//        Tab tab = new Tab();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/guaranteeForm.fxml"));
//            AnchorPane pane = loader.load();
//            CounterpartyFormController controller = loader.getController();
//            controller.setEntity(counterparty);
//            controller.setParentController(this);
//            tab.setContent(pane);
//            if (counterparty == null) {
//                tab.setText("new");
//            } else {
//                tab.setText(counterparty.getName());
//            }
//            tabPane.getSelectionModel().select(tab);
//        } catch (IOException e) {
//            System.out.println("can't load counterpartyForm");
//            e.printStackTrace();
//        }
//        tabPane.getTabs().add(tab);
    }

    @FXML
    private void newGuarantee() {
        showDetails(null);
    }

    @FXML
    private void deleteGuarantee() {
        Guarantee g = table.getSelectionModel().getSelectedItem();
        if (g == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Guarantee");
        alert.setHeaderText("Are you sure want to delete Guarantee?");
        alert.setContentText(g.getNumber());
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            Transaction transaction = null;
            try {
                ObservableList<Tab> tabList = tabPane.getTabs();
                if (tabList.size() > 1) {
                    for (int i = 1; i < tabList.size(); i++) {
                        if (tabList.get(i).getText().equals(g.getNumber())) {
                            tabPane.getTabs().remove(tabList.get(i));
                            break;
                        }
                    }
                }
                transaction = session.beginTransaction();
                table.getItems().remove(g);
                Guarantee cForDelete = session.get(Guarantee.class, g.getId());
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

    public void update(Guarantee guarantee) {
        ObservableList<Guarantee> list = table.getItems();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == guarantee.getId()) {
                list.set(i, guarantee);
                System.out.println("refresh " + guarantee.getNumber());
                break;
            }
        }
        table.refresh();
    }

}

