package ui.gui;

import model.Spending;
import model.SpendingList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SpendingListGui extends JFrame {
    private final JFrame frame = new JFrame("Spending List");

    private final JList list;
    private final DefaultListModel listModel;

    JButton spending = new JButton("Add Spending");
    JButton remove;

    JLabel label;

    double amount;

    Spending spendingExample;

    SpendingList spendingList = new SpendingList();

    String askCategory;
    String askDate;


    public SpendingListGui() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.pack();
        frame.setVisible(true);


        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(spending);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        remove = new JButton("Remove");
        remove.setActionCommand("Remove");
        remove.addActionListener(new RemoveListener());
        buttonPane.add(remove);

//        calculateTotal = new JButton("Calculate Total Spending");
//        calculateTotal.setActionCommand("Calculate Total Spending");
//        calculateTotal.addActionListener(new CalculateListener());
//        buttonPane.add(calculateTotal);

        label = new JLabel(" ");
        label.setFont(new Font("Times New Roman",Font.BOLD,16));
        buttonPane.add(label, BorderLayout.SOUTH);

        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.PAGE_END);

        spending.addActionListener(evt -> {

            String askAmount = JOptionPane.showInputDialog(frame,
                    "How much did you spend？", null);
            askCategory = JOptionPane.showInputDialog(frame,
                    "Enter spending category", null);
            askDate = JOptionPane.showInputDialog(frame,
                    "Enter Date(yyyy-mm-dd)", null);

            amount = Double.parseDouble(askAmount);

            spendingExample = new Spending(amount, askCategory, askDate);

            spendingList.addSpending(amount, askCategory, askDate);

            listModel.addElement(spendingExample.toString());

            label.setText("Your total spending so far is" + " " + "$" + (spendingList.calculateTotal()));
        });
    }

    class RemoveListener implements ActionListener {
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

}
