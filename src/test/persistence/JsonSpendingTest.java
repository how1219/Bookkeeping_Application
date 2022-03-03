package persistence;

import model.Spending;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSpendingTest {

    protected void checkSpending(double amount, String category, String date, Spending spending) {
        assertEquals(amount, spending.getAmount());
        assertEquals(category, spending.getCategory());
        assertEquals(date, spending.getDate());
    }
}
