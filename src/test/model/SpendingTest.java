package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpendingTest {
    private Spending spending1;
    private SpendingList spendingList1;

    @BeforeEach
    void runBefore() {
        spendingList1 = new SpendingList();
        spending1 = new Spending(100, "food","2022-02-04");
    }

    @Test
    void testSpendingConstructor() {
        assertEquals(100, spending1.getAmount());
        assertEquals("food", spending1.getCategory());
        assertEquals("2022-02-04", spending1.getDate());

    }

    @Test
    void calculateTotalEmptyTest() {
        spendingList1.addSpending(50,"book", "2022-02-03");
        spendingList1.removeSpending(0);
        assertEquals(0, spendingList1.calculateTotal());
    }

    @Test
    void calculateTotalTest() {
        spendingList1.addSpending(50, "book", "2022-02-03");
        spendingList1.addSpending(70, "food", "2022-02-03");
        assertEquals(50 + 70, spendingList1.calculateTotal());
        spendingList1.addSpending(100, "book", "2022-02-07");
        assertEquals(50 + 70 + 100, spendingList1.calculateTotal());
        assertEquals(3, spendingList1.getListOfSpending().size());

    }
}