package ru.ge.data;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.ge.data.entities.*;
import ru.ge.data.entities2.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbConverterSQL {

    public static Subsidiary defaultSubsidiary() {
        Subsidiary result = null;
        List<Subsidiary> list;
        String name = "Газпром экспорт";
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Subsidiary.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", name);
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Subsidiary();
                result.setName(name);
                result.setCode(11);
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Counterparty defaultBeneficiar() {
        Counterparty result = null;
        List<Counterparty> list;
        String name = "Газпром экспорт";
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Counterparty.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", name);
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Counterparty();
                result.setName(name);
                result.setShortName("GPE");
                result.setDateStart(LocalDate.of(2000, 1, 1));
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static CounterpartyGroup convertCommitteeGroup(CommitteeGroup2 entity) {
        CounterpartyGroup result = null;
        List<CounterpartyGroup> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    CounterpartyGroup.class.getSimpleName() +
                    " as x WHERE x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CounterpartyGroup();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static CommitteeLimit convertCommitteeLimit(CommitteeLimit2 entity) {
        CommitteeLimit result = null;
        List<CommitteeLimit> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    CommitteeLimit.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CommitteeLimit();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static CommitteeStatus convertCommitteeStatus(CommitteeStatus2 entity) {
        CommitteeStatus result = null;
        List<CommitteeStatus> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    CommitteeStatus.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new CommitteeStatus();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Committee convertCommittee(Committee2 entity) {
        Committee result = null;
        List<Committee> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " + Committee.class.getSimpleName();
            list = session.createQuery(sql).list();
            for (Committee x : list) {
                if (x.getCounterparty().getName().equals(entity.getCounterparty().getName()) &&
                        x.getStatus().getName().equals(entity.getCommitteeStatus().getName()) &&
                        x.getDateStart().equals(entity.getStartDate())) {
                    result = x;
                    break;
                }
            }
            if (result == null) {
                result = new Committee();
                result.setCounterparty(convertCounterparty(entity.getCounterparty()));
                result.setStatus(convertCommitteeStatus(entity.getCommitteeStatus()));
                if (entity.getCommitteeLimit() != null) {
                    result.setLimit(convertCommitteeLimit(entity.getCommitteeLimit()));
                }
                result.setDateStart(entity.getStartDate());
                result.setComment(entity.getComment());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Counterparty convertCounterparty(Counterparty2 entity) {
        Counterparty result = null;
        List<Counterparty> list = null;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Counterparty.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Counterparty();
                result.setName(entity.getName());
                result.setShortName(entity.getShortName());
                result.setIntraGroup(entity.isIntraGroup());
                result.setDateStart(entity.getStartDate());
                result.setComment(entity.getComment());
                result.setMonitored(entity.isMonitored());
                if (entity.getFinancialSector() != null) {
                    result.setFinancialSector(convertFinancialSector(entity.getFinancialSector()));
                }
                result.setCountry(convertCountry(entity.getCountryDomicile()));
                result.setCountryRisk(convertCountry(entity.getCountryRisk()));
                result.setTicker(entity.getTicker());
                if (entity.getRatingDonor() != null) {
                    result.setRatingDonor(convertCounterparty(entity.getRatingDonor()));
                }
                if (entity.getPortfolio() != null) {
                    List<Portfolio> portfolios = new ArrayList<>();
                    portfolios.add(convertPortfolio(entity.getPortfolio()));
                    result.setPortfolioList(portfolios);
                }
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
                System.out.println("converted: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static FinancialStatement convertFinancialStatement(CounterpartyFinancialStatement2 entity) {
        FinancialStatement result = null;
        List<FinancialStatement> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "FROM " +
                    FinancialStatement.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            for (FinancialStatement x : list) {
                if (x.getCounterparty().getName().equals(entity.getCounterparty().getName()) &&
                        x.getStandard().getName().equals(entity.getFinancialStatementStandard()) &&
                        x.getDateStart() == entity.getStartDate()) {
                    result = x;
                    break;
                }
            }
            if (result == null) {
                result = new FinancialStatement();
                result.setDateStart(entity.getStartDate());
                result.setCounterparty(convertCounterparty(entity.getCounterparty()));
                result.setStandard(convertFinancialStatementStandard(entity.getFinancialStatementStandard()));
                result.setComment(entity.getComment());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static FinancialStatementStandard convertFinancialStatementStandard(String standard) {
        FinancialStatementStandard result = null;
        List<FinancialStatementStandard> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    FinancialStatementStandard.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", standard);
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new FinancialStatementStandard();
                result.setName(standard);
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static RatingAgency convertRatingAgency(RatingAgency2 entity) {
        RatingAgency result = null;
        List<RatingAgency> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    RatingAgency.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingAgency x : list) {
                    if (x.getName().equals(entity.getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingAgency();
                String name = entity.getName();
//                if (name.equals("Moody's")) name = "Moody's";
                result.setName(name);
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static FinancialSector convertFinancialSector(FinancialSector2 entity) {
        FinancialSector result = null;
        List<FinancialSector> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    FinancialSector.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new FinancialSector();
                result.setName(entity.getName());
                result.setNameRu(entity.getRuName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Country convertCountry(Country2 entity) {
        Country result = null;
        List<Country> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Country.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Country();
                result.setName(entity.getName());
                result.setNameRu(entity.getNameRu());
                result.setShortName(entity.getShortName());
                result.setTicker(entity.getTicker());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Portfolio convertPortfolio(Portfolio2 entity) {
        Portfolio result = null;
        List<Portfolio> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Portfolio.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Portfolio();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Currency convertCurrency(Currency2 entity) {
        if (entity == null) return null;
        Currency result = null;
        List<Currency> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Currency.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Currency();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Rating convertRatingValue(RatingValue2 entity) {
        Rating result = null;
        List<Rating> list = null;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    Rating.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new Rating();
                String rating = entity.getName();
                result.setName(rating);
                sql = "from " + RatingGroup.class.getSimpleName() +
                        " as x where x.number = :number";
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
                List<RatingGroup> groupList = query.list();
                RatingGroup group = null;
                if (groupList.size() == 0) {
                    group = new RatingGroup();
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
                    session.save(group);
                } else {
                    group = groupList.get(0);
                }
                result.setRatingGroup(group);
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static RiskClass convertRiskClass(RiskClass2 entity) {
        RiskClass result = null;
        List<RiskClass> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    RiskClass.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new RiskClass();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static RatingInternal convertInternalRating(RatingCounterpartyInt2 entity) {
        RatingInternal result = null;
        List<RatingInternal> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "FROM " + RatingInternal.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingInternal x : list) {
                    if (x.getCounterparty().getName().equals(entity.getId().getCounterparty().getName()) &&
                            (x.getDateStart() == entity.getId().getStartDate()) &&
                            x.getRating().getName().equals(entity.getId().getRatingValue().getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingInternal();
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
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static RatingExternal convertExternalRating(RatingCounterpartyExt2 entity) {
        RatingExternal result = null;
        List<RatingExternal> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " + RatingExternal.class.getSimpleName();
            list = session.createQuery(sql, RatingExternal.class).getResultList();
            if (list != null && list.size() > 0) {
                for (RatingExternal x : list) {
                    if (x.getCounterparty().getName().equals(entity.getId().getCounterparty().getName()) &&
                            (x.getDateStart().equals(entity.getId().getStartDate())) &&
                            x.getRating().getName().equals(entity.getId().getRatingValue().getName()) &&
                            x.getRatingAgency().getName().equals(entity.getId().getRatingAgency().getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingExternal();
                result.setCounterparty(convertCounterparty(entity.getId().getCounterparty()));
                result.setRatingAgency(convertRatingAgency(entity.getId().getRatingAgency()));
                result.setRating(convertRatingValue(entity.getId().getRatingValue()));
                result.setDateStart(entity.getId().getStartDate());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static RatingCountry convertRatingCountry(RatingCountry2 entity) {
        RatingCountry result = null;
        List<RatingCountry> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "FROM " +
                    RatingCountry.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (RatingCountry x : list) {
                    if (x.getCountry().getName().equals(entity.getId().getCountry().getName())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new RatingCountry();
                result.setCountry(convertCountry(entity.getId().getCountry()));
                result.setRatingAgency(convertRatingAgency(entity.getId().getRatingAgency()));
                result.setDateStart(entity.getId().getStartDate());
                result.setRating(convertRatingValue(entity.getId().getRatingValue()));
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static GuaranteeType convertGuaranteeType(GuaranteeType2 entity) {
        GuaranteeType result = null;
        List<GuaranteeType> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "from " +
                    GuaranteeType.class.getSimpleName() +
                    " as x where x.name = :name";
            Query query = session.createQuery(sql);
            query.setParameter("name", entity.getName());
            list = query.list();
            if (list.size() > 0) {
                result = list.get(0);
            } else {
                result = new GuaranteeType();
                result.setName(entity.getName());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    public static Guarantee convertGuarantee(Guarantee2 entity) {
        Guarantee result = null;
        List<Guarantee> list;
        Session session = HibernateUtil.getSession();
        try {
            String sql = "FROM " +
                    Guarantee.class.getSimpleName();
            Query query = session.createQuery(sql);
            list = query.list();
            if (list.size() > 0) {
                for (Guarantee x : list) {
                    if (x.getCounterparty().getName().equals(entity.getCounterparty().getName()) &&
                            x.getGuarantor().getName().equals(entity.getGuarantor().getName()) &&
                            x.getDateStart() == entity.getStartDate() &&
                            x.getNumber().equals(entity.getGuaranteeNumber())) {
                        result = x;
                        break;
                    }
                }
            }
            if (result == null) {
                result = new Guarantee();
                result.setCounterparty(convertCounterparty(entity.getCounterparty()));
                result.setGuarantor(convertCounterparty(entity.getGuarantor()));
                result.setBeneficiar(defaultBeneficiar());
                result.setCurrency(convertCurrency(entity.getCurrency()));
                result.setType(convertGuaranteeType(entity.getType()));
                result.setAmount(entity.getAmount() > 0 ? entity.getAmount() * 100l : 0l);
                result.setDateStart(entity.getStartDate());
                result.setNumber(entity.getGuaranteeNumber());
                result.setSubsidiary(defaultSubsidiary());
                session.beginTransaction();
                session.save(result);
                session.getTransaction().commit();
                LocalDate reportDate = LocalDate.of(2019, 7, 1);
                if (entity.getEndDate() != null) {
                    reportDate = LocalDate.of(
                            entity.getEndDate().getYear(),
                            entity.getEndDate().getMonthValue(),
                            1);
                }
                GuaranteeReport report = new GuaranteeReport();
                report.setGuarantee(result);
                report.setDateReport(reportDate);
                report.setDateExpiration(entity.getEndDate());
                report.setAmountStart(result.getAmount());
                report.setAmountEnd(result.getAmount());
                report.setAmountOperation(0l);
                result.getReportList().add(report);
                session.beginTransaction();
                session.save(report);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
        return result;
    }

    private static void stop() {
        HibernateUtil2.close();
        HibernateUtil.close();
        System.gc();
        System.exit(0);
    }

}
