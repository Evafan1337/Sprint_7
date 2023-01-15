package tests;
import data.*;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class CourierTest {

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }


    @Test
    public void createCourierTest(){
        System.out.println("createCourierTest");

        NewCourier newCourierData = new NewCourier("5555_new_ninja", "1234", "saske");
        LoginCourier loginCourierData = new LoginCourier("5555_new_ninja","1234");

        given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourierData)
            .when()
            .post("/api/v1/courier")
            .then().statusCode(201);

        LoginCourierResponse response = given()
            .header("Content-type", "application/json")
            .and()
            .body(loginCourierData)
            .when()
            .post("/api/v1/courier/login")
            .body()
            .as(LoginCourierResponse.class);

        DeleteCourier deleteCourierData = new DeleteCourier(response.getId());

        given()
            .header("Content-type", "application/json")
            .and()
            .body(deleteCourierData)
            .when()
            .delete("/api/v1/courier/"+response.getId())
            .then().statusCode(200);
    }

    @Test
    public void createCourierReturnsTrue(){
        NewCourier newCourierData = new NewCourier("true_555_new_ninja", "1234", "saske");
        LoginCourier loginCourierData = new LoginCourier("true_555_new_ninja","1234");

        CreateCourierResponse createCourierResponse = given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourierData)
            .when()
            .post("/api/v1/courier")
            .body()
            .as(CreateCourierResponse.class);

        LoginCourierResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierData)
                .when()
                .post("/api/v1/courier/login")
                .body()
                .as(LoginCourierResponse.class);

        DeleteCourier deleteCourierData = new DeleteCourier(response.getId());

        given()
                .header("Content-type", "application/json")
                .and()
                .body(deleteCourierData)
                .when()
                .delete("/api/v1/courier/"+response.getId())
                .then().statusCode(200);
    }

    //Testing response code? For example: looking for 400?
    @Test
    public void failCreatingCourierWithoutLogin(){
        NewCourier newCourierData = new NewCourier("", "1234", "saske");
        LoginCourier loginCourierData = new LoginCourier("5555_new_ninja","1234");

        given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourierData)
            .when()
            .post("/api/v1/courier")
            .then().statusCode(400);
    }

    @Test
    public void failCreatingCourierWithoutPassword(){

        NewCourier newCourierData = new NewCourier("5555_new_ninja", "", "saske");
        LoginCourier loginCourierData = new LoginCourier("5555_new_ninja","1234");

        given()
            .header("Content-type", "application/json")
            .and()
            .body(newCourierData)
            .when()
            .post("/api/v1/courier")
            .then().statusCode(400);
    }

    @Test
    public void failCreatingTwoSimilarCourierTest(){


        NewCourier firstCourier = new NewCourier("duplicate_5_new_ninja", "1234", "saske");
        LoginCourier firstCourierLoginData = new LoginCourier("duplicate_5_new_ninja","1234");
        NewCourier secondCourier = new NewCourier("duplicate_5_new_ninja", "1234", "saske");

        given()
            .header("Content-type", "application/json")
            .and()
            .body(firstCourier)
            .when()
            .post("/api/v1/courier")
            .then().statusCode(201);

        given()
            .header("Content-type", "application/json")
            .and()
            .body(firstCourier)
            .when()
            .post("/api/v1/courier")
            .then().statusCode(409);

        // Login and deleting first created courier
        LoginCourierResponse response = given()
            .header("Content-type", "application/json")
            .and()
            .body(firstCourierLoginData)
            .when()
            .post("/api/v1/courier/login")
            .body()
            .as(LoginCourierResponse.class);

        DeleteCourier deleteCourierData = new DeleteCourier(response.getId());

        given()
            .header("Content-type", "application/json")
            .and()
            .body(deleteCourierData)
            .when()
            .delete("/api/v1/courier/"+response.getId())
            .then().statusCode(200);
    }


}
