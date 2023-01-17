package tests;
import data.*;
import fixtures.OrderCreateHelper;
import io.restassured.internal.RestAssuredResponseImpl;
import org.junit.Before;
import org.junit.Test;

import data.CreateOrderResponse;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import io.restassured.RestAssured;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    public String firstName;
    public String lastName;
    public String address;
    //public int metroStation;
    public String metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    //public ArrayList<String> color;
    public String[] color;
    boolean expected;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color, boolean expected){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { "name1", "lastname1", "addr1","metro1", "89542311525", 4, "2020-06-06", "comm1", new String[] {"BLACK","GRAY"}},
                { "name13", "lastname13", "addr1","metro1", "89542311525", 4, "2020-06-06", "comm1", new String[] {"GRAY"}},
                { "name12", "lastname12", "addr1","metro1", "89542311525", 4, "2020-06-06", "comm1", new String[] {""}},
                { "", "", "","", "", 4, "2020-06-06", "", new String[] {""}}
        };
    }

    @Test
    public void createOrder(){
        OrderCreateHelper orderRequest = new OrderCreateHelper(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        CreateOrderResponse response = orderRequest.getOrder().as(CreateOrderResponse.class);
        assertNotNull(response.getTrack());
    }
}
