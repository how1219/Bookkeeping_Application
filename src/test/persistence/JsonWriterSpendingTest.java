package persistence;

import model.Spending;
import model.SpendingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterSpendingTest extends JsonSpendingTest{

    @Test
    void testWriterInvalidFile() {
        try {
            SpendingList sl = new SpendingList();
            JsonWriterSpending writer = new JsonWriterSpending("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySpending() {
        try {
            SpendingList sl = new SpendingList();
            JsonWriterSpending writer = new JsonWriterSpending("./data/tests/testWriterEmptySpending.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReaderSpending reader = new JsonReaderSpending("./data/tests/testWriterEmptySpending.json");
            sl = reader.read();
            assertEquals(0, sl.calculateTotal());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSpending() {
        try {
            SpendingList sl = new SpendingList();
            sl.addSpending(1000, "food", "2003-11-26");
            sl.addSpending(2000, "book", "2003-11-26");
            JsonWriterSpending writer = new JsonWriterSpending("./data/tests/testWriterGeneralSpending.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReaderSpending reader = new JsonReaderSpending("./data/tests/testWriterGeneralSpending.json");
            sl = reader.read();
            List<Spending> listOfSpending = sl.getListOfSpending();
            assertEquals(2, listOfSpending.size());
            checkSpending(1000, "food", "2003-11-26", listOfSpending.get(0));
            checkSpending(2000, "book", "2003-11-26", listOfSpending.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
