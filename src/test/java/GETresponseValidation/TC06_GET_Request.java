package GETresponseValidation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06_GET_Request {
    @Test
    void captureEachNodeValue() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Delhi");

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getChar("City"));
        System.out.println(jsonPath.getChar("Temperature"));
        System.out.println(jsonPath.getChar("Humidity"));

        Assert.assertEquals(jsonPath.get("Temperature"), "30 Degree celsius");
    }
}