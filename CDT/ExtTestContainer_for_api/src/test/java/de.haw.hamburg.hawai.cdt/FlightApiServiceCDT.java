package de.haw.hamburg.hawai.cdt;

import com.google.gson.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import com.sun.org.apache.xpath.internal.operations.NotEquals;
import org.hamcrest.core.IsNull;
import org.hamcrest.text.IsEmptyString;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.UUID;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.number.OrderingComparison.greaterThan;

/**
 * Created by unknown on 02.12.15.
 */
@Test
public class FlightApiServiceCDT  {
    private String token;

    /**
     * The URL under which the Service is accessable.
     * On pure-local deployments this is usually 192.168.99.100
     * when Running Docker on OS X or Windows.
     * Change to 127.0.0.1 when using Linux.
     * Change to the real IP Address if using a production like environment.
     */
    private String apiServiceBaseURL = "127.0.0.1";

    public static void main(String[] args) {
    }

    @BeforeClass
    public void setupSecurityToken() {
        // Define relaxed SSL Auth, since we use self-signed certificates
        RestAssured.useRelaxedHTTPSValidation();
        // Register JSON Parser for plain text responses
        RestAssured.registerParser("text/plain", Parser.JSON);
        // Order and extract auth-token
//        token =
//                given().
//                        param("grant_type", "password").
//                        param("client_id", "acme").
//                        param("username", "user").
//                        param("password", "password").
//                        when().post("https://acme:acmesecret@" + apiServiceBaseURL + ":9999/uaa/oauth/token").then().
//                        extract().path("access_token");
    }

    @Test
    public void TestGetWantedFlights() {
//        given().
//                // use auth token for oauth v2 header
//                        auth().oauth2(token).
//                // make call to API
//                        when().get("https://" + apiServiceBaseURL + "/api/api/flight")
//                .then()
//                .body("size()", greaterThanOrEqualTo(0));


//        {flight:{delay:"bla", startTime:"11:00", }

        String url = "http://localhost:8080/api/api/flight";

        String json = given().get(url).getBody().print();

//      TODO - ggf. müssen die Zugriffe anhand des JsonObjektes angepasst werden z.B. flight.get(0)....

//      Prüft die Länge des Arrays mit Flügen >= 1
        given().get(url).then().body("size()", greaterThan(0));
//      Prüfen ob Delay !=null ist
        given().get(url).then().body("get(0).delay", notNullValue());
//      Prüft die Länge der FlightId(36)
        given().get(url).then().body("get(0).blueprint.flightnumber.length()", equalTo(36));
//      Prüft ob Flightnumber eine UUID ist
        given().get(url).then().body("get(0).blueprint.flightnumber", instanceOf(UUID.class));
//      Prüfen ob Startzeit !=null ist
        given().get(url).then().body("get(0).startTime", notNullValue());
//      Prüft die Länge der AircraftId(36)
        given().get(url).then().body("get(0).aircraft.length()", equalTo(36));
//      Prüft ob AircraftId eine UUID ist
        given().get(url).then().body("get(0).aircraft", instanceOf(UUID.class));
//      Prüfen ob Departure !=null ist
        given().get(url).then().body("get(0).blueprint.departure", notNullValue());
//      Prüfen ob Destination !=null ist
        given().get(url).then().body("get(0).blueprint.destination", notNullValue());
//      Prüfen ob Duration !=null ist
        given().get(url).then().body("get(0).blueprint.duration", notNullValue());
//      Prüfen ob Aircraftmodel !=null ist
        given().get(url).then().body("get(0).blueprint.aircraftmodel", notNullValue());

    }

//    @Test
//    public void TestFlightsRestGet() {
////        given().
////                // use auth token for oauth v2 header
////                        auth().oauth2(token).
////                // make call to API
////                        when().get("https://" + apiServiceBaseURL + "/api/api/flight")
////                .then()
////                .body("size()", greaterThanOrEqualTo(0));
//        String url = "http://localhost:8080/prices";
//        given().get(url).then().body("size()", greaterThanOrEqualTo(0));
//        given().get(url).then().body("list.get(0).flightId", equalTo("76fc2e8d-7587-4a18-b2f8-33eeda154b8b"));
//        String json = given().get(url).getBody().print();
//        System.out.println(json);
//
//        JsonParser parser = new JsonParser();
//        JsonArray arr = parser.parse(json).getAsJsonArray();
//        for(JsonElement jsonElement : arr){
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            System.out.println(jsonObject.get("flightId").getAsString());
//        }
//    }






//    @Test
//    public void TestGetWantedFlights() {
////        given().
////                // use auth token for oauth v2 header
////                        auth().oauth2(token).
////                // make call to API
////                        when().get("https://" + apiServiceBaseURL + "/api/api/flight")
////                .then()
////                .body("size()", greaterThanOrEqualTo(0));
//
//
////        {flight:{delay:"bla", startTime:"11:00", }
//
//        String url = "http://localhost:8080/api/api/flight";
//
//        String json = given().get(url).getBody().print();
//
////      TODO - ggf. müssen die Zugriffe anhand des JsonObjektes angepasst werden z.B. flight.get(0)....
//
////      Prüft die Länge des Arrays mit Flügen >= 1
//        given().get(url).then().body("size()", greaterThan(0));
////      Prüft die Stringlänge des Delay
//        given().get(url).then().body("get(0).delay.length()", greaterThanOrEqualTo(0));
////      Prüfen ob Delay !=null ist
//        given().get(url).then().body("get(0).delay.length()", greaterThanOrEqualTo(0));
////      Prüft die Länge der FlightId(36)
//        given().get(url).then().body("get(0).blueprint.flightnumber.length()", equalTo(36));
////      Prüft die Stringlänge der Startzeit
//        given().get(url).then().body("get(0).startTime.length()", greaterThan(0));
////      Prüft die Länge der AircraftId(36)
//        given().get(url).then().body("get(0).aircraft.length()", equalTo(36));
////      Prüft die Stringlänge der Departure
//        given().get(url).then().body("get(0).blueprint.departure.length()", greaterThanOrEqualTo(0));
////      Prüft die Stringlänge der Destination
//        given().get(url).then().body("get(0).blueprint.destination.length()", greaterThanOrEqualTo(0));
////      Prüft die Stringlänge der Duration
//        given().get(url).then().body("get(0).blueprint.duration.length()", greaterThanOrEqualTo(0));
////      Prüft die Stringlänge des Aircraftmodel
//        given().get(url).then().body("get(0).blueprint.aircraftmodel.length()", greaterThanOrEqualTo(0));
//
//
//
////      Prüft die Stringlänge des Aircraftmodel
//        given().get(url).then().body("get(0).blueprint.aircraftmodel.length()", isEmptyOrNullString());
//        given().get(url).then().body("get(0).blueprint.aircraftmodel.length()", IsEmptyString.isEmptyOrNullString());
//        given().get(url).then().body("get(0).blueprint.aircraftmodel.length()", notNullValue());
//
//    }

//      /api/api/flight
//      getWantedFlights - /flight
//      askForReservations

}
