package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Reporter;
import utils.apiRequests.RequestGET;
import java.util.HashMap;


public class GoogleSearch extends apiTests.BaseTestAPI {

    Response response;


    @Test
    public void findCity() {
        Reporter.log("Creating parameters map for request");
        HashMap<String, String> parametersForRequest = new HashMap<>();
        parametersForRequest.put("key", "AIzaSyASHu2Ic4ZJOmw6XoJbqDi9JVusokzBBSA");
        parametersForRequest.put("placeid", "ChIJdc6CS602MEcR6FSx7UekhMQ");
        parametersForRequest.put("location", "49.550862, 25.583690");
        parametersForRequest.put("radius", "1000");
        response = RequestGET.searchNearbyCity(requestSpecification, parametersForRequest);
        Reporter.log("Request sent. Validating response from server");
        Assert.assertEquals(response.statusCode(), "200");
    }
}
