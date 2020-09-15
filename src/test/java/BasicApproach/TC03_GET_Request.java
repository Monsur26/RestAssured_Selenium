package BasicApproach;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_GET_Request {
    @Test
    void googleMapHeader(){
        RestAssured.baseURI="https://maps.googleapis.com";
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request("/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius =1500&type=supermarket&key= AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        String responseBody=response.getBody().asString();
        System.out.println("response boy : "+responseBody);

        String contentType=response.header("Content-Type");
        System.out.println(contentType);
        Assert.assertEquals(contentType,"text/html; charset=UTF-8");

        String contentLength=response.header("Content-Length");
        System.out.println(contentLength);
        Assert.assertEquals(contentLength,"1555");

        String server=response.header("Server");
        System.out.println(server);
        Assert.assertEquals(server,"scaffolding on HTTPServer2");

    }
}
