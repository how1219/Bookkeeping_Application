package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {

    private IncomeList incomeList1;

    @BeforeEach
    void runBefore() {
        incomeList1 = new IncomeList();
    }

    @Test
    void calculateTotalEmptyTest() {
        incomeList1.addIncome(1000,"2022-02-04");
        incomeList1.removeIncome(0);
        assertEquals(0, incomeList1.calculateTotal());
    }

    @Test
    void calculateTotalTest() {
        incomeList1.addIncome(500, "2022-02-04");
        incomeList1.addIncome(700, "2022-02-04");
        assertEquals(500 + 700, incomeList1.calculateTotal());
    }
}
