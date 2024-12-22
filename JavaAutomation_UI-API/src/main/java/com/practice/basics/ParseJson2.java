package com.practice.basics;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseJson2 {

    public static void main(String[] args){
        ObjectMapper objectMapper=new ObjectMapper();
        String filePath = "resourcess/ExampleJson1.json";
        try (InputStream inputStream = ParseJson2.class.getClassLoader().getResourceAsStream("ExampleJson2.json")) {
            if (inputStream == null) {
                throw new IOException("File not found: ExampleJson2.json");
            }
            Map<String ,Object> response = objectMapper.readValue(inputStream, Map.class);
            System.out.println("Name of the Store "+((Map)response.get("store")).get("name"));

            //List<Map<String,Object>> customers = (List<Map<String, Object>>) ((Map)response.get("store")).get("customers");
            List<Map<String,Object>> customers = (List<Map<String, Object>>) ((Map)response.get("store")).get("customers");
            List<String> customer_emails = new ArrayList<>();
            for(Map<String,Object> i:  customers){
                customer_emails.add((String) i.get("email"));
                if(!((List<Map<String, Object>>) i.get("purchaseHistory")).isEmpty()){
                    for(Map<String, Object> j : (List<Map<String,Object>>)i.get("purchaseHistory")){
                        System.out.println("Isbn for "+i.get("customerId")+" is -- "+j.get("isbn"));
                        System.out.println("Price for "+i.get("customerId")+" is -- "+j.get("price"));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
