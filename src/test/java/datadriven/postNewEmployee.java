package datadriven;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class postNewEmployee {
    @Test
    void postNewEmployee(){
        RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest=RestAssured.given();

        JSONObject reqPARAM=new JSONObject();
        System.out.println(reqPARAM.put("name","Jhoncc"));
        System.out.println(reqPARAM.put("salary","1234"));
        System.out.println(reqPARAM.put("age","63"));

        httpRequest.header("Content-type","application/json");
        httpRequest.body(reqPARAM.toJSONString());

        Response response=httpRequest.request(Method.POST,"/create");

        String responseBody=response.getBody().asString();
        System.out.print("Response body :"+responseBody);
        Assert.assertEquals(responseBody.contains("Jhoncc"),true);
        Assert.assertEquals(responseBody.contains("1234"),true);
        Assert.assertEquals(responseBody.contains("63"),true);

        int statusCode=response.statusCode();
        Assert.assertEquals(statusCode,"200");

    }
}
