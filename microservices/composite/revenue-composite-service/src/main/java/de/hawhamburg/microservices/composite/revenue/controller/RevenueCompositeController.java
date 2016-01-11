package de.hawhamburg.microservices.composite.revenue.controller;

import de.hawhamburg.microservices.composite.revenue.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.revenue.model.CalculatedRevenue;
import de.hawhamburg.microservices.composite.revenue.model.Revenue;
import de.hawhamburg.microservices.composite.revenue.service.RevenueCompositeIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import javax.annotation.PostConstruct;
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
    private RestTemplate restTemplate;

    @Autowired
    private ServiceUtils utils;

    @PostConstruct
    public void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(1000 * 60 * 2);
                        updateStatistic();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<CalculatedRevenue> getRevenue(@PathVariable final UUID flightId){
        //OF TODO refactor this and use business layer to create the result
        ResponseEntity<CalculatedPrice> priceResult = revenueCompositeIntegration.getCalculatedPrice(flightId);
        ResponseEntity<Revenue> revenue = revenueCompositeIntegration.getRevenue(flightId);
        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody(),revenue.getBody()));
    }

    @RequestMapping(value = "/updateStatistic", method = RequestMethod.GET)
    public void updateStatistic(){
        revenueCompositeIntegration.updateStatistic();

    }

}
