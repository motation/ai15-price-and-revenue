package de.hawhamburg.microservices.composite.price.service;

import de.hawhamburg.microservices.composite.price.model.Flight;
import de.hawhamburg.microservices.composite.price.model.FlightBlueprint;
import de.hawhamburg.microservices.composite.price.model.Price;
import de.hawhamburg.microservices.composite.price.util.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import java.net.URI;
import java.util.UUID;

/**
 * Created by Ole on 07.11.2015.
 */
@Service
public class PriceCompositeIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(PriceCompositeIntegration.class);

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private RestTemplate restTemplate;

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<Price> getPrice(UUID flightID) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("price");
        String url = uri.toString() + "/price/" + flightID;

        Price price;
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        if (!resultStr.getStatusCode().is2xxSuccessful()) {
            LOG.info("Price need to be calculated");
            price = calculatePriceForFlight(flightID);
            if (price.equals(null)) {
                LOG.info("Something went wrong");
                return utils.createResponse(null, HttpStatus.NOT_FOUND);
            }
            createPrice(price);
            LOG.info("Price " + price.getValue() + " : " + price);
        } else {
            LOG.info("Price found");
            price = ResponseHelper.response2Price(resultStr);
            LOG.info("Price " + price.getValue() + " : " + price);
        }

        ResponseEntity<Price> response = new ResponseEntity<Price>(price, HttpStatus.OK);
        return response;
    }

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<Price> createPrice(Price price) {
        //OF TODO use this later
        LOG.info("create price with flightId " + price.getFlightId());
        URI uri = utils.getServiceUrl("price");
        String url = uri.toString() + "/price";
        ResponseEntity<Price> resultStr = restTemplate.postForEntity(url, price, Price.class);
        return utils.createOkResponse(resultStr.getBody());
    }

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")

    public boolean deletePrice(UUID flightId) {
        //OF TODO implement
        //OF TODO use this later
        URI uri = utils.getServiceUrl("price");
        String url = uri.toString() + "/price";
        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public boolean putPrice(Price price) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("price");
        String url = uri.toString() + "/price";
        try {
            restTemplate.put(url, price);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public ResponseEntity<Price> defaultPrice(UUID flightId) {
        return utils.createResponse(null, HttpStatus.BAD_GATEWAY);
    }


    public Flight getFlightFromFlightOp(UUID flightid) {
        //OF TODO use this later

        //OF for oauth2 secured resources!-->>
        String token = utils.getOauth2Token();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // <---

        URI uri = utils.getServiceUrl("flightopapi");
        String url = uri.toString() + "/api/flight/" + flightid;
        ResponseEntity<Flight> resultStr = restTemplate.exchange(url, HttpMethod.GET,entity,Flight.class);
        Flight flight = resultStr.getBody();
        return flight;
    }

    //Price calculation using duration of the flight
    //(duration in hours? Duration type changed from Date to String, according yaml)
    public Price calculatePriceForFlight(UUID flightid) {
        Double price = null;
        Flight flight = getFlightFromFlightOp(flightid);
        FlightBlueprint flightBlueprint = flight.getBlueprint();
        String duration = flightBlueprint.getDuration();
        if (!duration.equals(null)) {
            double durationInHours = Double.valueOf(duration);
            double priceForOneHour = 40.0;
            price = durationInHours * priceForOneHour;
        }

        Price result;
        if (!price.equals(null)) {
            result = new Price.PriceBuilder().withFlightId(flightid).withValue(price).build();
        } else {
            result = null;
        }
        return result;
    }
}
