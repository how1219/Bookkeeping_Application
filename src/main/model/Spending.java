package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a spending with amount, category, and date.
public class Spending implements Writable {
    private final double amount;
    private final String category;
    private final String date;

    // Construct a spending
    // EFFECTS: set the amount to spendingAmount; set the category to spendingCategory;
    //          set the date to spendingDate
    public Spending(double spendingAmount, String spendingCategory, String spendingDate) {
        // a date is in the form "yyyy-mm-dd".
        amount = spendingAmount;
        category = spendingCategory;
        date = spendingDate;
    }

    // EFFECTS: return spending amount
    public double getAmount() {
        return amount;
    }

    // EFFECTS: return spending category
    public String getCategory() {
        return category;
    }

    // EFFECTS: return spending date
    public String getDate() {
        return date;
    }

    @Override
    // MODIFIES: this
    // Effects: print the spending list in terms of string
    public String toString() {
        return "Spending{"
                + "amount=" + amount + ", category='" + category + '\'' + ", date='" + date + '\'' + '}';
    }

    @Override
    // MODIFIES: this
    // EFFECTS: make a Json Object with amount, category and date
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("category", category);
        json.put("date", date);
        return json;
    }
}
