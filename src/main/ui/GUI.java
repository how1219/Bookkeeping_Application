package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private int count = 0;
    private JFrame frame;
    //private JPanel panel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel label;

    public GUI() {
        frame = new JFrame();
        leftPanel = new JPanel();
        rightPanel = new JPanel();

        JButton buttonAddSpending = new JButton("Add Spending");
        JButton buttonAddIncome = new JButton("Add Income");

        buttonAddIncome.addActionListener(this);
        buttonAddSpending.addActionListener(this);

        label = new JLabel("Number of clicks: 0");

        //panel.setBorder(BorderFactory.createEmptyBorder(300,300,150,300));

        // create a panel separator
        JSplitPane sep = new JSplitPane(SwingConstants.VERTICAL, leftPanel, rightPanel);

        //panel.setLayout(new GridLayout());

//        panel = new JPanel(new BorderLayout());
//        panel.add(leftPanel, BorderLayout.WEST);
//        panel.add(rightPanel, BorderLayout.EAST);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(leftPanel);
        panel.add(rightPanel);

//        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//                leftPanel, rightPanel);
//        leftPanel.add(buttonAddSpending);
//        rightPanel.add(buttonAddIncome);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bookkeeping Application");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks" + count);

    }
}
