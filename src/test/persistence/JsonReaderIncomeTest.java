package persistence;

import model.Income;
import model.IncomeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderIncomeTest extends JsonIncomeTest {
    @Test
    void testReaderNonExistentFileIncome() {
        JsonReaderIncome reader = new JsonReaderIncome("./data/noSuchFile.json");
        try {
            IncomeList il = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyIncome() {
        JsonReaderIncome reader = new JsonReaderIncome("./data/tests/testReaderEmptyIncome.json");
        try {
            IncomeList il = reader.read();
            assertEquals(0, il.calculateTotal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralIncome() {
        JsonReaderIncome reader = new JsonReaderIncome("./data/tests/testReaderGeneralIncome.json");
        try {
            IncomeList il = reader.read();
            List<Income> listOfIncome = il.getListOfIncomes();
            assertEquals(2, listOfIncome.size());
            checkIncome(1000,"2003-11-26", listOfIncome.get(0));
            checkIncome(2000, "2003-11-26", listOfIncome.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
