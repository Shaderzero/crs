package ru.ge;

import org.hibernate.Session;
import ru.ge.data.DbConverterPostgre;
import ru.ge.data.HibernateUtil;
import ru.ge.data.HibernateUtil2;
import ru.ge.data.entities2.*;

import java.util.List;

public class ConvertP {
    public static void main(String[] args) {
        convertDB();
    }

    private static void convertDB() {
        Session session2 = HibernateUtil2.getSession();
        Session session = HibernateUtil.getSession();
        String sql;
        sql = "from " +
                Currency2.class.getSimpleName();
        List<Currency2> currencyList = session2.createQuery(sql, Currency2.class).getResultList();
        for (Currency2 x : currencyList) {
            DbConverterPostgre.convertCurrency(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Portfolio2.class.getSimpleName();
        List<Portfolio2> portfolioList = session2.createQuery(sql, Portfolio2.class).getResultList();
        for (Portfolio2 x : portfolioList) {
            DbConverterPostgre.convertPortfolio(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Country2.class.getSimpleName();
        List<Country2> countryList = session2.createQuery(sql, Country2.class).getResultList();
        for (Country2 x : countryList) {
            DbConverterPostgre.convertCountry(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + RatingCountry2.class.getSimpleName();
        List<RatingCountry2> ratingCountryList = session2.createQuery(sql, RatingCountry2.class).getResultList();
        for (RatingCountry2 x : ratingCountryList) {
            DbConverterPostgre.convertRatingCountry(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "from " + Counterparty2.class.getSimpleName();
        List<Counterparty2> counterpartyList = session2.createQuery(sql, Counterparty2.class).getResultList();
        for (Counterparty2 x : counterpartyList) {
            DbConverterPostgre.convertCounterparty(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + CommitteeLimit2.class.getSimpleName();
        List<CommitteeLimit2> committeeLimitList = session2.createQuery(sql, CommitteeLimit2.class).getResultList();
        for (CommitteeLimit2 x : committeeLimitList) {
            DbConverterPostgre.convertCommitteeLimit(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + Committee2.class.getSimpleName();
        List<Committee2> committeeList = session2.createQuery(sql, Committee2.class).getResultList();
        for (Committee2 x : committeeList) {
            DbConverterPostgre.convertCommittee(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyExt2.class.getSimpleName();
        List<RatingCounterpartyExt2> ratingExternalList = session2.createQuery(sql, RatingCounterpartyExt2.class).getResultList();
        for (RatingCounterpartyExt2 x : ratingExternalList) {
            DbConverterPostgre.convertExternalRating(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyInt2.class.getSimpleName();
        List<RatingCounterpartyInt2> ratingInternalList = session2.createQuery(sql, RatingCounterpartyInt2.class).getResultList();
        for (RatingCounterpartyInt2 x : ratingInternalList) {
            DbConverterPostgre.convertInternalRating(x);
        }
        session.close();

        session = HibernateUtil.getSession();
        sql = "FROM " + Guarantee2.class.getSimpleName();
        List<Guarantee2> guaranteeList = session2.createQuery(sql, Guarantee2.class).getResultList();
        for (Guarantee2 x : guaranteeList) {
            DbConverterPostgre.convertGuarantee(x);
        }
        session.close();

        System.out.println("complete");
    }
}
