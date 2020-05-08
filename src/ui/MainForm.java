package ui;

import busines.ContactBusines;
import busines.VitorException;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame{
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JPanel rootPanel;
    private JLabel lblQtdContacts;

    private ContactBusines mContactBusines;
    private String mName = "";
    private String mPhone = "";


    public MainForm() {
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusines = new ContactBusines();
        setListener();
        loadContacts();
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusines.getList();

        String[] columnNames = {"Nomes","Telefones"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for(ContactEntity i : contactList) {
            Object[] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);

        lblQtdContacts.setText(mContactBusines.getContactCount());
    }

    private void setListener() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    if(tableContacts.getSelectedRow() != -1) {
                        mName = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                    }
                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusines.delete(mName, mPhone);
                    loadContacts();
                    mName = "";
                    mPhone = "";
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });
    }
}
