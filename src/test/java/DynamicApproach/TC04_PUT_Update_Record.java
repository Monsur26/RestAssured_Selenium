package DynamicApproach;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.RestUtils;

public class TC04_PUT_Update_Record extends TestBase {
    String empName = RestUtils.empName();
    String empSal = RestUtils.empSal();
    String empAge = RestUtils.empAge();
    @BeforeClass
    void putNewEmployee() throws InterruptedException {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/employees";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject reqPARAM = new JSONObject();
        reqPARAM.put("name", empName);
        reqPARAM.put("salary", empSal);
        reqPARAM.put("age", empAge);
        httpRequest.header("Content-type", "application/json");
        httpRequest.body(reqPARAM.toJSONString());
        Response response = httpRequest.request(Method.POST, "/update/"+empID);
        Thread.sleep(5000);
    }
    @Test
    void responseBody() {
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empName), true);
        Assert.assertEquals(responseBody.contains(empSal), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
    }
    @Test
    void validateStatusCode() {
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, "200");
    }
    @Test
    void validateStatusLine() {
        String statusLine = response.statusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
    @Test
    void checkContentType() {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }
    @Test
    void checkServerType() {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }
    @Test
    void contentEncoding() {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, "gzip");
    }
    @Test
    void contentLength() {
        String contentLength = response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) > 100);
    }


}
