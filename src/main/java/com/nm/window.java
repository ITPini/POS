package com.nm;

import javax.swing.*;
import java.awt.*;

public class window extends Canvas {

    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;
    private static final String TITLE = "NM - Point of Sale";
    private boolean running = false;

    private JPanel startMenu;
    private JTextField idField;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel idLabel;
    private JLabel passwordLabel;

    private window() {
        SQL sql = new SQL();
        sql.connect("SELECT VERSION()");
        sql.disconnect();
    }

    public static void main(String[] args) {
        window Window = new window();
        JFrame Frame = new JFrame();

        //Font font = new Font("Arial", Font.PLAIN, 20);
        //Window.setFont(font);

        Frame.add(Window);
        Frame.setContentPane(new window().startMenu);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(WIDTH, HEIGHT);
        Frame.setLocationRelativeTo(null);
        Frame.setTitle(TITLE);
        Frame.setResizable(false);
        Frame.setVisible(true);
    }

    private void start() {
        if (running) return;
        running = true;
    }

    private void stop() {
        if (!running) return;
        running = false;
        System.exit(0);
    }

    private void checkLogin() {
        // TODO: check if id and password exist in SQL-database

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
