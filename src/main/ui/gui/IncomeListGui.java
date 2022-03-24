package ui.gui;

import model.Income;
import model.IncomeList;
import persistence.JsonReaderIncome;
import persistence.JsonWriterIncome;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IncomeListGui extends JFrame {
    private final JFrame frame = new JFrame("Income List");

    private final JList<String> list;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    JButton income = new JButton("Add Income");
    JButton remove = new JButton("Remove");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel label  = new JLabel(" ");
    JPanel buttonPane = new JPanel();

    double amount;
    Income incomeExample;
    String askDate;
    IncomeList incomeList = new IncomeList();


    private static final String JSON_STORE_INCOME = "./data/income.json";
    private final JsonReaderIncome jsonReaderIncome = new JsonReaderIncome(JSON_STORE_INCOME);
    private final JsonWriterIncome jsonWriterIncome = new JsonWriterIncome(JSON_STORE_INCOME);


    // Construct a window for income list
    public IncomeListGui() {
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
        income.addActionListener(new AddListener());
        buttonPane.add(income);

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

        // EFFECTS: remove selected income, and update total income amount
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            incomeList.removeIncome(index);

            label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));

        }
    }

    // Create a AddListener
    class AddListener implements ActionListener {

        // EFFECTS: Ask income amount and data and add them to the list
        @Override
        public void actionPerformed(ActionEvent e) {
            String askAmount = JOptionPane.showInputDialog(frame,
                    "Enter your income amount ", null);
            askDate = JOptionPane.showInputDialog(frame,
                    "Enter Date(yyyy-mm-dd)", null);

            amount = Double.parseDouble(askAmount);

            incomeExample = new Income(amount, askDate);

            incomeList.addIncome(amount, askDate);

            listModel.addElement("Amount: $" + incomeExample.getAmount() + "  Date: " + incomeExample.getDate());

            label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));
        }
    }

    // Create a SaveListener
    class SaveListener implements ActionListener {

        // EFFECTS: Save the income list to file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriterIncome.open();
                jsonWriterIncome.write(incomeList);
                jsonWriterIncome.close();
                System.out.println("Saved income List to " + JSON_STORE_INCOME);
            } catch (FileNotFoundException ee) {
                System.out.println("Unable to write to file: " + JSON_STORE_INCOME);
            }

        }
    }

    // Create a LoadListener
    class LoadListener implements ActionListener {

        // EFFECTS: Load the income list from file
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                incomeList = jsonReaderIncome.read();
                System.out.println("Loaded income list from " + JSON_STORE_INCOME);
            } catch (IOException ee) {
                System.out.println("Unable to read from file: " + JSON_STORE_INCOME);
            }


        }
    }
}
