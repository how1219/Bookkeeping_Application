package model;

// Represents a spending with amount, category, and date.
public class Spending {
    private double amount;
    private String category;
    private String date;

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
    // Effects: print the spending list in terms of string
    public String toString() {
        return "Spending{"
                + "amount=" + amount + ", category='" + category + '\'' + ", date='" + date + '\'' + '}';
    }
}
