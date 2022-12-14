package ui.gui;

import model.EventLog;
import model.Income;
import model.IncomeList;
import persistence.JsonReaderIncome;
import persistence.JsonWriterIncome;
import ui.LogPrinter;

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

    //https://www.swhealth.org/wp-content/uploads/2018/01/Calendar-Icon.jpg
    ImageIcon dateImage = new ImageIcon(new ImageIcon("./data/date.png").getImage()
            .getScaledInstance(100, 100, Image.SCALE_DEFAULT));

    private final LogPrinter lp = new LogPrinter();


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

        setLabel();

        // Create save and load button
        save.setActionCommand("Save");
        save.addActionListener(this);
        buttonPane.add(save);
        load.setActionCommand("Load");
        load.addActionListener(this);
        buttonPane.add(load);

        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.PAGE_END);

        closing();
    }

    // EFFECTS: confirm the user want to close the window. Is yes, print all log events.
    public void closing() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    lp.printLog(EventLog.getInstance());
                    System.exit(0);
                }
            }
        });
    }

    // EFFECTS: set label format and size
    public void setLabel() {
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        buttonPane.add(label, BorderLayout.SOUTH);
    }

    // EFFECTS: If the user pressed "Remove" button, remove selected income from the list.
    // If the user pressed "Add Income" button, run actionPerformeDate(). Otherwise, run actionPerformedMore()
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Remove")) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            incomeList.removeIncome(index);

            label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));
        } else if (e.getActionCommand().equals("Add Income")) {
            actionPerformedDate();
        } else {
            actionPerformedMore(e);
        }
    }

    // EFFECTS: the ask amount and date, and add the new income to the income list, then update the label.
    public void actionPerformedDate() {
        String askAmount = JOptionPane.showInputDialog(frame,
                "Enter your income amount ", null);
        String[] optionsYear = {"2022", "2021", "2020","2019"};
        String year = (String)JOptionPane.showInputDialog(frame,
                "Year", "Date",
                JOptionPane.QUESTION_MESSAGE, dateImage, optionsYear, optionsYear[0]);
        String[] optionsMonth = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        String month = (String)JOptionPane.showInputDialog(frame,
                "Month", "Date",
                JOptionPane.QUESTION_MESSAGE, dateImage, optionsMonth, optionsMonth[0]);
        String[] optionsDay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String day = (String)JOptionPane.showInputDialog(frame,
                "day", "Date",
                JOptionPane.QUESTION_MESSAGE, dateImage, optionsDay, optionsDay[0]);

        askDate = year + "-" + month + "-" + day;

        amount = Double.parseDouble(askAmount);

        incomeExample = new Income(amount, askDate);

        incomeList.addIncome(amount, askDate);

        listModel.addElement("Amount: $" + incomeExample.getAmount() + "  Date: " + incomeExample.getDate());

        label.setText("Your total income so far is" + " " + "$" + (incomeList.calculateTotal()));
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
