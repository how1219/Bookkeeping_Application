package persistence;

import model.Income;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonIncomeTest {
    protected void checkIncome(double amount, String date, Income income) {
        assertEquals(amount, income.getAmount());
        assertEquals(date, income.getDate());
    }
}
