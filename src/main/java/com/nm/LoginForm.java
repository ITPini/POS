package com.nm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    JFrame Frame = new JFrame();
    private JPanel LoginPanel;
    private JTextField idField;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel idLabel;
    private JLabel passwordLabel;

    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;
    private static final String TITLE = "NM - Point of Sale";

    public LoginForm() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
    }

    public void run() {
        Frame.setContentPane(new LoginForm().LoginPanel);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(WIDTH, HEIGHT);
        Frame.setLocationRelativeTo(null);
        Frame.setTitle(TITLE);
        Frame.setResizable(false);
        Frame.setVisible(true);
    }

    public void exit() {
        Frame.setVisible(false);
    }

    private void checkLogin() {
        SQLbuilder SQLbuilder = new SQLbuilder();
        String idFieldString;
        String passwordString;
        String passText = new String(passwordField.getPassword());

        SQLbuilder.connect();
        idFieldString = SQLbuilder.executeQuery("SELECT ID FROM sql7313399.POSlogin WHERE ID='" + idField.getText() + "'");
        passwordString = SQLbuilder.executeQuery("SELECT Password FROM sql7313399.POSlogin WHERE Password='" + passText + "' AND ID='" + idField.getText() + "'");

        try {
            if (idFieldString.equals(idField.getText()) && passwordString.equals(passText)) {
                SQLbuilder.disconnect();
                exit();

                POSForm POSForm = new POSForm();
                POSForm.currentUserLabel.setText(idFieldString);
                POSForm.run();
                SQLbuilder.disconnect();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enten bruger-ID eller kode er forkert", "Forkert log-in", JOptionPane.INFORMATION_MESSAGE);
            SQLbuilder.disconnect();
        }
    }

}
