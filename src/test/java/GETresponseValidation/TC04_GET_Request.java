package GETresponseValidation;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC04_GET_Request {
    @Test
    void printingAllHeaders(){
        RestAssured.baseURI="https://maps.googleapis.com";
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request("/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius =1500&type=supermarket&key= AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        String responseBody=response.getBody().asString();
        System.out.println("Response Body :"+responseBody);

        Headers allHeader=response.headers();//Capturing all header value
        for (Header header:allHeader
             ) {
            System.out.println(header.getName()+" -->> "+header.getValue());
        }
    }
}
