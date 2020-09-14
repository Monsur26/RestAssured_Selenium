package GETresponseValidation;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_POST_Request {
    @Test
    void registerCustomer(){
        RestAssured.baseURI="http://restapi.demoqa.com/customer";
        RequestSpecification httpRequest=RestAssured.given();

        //Request (to put) payload using jason
        JSONObject requestPARAM=new JSONObject();
        requestPARAM.put("FirstName","Sid");
        requestPARAM.put("LastName","Did");
        requestPARAM.put("UserName","SidXYZ");
        requestPARAM.put("Password","DidXYZ123");
        requestPARAM.put("Email","Sid.Did@nomail.com");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestPARAM.toJSONString());

        //Actual request
        Response response=httpRequest.request(Method.POST,"/register");

        String responseBody=response.getBody().asString();
        System.out.println(responseBody);

        //Validation part
        int statusCode=response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,201);

        String successCode=response.jsonPath().get("SuccessCode");
        System.out.println(successCode);
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");
    }
}
