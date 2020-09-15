package BasicApproach;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC07_GET_Request {
    @Test
    void basicAuthentication(){
        RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
        //Basic Authentication
        PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");
        RestAssured.authentication=authScheme;

        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request(Method.GET,"/");

        int statusCode=response.statusCode();
        System.out.println("Status Code : "+statusCode );
        Assert.assertEquals(statusCode,"200");

        String responseBody=response.getBody().asString();
        System.out.println("Response Body : "+responseBody);


    }
}
