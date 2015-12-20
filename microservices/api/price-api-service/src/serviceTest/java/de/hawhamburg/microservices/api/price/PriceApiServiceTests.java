package de.hawhamburg.microservices.api.price;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Ole on 08.12.2015.
 */
@Test
public class PriceApiServiceTests {
    private String token;
    private String baseAddress = "";

    @BeforeClass
    public void setupSecurityToken() {
        setupSystemIpConfig();
        // Define relaxed SSL Auth, since we use self-signed certificates
        RestAssured.useRelaxedHTTPSValidation();
        // Register JSON Parser for plain text responses
        RestAssured.registerParser("text/plain", Parser.JSON);
        // Order and extract
        token =
                given().
                        param("grant_type", "password").
                        param("client_id", "acme").
                        param("username", "user").
                        param("password", "password").
                        when().post("https://acme:acmesecret@" + baseAddress + ":9999/uaa/oauth/token").then().
                        extract().path("access_token");

    }

    private void setupSystemIpConfig() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0 || os.indexOf("mac") >= 0) {
            baseAddress = "192.168.99.100";
        } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0) {
            baseAddress = "127.0.0.1";
        }
    }

    @Test
    public void TestPriceRestGet() {
        UUID flightId = UUID.fromString("9aacad96-6730-4443-b6f6-33325b00ce39");
        given()
                .auth()
                .oauth2(token)
                .when()
                .get("https://" + baseAddress + "/api/price/" + flightId)
                .then()
                //OF TODO https://github.com/jayway/rest-assured/wiki/Usage#note-on-floats-and-doubles
                //OF we have to compare with float instead of double
                .body("basicPrice",equalTo(2000.0f));
    }
}
