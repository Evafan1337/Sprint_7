package tests;
import data.*;
import org.junit.Before;
import org.junit.Test;

import data.CreateOrderResponse;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

import java.util.ArrayList;

public class OrderTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createOrder(){

        String firstName = "Naruto";
        String lastName = "Uchiha";
        String address = "Konoha, 142 apt.";
        int metroStation = 4;
        String phone = "+7 800 355 35 35";
        int rentTime = 5;
        String deliveryDate = "2020-06-06";
        String comment = "Saske, come back to Konoha";
        ArrayList<String> color = new ArrayList<String>();
        color.add("BLACK");

        CreateOrder newOrderData = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        CreateOrderResponse response = given()
            .header("Content-type", "application/json")
            .and()
            .body(newOrderData)
            .when()
            .post("/api/v1/orders")
            .body()
            .as(CreateOrderResponse.class);

        System.out.println(response.getTrack());

    }
}
