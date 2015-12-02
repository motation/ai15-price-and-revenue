package de.haw.hamburg.hawai.cdt;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by unknown on 02.12.15.
 */
public class FlightApiServiceCDT {
    private String token;

    /**
     * The URL under which the Service is accessable.
     * On pure-local deployments this is usually 192.168.99.100
     * when Running Docker on OS X or Windows.
     * Change to 127.0.0.1 when using Linux.
     * Change to the real IP Address if using a production like environment.
     */
    private String apiServiceBaseURL = "127.0.0.1";

    @BeforeClass
    public void setupSecurityToken() {
        // Define relaxed SSL Auth, since we use self-signed certificates
        RestAssured.useRelaxedHTTPSValidation();
        // Register JSON Parser for plain text responses
        RestAssured.registerParser("text/plain", Parser.JSON);
        // Order and extract auth-token
        token =
                given().
                        param("grant_type", "password").
                        param("client_id", "acme").
                        param("username", "user").
                        param("password", "password").
                        when().post("https://acme:acmesecret@" + apiServiceBaseURL + ":9999/uaa/oauth/token").then().
                        extract().path("access_token");
    }

    @Test
    public void TestFlightsRestGet() {
        given().
                // use auth token for oauth v2 header
                        auth().oauth2(token).
                // make call to API
                        when().get("https://" + apiServiceBaseURL + "/api/api/flight")
                .then()
                .body("size()", greaterThanOrEqualTo(0));


        String url = "http://localhost:8080/prices2";
        given().get(url).then().body("size()", greaterThanOrEqualTo(0));
        given().get(url).then().body("get(0).flightId", equalTo("76fc2e8d-7587-4a18-b2f8-33eeda154b8b"));

    }

}
