package fixtures;
import static io.restassured.RestAssured.given;
import data.CreateOrder;
import io.restassured.internal.RestAssuredResponseImpl;

public class OrderCreateHelper {


    RestAssuredResponseImpl order;

    public OrderCreateHelper(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color){

        CreateOrder newOrderData = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        order = (RestAssuredResponseImpl) given()
            .header("Content-type", "application/json")
            .and()
            .body(newOrderData)
            .when()
            .post("/api/v1/orders")
            .body();
    }

    public RestAssuredResponseImpl getOrder(){
        return order;
    }
}
