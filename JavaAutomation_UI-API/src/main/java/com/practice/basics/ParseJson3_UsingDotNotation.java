package com.practice.basics;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import io.restassured.path.json.JsonPath;

public class ParseJson3_UsingDotNotation {
    public static void main(String[] args){
        String filePath = "resources/ExampleJson1.json";
        try (InputStream inputStream = ParseJson3_UsingDotNotation.class.getClassLoader().getResourceAsStream("ExampleJson1.json")) {
            if (inputStream == null) {
                throw new IOException("File not found: ExampleJson1.json");
            }
            JsonPath jsonPath = new JsonPath(inputStream);
            System.out.println("Person Name "+jsonPath.getString("person.name"));
            List<Map> hobbies = jsonPath.getList("person.hobbies");
            for(Map i : hobbies){
                System.out.println("Hobby -"+i.get("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream1 = ParseJson3_UsingDotNotation.class.getClassLoader().getResourceAsStream("ExampleJson2.json")) {
            if (inputStream1 == null) {
                throw new IOException("File not found: ExampleJson2.json");
            }
            JsonPath jsonPath = new JsonPath(inputStream1);
            System.out.println("Address -"+jsonPath.getString("store.location.address"));
            System.out.println("zip -"+jsonPath.getString("store.location.zip"));

            System.out.println("Inventory Author First name "+jsonPath.getString("store.inventory[0].author.firstName"));

            List<Map> reviews = jsonPath.getList("store.inventory[0].reviews");
            for(Map i : reviews){
                System.out.println("Reviewer Name -"+i.get("reviewer"));
                System.out.println("Reviewer Comment -"+i.get("comment"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}