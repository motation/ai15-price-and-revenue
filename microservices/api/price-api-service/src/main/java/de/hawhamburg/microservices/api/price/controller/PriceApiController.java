package de.hawhamburg.microservices.api.price.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hawhamburg.microservices.api.price.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.net.URI;
import java.security.Principal;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Ole on 08.11.2015.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class PriceApiController {

    private static final Logger LOG = LoggerFactory.getLogger(PriceApiController.class);

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<String> getPriceComposite(@PathVariable final UUID flightId,
                                                    @RequestHeader(value = "Authorization") String authorizationHeader,
                                                    Principal currentUser) {
        URI uri = loadBalancer.choose("pricecomposite").getUri();
        String url = uri.toString() + "/price/" + flightId;
//        String url = "http://localhost:8081/price/" + flightId;
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        return utils.createResponse(result);
    }

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public ResponseEntity<String> createPrice(@RequestBody final Price price,
                                              @RequestHeader(value = "Authorization") String authorizationHeader,
                                              Principal currentUser) {
        URI uri = loadBalancer.choose("pricecomposite").getUri();
        String url = uri.toString() + "/price";
//        String url = "http://localhost:8081/price";
        ResponseEntity<String> result = restTemplate.postForEntity(url, price, String.class);
        return utils.createResponse(result);
    }

    @RequestMapping(value = "/price/{flightId}", method = RequestMethod.DELETE)
    public void deletePrice(@PathVariable final UUID flightId, HttpServletResponse response,
                            @RequestHeader(value = "Authorization") String authorizationHeader,
                            Principal currentUser) {
        URI uri = loadBalancer.choose("pricecomposite").getUri();
        String url = uri.toString() + "/price/" + flightId;
//        String url = "http://localhost:8081/price/"+flightId;
        try {
            restTemplate.delete(url);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (RestClientException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/price", method = RequestMethod.PUT)
    public void updateCompletePrice(@RequestBody final Price price, HttpServletResponse response,
                                    @RequestHeader(value = "Authorization") String authorizationHeader,
                                    Principal currentUser) {
        URI uri = loadBalancer.choose("pricecomposite").getUri();
        String url = uri.toString() + "/price";
//        String url = "http://localhost:8081/price";
        try {
            restTemplate.put(url, price);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (RestClientException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<String> getRevenue(@PathVariable final UUID flightId,
                @RequestHeader(value = "Authorization") String authorizationHeader,
                Principal currentUser) {
        LOG.debug("D: got a request to revenue + flight id:: " + flightId);
        LOG.info("I: got a request to revenue + flight id:: " + flightId);
        URI uri = loadBalancer.choose("revenuecomposite").getUri();
        String url = uri.toString() + "/revenue/" + flightId;
        return utils.createResponse(restTemplate.getForEntity(url, String.class));
    }

}
