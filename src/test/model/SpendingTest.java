package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.SpendingList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

class SpendingTest {
    private SpendingList spendingList1;

    @BeforeEach
    void runBefore() {
        spendingList1 = new SpendingList();
    }

    @Test
    void calculateTotalEmptyTest() {
        assertEquals(0, spendingList1.calculateTotal());
    }

    @Test
    void calculateTotalTest() {
        spendingList1.addSpending(50, "book", 20220203);
        spendingList1.addSpending(70, "food", 20220203);
        assertEquals(50 + 70, spendingList1.calculateTotal());
    }
}