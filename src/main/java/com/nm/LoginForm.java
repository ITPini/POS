package com.nm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginForm {
    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;
    private static final String TITLE = "NM - Point of Sale";
    private final JFrame Frame = new JFrame();
    private JPanel LoginPanel;
    private JTextField idField;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JLabel credits;

    public LoginForm() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
    }

    public void start() {
        Frame.setContentPane(new LoginForm().LoginPanel);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(WIDTH, HEIGHT);
        Frame.setLocationRelativeTo(null);
        Frame.setTitle(TITLE);
        Frame.setResizable(false);
        Frame.setVisible(true);
    }

    private void exit() {
        Frame.dispose();
    }

    private void checkLogin() {
        SQLbuilder SQLbuilder = new SQLbuilder();
        SQLbuilder.connect();
        String passText = new String(passwordField.getPassword());
        String idFieldString = SQLbuilder.executeQuery("SELECT ID FROM sql7313399.POSlogin WHERE ID='" + idField.getText() + "'");
        String passwordString = SQLbuilder.executeQuery("SELECT Password FROM sql7313399.POSlogin WHERE Password='" + passText + "' AND ID='" + idField.getText() + "'");

        if (SQLbuilder.connectivity != null) {
            try {
                if (idFieldString.equals(idField.getText()) && passwordString.equals(passText)) {
                    main.currentUser = idFieldString;
                    main.POSForm.start();
                    main.loginForm.exit();
                }
            } catch (Exception e) {
                main.currentUser = null;
                JOptionPane.showMessageDialog(null, "Enten bruger-ID eller kode er forkert", "Forkert log-in", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                SQLbuilder.disconnect();
            }
        }
    }
}
