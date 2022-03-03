package persistence;

import model.IncomeList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// The main structure is referenced from the sample project provided

// Represents a reader that reads the income list from JSON data stored in file
public class JsonReaderIncome {
    private final String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReaderIncome(String source) {
        this.source = source;
    }

    // EFFECTS: reads income list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public IncomeList read() throws IOException {
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

    // EFFECTS: parses income list from JSON object and returns it
    private IncomeList parseWorkRoom(JSONObject jsonObject) {
        IncomeList il = new IncomeList();
        addIncomes(il, jsonObject);
        return il;
    }

    // MODIFIES: il
    // EFFECTS: parses incomes from JSON object and adds them to income list
    private void addIncomes(IncomeList il, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("income");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addIncome(il, nextThingy);
        }
    }

    // MODIFIES: il
    // EFFECTS: parses an income from JSON object and adds it to income list
    private void addIncome(IncomeList il, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String date = jsonObject.getString("date");

        il.addIncome(amount, date);
    }
}
