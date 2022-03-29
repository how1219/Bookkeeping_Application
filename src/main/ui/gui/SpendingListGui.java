package ui.gui;

import model.EventLog;
import model.Spending;
import model.SpendingList;
import persistence.JsonReaderSpending;
import persistence.JsonWriterSpending;
import ui.LogPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SpendingListGui extends JFrame implements ActionListener {
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

    // https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1500w,f_auto,q_auto:best/newscms/2019_06
    // /2746941/190208-stock-money-fanned-out-ew-317p.jpg
    ImageIcon categoryImage = new ImageIcon(new ImageIcon("./data/img_2.png").getImage()
            .getScaledInstance(300, 230, Image.SCALE_DEFAULT));
    //https://www.swhealth.org/wp-content/uploads/2018/01/Calendar-Icon.jpg
    ImageIcon dateImage = new ImageIcon(new ImageIcon("./data/date.png").getImage()
            .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    String[] options = {"Food and Groceries","Clothing", "Transportation", "Housing", "Debt Payoff", "Other"};
    String[] optionsYear = {"2022", "2021", "2020","2019"};
    String[] optionsDay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private final LogPrinter lp = new LogPrinter();

    // Construct a window for spending list
    public SpendingListGui() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);


        //Create the list and put it in a scroll pane.
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(list);

        //Create a panel that uses BoxLayout.
        buttonPane.setBackground(Color.lightGray);
        spending.setActionCommand("Add Spending");
        spending.addActionListener(this);
        buttonPane.add(spending);

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

    // EFFECTS: If the user pressed "Remove" button, remove selected spending from the list.
    // If the user pressed "Add Spending" button, run actionPerformedDate().  Otherwise, run actionPerformedMore()
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Remove")) {
            int index = list.getSelectedIndex();
            listModel.remove(index);

            spendingList.removeSpending(index);

            label.setText("Your total spending so far is" + " " + "$" + (spendingList.calculateTotal()));
        } else if (e.getActionCommand().equals("Add Spending")) {
            actionPerformedDate();
        } else {
            actionPerformedMore(e);
        }
    }

    // EFFECTS: the ask amount, category and date, and add the new spending to the spending list, then update the label.
    public void actionPerformedDate() {
        String askAmount = JOptionPane.showInputDialog(frame,
                "How much did you spend？", "Amount", JOptionPane.QUESTION_MESSAGE);

        askCategory = (String)JOptionPane.showInputDialog(frame,
                "Choose spending category", "Category",
                JOptionPane.QUESTION_MESSAGE, categoryImage, options, options[0]);

        String year = (String)JOptionPane.showInputDialog(frame,
                "Year", "Date", JOptionPane.QUESTION_MESSAGE, dateImage, optionsYear, optionsYear[0]);
        String[] optionsMonth = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        String month = (String)JOptionPane.showInputDialog(frame,
                "Month", "Date", JOptionPane.QUESTION_MESSAGE, dateImage, optionsMonth, optionsMonth[0]);
        String day = (String)JOptionPane.showInputDialog(frame,
                "day", "Date", JOptionPane.QUESTION_MESSAGE, dateImage, optionsDay, optionsDay[0]);

        askDate = year + "-" + month + "-" + day;
        amount = Double.parseDouble(askAmount);

        spendingExample = new Spending(amount, askCategory, askDate);

        spendingList.addSpending(amount, askCategory, askDate);

        listModel.addElement("Amount: $" + spendingExample.getAmount() + "  Category: "
                + spendingExample.getCategory() + "  Date: " + spendingExample.getDate());

        label.setText("Your total spending so far is" + " " + "$" + (spendingList.calculateTotal()));

    }

    // EFFECTS: If the user pressed "Save" button, save the spending list into file
    // If the user pressed "Load" button, load the spending list from file.
    public void actionPerformedMore(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            try {
                jsonWriterSpending.open();
                jsonWriterSpending.write(spendingList);
                jsonWriterSpending.close();
                System.out.println("Saved spending List to " + JSON_STORE_SPENDING);
            } catch (FileNotFoundException ee) {
                System.out.println("Unable to write to file: " + JSON_STORE_SPENDING);
            }
        } else if (e.getActionCommand().equals("Load")) {
            try {
                spendingList = jsonReaderSpending.read();

                for (int index = 0; index < spendingList.getListOfSpending().size(); index++) {
                    Spending spending1 = spendingList.getListOfSpending().get(index);
                    listModel.addElement("Amount: $" + spending1.getAmount() + "  Category: "
                            + spending1.getCategory() + "  Date: " + spending1.getDate());
                }
                System.out.println("Loaded spending list from " + JSON_STORE_SPENDING);
            } catch (IOException ee) {
                System.out.println("Unable to read from file: " + JSON_STORE_SPENDING);
            }
        }
    }
}

