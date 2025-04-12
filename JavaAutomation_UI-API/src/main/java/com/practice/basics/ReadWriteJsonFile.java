package com.practice.basics;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;

public class ReadWriteJsonFile {
    /// USED GSON CLASS TO PARSE JSON FILE
    private static final String FILE_PATH = "data.json";  // The path to your JSON file
    private static final Gson gson = new Gson();  // Gson instance

    // Method to read JSON file
    public static JsonObject readJsonFile() throws IOException {
        if (!Files.exists(Paths.get(FILE_PATH))) {
            return new JsonObject();  // Return an empty object if the file does not exist
        }

        FileReader reader = new FileReader(FILE_PATH);
        return JsonParser.parseReader(reader).getAsJsonObject();
    }

    // Method to write (overwrite) to the JSON file
    public static void writeJsonToFile(JsonObject jsonObject) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(jsonObject, writer);
        }
    }

    // Method to append data to the JSON file
    public static void appendJsonToFile(Map<String, Object> newData) throws IOException {
        JsonObject jsonObject = readJsonFile();  // Read existing data
        JsonObject newJsonData = gson.toJsonTree(newData).getAsJsonObject();  // Convert new data to JsonObject

        // Merge the new data with the existing data
        for (Map.Entry<String, JsonElement> entry : newJsonData.entrySet()) {
            jsonObject.add(entry.getKey(), entry.getValue());
        }

        // Write the updated content back to the file
        writeJsonToFile(jsonObject);
    }

    public static void main(String[] args) {
        try {
            // Example: Writing new data to the JSON file (overwrite)
            JsonObject newData = new JsonObject();
            newData.addProperty("name", "John Doe");
            newData.addProperty("age", 30);
            writeJsonToFile(newData);

            // Example: Appending new data to the JSON file
            Map<String, Object> additionalData = new HashMap<>();
            additionalData.put("city", "New York");
            appendJsonToFile(additionalData);

            // Example: Reading the file content
            JsonObject existingData = readJsonFile();
            System.out.println("Existing Data: " + existingData.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
