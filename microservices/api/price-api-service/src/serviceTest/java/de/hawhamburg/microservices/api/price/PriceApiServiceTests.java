package de.hawhamburg.microservices.api.price;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.ResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Ole on 08.12.2015.
 */
@Test
public class PriceApiServiceTests {
    public static final String LOCALHOST = "localhost";
    private String token;
    private String baseAddress = "";

    @BeforeClass
    public void setupSecurityToken() {
        sslTrustFake();
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

    private void sslTrustFake() {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        return true;
                    }
                });

        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {

                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLContext.setDefault(ctx);
        } catch (Exception ex) {
            ex.printStackTrace();
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
                .body("basicPrice", equalTo(2000.0f));
    }

    @Test
    public void testPriceToken() {
        //OF DONT REMOVE
        UUID flightId = UUID.fromString("9aacad96-6730-4443-b6f6-33325b00ce39");
        String uri = "https://" + baseAddress + "/api/price/" + flightId;
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = rest.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @Test
    public void testStatistic() {
        Date start = new Date(1452759122000L);
        Date end = new Date(1452759185000L);
        String fromDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
        String toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
        System.out.println(fromDate);
        System.out.println(toDate);

        String url = "https://" + baseAddress + "/api/price/statistic?fromDate=" +fromDate+"&toDate="+toDate;
        ResponseBody body = given()
                .auth()
                .oauth2(token)
                .when()
                .get(url)
                .getBody();
        body.prettyPrint();
    }
}
