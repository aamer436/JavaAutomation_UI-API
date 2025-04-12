package com.practice.basics;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ParseJson1 {
    /// USED JACKSON CLASS TO PARSE JSON FILE
    public static void main(String[] args){
        ObjectMapper objectMapper=new ObjectMapper();
        String filePath = "resourcess/ExampleJson1.json";
        try (InputStream inputStream = ParseJson1.class.getClassLoader().getResourceAsStream("ExampleJson1.json")) {
            if (inputStream == null) {
                throw new IOException("File not found: ExampleJson1.json");
            }
            // below line can be used when we actually know the file path -- filePath value should be correct.
            //Map<String, Object> map = objectMapper.readValue(new File(filePath), Map.class);
            Map<String, Object> map = objectMapper.readValue(inputStream, Map.class);
            System.out.println("Parsed Map "+map);

            Map<String, Object> person = (Map<String, Object>) map.get("person");
            System.out.println("Person Map "+person);

            Map<String, Object> address = (Map<String, Object>) person.get("address");
            System.out.println("Address Map "+address);
            System.out.println("Street Address "+address.get("street"));

            List<Map<String,Object>> hobbies = (List<Map<String, Object>>) person.get("hobbies");
            for(Map<String,Object> h: hobbies){
                System.out.println("hobbie name "+h.get("name"));
                System.out.println("hobbie type "+h.get("type"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
