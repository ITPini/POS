package com.nm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class POSForm {
    private static final int WIDTH = 720;
    private static final int HEIGHT = 720;
    private static final String TITLE = "NM - Point of Sale";
    private final JFrame Frame = new JFrame();
    private JPanel POSPanel;
    private JTabbedPane header;
    private JList<String> cartList;
    private JButton voksenButton;
    private JButton pensionistButton;
    private JButton barnButton;
    private JPanel taskBar;
    private JButton barn10Button;
    private JButton voksen10Button;
    private JButton pensionist10Button;
    private JTextField textField1;
    private JPanel footer;
    private JButton currentUserButton;
    private JLabel totalLabel;
    private JLabel amountLabel;
    private JScrollPane cart;
    private JLabel dateLabel;
    private DefaultListModel<String> list1 = new DefaultListModel<>();
    private float currentPrice;
    private int amount = 1;

    public POSForm() {
        currentUserButton.setText(main.currentUser);
        clock();
        cart.setPreferredSize(new Dimension(150, 150));

        // Indgang > Barn
        barnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.addElement("Indgang - barn");
                totalLabel.setText((currentPrice = 50 + currentPrice) + " kr");
                amountLabel.setText((amount++) + " ting i kurven");
                cartList.setModel(list1);
            }
        });

        // Log-out
        currentUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Vil du logge ud som " + main.currentUser + "?", "Log ud?", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    main.loginForm.start();
                    main.POSForm.exit();
                }
            }
        });
    }

    // TODO: Method for fetching price from database
    // TODO: Builder to easily add items in the cart (name + price)
    // TODO: Method to for 'price and amount' update
    // TODO: Method for deleting items in cart
    // TODO: Method for a tender menu

    private void clock() {
        Thread clock = new Thread(() -> {
            try {
                for (; ; ) {
                    Calendar today = new GregorianCalendar();
                    String weekday = today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, new java.util.Locale("da", "DK"));
                    int day = today.get(Calendar.DAY_OF_MONTH);
                    String month = today.getDisplayName(Calendar.MONTH, Calendar.SHORT, new java.util.Locale("da", "DK"));
                    int hour = today.get(Calendar.HOUR_OF_DAY);
                    int min = today.get(Calendar.MINUTE);
                    int second = today.get(Calendar.SECOND);

                    String dateFormat = weekday + " " + day + ". " + month + " " + hour + "." + min + "." + second;
                    dateLabel.setText(dateFormat);
                    //timeLabel.setText(hour + "." + min + "." + second + " | " + weekday + " " + day + "/" + month + " ");

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clock.start();
    }

    public void start() {
        Frame.setContentPane(new POSForm().POSPanel);
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
}
