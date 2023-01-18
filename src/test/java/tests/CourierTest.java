package tests;
import data.*;
import fixtures.CourierHelper;
import io.restassured.internal.RestAssuredResponseImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.ArrayList;

public class CourierTest {

    ArrayList <CourierHelper> createdCouriers = new ArrayList<>();;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }


    @Test
    public void createCourierTest(){
        CourierHelper courier = new CourierHelper("crt_3_new_ninja", "1234", "saske");
        courier.makeCreateCourierRequest();
        createdCouriers.add(courier);

        int courierCreateStatusCode = courier.getCourierCreateStatusCode();
        assert courierCreateStatusCode == 201;
    }

    @Test
    public void successloginCourierTest(){
        CourierHelper courier = new CourierHelper("login_check_new_ninja", "1234", "saske");
        courier.makeCreateCourierRequest();
        createdCouriers.add(courier);

        courier.makeCourierLoginRequest();
        int courierLoginStatusCode = courier.getCourierLoginStatusCode();
        assert courierLoginStatusCode == 200;
    }

    @Test
    public void loginUnregisteredCourierTest(){
        CourierHelper courier = new CourierHelper("aaaaaa11111", "aaaaa1111", "aaaaaaasaske");
        courier.makeCourierLoginRequest();
        int courierLoginStatusCode = courier.getCourierLoginStatusCode();
        assert courierLoginStatusCode == 404;
    }

    @Test
    public void loginCourierWithoutLoginTest(){
        CourierHelper courier = new CourierHelper("", "aaaaa1111", "aaaaaaasaske");
        courier.makeCourierLoginRequest();
        int courierLoginStatusCode = courier.getCourierLoginStatusCode();
        assert courierLoginStatusCode == 400;
    }

    @Test
    public void loginCourierWithoutPasswordTest(){
        CourierHelper courier = new CourierHelper("aaaaaa11111", "", "aaaaaaasaske");
        courier.makeCourierLoginRequest();
        int courierLoginStatusCode = courier.getCourierLoginStatusCode();
        assert courierLoginStatusCode == 400;
    }

    @Test
    public void createCourierReturnsTrue(){
        CourierHelper courier = new CourierHelper("crt_4_new_ninja_true", "1234", "saske");
        courier.makeCreateCourierRequest();
        CreateCourierResponse response = courier.getCourier().as(CreateCourierResponse.class);
        createdCouriers.add(courier);
        assert response.getOk().equals("true");
    }

    @Test
    public void failCreatingCourierWithoutLogin(){
        CourierHelper courier = new CourierHelper("", "1234", "saske");
        courier.makeCreateCourierRequest();
        int courierCreateStatusCode = courier.getCourierCreateStatusCode();
        assert courierCreateStatusCode == 400;
    }

    @Test
    public void failCreatingCourierWithoutPassword(){
        CourierHelper courier = new CourierHelper("fail_create", "", "saske");
        courier.makeCreateCourierRequest();
        int courierCreateStatusCode = courier.getCourierCreateStatusCode();
        assert courierCreateStatusCode == 400;
    }

    @Test
    public void failCreatingTwoSimilarCourierTest(){

        CourierHelper courier1 = new CourierHelper("duplicate_111_ninja11", "1234", "saske");
        courier1.makeCreateCourierRequest();
        assert courier1.getCourierCreateStatusCode() == 201;
        createdCouriers.add(courier1);

        CourierHelper courier2 = new CourierHelper("duplicate_111_ninja11", "1234", "saske");
        courier2.makeCreateCourierRequest();
        assert courier2.getCourierCreateStatusCode() == 409;

    }

    @After
    public void rollBack(){

        for (CourierHelper elem: createdCouriers)
        {
            elem.deleteCourier();
        }
    }


}
