package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.when;

public class WeatherMap extends apiTests.BaseTestAPI {

    String baseURI = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";


    @Test
    public void executeAPI2(){
        Response response = when().get(baseURI);
        Assert.assertEquals(response.statusCode(), 200, "The status code of: " + response.statusCode() + " doesn't math the expected!");
        JsonPath jsonPath = response.jsonPath();
        HashMap<String,Integer> coords = jsonPath.get("coord");
        coords.get("lon");
        coords.get("lat");
        System.out.println("Coordinates: " + "\n" + coords.toString());
    }
}
