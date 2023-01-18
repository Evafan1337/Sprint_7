package fixtures;
import io.restassured.internal.RestAssuredResponseImpl;
import static io.restassured.RestAssured.given;

public class GetOrdersHelper {

    RestAssuredResponseImpl orders;

    public GetOrdersHelper(){
        orders = (RestAssuredResponseImpl) given()
            .header("Content-type", "application/json")
            .and()
            .when()
            .get("/api/v1/orders")
            .body();
    }

    public RestAssuredResponseImpl getOrdersRequest(){
        return orders;
    }

    public int getOrdersRequestStatusCode(){
        return orders.getStatusCode();
    }
}
