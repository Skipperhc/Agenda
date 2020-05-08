package ui;

import busines.ContactBusines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JTextField textName;
    private JTextField textFone;
    private JButton buttonCancel;
    private JButton buttonSave;

    private ContactBusines mContactBusines;

    public ContactForm() {
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusines = new ContactBusines();
        setListeners();
    }

    private void setListeners() {
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textName.getText();
                    String fone = textFone.getText();

                    mContactBusines.save(name, fone);
                    new MainForm();
                    dispose();
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });
    }
}
