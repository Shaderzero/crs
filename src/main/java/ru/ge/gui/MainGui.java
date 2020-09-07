package ru.ge.gui;

import org.hibernate.Session;
import ru.ge.data.DbConverterPostgre;
import ru.ge.data.HibernateUtil;
import ru.ge.data.HibernateUtil2;
import ru.ge.data.entities2.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class MainGui extends JFrame {

    private static MainGui instance;
    private JPanel toolbar;
    private JPanel leftPanel;
    private JPanel viewPanel;
    private JPanel bottomPanel;
    private JButton counterpartyButton;
    private JButton guaranteeButton;
    private JButton reportButton;
    private JButton ratingButton;
    private TablePanel outPanel;

    private MainGui() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        setTitle("Credit Risk Suite");
        setSize(900, 600);
        setJMenuBar(createMenuBar());

//        createLeftPanel();
        createToolbar();
//        createBottomPanel();
        createViewPanel();
        createMainLayout();
        createListeners();
//        setContentPane(rootPanel);
//        pack();

    }

    public static MainGui getInstance() {
        if (instance == null) {
            instance = new MainGui();
        }
        return instance;
    }

    private void onExit() {
        HibernateUtil.getSessionFactory().close();
        HibernateUtil2.getSessionFactory().close();
        dispose();
        System.gc();
        System.exit(0);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainGui.this, "меню выбрано", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainGui.this,
                        "Do you really want to exit the application?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    WindowListener[] listeners = getWindowListeners();

                    for (WindowListener listener : listeners) {
                        listener.windowClosing(new WindowEvent(MainGui.this, 0));
                    }
                }
            }
        });

        return menuBar;
    }

    private void createMainLayout() {
        add(toolbar, BorderLayout.PAGE_START);
//        add(leftPanel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.CENTER);
//        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, -1);
            }
        };
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private void createToolbar() {
        toolbar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(-1, 40);
            }
        };
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBorder(new EmptyBorder(5, 5, 5, 5));
        counterpartyButton = new JButton("Counterparties");
        guaranteeButton = new JButton("Guarantees");
        reportButton = new JButton("Reports");
        ratingButton = new JButton("Ratings");
        toolbar.add(counterpartyButton);
        toolbar.add(guaranteeButton);
        toolbar.add(reportButton);
        toolbar.add(ratingButton);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton button1 = new JButton("Один");
        JButton button2 = new JButton("Два");
        JButton button3 = new JButton("Три");
        bottomPanel.add(button1);
        bottomPanel.add(button2);
        bottomPanel.add(button3);
    }

    private void createViewPanel() {
        viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private void createListeners() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                onExit();
            }
        });

        counterpartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToView(CounterpartiesPanel.getInstance());
            }
        });

    }

    public void addToView(TablePanel panel) {
        this.outPanel = panel;
        System.out.println("дополнена viewPanel");
        viewPanel.add(outPanel);
        viewPanel.repaint();
        viewPanel.validate();
    }

    public void addToView(JPanel panel) {
//        this.outPanel = panel;
        System.out.println("дополнена viewPanel");
        viewPanel.add(panel);
        viewPanel.repaint();
        viewPanel.validate();
    }

    public void clearView() {
        viewPanel.removeAll();
        System.out.println("очищена viewPanel");
    }

    public void reloadView() {
        outPanel.reload();
    }

    private void convert() {
        Session session = HibernateUtil2.getSession();
        Session sessionCore = HibernateUtil.getSession();
        String sql = "FROM " +
                Currency2.class.getSimpleName();
        List<Currency2> currencyList = session.createQuery(sql, Currency2.class).getResultList();
        for (Currency2 x : currencyList) {
            DbConverterPostgre.convertCurrency(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + Country2.class.getSimpleName();
        List<Country2> countryList = session.createQuery(sql, Country2.class).getResultList();
        for (Country2 x : countryList) {
            DbConverterPostgre.convertCountry(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + Counterparty2.class.getSimpleName();
        List<Counterparty2> counterpartyList = session.createQuery(sql, Counterparty2.class).getResultList();
        for (Counterparty2 x : counterpartyList) {
            DbConverterPostgre.convertCounterparty(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + CommitteeLimit2.class.getSimpleName();
        List<CommitteeLimit2> committeeLimitList = session.createQuery(sql, CommitteeLimit2.class).getResultList();
        for (CommitteeLimit2 x : committeeLimitList) {
            DbConverterPostgre.convertCommitteeLimit(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + Committee2.class.getSimpleName();
        List<Committee2> committeeList = session.createQuery(sql, Committee2.class).getResultList();
        for (Committee2 x : committeeList) {
            DbConverterPostgre.convertCommittee(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyExt2.class.getSimpleName();
        List<RatingCounterpartyExt2> ratingExternalList = session.createQuery(sql, RatingCounterpartyExt2.class).getResultList();
        for (RatingCounterpartyExt2 x : ratingExternalList) {
            DbConverterPostgre.convertExternalRating(x);
        }
        sessionCore.close();

        sessionCore = HibernateUtil.getSession();
        sql = "FROM " + RatingCounterpartyInt2.class.getSimpleName();
        List<RatingCounterpartyInt2> ratingInternalList = session.createQuery(sql, RatingCounterpartyInt2.class).getResultList();
        for (RatingCounterpartyInt2 x : ratingInternalList) {
            DbConverterPostgre.convertInternalRating(x);
        }
        sessionCore.close();

        System.out.println("complete");
    }

}
