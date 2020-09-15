package datadriven;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class postEmployeeDataUsingDataProvider {
    //Hard-Coded data
    @DataProvider(name = "employeeRecord")
    Object[][] employeeRecord(){
        String[][] employeeRecord={{"xyz","1234","22"},{"abc","1264","27"},{"abcxyz","1334","25"}};
        return employeeRecord;
    }
    @Test(dataProvider = "employeeRecord")
    void postNewEmployee(String ename,String esalary,String eage){
        RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest=RestAssured.given();

        JSONObject reqPARAM=new JSONObject();
        System.out.println(reqPARAM.put("name",ename));
        System.out.println(reqPARAM.put("salary",esalary));
        System.out.println(reqPARAM.put("age",eage));

        httpRequest.header("Content-type","application/json");
        httpRequest.body(reqPARAM.toJSONString());

        Response response=httpRequest.request(Method.POST,"/create");

        String responseBody=response.getBody().asString();
        System.out.print("Response body :"+responseBody);
        Assert.assertEquals(responseBody.contains(ename),true);
        Assert.assertEquals(responseBody.contains(esalary),true);
        Assert.assertEquals(responseBody.contains(eage),true);

       /* int statusCode=response.statusCode();
        Assert.assertEquals(statusCode,"200");*/

    }

}
