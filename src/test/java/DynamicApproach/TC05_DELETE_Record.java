package DynamicApproach;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC05_DELETE_Record extends TestBase {
    @BeforeClass
    void putNewEmployee() throws InterruptedException {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response=httpRequest.request(Method.GET,"/employee");
        //JsonPath Object from Response Interface
        JsonPath jsonPath=response.jsonPath();
        int empID=jsonPath.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/update/"+empID);
        Thread.sleep(5000);
    }
    @Test
    void checkResponseBody(){
        String responseBody=response.getBody().asString();
        Assert.assertEquals(responseBody.contains("Successfully! deleted Records"),true);
    }

}
