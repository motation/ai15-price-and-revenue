package de.hawhamburg.microservices.composite.price.controller;

import de.hawhamburg.microservices.composite.price.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.price.model.Price;
import de.hawhamburg.microservices.composite.price.service.PriceCompositeIntegration;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 27.10.15.
 */

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class PriceCompositeController {

    private static final Logger LOG = LoggerFactory.getLogger(PriceCompositeController.class);

    @Autowired
    private PriceCompositeIntegration priceCompositeIntegration;

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/price/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<CalculatedPrice> getPrice(@PathVariable final UUID flightId) {
        ResponseEntity<Price> priceResult = priceCompositeIntegration.getPrice(flightId);
//                if(!priceResult.getStatusCode().is2xxSuccessful()){
//            Price price = priceCompositeIntegration.calculatePriceForFlight(flightId);
////            TODO
////            price composite integration anschauen, revenue composite greift auf price zu, auch anschauen
////            calculate from flightop api
////            new price
////            priceCompositeIntegration.createPrice()
////                    return priceCompositeIntegration
////            core service anschauen
//            if (price.equals(null)) {
//                return utils.createResponse(null,priceResult.getStatusCode());
//            }
//            return utils.createOkResponse(new CalculatedPrice(price));
//        }
        return utils.createOkResponse(new CalculatedPrice(priceResult.getBody()));
    }


    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public ResponseEntity<CalculatedPrice> createPrice(@RequestBody final Price price) {
        ResponseEntity<Price> priceResult = priceCompositeIntegration.createPrice(price);
        if (!priceResult.getStatusCode().is2xxSuccessful()) {
            return utils.createResponse(null, priceResult.getStatusCode());
        }
        return utils.createOkResponse(new CalculatedPrice(priceResult.getBody()));
    }

    @RequestMapping(value = "/price/{flightID}", method = RequestMethod.DELETE)
    public void deletePrice(@RequestBody final UUID flightID, HttpServletResponse response) {
        try {
            priceCompositeIntegration.deletePrice(flightID);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/price", method = RequestMethod.PUT)
    public void putPrice(@RequestBody final Price price, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            priceCompositeIntegration.putPrice(price);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/test/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<String> testApiCall(@PathVariable final UUID flightId) {
        LOG.info("Got a request to /test/" + flightId);
        LOG.info("Now trying to make API Call");
        String url = utils.getServiceUrl("priceapi").toString();
        url += "/" + flightId;
        LOG.info("API URL IS: " + url);

        //OF for oauth2 secured resources!-->>
        String token = utils.getOauth2Token();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        //<----
        LOG.info("Token is: " + token);
        ResponseEntity<String> result = null;
        try {
            result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            LOG.info("RESTTEMPLATE ERROR:" + e.getMessage());
        }
        return result;
    }
}
