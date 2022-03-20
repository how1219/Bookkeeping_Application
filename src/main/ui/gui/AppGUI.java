package ui.gui;

import model.Spending;
import model.SpendingList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppGUI implements ActionListener, ListSelectionListener {
    private JFrame frame = new JFrame("Book-keeping Application");
    private int clickCount = 0;
    //private JLabel clickLabel = new JLabel("Number of clicks: 0");
//    JButton increase = new JButton("Increase Count");
//    JButton decrease = new JButton("Decrease Count");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    private JTextField spending;

    private JList list;
    private JList list2;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    JButton addSpending = new JButton("Add Spending");
    JButton removeSpending = new JButton("Remove Spending");

    public AppGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Creating upper portion of UI
        JPanel upperPanel = new JPanel();
        JLabel title = new JLabel("Book-Keeping Application");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.add(title);

        //Create the Spending list and put it in a scroll pane.
        listModel = new DefaultListModel();
        listModel.addElement("Spending");

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane middleLeftPane = new JScrollPane(list);

//        AddListener addListener = new AddListener(addSpendingButton);
//        addSpendingButton.setActionCommand(addSpending);
//        addSpendingButton.addActionListener(addListener);
//        addSpendingButton.setEnabled(false);

//        fireButton = new JButton(fireString);
//        fireButton.setActionCommand(fireString);
//        fireButton.addActionListener(new ListDemo.FireListener());

//        spending = new JTextField(10);
//        spending.addActionListener(addListener);
//        spending.getDocument().addDocumentListener(addListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();


        //Create the Income list and put it in a scroll pane.
        listModel2 = new DefaultListModel();
        listModel2.addElement("Income");

        list2 = new JList(listModel2);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setSelectedIndex(0);
        list2.addListSelectionListener(this);
        list2.setVisibleRowCount(5);
        JScrollPane middleRightPane = new JScrollPane(list2);


        // Creating middle portion of UI
        JPanel middlePanel = new JPanel();
        middleLeftPane.setBorder(BorderFactory.createLineBorder(Color.black));
        middleRightPane.setBorder(BorderFactory.createLineBorder(Color.black));

        //middleLeftPane.add(clickLabel);
        middleLeftPane.add((new Label("Spending")));
//        increase.addActionListener(this);
//        middleLeftPane.add(increase);
//        decrease.addActionListener(this);
//        middleLeftPane.add(decrease);

        middleRightPane.add(new Label("Income"));

        middlePanel.add(middleLeftPane);
        middlePanel.add(middleRightPane);
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middleLeftPane.add(addSpending);

        // Creating lower portion of UI
        JPanel lowerPanel = new JPanel();
        save.addActionListener(this);
        load.addActionListener(this);

        lowerPanel.add(save);
        lowerPanel.add(load);
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Adding components to the frame
        frame.getContentPane().add(BorderLayout.NORTH, upperPanel);
        frame.getContentPane().add(BorderLayout.CENTER, middlePanel);
        frame.getContentPane().add(BorderLayout.SOUTH, lowerPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
//
        }


        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }


       // @Override
   // public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == increase) {
//            clickCount++;
//            clickLabel.setText("Number of clicks: " + clickCount);
//        } else if (e.getSource() == decrease) {
//            clickCount--;
//            clickLabel.setText("Number of clicks: " + clickCount);
//        } else if (e.getSource() == save) {
//            System.out.println("Saving");
//            // mySpendList.addSpending(1.22, "Hello", "Today");
//        } else if (e.getSource() == load) {
//            System.out.println("Loading");
//        }
    }

    public void startGUI() {
        frame.setVisible(true);
    }

    public void endGUI() {
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        AppGUI myGUI = new AppGUI();
        myGUI.startGUI();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
