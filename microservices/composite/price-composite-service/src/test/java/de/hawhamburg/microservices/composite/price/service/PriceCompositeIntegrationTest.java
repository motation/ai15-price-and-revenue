package de.hawhamburg.microservices.composite.price.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.hawhamburg.microservices.composite.price.model.Flight;
import de.hawhamburg.microservices.composite.price.model.FlightBlueprint;
import de.hawhamburg.microservices.composite.price.model.Price;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.callista.microservices.util.ServiceUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by unknown on 02.12.15.
 */
public class PriceCompositeIntegrationTest {

    @Spy
    @InjectMocks
    private PriceCompositeIntegration priceCompositeIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ServiceUtils utils;


    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    //When price exists
    @Test
    public void testGetPrice() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
        Price price = new Price.PriceBuilder().withFlightId(flightId).withValue(200.0).build();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String priceJson = gson.toJson(price);

        ResponseEntity<String> priceResponseEntity = new ResponseEntity<String>(priceJson, HttpStatus.OK);

        String url = "http://localhost:8080/price/"+flightId;

        Mockito.when(utils.getServiceUrl("price")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.getForEntity(url,String.class)).thenReturn(priceResponseEntity);
        Mockito.when(utils.createOkResponse(price)).thenReturn(new ResponseEntity<Price>(price,HttpStatus.OK));
        
        ResponseEntity<Price> responseEntity = priceCompositeIntegration.getPrice(flightId);
        Price priceToCheck = responseEntity.getBody();

        Assert.assertEquals(priceToCheck.getValue(),200.0);
    }

    //When price need to be calculated
    @Test
    public void testCalculatePriceForFlight() throws URISyntaxException {
        // TODO calculate price
        UUID flightId = UUID.randomUUID();
        Flight flight = new Flight();
        flight.setId(flightId);
        FlightBlueprint flightBlueprint = new FlightBlueprint();
        flightBlueprint.setDuration("90");
        flight.setBlueprint(flightBlueprint);

        Mockito.doReturn(flight).when(priceCompositeIntegration).getFlightFromFlightOp(flightId);

        Price price = priceCompositeIntegration.calculatePriceForFlight(flightId);
        Assert.assertEquals(price.getValue(),3600.0);
    }

    @Test
    public void testGetFlightFromFlightOp() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
        URI uri = new URI("http://localhost:8080");
        String url = uri.toString() + "/api/flight/" + flightId;
        Mockito.when(utils.getServiceUrl("flightopapi")).thenReturn(uri);
        Flight flight = new Flight();
        flight.setId(flightId);

        Mockito.when(utils.getOauth2Token()).thenReturn("123");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "123");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Flight> resultStr = new ResponseEntity<Flight>(flight,HttpStatus.OK);
        Mockito.when(restTemplate.exchange(url, HttpMethod.GET,entity,Flight.class)).thenReturn(resultStr);
        Flight flightToTest = priceCompositeIntegration.getFlightFromFlightOp(flightId);
        Assert.assertEquals(flightToTest.getId(),flightId);
    }

    @Test
    public void testCreatePrice() throws URISyntaxException {
        UUID uuid = UUID.randomUUID();
        Price price = new Price.PriceBuilder().withFlightId(uuid).withValue(200.0).build();
        String urlToPriceService = "http://localhost:8080";
        String url =urlToPriceService + "/price";
        ResponseEntity<Price> resultStr = new ResponseEntity<>(price, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(url, price, Price.class)).thenReturn(resultStr);
        Mockito.when(utils.createOkResponse(price)).thenReturn(new ResponseEntity<>(price,HttpStatus.OK));
        URI uri = new URI(urlToPriceService);
        Mockito.when(utils.getServiceUrl("price")).thenReturn(uri);
        ResponseEntity<Price> responseEntity = priceCompositeIntegration.createPrice(price);
        Price priceToCheck = responseEntity.getBody();

        Assert.assertEquals(priceToCheck.getValue(),200.0);
    }

    @Test
    public void testDeletePrice() throws URISyntaxException {
        UUID uuid = UUID.randomUUID();
        Price price = new Price.PriceBuilder().withFlightId(uuid).withValue(200.0).build();

        String urlToPriceService = "http://localhost:8080";
        String url =urlToPriceService + "/price";
        ResponseEntity<Price> resultStr = new ResponseEntity<Price>(price, HttpStatus.OK);
        Mockito.when(restTemplate.postForEntity(url,price,Price.class)).thenReturn(resultStr);
        Mockito.when(utils.createOkResponse(price)).thenReturn(new ResponseEntity<>(price,HttpStatus.OK));
        URI uri = new URI(urlToPriceService);
        Mockito.when(utils.getServiceUrl("price")).thenReturn(uri);
        priceCompositeIntegration.createPrice(price);

        Boolean response = priceCompositeIntegration.deletePrice(uuid);
        Assert.assertTrue(response);
    }

    @Test
    public void testPutPrice() throws URISyntaxException {
        UUID uuid = UUID.randomUUID();
        Price price = new Price.PriceBuilder().withFlightId(uuid).withValue(200.0).build();

        String urlToPriceService = "http://localhost:8080";
        URI uri = new URI(urlToPriceService);
        Mockito.when(utils.getServiceUrl("price")).thenReturn(uri);
        String url = "http://localhost:8080/price";
        Mockito.doNothing().when(restTemplate).put(url,price);

        Boolean response = priceCompositeIntegration.putPrice(price);
        Assert.assertTrue(response);

    }
}
