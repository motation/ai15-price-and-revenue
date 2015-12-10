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
@Test
public class ReservationApiServiceCDT {
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
    public void TestGetReservationForFlights(){
//        given().
//                // use auth token for oauth v2 header
//                        auth().oauth2(token).
//                // make call to API
//                        when().get("https://" + apiServiceBaseURL + "/api/api/flight")
//                .then()
//                .body("size()", greaterThanOrEqualTo(0));


//      TODO - fraglich ob das so läuft
        String urlId = "http://localhost:8080/api/flights";
        String jsonId = given().get(urlId).getBody().print();

        JsonParser parser = new JsonParser();
        JsonArray arr = parser.parse(jsonId).getAsJsonArray();
        JsonElement jsonElement = arr.get(0);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        UUID testId = UUID.fromString(jsonObject.get("flightId").getAsString());


//      TODO - Für welche FlightId sollen die Reservierungen angefragt werden??
        UUID id = UUID.fromString("76fc2e8d-7587-4a18-b2f8-33eeda154b8b");

        String url = "http://localhost:8080/api/tickets/flight/";

//      TODO - hier muss die Id ggf. gewechselt werden
        String json = given().get(url+testId).getBody().print();

//      Prüft die Länge des Arrays mit Flügen >= 1
        given().get(url).then().body("size()", greaterThan(0));

//      Prüfen ob TicketId !=null ist
        given().get(url).then().body("get(0).ticketId", notNullValue());

//      Prüfen ob TicketId ist instanceOf Integer
        given().get(url).then().body("get(0).ticketId", instanceOf(Integer.class));

//      Prüfen ob passengerArray !=null ist
        given().get(url).then().body("get(0).passenger", notNullValue());

//      Prüfen ob reservationArray !=null ist
        given().get(url).then().body("get(0).reservation", notNullValue());

//      Prüfen ob in Reservation eine Buchungsart vorhanden ist
        given().get(url).then().body("get(0).reservation.reservationType", notNullValue());

//      Prüft ob ReservationType vom Typ "Eco", "Busi" oder "First" ist
//        given().get(url).then().body("get(0).reservation.reservationType", contains("Economy") || contains("Business"));

//      Prüfen ob in Reservation die ReservationId ist instanceOf Integer
        given().get(url).then().body("get(0).reservation.reservationId", instanceOf(Integer.class));

//      Prüfen ob Bookingtype !=null ist
        given().get(url).then().body("get(0).bookingType", notNullValue());
//      Prüfen ob TicketDate !=null ist
        given().get(url).then().body("get(0).ticketDate.length()", greaterThan(0));


//      Prüfen ob in Reservation CheckedIn ist instanceOf Boolean
//        given().get(url).then().body("get(0).reservation.checkedIn", instanceOf(Boolean.class));
    }

}
