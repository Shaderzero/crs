package ru.ge.data;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.ge.data.entities2.*;
import ru.ge.data.entitiesCore.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbConverterCore {

    public static CounterpartyGroupCore convertCommitteeGroup(CommitteeGroup2 entity) {
        CounterpartyGroupCore result = null;
        List<CounterpartyGroupCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CounterpartyGroupCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CounterpartyGroupCore();
                result.setName(entity.getName());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CommitteeLimitCore convertCommitteeLimit(CommitteeLimit2 entity) {
        CommitteeLimitCore result = null;
        List<CommitteeLimitCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CommitteeLimitCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CommitteeLimitCore();
                result.setName(entity.getName());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CommitteeStatusCore convertCommitteeStatus(CommitteeStatus2 entity) {
        CommitteeStatusCore result = null;
        List<CommitteeStatusCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CommitteeStatusCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CommitteeStatusCore();
                result.setName(entity.getName());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CommitteeCore convertCommittee(Committee2 entity) {
        CommitteeCore result = null;
        List<CommitteeCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " + CommitteeCore.class.getSimpleName();
            System.out.println("sql = " + sql);
            list = session.createQuery(sql).list();
            for (CommitteeCore x : list) {
                if (x.getCounterparty().getName().equals(entity.getCounterparty().getName()) &&
                        x.getCommiitteeStatus().getName().equals(entity.getCommitteeStatus().getName()) &&
                        x.getDateStart().equals(entity.getStartDate())) {
                    result = x;
                    break;
                }
            }
            if (result == null) {
                result = new CommitteeCore();
                result.setCounterparty(convertCounterparty(entity.getCounterparty()));
                result.setCommiitteeStatus(convertCommitteeStatus(entity.getCommitteeStatus()));
                if (entity.getCommitteeLimit() != null) {
                    result.setCommitteeLimit(convertCommitteeLimit(entity.getCommitteeLimit()));
                }
                result.setDateStart(entity.getStartDate());
                result.setComment(entity.getComment());
                System.out.println(result);
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CounterpartyCore convertCounterparty(Counterparty2 entity) {
        CounterpartyCore result = null;
        List<CounterpartyCore> list = null;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CounterpartyCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CounterpartyCore();
                result.setName(entity.getName());
                result.setShortName(entity.getShortName());
                result.setIntraGroup(entity.isIntraGroup());
                result.setDateStart(entity.getStartDate());
                result.setComment(entity.getComment());
                result.setMonitored(entity.isMonitored());
                if (entity.getFinancialSector() != null) {
                    result.setFinancialSector(convertFinancialSector(entity.getFinancialSector()));
                }
                result.setCountryDomicile(convertCountry(entity.getCountryDomicile()));
                result.setCountryRisk(convertCountry(entity.getCountryRisk()));
                result.setTicker(entity.getTicker());
                if (entity.getRatingDonor() != null) {
                    result.setRatingDonor(convertCounterparty(entity.getRatingDonor()));
                }
                if (entity.getPortfolio() != null) {
                    Set<PortfolioCore> portfolio = new HashSet<>();
                    portfolio.add(convertPortfolio(entity.getPortfolio()));
                    result.setPortfolioSet(portfolio);
                }
                session.saveOrUpdate(result);
                System.out.println("converted: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static FinancialStatementCore convertFinancialStatement(CounterpartyFinancialStatement2 entity) {
        FinancialStatementCore result = null;
        List<FinancialStatementCore> list = null;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    FinancialStatementCore.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            for (FinancialStatementCore x : list) {
                if (x.getCounterparty().getName().equals(entity.getCounterparty().getName()) &&
                        x.getStandard().getName().equals(entity.getFinancialStatementStandard()) &&
                        x.getDateStart() == entity.getStartDate()) {
                    result = x;
                    break;
                }
            }
            if (result == null) {
                result = new FinancialStatementCore();
                result.setDateStart(entity.getStartDate());
                result.setCounterparty(convertCounterparty(entity.getCounterparty()));
                result.setStandard(convertFinancialStatementStandard(entity.getFinancialStatementStandard()));
                result.setComment(entity.getComment());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static FinancialStatementStandardCore convertFinancialStatementStandard(String standard) {
        FinancialStatementStandardCore result = null;
        List<FinancialStatementStandardCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    FinancialStatementStandardCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", standard);
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new FinancialStatementStandardCore();
                result.setName(standard);
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RatingAgencyCore convertRatingAgency(RatingAgency2 entity) {
        RatingAgencyCore result = null;
        List<RatingAgencyCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    RatingAgencyCore.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingAgencyCore x : list) {
                    if (x.getName().equals(entity.getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingAgencyCore();
                String name = entity.getName();
//                if (name.equals("Moody's")) name = "Moody's";
                result.setName(name);
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static FinancialSectorCore convertFinancialSector(FinancialSector2 entity) {
        FinancialSectorCore result = null;
        List<FinancialSectorCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    FinancialSectorCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new FinancialSectorCore();
                result.setName(entity.getName());
                result.setNameRu(entity.getRuName());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CountryCore convertCountry(Country2 entity) {
        CountryCore result = null;
        List<CountryCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CountryCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CountryCore();
                result.setName(entity.getName());
                result.setNameRu(entity.getNameRu());
                result.setShortName(entity.getShortName());
                result.setTicker(entity.getTicker());
                session.saveOrUpdate(result);
                System.out.println("converted: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static PortfolioCore convertPortfolio(Portfolio2 entity) {
        PortfolioCore result = null;
        List<PortfolioCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    PortfolioCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new PortfolioCore();
                result.setName(entity.getName());
                session.beginTransaction();
                session.saveOrUpdate(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CurrencyCore convertCurrency(Currency2 entity) {
        CurrencyCore result = null;
        List<CurrencyCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    CurrencyCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CurrencyCore();
                result.setName(entity.getName());
                session.saveOrUpdate(result);
                System.out.println("converted: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RatingCore convertRatingValue(RatingValue2 entity) {
        RatingCore result = null;
        List<RatingCore> list = null;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    RatingCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new RatingCore();
                String rating = entity.getName();
                result.setName(rating);
                sql = "FROM " + RatingGroupCore.class.getSimpleName() +
                        " WHERE Number = :number";
                query = session.createQuery(sql);
                int groupNumber = 0;
                switch (rating) {
                    case "AAA":
                        result.setScore(1);
                        groupNumber = 1;
                        break;
                    case "AA+":
                        result.setScore(2);
                        groupNumber = 1;
                        break;
                    case "AA":
                        result.setScore(3);
                        groupNumber = 1;
                        break;
                    case "AA-":
                        result.setScore(4);
                        groupNumber = 1;
                        break;
                    case "A+":
                        result.setScore(5);
                        groupNumber = 1;
                        break;
                    case "A":
                        result.setScore(6);
                        groupNumber = 1;
                        break;
                    case "A-":
                        result.setScore(7);
                        groupNumber = 1;
                        break;
                    case "BBB+":
                        result.setScore(8);
                        groupNumber = 2;
                        break;
                    case "BBB":
                        result.setScore(9);
                        groupNumber = 2;
                        break;
                    case "BBB-":
                        result.setScore(10);
                        groupNumber = 2;
                        break;
                    case "BB+":
                        result.setScore(11);
                        groupNumber = 3;
                        break;
                    case "BB":
                        result.setScore(12);
                        groupNumber = 3;
                        break;
                    case "BB-":
                        result.setScore(13);
                        groupNumber = 3;
                        break;
                    case "B+":
                        result.setScore(14);
                        groupNumber = 3;
                        break;
                    case "B":
                        result.setScore(15);
                        groupNumber = 3;
                        break;
                    case "B-":
                        result.setScore(16);
                        groupNumber = 3;
                        break;
                    case "CCC+":
                        result.setScore(17);
                        groupNumber = 4;
                        break;
                    case "CCC":
                        result.setScore(18);
                        groupNumber = 4;
                        break;
                    case "CCC-":
                        result.setScore(19);
                        groupNumber = 4;
                        break;
                    case "CC":
                        result.setScore(20);
                        groupNumber = 4;
                        break;
                    case "C":
                        result.setScore(21);
                        groupNumber = 5;
                        break;
                    case "D":
                        result.setScore(22);
                        groupNumber = 5;
                        break;
                    case "WD":
                        result.setScore(23);
                        groupNumber = 5;
                        break;
                }
                query.setParameter("number", groupNumber);
                List<RatingGroupCore> groupList = query.list();
                RatingGroupCore group = null;
                if (groupList.size() == 0) {
                    group = new RatingGroupCore();
                    group.setNumber(groupNumber);
                    if (groupNumber == 1) {
                        group.setLimit(1500000000000l);
                        group.setLimitBank(80000000000l);
                    } else if (groupNumber == 2) {
                        group.setLimit(800000000000l);
                        group.setLimitBank(45000000000l);
                    } else if (groupNumber == 3) {
                        group.setLimit(150000000000l);
                        group.setLimitBank(20000000000l);
                    } else if (groupNumber == 4) {
                        group.setLimit(10000000000l);
                        group.setLimitBank(1000000000l);
                    } else {
                        group.setLimit(10000000000l);
                        group.setLimitBank(1000000000l);
                    }
                    session.saveOrUpdate(group);
                } else {
                    group = groupList.get(0);
                }
                result.setRatingGroup(group);
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RiskClassCore convertRiskClass(RiskClass2 entity) {
        RiskClassCore result = null;
        List<RiskClassCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    RiskClassCore.class.getSimpleName() +
                    " WHERE Name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new RiskClassCore();
                result.setName(entity.getName());
                session.beginTransaction();
                session.saveOrUpdate(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static RatingInternalCore convertInternalRating(RatingCounterpartyInt2 entity) {
        RatingInternalCore result = null;
        List<RatingInternalCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " + RatingInternalCore.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingInternalCore x : list) {
                    if (x.getCounterparty().getName().equals(entity.getId().getCounterparty().getName()) &&
                            (x.getDateStart() == entity.getId().getStartDate()) &&
                            x.getRating().getName().equals(entity.getId().getRatingValue().getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingInternalCore();
                result.setCounterparty(convertCounterparty(entity.getId().getCounterparty()));
                result.setAnalyst(entity.getId().getAnalyst());
                result.setComment(entity.getId().getComment());
                result.setConservative(entity.getId().isConservative());
                if (entity.getFinancialStatement() != null) {
                    result.setFinancialStatement(convertFinancialStatement(entity.getFinancialStatement()));
                }
                result.setRating(convertRatingValue(entity.getId().getRatingValue()));
                if (entity.getRatingValueWihoutCountry() != null) {
                    result.setRatingWc(convertRatingValue(entity.getRatingValueWihoutCountry()));
                }
                if (entity.getRiskClass() != null) {
                    result.setRiskClass(convertRiskClass(entity.getRiskClass()));
                }
                result.setDateStart(entity.getId().getStartDate());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtilCore.close();
            HibernateUtil2.close();
            System.gc();
            System.exit(0);
        }
        return result;
    }

    public static RatingExternalCore convertExternalRating(RatingCounterpartyExt2 entity) {
        RatingExternalCore result = null;
        List<RatingExternalCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "from " + RatingExternalCore.class.getSimpleName();
            list = session.createQuery(sql, RatingExternalCore.class).getResultList();
            if (list != null && list.size() > 0) {
                for (RatingExternalCore x : list) {
                    if (x.getCounterparty().getName().equals(entity.getId().getCounterparty().getName()) &&
                            (x.getDateStart().equals(entity.getId().getStartDate())) &&
                            x.getRatingValue().getName().equals(entity.getId().getRatingValue().getName()) &&
                            x.getRatingAgency().getName().equals(entity.getId().getRatingAgency().getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingExternalCore();
                result.setCounterparty(convertCounterparty(entity.getId().getCounterparty()));
                result.setRatingAgency(convertRatingAgency(entity.getId().getRatingAgency()));
                result.setRatingValue(convertRatingValue(entity.getId().getRatingValue()));
                result.setDateStart(entity.getId().getStartDate());
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtilCore.close();
            HibernateUtil2.close();
            System.gc();
            System.exit(0);
        }
        return result;
    }

    public static RatingCountryCore convertRatingCountry(RatingCountry2 entity) {
        RatingCountryCore result = null;
        List<RatingCountryCore> list;
        Session session = HibernateUtilCore.getSession();
        try {
            String sql = "FROM " +
                    RatingCountryCore.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingCountryCore x : list) {
                    if (x.getCountry().getName().equals(entity.getId().getCountry().getNameRu())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingCountryCore();
                result.setCountry(convertCountry(entity.getId().getCountry()));
                result.setRatingAgency(convertRatingAgency(entity.getId().getRatingAgency()));
                result.setDateStart(entity.getId().getStartDate());
                result.setRating(convertRatingValue(entity.getId().getRatingValue()));
                session.saveOrUpdate(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
