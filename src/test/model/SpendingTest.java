package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpendingTest {
    private SpendingList spendingList1;

    @BeforeEach
    void runBefore() {
        spendingList1 = new SpendingList();
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
    }
}