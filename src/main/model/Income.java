package model;

import org.json.JSONObject;
import persistence.Writable;

// // Represents an income with amount and date.
public class Income implements Writable {
    private final double amount;
    private final String date;

    // Construct an income
    // EFFECTS: set the amount to incomeAmount; set the date to incomeDate
    public Income(double incomeAmount, String incomeDate) {
        // a date is in the form "yyyy-mm-dd"
        amount = incomeAmount;
        date = incomeDate;
    }

    // EFFECTS: return income amount
    public double getAmount() {
        return amount;
    }

    // EFFECTS: return income date
    public String getDate() {
        return date;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: print the income list in terms of string
    public String toString() {
        return "Income{"
                + "amount=" + amount + ", date='" + date + '\'' + '}';
    }

    @Override
    // MODIFIES: this
    // EFFECTS: make a Json Object with amount and date
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("date", date);
        return json;
    }
}
