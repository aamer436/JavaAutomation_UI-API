package com.practice.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClass {
    // API documentation https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md
    String accessToken="";
    String baseUrl="https://simple-grocery-store-api.glitch.me";
    @BeforeClass
    public void get_access_code(){
        Map<String,String> body=new HashMap<String,String>();
        body.put("clientName","Aamer");
        body.put("clientEmail","mohdaamer12@gmail.com");
        System.out.println("Body "+body);
        Response response=given().contentType(ContentType.JSON).body(body).when().post(baseUrl+"/api-clients?clientName&clientEmail");
        System.out.println("Response code "+response.getStatusCode());
        System.out.println("Response Body "+response.getBody().asString());
        accessToken="69a8aa589662c05dceb9fa31f3fc11c6397b1d162adf282ea9447a9357ae524e";
    }


}
