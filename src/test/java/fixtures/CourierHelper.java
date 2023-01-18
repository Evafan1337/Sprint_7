package fixtures;
import data.DeleteCourier;
import data.LoginCourier;
import data.LoginCourierResponse;
import data.NewCourier;
import io.restassured.internal.RestAssuredResponseImpl;

import static io.restassured.RestAssured.given;

public class CourierHelper {

    RestAssuredResponseImpl courier;
    RestAssuredResponseImpl loginCourier = null;

    NewCourier courierData;
    LoginCourier loginCourierData;

    public CourierHelper(String login, String password, String firstName){

        courierData = new NewCourier(login, password, firstName);
        loginCourierData = new LoginCourier(login,password);
    }

    public void makeCreateCourierRequest(){
        courier = (RestAssuredResponseImpl) given()
            .header("Content-type", "application/json")
            .and()
            .body(courierData)
            .when()
            .post("/api/v1/courier")
            .body();
    }

    public void makeCourierLoginRequest(){
        loginCourier = (RestAssuredResponseImpl) given()
            .header("Content-type", "application/json")
            .and()
            .body(loginCourierData)
            .when()
            .post("/api/v1/courier/login")
            .body();
    }

    public LoginCourierResponse getCourierLoginData(){
        return loginCourier.as(LoginCourierResponse.class);
    }

    public RestAssuredResponseImpl getCourier(){
        return courier;
    }

    public int getCourierId(){

        if(this.loginCourier == null){
            this.makeCourierLoginRequest();
            return this.getCourierLoginData().getId();
        } else {
            return this.getCourierLoginData().getId();
        }
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

    public int getCourierLoginStatusCode(){
        return loginCourier.getStatusCode();
    }
}
