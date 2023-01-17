package tests;
import data.GetOrdersResponse;
import fixtures.GetOrdersHelper;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;


public class GetOrdersTest {

    public GetOrdersHelper correctRequest;
    public GetOrdersResponse correctResponse;
    public int correctResponseStatusCode;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";

        correctRequest = new GetOrdersHelper();
        correctResponse = correctRequest.getOrdersRequest().as(GetOrdersResponse.class);
        correctResponseStatusCode = correctRequest.getOrdersRequestStatusCode();
    }

    @Test
    public void getOrders(){
        assertNotNull(correctResponse.getPageInfo());
    }

    @Test
    public void getOrdersRequestCorrectStatusCode(){
        assert correctResponseStatusCode == 200;
    }
}
