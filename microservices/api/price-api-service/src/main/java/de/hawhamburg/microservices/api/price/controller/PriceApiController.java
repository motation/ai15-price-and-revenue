package de.hawhamburg.microservices.api.price.controller;

import de.hawhamburg.microservices.composite.revenue.model.CalculatedRevenue;
import de.hawhamburg.microservices.core.price.jpa.domain.Price;
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
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Ole on 08.11.2015.
 */
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class PriceApiController {

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

    @RequestMapping(value="/revenue/{flightId}")
    public ResponseEntity<String> getRevenue(@PathVariable final UUID flightID){
        URI uri = loadBalancer.choose("revenuecomposite").getUri();
        String url = uri.toString() + "/revenue/" + flightID;
        return utils.createResponse(restTemplate.getForEntity(url,String.class));
    }

}
