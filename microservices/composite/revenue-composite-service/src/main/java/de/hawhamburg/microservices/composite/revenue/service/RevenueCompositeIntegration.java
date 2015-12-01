package de.hawhamburg.microservices.composite.revenue.service;

import de.hawhamburg.microservices.composite.revenue.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.revenue.model.Revenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import java.net.URI;
import java.util.UUID;

/**
 * Created by Ole on 07.11.2015.
 */
@Service
public class RevenueCompositeIntegration {

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<CalculatedPrice> getCalculatedPrice(UUID flightID){
        //OF TODO use this later
        URI uri = utils.getServiceUrl("pricecomposite");
        String url = uri.toString() + "/price/" + flightID;
        ResponseEntity<CalculatedPrice> resultStr = restTemplate.getForEntity(url,CalculatedPrice.class);
        return utils.createOkResponse(resultStr.getBody());
    }

    public ResponseEntity<Revenue> getRevenue(UUID flightID){
        //OF TODO use this later
        URI uri = utils.getServiceUrl("revenue");
        String url = uri.toString() + "/revenue/" + flightID;
        ResponseEntity<Revenue> resultStr = restTemplate.getForEntity(url, Revenue.class);
        return utils.createOkResponse(resultStr.getBody());
    }
}
