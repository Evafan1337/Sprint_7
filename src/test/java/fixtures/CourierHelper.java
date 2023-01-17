package fixtures;

import data.DeleteCourier;
import data.LoginCourier;
import data.LoginCourierResponse;
import data.NewCourier;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

public class CourierHelper {

    RestAssuredResponseImpl courier;
    NewCourier courierData;
    LoginCourier loginCourierData;

    public CourierHelper(String login, String password, String firstName){

        courierData = new NewCourier(login, password, firstName);
        loginCourierData = new LoginCourier(login,password);

        courier = (RestAssuredResponseImpl) given()
            .header("Content-type", "application/json")
            .and()
            .body(courierData)
            .when()
            .post("/api/v1/courier")
            .body();
    }

    public LoginCourierResponse getCourierLoginData(){
        LoginCourierResponse response = given()
            .header("Content-type", "application/json")
            .and()
            .body(loginCourierData)
            .when()
            .post("/api/v1/courier/login")
            .body()
            .as(LoginCourierResponse.class);
        return response;
    }

    public RestAssuredResponseImpl getCourier(){
        return courier;
    }

    public int getCourierId(){
        return this.getCourierLoginData().getId();
    }

    public void deleteCourier(){

        int courierId = this.getCourierId();

        DeleteCourier deleteCourierData = new DeleteCourier(courierId);

        given()
            .header("Content-type", "application/json")
            .and()
            .body(deleteCourierData)
            .when()
            .delete("/api/v1/courier/"+courierId)
            .then().statusCode(200);
    }

    public int getCourierCreateStatusCode(){
        return courier.getStatusCode();
    };
}
