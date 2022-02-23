package ui;

import model.IncomeList;
import model.SpendingList;

import java.util.Scanner;

public class UserInterface {
    private Scanner scn1;
    private SpendingList spendingList1;
    private IncomeList incomeList1;

    // Start the Bookkeeping application
    public UserInterface() {
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
        System.out.println("\t 1 -> ** add a spending **");
        System.out.println("\t 2 -> ** add an income **");
        System.out.println("\t 3 -> <show my spending list>");
        System.out.println("\t 4 -> <calculate total spending amount>");
        System.out.println("\t 5 -> <show my income list>");
        System.out.println("\t 6 -> <calculate total income amount>");
        System.out.println("\t 11 -> save spending list to file");
        System.out.println("\t 22 -> load spending list from file");
        System.out.println("\t 33 -> save income list to file");
        System.out.println("\t 44 -> load income list from file");
        System.out.println("\t 0 -> ** quit **");
    }

    // MODIFIES: this
    // EFFECTS: read user selected, and serve their need. If the given int is not 1, 2, 3, 4, 5, or 6, return "invalid".
    private void readSelected(int command) {
        if (command == 1) {
            addSpending();
        } else if (command == 2) {
            addIncome();
        } else if (command == 3) {
            System.out.println("Your spending list: ");
            System.out.println(spendingList1.getListOfSpending());
        } else if (command == 4) {
            System.out.println("Your total spending amount so far is $" + spendingList1.calculateTotal());
        } else if (command == 5) {
            System.out.println("Your income list: ");
            System.out.println(incomeList1.getListOfIncomes());
        }  else if (command == 6) {
            System.out.println("Your total income amount so far is $" + incomeList1.calculateTotal());
        } else if (command == 11) {
            saveSpendingList();
        } else  if (command == 22) {
            loadSpendingList();
        } else if (command == 33) {
            saveIncomeList();
        } else  if (command == 44) {
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

    private void saveSpendingList() {

    }

    private void loadSpendingList() {

    }

    private void saveIncomeList() {

    }

    private void loadIncomeList() {

    }



}
