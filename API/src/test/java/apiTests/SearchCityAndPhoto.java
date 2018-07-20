package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Reporter;
import utils.apiRequests.RequestGET;
import utils.apiRequests.RequestPOST;

import java.util.*;

public class SearchCityAndPhoto extends apiTests.BaseTestAPI {

    Response response;
    String key = "AIzaSyASHu2Ic4ZJOmw6XoJbqDi9JVusokzBBSA";

    @Test
    public void findNearbyCity() {
        Reporter.log("Creating parameters map for request");
        HashMap<String, String> parametersForRequest = new HashMap<>();
        parametersForRequest.put("key", key);
        parametersForRequest.put("placeid", "ChIJdc6CS602MEcR6FSx7UekhMQ");
        parametersForRequest.put("location", "49.550862, 25.583690");
        parametersForRequest.put("radius", "1000");
        response = RequestGET.searchNearbyCity(requestSpecification, parametersForRequest);
        Reporter.log("Request sent. Validating response from server");
        Assert.assertEquals(response.statusCode(), 200, "The status code of: " + response.statusCode() + " doesn't math the expected!");
        Reporter.log("Response status code is: " + response.statusCode());
    }

    @Test
    public void findPlaceAddPhoto() {
        Reporter.log("Creating parameters map for  Search Place request");
        HashMap<String, String> parametersForSearchPlace = new HashMap<>();
        parametersForSearchPlace.put("key", "AIzaSyASHu2Ic4ZJOmw6XoJbqDi9JVusokzBBSA");
        parametersForSearchPlace.put("input", "Cathedral of the Immaculate Conception of the Blessed Virgin Mary");
        parametersForSearchPlace.put("inputtype", "textquery");
        parametersForSearchPlace.put("fields", "photos,name");
        response = RequestGET.searchPlace(requestSpecification, parametersForSearchPlace);
        Reporter.log("Request sent. Validating response from server");
        Assert.assertEquals(response.statusCode(), 200, "The status code of: " + response.statusCode() + " doesn't math the expected!");
        Reporter.log("Response status code is: " + response.statusCode());
        //String body = response.getBody().asString();
        JsonPath jsonPath = response.jsonPath();
        String resultReference = jsonPath.getString("candidates.photos.photo_reference").
                replaceAll("\\[", "").replaceAll("\\]","");

        HashMap<String, String> parametersForPhoto = new HashMap<>();
        parametersForPhoto.put("key", key);
        parametersForPhoto.put("maxwidth", "5620");
        parametersForPhoto.put("photoreference", resultReference);
        response = RequestPOST.postPhoto(requestSpecification, parametersForPhoto);
        Assert.assertEquals(response.statusCode(), 200, "The status code of: " + response.statusCode() + " doesn't math the expected!");
        Reporter.log("Response status code is: " + response.statusCode());
    }
}
