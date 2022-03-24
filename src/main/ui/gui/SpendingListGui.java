package ui.gui;

import model.Spending;
import model.SpendingList;
import persistence.JsonReaderSpending;
import persistence.JsonWriterSpending;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpendingListGui extends JFrame {
    private final JFrame frame = new JFrame("Spending List");
    JButton spending = new JButton("Add Spending");
    JButton remove = new JButton("Remove");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel label = new JLabel(" ");
    JPanel buttonPane = new JPanel();

    private final JList<String> list;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    double amount;
    Spending spendingExample;
    String askCategory;
    String askDate;
    SpendingList spendingList = new SpendingList();

    private static final String JSON_STORE_SPENDING = "./data/spending.json";
    private final JsonReaderSpending jsonReaderSpending = new JsonReaderSpending(JSON_STORE_SPENDING);
    private final JsonWriterSpending jsonWriterSpending = new JsonWriterSpending(JSON_STORE_SPENDING);

    // Construct a window for spending list
    public SpendingListGui() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);


        //Create the list and put it in a scroll pane.
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        //Create a panel that uses BoxLayout.
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBackground(Color.lightGray);
        spending.addActionListener(new AddListener());
        buttonPane.add(spending);

        remove.setActionCommand("Remove");
        remove.addActionListener(new RemoveListener());
        buttonPane.add(remove);

        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        buttonPane.add(label, BorderLayout.SOUTH);

        // Create save and load button
        save.addActionListener(new SaveListener());
        buttonPane.add(save);
        load.addActionListener(new LoadListener());
        buttonPane.add(load);

        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.PAGE_END);
    }

    // Create a RemoveListener
    class RemoveListener implements ActionListener {
        // EFFECTS: remove selected spending, and update total spending amount
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            spendingList.removeSpending(index);

            label.setText("Your total spending so far is" + " " + "$" + (spendingList.calculateTotal()));

        }
    }

    // Create a AddListener
    class AddListener implements ActionListener {

        // EFFECTS: Ask spending amount, category and data and add them to the list
        @Override
        public void actionPerformed(ActionEvent e) {
            //ImageIcon amountImage = new ImageIcon("./data/img_1.png");
            String askAmount = JOptionPane.showInputDialog(frame,
                    "How much did you spend？", null);
            askCategory = JOptionPane.showInputDialog(frame,
                    "Enter spending category", null);
            askDate = JOptionPane.showInputDialog(frame,
                    "Enter Date(yyyy-mm-dd)", null);

            amount = Double.parseDouble(askAmount);

            spendingExample = new Spending(amount, askCategory, askDate);

            spendingList.addSpending(amount, askCategory, askDate);

            listModel.addElement("Amount: $" + spendingExample.getAmount() + "  Category: "
                    + spendingExample.getCategory() + "  Date: " + spendingExample.getDate());

            label.setText("Your total spending so far is" + " " + "$" + (spendingList.calculateTotal()));
        }
    }


    // Create a SaveListener
    class SaveListener implements ActionListener {

        // EFFECTS: Save the spending list to file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriterSpending.open();
                jsonWriterSpending.write(spendingList);
                jsonWriterSpending.close();
                System.out.println("Saved spending List to " + JSON_STORE_SPENDING);
            } catch (FileNotFoundException ee) {
                System.out.println("Unable to write to file: " + JSON_STORE_SPENDING);
            }

        }
    }


    // Create a LoadListener
    class LoadListener implements ActionListener {

        // EFFECTS: Load the spending list from file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                spendingList = jsonReaderSpending.read();
                System.out.println("Loaded spending list from " + JSON_STORE_SPENDING);
            } catch (IOException ee) {
                System.out.println("Unable to read from file: " + JSON_STORE_SPENDING);
            }

        }
    }
}
