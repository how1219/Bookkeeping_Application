package persistence;

import model.Income;
import model.IncomeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterIncomeTest extends JsonIncomeTest {

    @Test
    void testWriterInvalidFile() {
        try {
            IncomeList il = new IncomeList();
            JsonWriterIncome writer = new JsonWriterIncome("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyIncome() {
        try {
            IncomeList il = new IncomeList();
            JsonWriterIncome writer = new JsonWriterIncome("./data/tests/testWriterEmptyIncome.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReaderIncome reader = new JsonReaderIncome("./data/tests/testWriterEmptyIncome.json");
            il = reader.read();
            assertEquals(0, il.calculateTotal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralIncome() {
        try {
            IncomeList il = new IncomeList();
            il.addIncome(1000, "2003-11-26");
            il.addIncome(2000,  "2003-11-26");
            JsonWriterIncome writer = new JsonWriterIncome("./data/tests/testWriterGeneralIncome.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReaderIncome reader = new JsonReaderIncome("./data/tests/testWriterGeneralIncome.json");
            il = reader.read();
            List<Income> listOfIncome = il.getListOfIncomes();
            assertEquals(2, listOfIncome.size());
            checkIncome(1000, "2003-11-26", listOfIncome.get(0));
            checkIncome(2000, "2003-11-26", listOfIncome.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
