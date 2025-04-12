package com.practice.APITests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestOpenCartNestedAPI {

    @Test
    public void testOpenCartAPI(){
        Response response=given().contentType(ContentType.JSON).when().get("https://api.agentfy.ai/v1/agents/agent-6c5d5cbf-f100-43d9-b1f0-862dc3d578a2");
        System.out.println("Dot notation usage in Rest assured nested response --- "+response.jsonPath().getString("data.team.name"));
        System.out.println("Another example --- "+response.jsonPath().getString("data.avatar.teamId"));

    }
}
