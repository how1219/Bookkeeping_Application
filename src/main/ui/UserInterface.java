package ui;

import model.IncomeList;
import model.SpendingList;
import persistence.JsonReaderIncome;
import persistence.JsonReaderSpending;
import persistence.JsonWriterIncome;
import persistence.JsonWriterSpending;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Scanner scn1;
    private SpendingList spendingList1;
    private IncomeList incomeList1;
    private static final String JSON_STORE_SPENDING = "./data/spending.json";
    private static final String JSON_STORE_INCOME = "./data/income.json";
    private final JsonReaderSpending jsonReaderSpending;
    private final JsonWriterSpending jsonWriterSpending;
    private final JsonReaderIncome jsonReaderIncome;
    private final JsonWriterIncome jsonWriterIncome;

    // Start the Bookkeeping application
    public UserInterface() {
        jsonWriterSpending = new JsonWriterSpending(JSON_STORE_SPENDING);
        jsonReaderSpending = new JsonReaderSpending(JSON_STORE_SPENDING);
        jsonWriterIncome = new JsonWriterIncome(JSON_STORE_INCOME);
        jsonReaderIncome = new JsonReaderIncome(JSON_STORE_INCOME);
        enterMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: display the menu and read if the user input 0, and continue
    private void enterMainMenu() {
        boolean ifFlag = true;
        int command;

        spendingList1 = new SpendingList();
        incomeList1 = new IncomeList();
        scn1 = new Scanner(System.in);

        System.out.println("\n***** Bookkeeping Application *****");

        while (ifFlag) {
            displayMenu();
            command = scn1.nextInt();

            if (command == 0) {
                ifFlag = false;
            } else {
                readSelected(command);
            }
        }
    }

    // EFFECTS: display user options
    private void displayMenu() {
        System.out.println();
        System.out.println("\t***** Please select: *****");
        System.out.println("SPENDING");
        System.out.println("\t 1 -> ** add a spending **");
        System.out.println("\t 2 -> <show my spending list>");
        System.out.println("\t 3 -> <save spending list to file>");
        System.out.println("\t 4 -> <load spending list from file>");
        System.out.println("INCOME");
        System.out.println("\t 5 -> ** add an income **");
        System.out.println("\t 6 -> <show my income list>");
        System.out.println("\t 7 -> <save income list to file>");
        System.out.println("\t 8 -> <load income list from file>");
        System.out.println();
        System.out.println("\t 0 -> ** quit **");
    }

    // MODIFIES: this
    // EFFECTS: read user selected, and serve their need. If the given int is not 1, 2, 3, 4, 5, 6, 7 or 8
    // return "invalid".
    private void readSelected(int command) {
        if (command == 1) {
            addSpending();
        } else if (command == 2) {
            System.out.println("Your spending list: ");
            System.out.println(spendingList1.getListOfSpending());
        } else if (command == 3) {
            saveSpendingList();
        } else if (command == 4) {
            loadSpendingList();
        } else if (command == 5) {
            addIncome();
        }  else if (command == 6) {
            System.out.println("Your income list: ");
            System.out.println(incomeList1.getListOfIncomes());
        } else if (command == 7) {
            saveIncomeList();
        } else  if (command == 8) {
            loadIncomeList();
        } else {
            System.out.println("invalid");
        }

    }

    // REQUIRES: spending amount >= 0
    // MODIFIES: this
    // EFFECTS: add a spending to a spending list, then offer additional options for the user
    private void addSpending() {

        System.out.print("Enter amount spent: $");
        double amountEntered = scn1.nextDouble();

        scn1.nextLine();
        System.out.println("Enter category: ");
        String categoryEntered = scn1.nextLine();

        System.out.println("Enter date (yyyy-mm-dd): ");
        String dateEntered = scn1.nextLine();

        spendingList1.addSpending(amountEntered,categoryEntered,dateEntered);

        System.out.println("You've successfully added a new spending: amount $" + amountEntered);
        System.out.println("for " + categoryEntered);
        System.out.println("on " + dateEntered);

        System.out.println();
        System.out.println("additional services:");
        System.out.println("\t a -> calculate total spending amount");
        System.out.println("\t b -> remove a spending");
        System.out.println("\t s -> show my spending list");
        System.out.println("\t c -> back to main menu");
        spendingMore();
    }

    // MODIFIES: this
    // EFFECTS: return the spending list, total spending amount, or delete a spending from the list
    private void spendingMore() {
        String commandString;

        commandString = scn1.nextLine();

        switch (commandString) {
            case "a":
                System.out.println("Your total spending amount so far is $" + spendingList1.calculateTotal());

                break;
            case "b":
                System.out.println("Your spending list: ");
                System.out.println(spendingList1.getListOfSpending());
                System.out.println();
                System.out.println("Please enter which one you would like to delete");
                System.out.println("(ex. first spending -> 1; second spending -> 2)");
                int deleteThis = scn1.nextInt() - 1;
                System.out.println("Your new spending list is " + spendingList1.removeSpending(deleteThis));

                break;
            case "s":
                System.out.println("Your spending list: ");
                System.out.println(spendingList1.getListOfSpending());
                break;
        }
    }

    // REQUIRES: income amount >= 0
    // MODIFIES: this
    // EFFECTS: add an income to an income list, then offer additional options for the user
    private void addIncome() {
        System.out.println("Enter income amount: $");
        double amountEntered = scn1.nextDouble();

        scn1.nextLine();

        System.out.println("Enter date (yyyy-mm-dd): ");
        String dateEntered = scn1.nextLine();

        incomeList1.addIncome(amountEntered, dateEntered);

        System.out.println("You've successfully added a new income: amount $" + amountEntered);
        System.out.println("at " + dateEntered);

        System.out.println();
        System.out.println("additional services:");
        System.out.println("\t a -> calculate total income amount");
        System.out.println("\t b -> remove an income");
        System.out.println("\t s -> show my income list");
        System.out.println("\t c -> back to main menu");
        incomeMore();
    }

    // MODIFIES: this
    // EFFECTS: return the income list, total income amount, or delete an income from the list
    private void incomeMore() {
        String commandString;

        commandString = scn1.nextLine();

        switch (commandString) {
            case "a":
                System.out.println("Your total income so far is $" + incomeList1.calculateTotal());

                break;
            case "b":
                System.out.println("Your income list: ");
                System.out.println(incomeList1.getListOfIncomes());
                System.out.println();
                System.out.println("Please enter which one you would like to delete");
                System.out.println("(ex. first income -> 1; second income -> 2)");
                int deleteThis = scn1.nextInt() - 1;
                System.out.println("Your new income list is " + incomeList1.removeIncome(deleteThis));

                break;
            case "s":
                System.out.println("Your income list: ");
                System.out.println(incomeList1.getListOfIncomes());
                break;
        }
    }

    // EFFECTS: saves the spending list to file
    public void saveSpendingList() {
        try {
            jsonWriterSpending.open();
            jsonWriterSpending.write(spendingList1);
            jsonWriterSpending.close();
            System.out.println("Saved spending List to " + JSON_STORE_SPENDING);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SPENDING);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads spending list from file
    private void loadSpendingList() {

        try {
            spendingList1 = jsonReaderSpending.read();
            System.out.println("Loaded spending list from " + JSON_STORE_SPENDING);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SPENDING);
        }
    }

    // EFFECTS: saves the income list to file
    private void saveIncomeList() {
        try {
            jsonWriterIncome.open();
            jsonWriterIncome.write(incomeList1);
            jsonWriterIncome.close();
            System.out.println("Saved spending List to " + JSON_STORE_INCOME);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_INCOME);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads income list from file
    private void loadIncomeList() {

        try {
            incomeList1 = jsonReaderIncome.read();
            System.out.println("Loaded income list from " + JSON_STORE_INCOME);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_INCOME);
        }

    }



}
