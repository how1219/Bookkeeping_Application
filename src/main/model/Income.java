package model;

// // Represents an income with amount and date.
public class Income {
    private double amount;
    private String date;

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
    public String toString() {
        return "Income{"
                + "amount=" + amount + ", date='" + date + '\'' + '}';
    }
}
