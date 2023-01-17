package tests;
import data.GetOrdersResponse;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;


public class GetOrdersTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void getOrders(){

        GetOrdersResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("/api/v1/orders")
                .body()
                .as(GetOrdersResponse.class);
    }
}
