package ru.ge.gui;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public abstract class TablePanel<T> extends JPanel {

    protected JScrollPane scrollPane;
    protected JPanel bottomPanel;
    protected JLabel titleLabel;
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JPopupMenu popup;
    protected JMenuItem newItem;
    protected JMenuItem editItem;
    protected JMenuItem removeItem;
    protected JButton createButton;
    protected JButton updateButton;
    protected JButton deleteButton;
    protected List<T> list;
    protected int count;

    public TablePanel() {
        getData();
        init();
        addListeners();
        setVisible(true);
    }

    protected abstract Class<T> getEntityClass();

    protected abstract String getTitle();

    protected abstract void create();

    protected abstract void update();

    protected abstract String[] getColumnNames();

    protected abstract Object getValueAtModel(T entity, int columnIndex);

    protected abstract Session getSession();

    private String getQuery() {
        String sql = "FROM " + getEntityClass().getSimpleName();
        return sql;
    }

    public void getData() {
        Transaction transaction = getSession().beginTransaction();
        try {
            System.out.println(getQuery());
            list = getSession().createQuery(getQuery()).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
//                    getSession.close();
        }
    }

    protected T getEntity() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        } else {
            int id = (int) table.getValueAt(selectedRow, 0);
            return getSession().get(getEntityClass(), id);
        }
    }

    protected void addListeners() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    System.out.println("no row selected");
                } else {
                    update();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    System.out.println("no row selected");
                } else {
                    delete();
                }
            }
        });
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("mouse double clicked");
                    update();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());

                table.getSelectionModel().setSelectionInterval(row, row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                create();
            }
        });
        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (getEntity() != null) {
                    update();
                }
            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (getEntity() != null) {
                    delete();
                }
            }
        });

    }

    protected int confirmDelete() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1)
            JOptionPane.showMessageDialog(null, "No row was selected");
        else {
            StringBuilder confirmMessage = new StringBuilder(
                    "You're trying to delete this entry: \r\n\n");
            for (int i = 0; i < table.getColumnCount(); i++) {
                confirmMessage.append(table.getColumnName(i)).append(": ").append(table.getValueAt(selectedRow, i)).append("\r\n");
            }
            confirmMessage
                    .append("\r\nAre you sure you want do delete this entry?\r\n");
            return JOptionPane.showConfirmDialog(null,
                    confirmMessage.toString(), "Warning",
                    JOptionPane.YES_NO_OPTION);
        }
        return JOptionPane.NO_OPTION;
    }

    protected void delete() {
        if (confirmDelete() == JOptionPane.YES_OPTION) {
            getSession().beginTransaction();
            getSession().delete(getEntity());
            getSession().getTransaction().commit();
            reload();
        }
//        if (getEntity() != null) {
//            getSession().beginTransaction();
//            getSession().delete(getEntity());
//            getSession().getTransaction().commit();
//            reload();
//        }
    }

    protected void init() {
        setLayout(new BorderLayout());
        titleLabel = new JLabel(getTitle());
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        table = new JTable();
        createTable();
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(table);
        add(scrollPane, BorderLayout.CENTER);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        createButton = new JButton("Add");
        updateButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        bottomPanel.add(createButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);

        popupMenu();
    }

    private void popupMenu() {
        popup = new JPopupMenu();
        newItem = new JMenuItem("Create");
        editItem = new JMenuItem("Edit");
        removeItem = new JMenuItem("Delete");
        popup.add(newItem);
        popup.add(editItem);
        popup.add(removeItem);
    }

    public void reload() {
        getData();
        tableModel.fireTableDataChanged();
    }

    protected void createTable() {
        tableModel = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int columnIndex) {
                for (T entity : list) {
                    if (getValueAtModel(entity, columnIndex) != null) {
                        return getValueAtModel(entity, columnIndex).getClass();
                    }
                }
                return String.class;
            }

            @Override
            public String getColumnName(int column) {
                return getColumnNames()[column];
            }

            @Override
            public int getRowCount() {
                return list.size();
            }

            @Override
            public int getColumnCount() {
                return getColumnNames().length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                T entity = list.get(rowIndex);
                return getValueAtModel(entity, columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }
        };
        prepareTable();
    }

    private void prepareTable() {
        table.setModel(tableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        TableRowSorter<TableModel> sorter =
                new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
    }

}
