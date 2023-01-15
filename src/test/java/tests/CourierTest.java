package tests;
import data.DeleteCourier;
import data.LoginCourier;
import data.LoginCourierResponse;
import data.NewCourier;
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
    public void failCreatingTwoSimilarCourierTest(){
        System.out.println("failCreatingTwoSimilarCourierTest");
    }


}
