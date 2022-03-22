package ui.gui;

import model.Spending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class AppGuiNew extends JFrame implements ActionListener {
    private JFrame frame = new JFrame("Book-keeping Application");

    JButton spending = new JButton("Add Spending");
    JButton income = new JButton("Add Income");
    Spending spendingEx;



    public AppGuiNew() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        JPanel upperPanel = new JPanel();
        JLabel title = new JLabel("Book-Keeping Application");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.add(title);

        JPanel middlePanel = new JPanel();
        JLabel welcomeTitle = new JLabel("WELCOME");
        welcomeTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        middlePanel.add(welcomeTitle);
//
//        BufferedImage welcomeImg = ImageIO.read(new File("./data/welcome.png"));
//
//        JLabel welcomeLabel = new JLabel(new ImageIcon(welcomeImg));

//        Image resizedImg = welcomeImg.getScaledInstance(welcomeLabel.getWidth(), welcomeLabel.getHeight(),
//                Image.SCALE_SMOOTH);
// middlePanel.add(welcomeLabel);
//        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel lowerPanel = new JPanel();
        spending.addActionListener(this);
        income.addActionListener(this);

        lowerPanel.add(spending);
        lowerPanel.add(income);
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.black));


        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, upperPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, lowerPanel);
        frame.getContentPane().add(BorderLayout.CENTER, middlePanel);

        spending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SpendingListGui sl = new SpendingListGui();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void startGUI() {
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AppGuiNew myGUI = new AppGuiNew();
        myGUI.startGUI();
    }

}
