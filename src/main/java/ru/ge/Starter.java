package ru.ge;

import ru.ge.gui.MainGui;

import javax.swing.*;

public class Starter {

    public static void main(String[] args) {
        try {
            System.out.println("Make windows like UI");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGui frame = MainGui.getInstance();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
