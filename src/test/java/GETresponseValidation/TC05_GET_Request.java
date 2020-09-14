package GETresponseValidation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_GET_Request {
    @Test
    void validateJsonResponseBody(){
        RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request(Method.GET,"/Kolkata");

        String responseBody=response.getBody().asString();
        System.out.println("Response Body : "+responseBody);
        Assert.assertEquals(responseBody.contains("Kolkata"),true);
    }
}
