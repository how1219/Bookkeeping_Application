package ui.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SpendingList extends JPanel
        implements ListSelectionListener {

    static  JList list;
    private DefaultListModel listModel;


    private static final String add = "Add Spending";
    private static final String remove = "Remove Spending";
    private JButton removeButton;

    public SpendingList() {

        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(20);

       // JButton hireButton = new JButton(add);
//        HireListener hireListener = new HireListener(hireButton);
//        hireButton.setActionCommand(hireString);
//        hireButton.addActionListener(hireListener);
//        hireButton.setEnabled(false);

        //removeButton = new JButton(remove);
//        fireButton.setActionCommand(fireString);
//        fireButton.addActionListener(new FireListener());

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
