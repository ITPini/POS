package com.nm;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class POSForm {
    private JPanel POSPanel;
    private JTabbedPane menu;
    private JLabel timeLabel;
    private JList cartList;
    private JButton voksenButton;
    private JButton pensionistButton;
    private JButton barnButton;
    private JPanel taskBar;
    private JButton barn10Button;
    private JButton voksen10Button;
    private JButton pensionist10Button;
    private JTextField textField1;
    private JPanel cart;
    public JLabel currentUserLabel;

    private static final int WIDTH = 720;
    private static final int HEIGHT = 720;
    private static final String TITLE = "NM - Point of Sale";

    public POSForm() {
        clock();
    }

    public void clock() {
        Thread clock = new Thread(() -> {
            try {
                for (; ; ) {
                    Calendar today = new GregorianCalendar();
                    String weekday = today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
                    int day = today.get(Calendar.DAY_OF_MONTH);
                    int month = today.get(Calendar.MONTH) + 1;

                    int hour = today.get(Calendar.HOUR_OF_DAY);
                    int min = today.get(Calendar.MINUTE);
                    int second = today.get(Calendar.SECOND);

                    timeLabel.setText(hour + "." + min + "." + second + " | " + weekday + ". " + day + "/" + month + " ");

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clock.start();
    }

    public void run() {
        JFrame Frame = new JFrame();

        Frame.setContentPane(new POSForm().POSPanel);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(WIDTH, HEIGHT);
        Frame.setLocationRelativeTo(null);
        Frame.setTitle(TITLE);
        Frame.setResizable(false);
        Frame.setVisible(true);
    }

}
