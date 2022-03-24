package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGuiNew extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Book-keeping Application");

    JButton spending = new JButton("Add Spending");
    JButton income = new JButton("Add Income");
    JLabel title = new JLabel("Book-Keeping Application");
    JLabel imgLabel = new JLabel();

    JPanel upperPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel lowerPanel = new JPanel();

    ImageIcon imageIcon = new ImageIcon(new ImageIcon("./data/img2.png").getImage()
            .getScaledInstance(650, 440, Image.SCALE_DEFAULT));

    // Construct the basic layout of the main window
    public AppGuiNew() {
        // generate frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // create upper panel
        title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        title.setForeground(Color.black);
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.add(title);
        upperPanel.setBackground(Color.lightGray);

        // create upper panel
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        middlePanel.setBackground(Color.white);

        imgLabel.setIcon(imageIcon);
        middlePanel.add(imgLabel);

        // create lower panel
        spending.addActionListener(this);
        income.addActionListener(this);

        // lower panel add buttons
        lowerPanel.add(spending);
        lowerPanel.add(income);
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        lowerPanel.setBackground(Color.lightGray);

        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, upperPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, lowerPanel);
        frame.getContentPane().add(BorderLayout.CENTER, middlePanel);

        spending.addActionListener(new SpendingListener());
        income.addActionListener(new IncomeListener());
    }

    // Create a spending button listener
    static class SpendingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new SpendingListGui();
        }
    }

    // Create an income button listener
    static class IncomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new IncomeListGui();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // EFFECTS: start GUI
    public void startGUI() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AppGuiNew myGUI = new AppGuiNew();
        myGUI.startGUI();
    }

}
