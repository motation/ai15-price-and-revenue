package de.hawhamburg.microservices.composite.revenue.controller;

import de.hawhamburg.microservices.composite.price.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.revenue.model.CalculatedRevenue;
import de.hawhamburg.microservices.composite.revenue.service.RevenueCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.callista.microservices.util.ServiceUtils;

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
public class RevenueCompositeController {
    @Autowired
    private RevenueCompositeIntegration revenueCompositeIntegration;

    @Autowired
    private ServiceUtils utils;

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<CalculatedRevenue> getRevenue(@PathVariable final UUID flightId){
        ResponseEntity<CalculatedPrice> priceResult = revenueCompositeIntegration.getCalculatedPrice(flightId);
        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody()));
    }

//
//    @RequestMapping(value = "/price", method = RequestMethod.POST)
//    public ResponseEntity<CalculatedRevenue> createPrice(@RequestBody final Price price){
//        ResponseEntity<Price> priceResult = revenueCompositeIntegration.createPrice(price);
//        if(!priceResult.getStatusCode().is2xxSuccessful()){
//            return utils.createResponse(null,priceResult.getStatusCode());
//        }
//        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody()));
//    }
//
//    @RequestMapping(value = "/price", method = RequestMethod.DELETE)
//    public ResponseEntity<CalculatedRevenue> deletePrice(@RequestBody final Price price){
//        ResponseEntity<Price> priceResult = revenueCompositeIntegration.deletePrice(price);
//        if(!priceResult.getStatusCode().is2xxSuccessful()){
//            return utils.createResponse(null,priceResult.getStatusCode());
//        }
//        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody()));
//    }
//
//    @RequestMapping(value = "/price", method = RequestMethod.PATCH)
//    public ResponseEntity<CalculatedRevenue> updatePrice(@RequestBody final Price price){
//        ResponseEntity<Price> priceResult = revenueCompositeIntegration.patchPrice(price);
//        if(!priceResult.getStatusCode().is2xxSuccessful()){
//            return utils.createResponse(null,priceResult.getStatusCode());
//        }
//        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody()));
//    }
//
//    @RequestMapping(value = "/price", method = RequestMethod.PUT)
//    public ResponseEntity<CalculatedRevenue> update2Price(@RequestBody final Price price){
//        ResponseEntity<Price> priceResult = revenueCompositeIntegration.putPrice(price);
//        if(!priceResult.getStatusCode().is2xxSuccessful()){
//            return utils.createResponse(null,priceResult.getStatusCode());
//        }
//        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody()));
//    }
}
