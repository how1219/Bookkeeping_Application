package persistence;

import model.Spending;
import model.SpendingList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the spending list from JSON data stored in file
public class JsonReaderSpending {
    private final String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReaderSpending(String source) {
        this.source = source;
    }

    // EFFECTS: reads spending list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SpendingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses spending list from JSON object and returns it
    private SpendingList parseWorkRoom(JSONObject jsonObject) {
        SpendingList sl = new SpendingList();
        addSpending(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses spending from JSON object and adds them to spending list
    private void addSpending(SpendingList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("spending");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addASpending(sl, nextThingy);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses a spending from JSON object and adds it to spending list
    private void addASpending(SpendingList sl, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String category = jsonObject.getString("category");
        String date = jsonObject.getString("date");

        sl.addSpending(amount, category, date);
    }
}
