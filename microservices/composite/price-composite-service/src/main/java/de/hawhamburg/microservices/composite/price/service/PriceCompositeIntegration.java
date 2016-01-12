package de.hawhamburg.microservices.composite.price.service;

import de.hawhamburg.microservices.composite.price.model.Flight;
import de.hawhamburg.microservices.composite.price.model.FlightBlueprint;
import de.hawhamburg.microservices.composite.price.model.Price;
import de.hawhamburg.microservices.composite.price.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
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
public class PriceCompositeIntegration {

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<Price> getPrice(UUID flightID) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("price");
        String url = uri.toString() + "/price/" + flightID;

        //OF TODO remove this later!
//        String urlToPriceService = "http://localhost:8080";
//        String url =urlToPriceService + "/price/" + flightID;
        Price price;
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        if (!resultStr.getStatusCode().is2xxSuccessful()) {
            System.out.println("Price need to be calculated");
            price = calculatePriceForFlight(flightID);
            System.out.println("Price " + price.getValue() + " : " + price);
        } else {
            System.out.println("Price found");
            price = ResponseHelper.response2Price(resultStr);
            System.out.println("Price " + price.getValue() + " : " + price);
        }

        if (price.equals(null)) {
            System.out.println("Something went wrong");
            return utils.createResponse(null, HttpStatus.NOT_FOUND);
        }

        ResponseEntity<Price> response = new ResponseEntity<Price>(price, HttpStatus.OK);
        return response;


    }

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<Price> createPrice(Price price) {
        //OF TODO use this later
//        URI uri = utils.getServiceUrl("price");
//        String url = uri.toString() + "/price";

        //OF TODO remove this later!
        String urlToPriceService = "http://localhost:8080";
        String url = urlToPriceService + "/price";
        ResponseEntity<Price> resultStr = restTemplate.postForEntity(url, price, Price.class);
        return utils.createOkResponse(resultStr.getBody());
    }

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")

    public boolean deletePrice(UUID flightId) {
        //OF TODO implement
        //OF TODO use this later
//        URI uri = utils.getServiceUrl("price");
//        String url = uri.toString() + "/price";

        //OF TODO remove this later!
        String urlToPriceService = "http://localhost:8080";
        String url = urlToPriceService + "/price/" + flightId;
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
//        URI uri = utils.getServiceUrl("price");
//        String url = uri.toString() + "/price";

        //OF TODO remove this later!
        String urlToPriceService = "http://localhost:8080";
        String url = urlToPriceService + "/price";
        try {
            restTemplate.put(url, price);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public ResponseEntity<Price> defaultProduct(UUID flightId) {
        return utils.createResponse(null, HttpStatus.BAD_GATEWAY);
    }


    public Flight getFlightFromFlightOp(UUID flightid) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("flightOp");
        String url = uri.toString() + "/api/flight/" + flightid;
        ResponseEntity<Flight> resultStr = restTemplate.getForEntity(url, Flight.class);
        Flight flight = resultStr.getBody();
        return flight;
    }

//    public UUID getFlightDepartureFromFlightOp(UUID flightid){
//        Flight flight = getFlightFromFlightOp(flightid);
//        UUID departure = flight.getBlueprint().getDeparture();
//        return departure;
//    }
//
//    public UUID getFlightDestinationFromFlightOp(UUID flightid){
//        Flight flight = getFlightFromFlightOp(flightid);
//        UUID destination = flight.getBlueprint().getDestination();
//        return destination;
//    }

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

//        //Price calculation using departure and destination
//        UUID deperture = getFlightDepartureFromFlightOp(flightid);
//        UUID destination = getFlightDestinationFromFlightOp(flightid);
//        String dep = deperture.toString();
//        switch(dep) {
//            case "08388730-b705-11e5-a837-0800200c9a66" : price = flightFromHamburg(destination);
//                break;
//            case "a00dd206-e496-4a6d-905d-db4ec2c5efef" : price = flightFromBerlin(destination);
//                break;
//            case "4a9ec8b0-19de-4eb7-b75c-dc16df57e8e3" : price = flightFromBremen(destination);
//                break;
//            default : price = null;
//                break;
//        }
//        Price result;
//        if (!price.equals(null)) {
//            result = new Price.PriceBuilder().withFlightId(flightid).withValue(price).build();
//        } else {
//            result = null;
//        }
//        return result;
    }

//    private Double flightFromHamburg(UUID destination) {
//        Double price = null;
//        String dest = destination.toString();
//        switch(dest) {
//            case "a00dd206-e496-4a6d-905d-db4ec2c5efef" : price = 100.0;
//                break;
//            case "4a9ec8b0-19de-4eb7-b75c-dc16df57e8e3" : price = 80.0;
//                break;
//            default : price = null;
//        }
//        return price;
//    }
//
//    private Double flightFromBerlin(UUID destination) {
//        Double price = null;
//        String dest = destination.toString();
//        switch(dest) {
//            case "08388730-b705-11e5-a837-0800200c9a66" : price = 100.0;
//                break;
//            case "4a9ec8b0-19de-4eb7-b75c-dc16df57e8e3" : price = 70.0;
//                break;
//            default : price = null;
//                break;
//        }
//        return price;
//    }
//
//    private Double flightFromBremen(UUID destination) {
//        String dest = destination.toString();
//        Double price;
//        switch(dest) {
//            case "08388730-b705-11e5-a837-0800200c9a66" : price = 80.0;
//                break;
//            case "a00dd206-e496-4a6d-905d-db4ec2c5efef" : price = 70.0;
//                break;
//            default : price = null;
//                break;
//        }
//        return price;
//    }
}
