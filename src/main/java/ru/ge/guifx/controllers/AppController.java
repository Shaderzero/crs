package ru.ge.guifx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.ge.data.DbConverterSQL;
import ru.ge.data.HibernateUtil;
import ru.ge.data.HibernateUtil2;
import ru.ge.data.entities.UserSetting;
import ru.ge.data.entities2.*;
import ru.ge.guifx.FxUtil;

import java.io.IOException;
import java.util.List;

public class AppController {
    @FXML
    private Button btnCounterparties;
    @FXML
    private Button btnCountries;
    @FXML
    private Pane output;

    @FXML
    void initialize() {
        getSettings();
    }

    @FXML
    private void showCounterparties() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/counterparties.fxml"));
            TabPane pane = loader.load();
            output.getChildren().setAll(pane);
            AnchorPane.setBottomAnchor(pane, 0d);
            AnchorPane.setTopAnchor(pane, 0d);
            AnchorPane.setLeftAnchor(pane, 0d);
            AnchorPane.setRightAnchor(pane, 0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCountries() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/countries.fxml"));
            TabPane pane = loader.load();
            output.getChildren().setAll(pane);
            AnchorPane.setBottomAnchor(pane, 0d);
            AnchorPane.setTopAnchor(pane, 0d);
            AnchorPane.setLeftAnchor(pane, 0d);
            AnchorPane.setRightAnchor(pane, 0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showGuarantees() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/guarantees.fxml"));
            TabPane pane = loader.load();
            output.getChildren().setAll(pane);
            AnchorPane.setBottomAnchor(pane, 0d);
            AnchorPane.setTopAnchor(pane, 0d);
            AnchorPane.setLeftAnchor(pane, 0d);
            AnchorPane.setRightAnchor(pane, 0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void convertDB() {
        Session session2 = HibernateUtil2.getSession();
        Session session = HibernateUtil.getSession();
        String sql;
        sql = "from " +
                Currency2.class.getSimpleName();
        List<Currency2> currencyList = session2.createQuery(sql, Currency2.class).getResultList();
        for (Currency2 x : currencyList) {
            DbConverterSQL.convertCurrency(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Portfolio2.class.getSimpleName();
        List<Portfolio2> portfolioList = session2.createQuery(sql, Portfolio2.class).getResultList();
        for (Portfolio2 x : portfolioList) {
            DbConverterSQL.convertPortfolio(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Country2.class.getSimpleName();
        List<Country2> countryList = session2.createQuery(sql, Country2.class).getResultList();
        for (Country2 x : countryList) {
            DbConverterSQL.convertCountry(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + RatingCountry2.class.getSimpleName();
        List<RatingCountry2> ratingCountryList = session2.createQuery(sql, RatingCountry2.class).getResultList();
        for (RatingCountry2 x : ratingCountryList) {
            DbConverterSQL.convertRatingCountry(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Counterparty2.class.getSimpleName();
        List<Counterparty2> counterpartyList = session2.createQuery(sql, Counterparty2.class).getResultList();
        for (Counterparty2 x : counterpartyList) {
            DbConverterSQL.convertCounterparty(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + CommitteeLimit2.class.getSimpleName();
        List<CommitteeLimit2> committeeLimitList = session2.createQuery(sql, CommitteeLimit2.class).getResultList();
        for (CommitteeLimit2 x : committeeLimitList) {
            DbConverterSQL.convertCommitteeLimit(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + Committee2.class.getSimpleName();
        List<Committee2> committeeList = session2.createQuery(sql, Committee2.class).getResultList();
        for (Committee2 x : committeeList) {
            DbConverterSQL.convertCommittee(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyExt2.class.getSimpleName();
        List<RatingCounterpartyExt2> ratingExternalList = session2.createQuery(sql, RatingCounterpartyExt2.class).getResultList();
        for (RatingCounterpartyExt2 x : ratingExternalList) {
            DbConverterSQL.convertExternalRating(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyInt2.class.getSimpleName();
        List<RatingCounterpartyInt2> ratingInternalList = session2.createQuery(sql, RatingCounterpartyInt2.class).getResultList();
        for (RatingCounterpartyInt2 x : ratingInternalList) {
            DbConverterSQL.convertInternalRating(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + Guarantee2.class.getSimpleName();
        List<Guarantee2> guaranteeList = session2.createQuery(sql, Guarantee2.class).getResultList();
        for (Guarantee2 x : guaranteeList) {
            DbConverterSQL.convertGuarantee(x);
        }
        session.close();

        System.out.println("complete");
    }

    private void getSettings() {
        Session session = HibernateUtil.getSession();
        try {
            String username = System.getProperty("user.name");
            System.out.println(username);
            String sql = "from " + UserSetting.class.getSimpleName() + " as x where x.username = :username";
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            List<UserSetting> settingsList = query.list();
            if (settingsList.size() == 0) {
                //create userSettings with defaults
                FxUtil.userSettings = new UserSetting(username);
            } else {
                FxUtil.userSettings = settingsList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can't load user settings from database");
        } finally {
            session.close();
        }
    }
}
