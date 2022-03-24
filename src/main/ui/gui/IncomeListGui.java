package ui.gui;

import model.Income;
import model.IncomeList;
import model.Spending;
import persistence.JsonReaderIncome;
import persistence.JsonWriterIncome;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IncomeListGui extends JFrame implements ActionListener {
    private final JFrame frame = new JFrame("Income List");

    private final JList<String> list;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    JButton income = new JButton("Add Income");
    JButton remove = new JButton("Remove");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JLabel label = new JLabel(" ");
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
        frame.setSize(700, 700);
        frame.setVisible(true);

        //Create the list and put it in a scroll pane.
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);

        //Create a panel that uses BoxLayout.
       // buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBackground(Color.lightGray);
        income.setActionCommand("Add Income");
        income.addActionListener(this);
        buttonPane.add(income);

        remove.setActionCommand("Remove");
        remove.addActionListener(this);
        buttonPane.add(remove);

        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        buttonPane.add(label, BorderLayout.SOUTH);

        // Create save and load button
        save.setActionCommand("Save");
        save.addActionListener(this);
        buttonPane.add(save);
        load.setActionCommand("Load");
        load.addActionListener(this);
        buttonPane.add(load);

        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: If the user pressed "Remove" button, remove selected income from the list.
    // If the user pressed "Add Income" button, the ask amount and data, and add the new income to the income list,
    // then update the label. Otherwise, run actionPerformedMore()
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Remove")) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            incomeList.removeIncome(index);

            label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));
        } else if (e.getActionCommand().equals("Add Income")) {
            String askAmount = JOptionPane.showInputDialog(frame,
                    "Enter your income amount ", null);
            askDate = JOptionPane.showInputDialog(frame,
                    "Enter Date(yyyy-mm-dd)", null);

            amount = Double.parseDouble(askAmount);

            incomeExample = new Income(amount, askDate);

            incomeList.addIncome(amount, askDate);

            listModel.addElement("Amount: $" + incomeExample.getAmount() + "  Date: " + incomeExample.getDate());

            label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));
        } else {
            actionPerformedMore(e);
        }
    }

    // EFFECTS: If the user pressed "Save" button, save the income list into file
    // If the user pressed "Load" button, load the income list from file.
    public void actionPerformedMore(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            try {
                jsonWriterIncome.open();
                jsonWriterIncome.write(incomeList);
                jsonWriterIncome.close();
                System.out.println("Saved income List to " + JSON_STORE_INCOME);
            } catch (FileNotFoundException ee) {
                System.out.println("Unable to write to file: " + JSON_STORE_INCOME);
            }
        } else if (e.getActionCommand().equals("Load")) {
            try {
                incomeList = jsonReaderIncome.read();
                for (int index = 0; index < incomeList.getListOfIncomes().size(); index++) {
                    Income income1 = incomeList.getListOfIncomes().get(index);
                    listModel.addElement("Amount: $" + income1.getAmount()
                            + "  Date: " + income1.getDate());
                }
                System.out.println("Loaded income list from " + JSON_STORE_INCOME);
            } catch (IOException ee) {
                System.out.println("Unable to read from file: " + JSON_STORE_INCOME);
            }
        }
    }
}
