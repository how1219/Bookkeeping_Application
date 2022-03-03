package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {

    private IncomeList incomeList1;
    private Income income1;

    @BeforeEach
    void runBefore() {
        incomeList1 = new IncomeList();
        income1 = new Income(10000,"2022-02-04");
    }

    @Test
    void testIncomeConstructor() {
        assertEquals(10000, income1.getAmount());
        assertEquals("2022-02-04", income1.getDate());
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
        incomeList1.addIncome(1000, "2022-02-07");
        assertEquals(500 + 700 +1000, incomeList1.calculateTotal());
        assertEquals(3, incomeList1.getListOfIncomes().size());
    }

    @Test
    void getDateTest() {
        assertEquals("2022-02-04", income1.getDate());
    }

    @Test
    void toStringTest() {
        assertEquals("Income{amount=10000.0, date='2022-02-04'}", income1.toString());
    }
}
