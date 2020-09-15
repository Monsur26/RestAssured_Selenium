package DynamicApproach;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC02_GET_Specific_Employee_Info extends TestBase {
    @BeforeClass
    void getAllEmployeeInfo() throws InterruptedException {
        logger.info("************Started TC02_GET_Single_Employees***********");
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees/"+empID);
        Thread.sleep(5000);
    }
    @Test
    void responseBody() {
        logger.info("******Response Body********");
        String responseBody = response.getBody().asString();
        logger.info("Response body :" + responseBody);
        Assert.assertEquals(responseBody.contains(empID),true);
    }

    @Test
    void validateStatusCode() {
        logger.info("******Status Code********");
        int statusCode = response.statusCode();
        logger.info("Status Code is:" + statusCode);
        Assert.assertEquals(statusCode, "200");
    }

    @Test
    void responseTime() {
        logger.info("******Response Time********");
        long responseTime = response.getTime();
        logger.info("Response Time " + responseTime);
        if (responseTime > 2000)
            logger.warn("Response Time is greater than 2000");
        Assert.assertTrue(responseTime < 2000);
    }

    @Test
    void validateStatusLine() {
        logger.info("******Status Line********");
        String statusLine = response.statusLine();
        logger.info("Status Line is:" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {
        logger.info("******Content Type********");
        String contentType = response.header("Content-Type");
        logger.info("Content Type is:" + contentType);
        Assert.assertEquals(contentType, "application/json");
    }

    @Test
    void checkServerType() {
        logger.info("******Server Type********");
        String serverType = response.header("Server");
        logger.info("Server is:" + serverType);
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }
    @Test
    void contentEncoding() {
        logger.info("******Content Encoding********");
        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding is:" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }


    @Test
    void contentLength() {
        logger.info("******Content Length********");
        String contentLength = response.header("Content-Length");
        logger.info("Content Length is :" + contentLength);
        if (Integer.parseInt(contentLength) < 100)
            logger.warn("Content length is less than 100");
        Assert.assertTrue(Integer.parseInt(contentLength) > 100);
    }

    @Test
    void checkCookies() {
        logger.info("******Cookies********");
        String cookies = response.getCookie("PHPSESSID");
    }

    @AfterClass
    void tearDown() {
        logger.info("******TC02_GET_Single_Employees_finished*********");
    }
}
