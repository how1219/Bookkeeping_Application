package model;

// Represents a spending with amount, category, and date.
public class Spending {
    private double amount;
    private String category;
    private int date;

    // Construct a spending
    // EFFECTS: set the amount to spendingAmount; set the category to spendingCategory;
    //          set the date to spendingDate

    public Spending(double spendingAmount, String spendingCategory, int spendingDate) { //date is in form xxxxxxx
                                                                                        //        (year month day)
        amount = spendingAmount;
        category = spendingCategory;
        date = spendingDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public int getDate() {
        return date;
    }

}
