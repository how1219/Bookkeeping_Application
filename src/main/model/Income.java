package model;

// // Represents an income with amount and date.
public class Income {
    private double amount;
    private String date;

    // Construct an income
    // EFFECTS: set the amount to incomeAmount; set the date to incomeDate
    public Income(double incomeAmount, String incomeDate) {
        // a date is in the form "xxxx-xx-xx"(year-month-day)
        amount = incomeAmount;
        date = incomeDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }



}
