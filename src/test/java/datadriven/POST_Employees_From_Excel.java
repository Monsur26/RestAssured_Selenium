package datadriven;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class POST_Employees_From_Excel{
    @DataProvider(name = "employeeRecord")
    Object[][] employeeRecord() throws IOException {
        String path="src/test/java/Documents/EmployeeData.xlsx";
        int rowCount=ExcelRead.getRowCount(path,"Sheet1");
        int colCount=ExcelRead.getCellCount(path,"Sheet1",1);
        String[][] employeeRecord=new String[rowCount][colCount];
        for (int i = 1; i <=rowCount ; i++) {
            for (int j = 0; j <colCount ; j++) {
                employeeRecord[i-1][j]=ExcelRead.getCellData(path,"Sheet1",i,j);
            }

        }
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
        System.out.println("Response body :"+responseBody);
        Assert.assertEquals(responseBody.contains(ename),true);
        Assert.assertEquals(responseBody.contains(esalary),true);
        Assert.assertEquals(responseBody.contains(eage),true);

       /* int statusCode=response.statusCode();
        Assert.assertEquals(statusCode,"200");*/

    }
}
