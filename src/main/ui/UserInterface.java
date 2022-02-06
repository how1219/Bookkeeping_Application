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
        int command = 0;

        spendingList1 = new SpendingList();
        incomeList1 = new IncomeList();
        scn1 = new Scanner(System.in);

        while (ifFlag) {
            System.out.println("\n***** Bookkeeping Application *****");
            System.out.println("\t***** Please select: *****");
            System.out.println("\t 1 -> <add a spending>");
            System.out.println("\t 2 -> <add an income >");
            System.out.println("\t 3 -> <show my spending list>");
            System.out.println("\t 4 -> <calculate total spending amount>");
            System.out.println("\t 0 -> <quit>");
            command = scn1.nextInt();

            if (command == 0) {
                System.out.println("Are you sure you want to quit? Yes -> enter 1; No -> enter 0");
                double quit = scn1.nextDouble();
                if (command == 1) {
                    break;
                }
            } else {
                readSelected(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: read user selected
    private void readSelected(int command) {
        if (command == 1) {
            addSpending();
        } else if (command == 2) {
            addIncome();
        } else if (command == 3) {
            System.out.println("Your spending list: ");
            System.out.println(spendingList1.getListOfSpending());
        } else {
            System.out.println("invalid");
        }

    }

    // REQUIRES: spending amount >= 0
    // MODIFIES: this
    // EFFECTS: add a spending to a spending list
    private void addSpending() {
        //String commandString = null;

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

        System.out.println("additional services:");
        System.out.println("\t a -> calculate total spending");
        System.out.println("\t b -> remove a spending");
        System.out.println("\t s -> show my spending list");
        System.out.println("\t c -> back to main menu");
        spendingMore();
    }

    // EFFECTS: return the spending list, total spending amount, and delete a spending from the list
    private void spendingMore() {
        String commandString = null;

        commandString = scn1.nextLine();

        if (commandString.equals("a")) {
            System.out.println("Your total spending so far is $" + spendingList1.calculateTotal());

        } else if (commandString.equals("b")) {
            System.out.println("Your spending list: ");
            System.out.println(spendingList1.getListOfSpending());
            System.out.println("Please enter which one you would like to delete");
            System.out.println("(ex. first spending -> 1; second spending -> 2)");
            int deleteThis = scn1.nextInt() - 1;
            System.out.println("Your new spending list is " + spendingList1.removeSpending(deleteThis));

        } else if (commandString.equals("s")) {
            System.out.println("Your spending list: ");
            System.out.println(spendingList1.getListOfSpending());
        }
    }

    // REQUIRES: income amount >= 0
    // MODIFIES: this
    // EFFECTS: add an income to an income list
    private void addIncome() {
        incomeList1 = new IncomeList();
        System.out.println("Enter amount of the income: $");
        double amountEntered = scn1.nextDouble();

        scn1.nextLine();

        System.out.println("Enter date (year-month-date xxxx-xx-xx): ");
        String dateEntered = scn1.nextLine();

        incomeList1.addIncome(amountEntered, dateEntered);

        System.out.println("You successfully added a new income: amount $" + amountEntered);
        System.out.println("at " + dateEntered);
    }


}
