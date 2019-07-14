package ru.ge.gui;

import org.hibernate.Session;
import ru.ge.data.HibernateUtil;
import ru.ge.data.entities.Counterparty;
import ru.ge.data.entities.Portfolio;

import java.util.Iterator;
import java.util.List;

public class CounterpartyTable extends TablePanel<Counterparty> {

    protected static CounterpartiesPanel parentPanel = CounterpartiesPanel.getInstance();
    private static String title = "Counterparties";
    private static String[] columnNames = {"Id", "Name", "Short Name", "Sector", "Country of Risk", "Country of Domicile", "Intra Group", "Portfolio", "Comment", "Donor", "Start Date"};

    @Override
    protected void create() {
        try {
//            new CounterpartyGui(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void update() {
        try {
//            if (getEntity() != null)
//                new CounterpartyGui((Counterparty) getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Object getValueAtModel(Counterparty entity, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return entity.getId();
            case 1:
                return entity.getName();
            case 2:
                return entity.getShortName();
            case 3:
                return entity.getFinancialSector();
            case 4:
                return entity.getCountryRisk();
            case 5:
                return entity.getCountry();
            case 6:
                return entity.isIntraGroup();
            case 7:
                return parsePortfolio(entity);
            case 8:
                return entity.getComment();
            case 9:
                return entity.getRatingDonor();
            case 10:
                return entity.getDateStart();
        }
        return null;
    }

    @Override
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    private String parsePortfolio(Counterparty counterparty) {
        String answer = "";
        List<Portfolio> list = counterparty.getPortfolioList();
        if (list.size() > 0) {
            Iterator iterator = list.iterator();
            Portfolio first = (Portfolio) iterator.next();
            answer = first.getName();
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    Portfolio next = (Portfolio) iterator.next();
                    answer += " / " + next.getName();
                }
            }
        }
//        for (int i = 0; i < counterparty.getPortfolioList().size(); i++) {
//            answer += counterparty.getPortfolioList().get(i).getName();
//            if (i < counterparty.getPortfolioList().size() - 1) {
//                answer += " | ";
//            }
//        }
        return answer;
    }

    @Override
    protected Class<Counterparty> getEntityClass() {
        return Counterparty.class;
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    protected String[] getColumnNames() {
        return columnNames;
    }

}
