package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a list of spending
public class SpendingList implements Writable {

    private final List<Spending> listOfSpending;

    // Construct a spending list
    public SpendingList() {
        listOfSpending = new ArrayList<>();
    }

    // REQUIRES: only one spending is added each time
    // MODIFIES: this
    // EFFECTS: add a spending to the spending list
    public void addSpending(double amountSpent, String category, String date) {
        Spending spending = new Spending(amountSpent, category, date);
        listOfSpending.add(spending);
    }

    // REQUIRES: only one spending is removed each time
    // MODIFIES: this
    // EFFECTS: remove a spending from the spending list
    public List<Spending> removeSpending(int removeIndex) {
        listOfSpending.remove(removeIndex);
        return listOfSpending;
    }


    // MODIFIES: this
    // EFFECT: calculate the total spending amount in the spending list
    //         If the list is empty, return 0
    public double calculateTotal() {
        if (listOfSpending.size() != 0) {
            double value = 0;
            for (Spending spending: listOfSpending) {
                value += spending.getAmount();
            }
            return value;
        }
        return 0;
    }

    // EFFECTS: return the spending list
    public List<Spending> getListOfSpending() {
        return listOfSpending;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("spending", spendingToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray spendingToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Spending s : listOfSpending) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
