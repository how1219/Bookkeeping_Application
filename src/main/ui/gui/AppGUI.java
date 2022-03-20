package ui.gui;

import ui.ListDemo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.javafx.fxml.expression.Expression.add;

public class AppGUI implements ActionListener, ListSelectionListener {
    private JFrame frame = new JFrame("Book-keeping Application");

    JDesktopPane desk;
    JInternalFrame middleLeftFrame;

    JButton save = new JButton("Save");
    JButton load = new JButton("Load");

    private JList list;
    private JList list2;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    private static final String addSpending = "Add Spending";
    private static final String removeSpending = "Remove Spending";
    private JButton removeSpendingButton;
    private JTextField category;

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

//        JButton addSpendingButton = new JButton(addSpending);
//        AddListener addListener = new AddListener(addSpendingButton);
//        addSpendingButton.setActionCommand(addSpending);
//        addSpendingButton.addActionListener(addListener);
//        addSpendingButton.setEnabled(false);
//
//        removeSpendingButton = new JButton(removeSpending);
//        removeSpendingButton.setActionCommand(removeSpending);
//        removeSpendingButton.addActionListener(new RemoveListener());
//
//        category = new JTextField(10);
//        category.addActionListener(addListener);
//        category.getDocument().addDocumentListener(addListener);
//        String categoryName = listModel.getElementAt(
//                list.getSelectedIndex()).toString();
//
//        //Create a panel that uses BoxLayout.
//        JPanel spendingButtonPane = new JPanel();
//        spendingButtonPane.setLayout(new BoxLayout(spendingButtonPane,
//                BoxLayout.LINE_AXIS));
//        spendingButtonPane.add(removeSpendingButton);
//        spendingButtonPane.add(Box.createHorizontalStrut(5));
//        spendingButtonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        spendingButtonPane.add(Box.createHorizontalStrut(5));
//        spendingButtonPane.add(category);
//        spendingButtonPane.add(addSpendingButton);
//        spendingButtonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

//        add(middleLeftPane, BorderLayout.CENTER);
//        add(spendingButtonPane, BorderLayout.PAGE_END);



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


        middlePanel.add(middleLeftPane);
        middlePanel.add(middleRightPane);
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));

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

//    class RemoveListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            //This method can be called only if
//            //there's a valid selection
//            //so go ahead and remove whatever's selected.
//            int index = list.getSelectedIndex();
//            listModel.remove(index);
//
//            int size = listModel.getSize();
//
//            if (size == 0) { //Nobody's left, disable firing.
//                removeSpendingButton.setEnabled(false);
//
//            } else { //Select an index.
//                if (index == listModel.getSize()) {
//                    //removed item in last position
//                    index--;
//                }
//
//                list.setSelectedIndex(index);
//                list.ensureIndexIsVisible(index);
//            }
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    class AddListener implements ActionListener, DocumentListener {
//        private boolean alreadyEnabled = false;
//        private JButton button;
//
//        public AddListener(JButton button) {
//            this.button = button;
//        }
//
//        //Required by ActionListener.
//        public void actionPerformed(ActionEvent e) {
//            String categoryName = category.getText();
//
//            //User didn't type in a unique name...
//            if (categoryName.equals("") || alreadyInList(categoryName)) {
//                Toolkit.getDefaultToolkit().beep();
//                category.requestFocusInWindow();
//                category.selectAll();
//                return;
//            }
//
//            int index = list.getSelectedIndex(); //get selected index
//            if (index == -1) { //no selection, so insert at beginning
//                index = 0;
//            } else {           //add after the selected item
//                index++;
//            }
//
//            listModel.insertElementAt(category.getText(), index);
//            //If we just wanted to add to the end, we'd do this:
//            //listModel.addElement(employeeName.getText());
//
//            //Reset the text field.
//            category.requestFocusInWindow();
//            category.setText("");
//
//            //Select the new item and make it visible.
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//
//        //This method tests for string equality. You could certainly
//        //get more sophisticated about the algorithm.  For example,
//        //you might want to ignore white space and capitalization.
//        protected boolean alreadyInList(String name) {
//            return listModel.contains(name);
//        }
//
//        //Required by DocumentListener.
//        public void insertUpdate(DocumentEvent e) {
//            enableButton();
//        }
//
//        //Required by DocumentListener.
//        public void removeUpdate(DocumentEvent e) {
//            handleEmptyTextField(e);
//        }
//
//        //Required by DocumentListener.
//        public void changedUpdate(DocumentEvent e) {
//            if (!handleEmptyTextField(e)) {
//                enableButton();
//            }
//        }
//
//        private void enableButton() {
//            if (!alreadyEnabled) {
//                button.setEnabled(true);
//            }
//        }
//
//        private boolean handleEmptyTextField(DocumentEvent e) {
//            if (e.getDocument().getLength() <= 0) {
//                button.setEnabled(false);
//                alreadyEnabled = false;
//                return true;
//            }
//            return false;
//        }
//    }

    //This method is required by ListSelectionListener.
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                removeSpendingButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                removeSpendingButton.setEnabled(true);
//            }
//        }
//    }

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
