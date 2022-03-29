package ui.gui;

import model.EventLog;
import ui.LogPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AppGui extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Book-keeping Application");

    JButton spending = new JButton("Add Spending");
    JButton income = new JButton("Add Income");
    JLabel title = new JLabel("Book-Keeping Application");
    JLabel imgLabel = new JLabel();

    JPanel upperPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel lowerPanel = new JPanel();

   // https://cdn3.vectorstock.com/i/1000x1000/43/87/door-hanging-sign-welcome-on-white-background-vector-31114387.jpg
    ImageIcon imageIcon = new ImageIcon(new ImageIcon("./data/img2.png").getImage()
            .getScaledInstance(650, 440, Image.SCALE_DEFAULT));

    private LogPrinter lp = new LogPrinter();

    // Construct the basic layout of the main window
    public AppGui() {
        // generate frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

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
        spending.setActionCommand("Add Spending");
        income.setActionCommand("Add Income");
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

    }

    // EFFECTS: if user pressed button "Add Spending", popup spending list window.
    // If user pressed "Add Income", popup income list window
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Spending")) {
            new SpendingListGui();
        } else if (e.getActionCommand().equals("Add Income")) {
            new IncomeListGui();
        }
    }

    // EFFECTS: start GUI
    public void startGUI() {
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    lp.printLog(EventLog.getInstance());
                }
            }
        });
    }


    public static void main(String[] args) {
        AppGui myGUI = new AppGui();
        myGUI.startGUI();
    }

}
