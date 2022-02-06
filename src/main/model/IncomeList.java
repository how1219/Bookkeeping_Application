package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of incomes
public class IncomeList {
    private List<Income> listOfIncomes;
    private double value = 0;

    // Construct an income list
    public IncomeList() {
        listOfIncomes = new ArrayList<>();
    }

    // REQUIRES: only one income is added each time
    // MODIFIES: this
    // EFFECTS: add income to income list
    public void addIncome(double amountIncome, String date) {
        Income income = new Income(amountIncome, date);
        listOfIncomes.add(income);
    }

    // REQUIRES: only one income is removed each time
    // MODIFIES: this
    // EFFECTS: remove income from income list
    public List<Income> removeIncome(int removeIndex) {
        listOfIncomes.remove(removeIndex);
        return listOfIncomes;
    }

    // MODIFIES: this
    // EFFECT: calculate the total income amount in the income list
    //         If the list is empty, return 0
    public double calculateTotal() {
        if (listOfIncomes.size() != 0) {
            for (Income income: listOfIncomes) {
                value += income.getAmount();
            }
            return value;
        }
        return 0;
    }

    public List<Income> getListOfIncomes() {
        return listOfIncomes;
    }
}
