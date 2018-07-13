package utils.apiRequests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestGET {

    public static Response searchNearbyCity(RequestSpecification specification, Map<String, String> parameters) {
        return given().
                spec(specification).
                queryParams(parameters).
                when().get("maps/api/place/nearbysearch/json").
                andReturn();
    }
}
