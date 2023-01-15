package tests;
import data.*;
import org.junit.Before;
import org.junit.Test;

import data.CreateOrderResponse;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

import java.util.ArrayList;

public class OrderTest {

    public String firstName;
    public String lastName;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public ArrayList<String> color;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";

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
    }

    @Test
    public void createOrder(){

        CreateOrder newOrderData = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        CreateOrderResponse response = given()
            .header("Content-type", "application/json")
            .and()
            .body(newOrderData)
            .when()
            .post("/api/v1/orders")
            .body()
            .as(CreateOrderResponse.class);
    }

    @Test
    public void createOrderWithoutColor(){

        this.color = new ArrayList<String>();

        CreateOrder newOrderData = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        CreateOrderResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(newOrderData)
                .when()
                .post("/api/v1/orders")
                .body()
                .as(CreateOrderResponse.class);
    }

    @Test
    public void createOrderWithManyColors(){
        this.color = new ArrayList<String>();
        color.add("GREY");
        color.add("BLACK");

        CreateOrder newOrderData = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        given()
            .header("Content-type", "application/json")
            .and()
            .body(newOrderData)
            .when()
            .post("/api/v1/orders")
            .then().statusCode(201);
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

        System.out.println(response.getOrders());
    }
}
