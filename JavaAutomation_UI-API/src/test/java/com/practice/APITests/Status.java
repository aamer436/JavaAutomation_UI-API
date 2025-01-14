package com.practice.APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Status extends BaseClass {
    @Test
    public void getStatus(){
        System.out.println("Access Token "+accessToken);
        Response response = given().contentType(ContentType.JSON).when().get(baseUrl+"/status");
        System.out.println("Response code "+response.getStatusCode());
        System.out.println("Response body "+response.getBody().asString());
        System.out.println("Response status "+response.jsonPath().get("status"));
    }


}
