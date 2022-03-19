package ui;

import javax.swing.*;

public class Test extends JFrame {


    public Test() {
        setTitle("Bookkeeping Application");
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        JButton spending = new JButton("Spending");
        JButton income = new JButton("Income");

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setTopComponent(spending);
        splitPane.setBottomComponent(income);

        splitPane.setDividerSize(10);
        splitPane.setDividerLocation(300);
        splitPane.setOneTouchExpandable(true);

        add(splitPane);

        validate();
    }

    public static void main(String[] args) {
        new Test();
    }
}
