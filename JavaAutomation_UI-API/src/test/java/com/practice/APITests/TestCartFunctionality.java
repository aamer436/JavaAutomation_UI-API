package com.practice.APITests;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestCartFunctionality extends BaseClass {
    public static String cartId = "";
    public List<Integer> productId = new ArrayList<>();
    public static Integer itemId = 0;

    @Test(priority = 0)
    public void testCreateNewCart() {
        Response response = given().contentType(ContentType.JSON).when().post(baseUrl + "/carts");
        System.out.println("response code " + response.getStatusCode());
        System.out.println("response body" + response.getBody().asString());
        System.out.println("created cart id " + response.jsonPath().get("cartId"));
        cartId = response.jsonPath().get("cartId");
    }

    @Test(priority = 1)
    public void testAddItemToCart() {
        Response productsResponse = given().contentType(ContentType.JSON).when().get(baseUrl + "/products");
        System.out.println(productsResponse.getBody().asString());
        for (Map i : (List<Map<String, Object>>) productsResponse.jsonPath().get()) {
            productId.add((Integer) i.get("id"));
        }
        System.out.println("ProductId :" + productId);
        for (int i = 1; i <= 1; i++) {
            Integer productIdSelected = productId.get(new Random().nextInt(productId.size()));
            System.out.println("Adding Productid to cart "+productIdSelected);
            Map<String, Integer> productPayload = new HashMap<>();
            productPayload.put("productId", productIdSelected);
            productPayload.put("quantity", 2);
            Response response = given().pathParam("cartId", cartId).body(productPayload)
                    //.queryParam("cartId",cartId)
                    .contentType(ContentType.JSON)
                    .when().post(baseUrl + "/carts/{cartId}/items?cartId=" + cartId);
            System.out.println("response code " + response.getStatusCode());
            System.out.println("response body " + response.jsonPath().get("itemId"));
            itemId = response.jsonPath().get("itemId");
        }

    }

    @Test(priority = 2)
    public void testGetCartSummary() {
        //// WITH QUERY PARAM METHOD //////
        Response response = given().pathParam("cartId", cartId)
                .queryParam("cartId", cartId)
                .contentType(ContentType.JSON)
                .when().get(baseUrl + "/carts/{cartId}/items");
        System.out.println("response code " + response.getStatusCode());
        System.out.println("response body" + response.getBody().asString());
        for (Map c : (List<Map>) response.jsonPath().get()) {
            System.out.println("ID " + c.get("id"));
        }
    }

    @Test(priority = 3)
    public void testGetCartInfo() {
        //// WITHOUT QUERY PARAM METHOD
        System.out.println("Running the test -- testGetCartInfo");
        Response response = given().pathParam("cartId", cartId).contentType(ContentType.JSON)
                .when().get(baseUrl + "/carts/{cartId}?cartId=" + cartId);
        System.out.println("Response code " + response.getStatusCode());
        for (Map i : (List<Map>) response.jsonPath().get("items")) {
            System.out.println("ID " + i.get("id"));
        }

    }

    @Test(priority = 4)
    public void testModifyItemInCart() {
        System.out.println("Running the test -- testModifyItemInCart");
        Map<String, Integer> modifyPayload = new HashMap<>();
        Integer newQuantity = 8;
        modifyPayload.put("quantity", newQuantity);
        Response response = given().pathParam("cartId", cartId)
                .pathParam("itemId", itemId).body(modifyPayload).contentType(ContentType.JSON)
                .when().patch(baseUrl + "/carts/{cartId}/items/{itemId}?cartId=" + cartId + "&itemId=" + itemId);
        System.out.println("Response code " + response.getStatusCode());

        if (response.getStatusCode() == 204) {
            Response cartSummaryResponse = given().pathParam("cartId", cartId)
                    .queryParam("cartId", cartId)
                    .contentType(ContentType.JSON)
                    .when().get(baseUrl + "/carts/{cartId}/items");
            System.out.println("response code " + cartSummaryResponse.getStatusCode());
            System.out.println("response body" + cartSummaryResponse.getBody().asString());
            for (Map c : (List<Map>) cartSummaryResponse.jsonPath().get()) {
                System.out.println("ID " + c.get("id")+ "quantity " + c.get("quantity"));
                System.out.println("type "+c.get("id").getClass());
                if (c.get("id").equals(itemId) && c.get("quantity") == newQuantity) {
                    System.out.println("Patch successful - new quantity set to " + newQuantity);
                    break;
                } else {
                    System.out.println("Patch returned 204 -- however update unsuccessful");
                }
            }
        }else{
            System.out.println("Patch not working, got status code "+response.getStatusCode());
        }

    }
}
