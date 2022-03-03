package persistence;

import model.Spending;
import model.SpendingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderSpendingTest extends JsonSpendingTest {

    @Test
    void testReaderNonExistentFileSpending() {
        JsonReaderSpending reader = new JsonReaderSpending("./data/noSuchFile.json");
        try {
            SpendingList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySpending() {
        JsonReaderSpending reader = new JsonReaderSpending("./data/tests/testReaderEmptySpending.json");
        try {
            SpendingList sl = reader.read();
            assertEquals(0, sl.calculateTotal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSpending() {
        JsonReaderSpending reader = new JsonReaderSpending("./data/tests/testReaderGeneralSpending.json");
        try {
            SpendingList sl = reader.read();
            List<Spending> listOfSpending = sl.getListOfSpending();
            assertEquals(2, listOfSpending.size());
            checkSpending(1000, "food", "2003-11-26", listOfSpending.get(0));
            checkSpending(2000, "book", "2003-11-26", listOfSpending.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
