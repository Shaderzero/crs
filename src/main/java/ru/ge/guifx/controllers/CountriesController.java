package ru.ge.guifx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Country;
import ru.ge.data.entities.RatingCountry;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class CountriesController {

    @FXML
    private TableView<Country> table;
    @FXML
    private TableColumn<Country, Integer> idCol;
    @FXML
    private TableColumn<Country, String> nameCol;
    @FXML
    private TableColumn<Country, String> nameRuCol;
    @FXML
    private TableColumn<Country, String> shortNameCol;
    @FXML
    private TableColumn<Country, String> ratingCol;
    @FXML
    private TableColumn<Country, String> agencyCol;
    @FXML
    private TableColumn<Country, String> tickerCol;
    @FXML
    private TableColumn<Country, String> dateRatingCol;
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
        String sql = "from " + Country.class.getSimpleName() + " as x order by x.name";
        List<Country> countryList = session.createQuery(sql, Country.class).getResultList();
        ObservableList<Country> list = FXCollections.observableArrayList(countryList);
        table.setItems(list);
    }

    private void hideColumns() {

    }

    private void setColumnFactories() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameRuCol.setCellValueFactory(new PropertyValueFactory<>("nameRu"));
        shortNameCol.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        tickerCol.setCellValueFactory(new PropertyValueFactory<>("ticker"));
        ratingCol.setCellValueFactory(param -> {
            Country c = param.getValue();
            List<RatingCountry> set = c.getRatingCountryList();
            String result = "";
            RatingCountry first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (RatingCountry) iterator.next();
                for (RatingCountry next : set) {
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
        agencyCol.setCellValueFactory(param -> {
            Country c = param.getValue();
            List<RatingCountry> set = c.getRatingCountryList();
            String result = "";
            RatingCountry first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (RatingCountry) iterator.next();
                for (RatingCountry next : set) {
                    if (next.getDateStart().compareTo(first.getDateStart()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getRatingAgency().getName();
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
        dateRatingCol.setCellValueFactory(param -> {
            Country c = param.getValue();
            List<RatingCountry> set = c.getRatingCountryList();
            String result = "";
            RatingCountry first = null;
            if (set.size() > 0) {
                Iterator iterator = set.iterator();
                first = (RatingCountry) iterator.next();
                for (RatingCountry next : set) {
                    if (next.getDateStart().compareTo(first.getDateStart()) > 0) {
                        first = next;
                    }
                }
            }
            if (first != null) {
                result = first.getDateStart().toString();
            }
            SimpleStringProperty stringProp = new SimpleStringProperty(result);
            return stringProp;
        });
    }

    private void addListiners() {
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Country country = table.getSelectionModel().getSelectedItem();
                showDetails(country);
            }
        });
    }

    private void showDetails(Country country) {
        ObservableList<Tab> tabList = tabPane.getTabs();
        if (tabList.size() > 1 && country != null) {
            for (int i = 1; i < tabList.size(); i++) {
                if (tabList.get(i).getText().equals(country.getName())) {
                    tabPane.getSelectionModel().select(tabList.get(i));
                    return;
                }
            }
        }
        Tab tab = new Tab();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/countryForm.fxml"));
            AnchorPane pane = loader.load();
            CountryFormController controller = loader.getController();
            controller.setEntity(country);
            controller.setParentController(this);
            tab.setContent(pane);
            if (country == null) {
                tab.setText("new");
            } else {
                tab.setText(country.getName());
            }
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            System.out.println("can't load countryForm");
            e.printStackTrace();
        }
        tabPane.getTabs().add(tab);
    }

    @FXML
    private void newCountry() {
        showDetails(null);
    }

    @FXML
    private void deleteCountry() {
        Country c = table.getSelectionModel().getSelectedItem();
        if (c == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Country");
        alert.setHeaderText("Are you sure want to delete Country?");
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
                table.getItems().remove(c);
                session.getTransaction().begin();
                session.remove(c);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                table.getItems().add(c);
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

    public void update(Country country) {
        ObservableList<Country> list = table.getItems();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == country.getId()) {
                list.set(i, country);
                System.out.println("refresh " + country.getName());
                break;
            }
        }
        table.refresh();
    }

}

