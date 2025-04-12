package com.practice.basics;

import io.restassured.path.json.JsonPath;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseJson4_UsingDotNotation {
    /// USED REST ASSURED "JSON PATH" CLASS
    public static void main(String[] args) {
        String filePath = "resources/ExampleJson3.json";
        try (InputStream inputStream = ParseJson4_UsingDotNotation.class.getClassLoader().getResourceAsStream("ExampleJson3.json")) {
            if (inputStream == null) {
                throw new IOException("File not found: ExampleJson3.json");
            }
            JsonPath jsonPath = new JsonPath(inputStream);
            List<Map<String, Object>> list = jsonPath.getList("interviewPrep.topics");
            List<List> final_result = new ArrayList<>();
            for (Map i : list) {
                List<Map<String, Object>> questions = (List<Map<String, Object>>) i.get("questions");
                List<String> result = null;
                for (Map j : questions) {
                    List<Map<String, Object>> resources = (List<Map<String, Object>>) j.get("resources");
                    for (Map k : resources) {
                        result = new ArrayList<>();
                        result.add((String) i.get("name"));
                        result.add((String) j.get("question"));
                        result.add((String) j.get("difficulty"));
                        result.add((String) k.get("title"));
                        final_result.add(result);
                    }
                }
            }
            for (List i : final_result) {
                System.out.println(i);
            }
            CSVPrinter printer = new CSVPrinter(new FileWriter("output/output.csv"), CSVFormat.DEFAULT);
            String csvFile = "Automation/output.csv";
            //create header row
            printer.printRecord("Name", "Question", "Difficulty", "Title");
            // create data rows
            for (List i : final_result) {
                printer.printRecord(i);
            }
            //close the printer after the file is complete
            printer.flush();
            printer.close();

            Map<String,Integer> areas=jsonPath.getMap("interviewPrep.selfAssessment.confidence.areas");
            for(Map.Entry<String,Integer> entry: areas.entrySet()){
                System.out.println("KEY-VALUE "+entry.getKey() +"-"+entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
