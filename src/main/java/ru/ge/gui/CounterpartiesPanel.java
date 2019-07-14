package ru.ge.gui;

import javax.swing.*;
import java.awt.*;

public class CounterpartiesPanel extends JPanel {

    private static CounterpartiesPanel instance;
    protected JTabbedPane tabbedPane;

    private CounterpartiesPanel() {
        init();
    }

    public static CounterpartiesPanel getInstance() {
        if (instance == null) {
            instance = new CounterpartiesPanel();
        }
        return instance;
    }

    private void init() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.add("list", new CounterpartyTable());
    }

}
