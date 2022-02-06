package ui;

import model.Income;
import model.Spending;
import model.SpendingList;

import java.util.Scanner;

public class UserInterface {
    private Scanner scn1;
    private Spending spending1;
    private Income income1;
    private SpendingList spendingList1;

    // Start the Bookkeeping application
    public UserInterface() {
        enterMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: read the user input and continue
    private void enterMainMenu() {
        boolean ifFlag = true;
        int command = 0;
        spendingList1 = new SpendingList();

        while (ifFlag) {
            System.out.println("\n*****Bookkeeping Application*****");
            System.out.println("\t*****Please select:**************");
            System.out.println("\t*****1 add a spending************");
            System.out.println("\t*****2 add an income*************");
            System.out.println("\t*****0 quit**********************");


            if (command == 0) {
                System.out.println("Are you sure you want to quit? Yes -> enter 1; No -> enter 0");
                if (command == 1) {
                    ifFlag = false;
                }
                break;
            } else {
                readSelected(command);
            }
        }
    }

    private void readSelected(int command) {
        if (command == 1) {
            addSpending();
        } else if (command == 2) {
            addIncome();
        } else {
            System.out.println("invalid");
        }

    }

    // REQUIRES: spending amount >= 0
    // MODIFIES: this
    // EFFECTS: add a spending to a spending list
    private void addSpending() {
        spendingList1 = new SpendingList();
        System.out.print("Enter amount spent: $");
    }
}
