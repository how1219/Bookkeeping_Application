package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of incomes
public class IncomeList implements Writable {
    private List<Income> listOfIncomes;
    private double value = 0;

    // Construct an income list
    public IncomeList() {
        listOfIncomes = new ArrayList<>();
    }

    // REQUIRES: only one income is added each time
    // MODIFIES: this
    // EFFECTS: add an income to the income list
    public void addIncome(double amountIncome, String date) {
        Income income = new Income(amountIncome, date);
        listOfIncomes.add(income);
    }

    // REQUIRES: only one income is removed each time
    // MODIFIES: this
    // EFFECTS: remove an income from the income list
    public List<Income> removeIncome(int removeIndex) {
        listOfIncomes.remove(removeIndex);
        return listOfIncomes;
    }

    // MODIFIES: this
    // EFFECT: calculate the total income amount in the income list
    //         If the list is empty, return 0
    public double calculateTotal() {
        value = 0;
        if (listOfIncomes.size() != 0) {
            for (Income income: listOfIncomes) {
                value += income.getAmount();
            }
            return value;
        }
        return 0;
    }

    // EFFECTS: return the income list
    public List<Income> getListOfIncomes() {
        return listOfIncomes;
    }

    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("income", incomeToJson());
        return json;
    }

    // EFFECTS: returns incomes in this income list as a JSON array
    private JSONArray incomeToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Income i : listOfIncomes) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
