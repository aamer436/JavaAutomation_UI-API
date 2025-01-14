package com.practice.APITests;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestCartFunctionality extends BaseClass {
    public static String cartId="";
    public List<Integer> productId = new ArrayList<>();
    @Test(priority = 0)
    public void testCreateNewCart(){
        Response response=given().contentType(ContentType.JSON).when().post(baseUrl+"/carts");
        System.out.println("response code "+response.getStatusCode());
        System.out.println("response body"+response.getBody().asString());
        System.out.println("created cart id "+response.jsonPath().get("cartId"));
        cartId=response.jsonPath().get("cartId");
    }

    @Test(priority = 1)
    public void testAddItemToCart(){
        Response productsResponse= given().contentType(ContentType.JSON).when().get(baseUrl+"/products");
        System.out.println(productsResponse.getBody().asString());
        for(Map i:(List<Map<String,Object>>)productsResponse.jsonPath().get()){
            productId.add((Integer) i.get("id"));
        }
        System.out.println("ProductId :"+productId);
        for(int i=1;i<=3;i++){
            Integer productIdSelected=productId.get(new Random().nextInt(productId.size()));
            Map<String,Integer> productPayload=new HashMap<>();
            productPayload.put("productId",productIdSelected);
            productPayload.put("quantity",2);
            Response response=given().pathParam("cartId",cartId).body(productPayload)
                    //.queryParam("cartId",cartId)
                    .contentType(ContentType.JSON)
                    .when().post(baseUrl+"/carts/{cartId}/items?cartId="+cartId);
            System.out.println("response code "+response.getStatusCode());
            System.out.println("response body "+response.jsonPath().get("itemId"));
        }

    }

    @Test(priority = 2)
    public void testGetCartSummary(){
        //// WITH QUERY PARAM METHOD //////
        Response response=given().pathParam("cartId",cartId)
                .queryParam("cartId",cartId)
                .contentType(ContentType.JSON)
                .when().get(baseUrl+"/carts/{cartId}/items");
        System.out.println("response code "+response.getStatusCode());
        System.out.println("response body"+response.getBody().asString());
        for(Map c:(List<Map>)response.jsonPath().get()){
            System.out.println("ID "+c.get("id"));
        }
    }

    @Test(priority = 3)
    public void testGetCartInfo(){
        //// WITHOUT QUERY PARAM METHOD
        System.out.println("Running the test -- testGetCartInfo");
        Response response=given().pathParam("cartId",cartId).contentType(ContentType.JSON)
                .when().get(baseUrl+"/carts/{cartId}?cartId="+cartId);
        System.out.println("Response code "+response.getStatusCode());
        for(Map i:(List<Map>)response.jsonPath().get("items")){
            System.out.println("ID "+i.get("id"));
        }

    }
}
