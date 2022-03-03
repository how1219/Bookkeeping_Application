package persistence;

import model.IncomeList;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// The main structure is referenced from the sample project provided

// Represents a writer that writes JSON representation of income list to file
public class JsonWriterIncome {

    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterIncome(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of income list to file
    public void write(IncomeList il) {
        JSONObject json = il.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
