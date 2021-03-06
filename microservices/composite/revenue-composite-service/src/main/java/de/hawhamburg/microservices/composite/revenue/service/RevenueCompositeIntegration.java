package de.hawhamburg.microservices.composite.revenue.service;

import de.hawhamburg.microservices.composite.revenue.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.callista.microservices.util.ServiceUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ole on 07.11.2015.
 */
@Service
public class RevenueCompositeIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(RevenueCompositeIntegration.class);

    @Autowired
    private ServiceUtils utils;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    //OF TODO add @HystrixCommand(fallbackMethod = "defaultPrice")
    public ResponseEntity<CalculatedPrice> getCalculatedPrice(UUID flightID) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("pricecomposite");
        String url = uri.toString() + "/price/" + flightID;
        ResponseEntity<CalculatedPrice> resultStr = restTemplate.getForEntity(url, CalculatedPrice.class);
        return utils.createOkResponse(resultStr.getBody());
    }

    public ResponseEntity<Revenue> getRevenue(UUID flightID) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("revenue");
        String url = uri.toString() + "/revenue/" + flightID;
        ResponseEntity<Revenue> resultStr = restTemplate.getForEntity(url, Revenue.class);
        return utils.createOkResponse(resultStr.getBody());
    }

    public ResponseEntity<Revenue> saveRevenue(Revenue newRevenue) {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("revenue");
        String url = uri.toString() + "/revenue/" + newRevenue.getFlightId();
        ResponseEntity<Revenue> resultStr = restTemplate.postForEntity(url, newRevenue, Revenue.class);
        Revenue tempRevenue = resultStr.getBody();
        return utils.createOkResponse(tempRevenue);
    }

    public ResponseEntity<Flight[]> getAllFlightsFromFlightOp() {
        //OF TODO use this later
        URI uri = utils.getServiceUrl("flightopapi");
        String url = uri.toString() + "/api/flight";

        //OF for oauth2 secured resources!-->>
        String token = utils.getOauth2Token();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // <---

        ResponseEntity<Flight[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Flight[].class);
        return utils.createOkResponse(responseEntity.getBody());
    }

    public ResponseEntity<Ticket[]> getTicketsFromReservation(UUID flightID) {
//        OF TODO use this later
        URI uri = utils.getServiceUrl("reservationapi");
        String url = uri.toString() + "/api/tickets/flight/" + flightID;

        //OF for oauth2 secured resources!-->>
        String token = utils.getOauth2Token();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        // <---

        ResponseEntity<Ticket[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Ticket[].class);
        return utils.createOkResponse(responseEntity.getBody());
    }

//    public


    public ResponseEntity<Revenue[]> updateStatistic() {


        double soldTicketsFirstClassInternet = 0;
        double soldTicketsBusinessClassInternet = 0;
        double soldTicketsEconomyClassInternet = 0;
        double soldTicketsFirstClassTravelOffice = 0;
        double soldTicketsEconomyClassTravelOffice = 0;
        double soldTicketsBusinessClassTravelOffice = 0;
        double soldTicketsFirstClassCounter = 0;
        double soldTicketsBusinessClassCounter = 0;
        double soldTicketsEconomyClassCounter = 0;
        double soldTicketsEconomyClassStaff = 0;
        double soldTicketsBusinessClassStaff = 0;
        double soldTicketsFirstClassStaff = 0;


        List<Revenue> resultList = new ArrayList<Revenue>();
        ResponseEntity<Flight[]> fl = getAllFlightsFromFlightOp();
        List<Flight> allFlightIdsArray = convertFlight(fl.getBody());

        for (Flight flightObj : allFlightIdsArray) {

            List<Ticket> allTicketsForFlightID = convertTicket(getTicketsFromReservation(flightObj.getId()).getBody());

            for (Ticket ticketObj : allTicketsForFlightID) {

                boolean internet = false;
                boolean counter = false;
                boolean agency = false;
                boolean staff = false;
                boolean economy_class = false;
                boolean business_class = false;
                boolean first_class = false;

                switch (ticketObj.getBookingType()) {
                    case "BOOKING_TYPE_INTERNET":
                        internet = true;
                        break;
                    case "BOOKING_TYPE_COUNTER":
                        counter = true;
                        break;
                    case "BOOKING_TYPE_AGENCY":
                        agency = true;
                        break;
                    case "BOOKING_TYPE_STAFF":
                        staff = true;
                        break;
                    default:
                }

                for (Reservation reservationObj : ticketObj.getReservations()) {

                    switch (reservationObj.getReservationType()) {
                        case "ECONOMY_CLASS":
                            economy_class = true;
                            break;
                        case "BUSINESS_CLASS":
                            business_class = true;
                            break;
                        case "FIRST_CLASS":
                            first_class = true;
                            break;
                        default:
                    }

                    if (internet == true && economy_class == true) soldTicketsEconomyClassInternet += 1;
                    if (internet == true && business_class == true) soldTicketsBusinessClassInternet += 1;
                    if (internet == true && first_class == true) soldTicketsFirstClassInternet += 1;
                    if (counter == true && economy_class == true) soldTicketsEconomyClassCounter += 1;
                    if (counter == true && business_class == true) soldTicketsBusinessClassCounter += 1;
                    if (counter == true && first_class == true) soldTicketsFirstClassCounter += 1;
                    if (agency == true && economy_class == true) soldTicketsEconomyClassTravelOffice += 1;
                    if (agency == true && business_class == true) soldTicketsBusinessClassTravelOffice += 1;
                    if (agency == true && first_class == true) soldTicketsFirstClassTravelOffice += 1;
                    if (staff == true && economy_class == true) soldTicketsEconomyClassStaff += 1;
                    if (staff == true && business_class == true) soldTicketsBusinessClassStaff += 1;
                    if (staff == true && first_class == true) soldTicketsFirstClassStaff += 1;

                }


            }
//              TODO - Speicherung des einzelnen Revenue
            Revenue newRevenue = new Revenue.RevenueBuilder()
                    .withFlightId(flightObj.getId())
                    .withTimestamp(flightObj.getStartTime().getTime())
                    .withsoldTicketsBusinessClassCounter(soldTicketsBusinessClassCounter)
                    .withSoldTicketsBusinessClassInternet(soldTicketsBusinessClassInternet)
                    .withsoldTicketsBusinessClassTravelOffice(soldTicketsBusinessClassTravelOffice)
                    .withSoldTicketsFirstClassCounter(soldTicketsFirstClassCounter)
                    .withSoldTicketsFirstClassInternet(soldTicketsFirstClassInternet)
                    .withSoldTicketsFirstClassTravelOffice(soldTicketsFirstClassTravelOffice)
                    .withSoldTicketsEconomyClassCounter(soldTicketsEconomyClassCounter)
                    .withSoldTicketsEconomyClassInternet(soldTicketsEconomyClassInternet)
                    .withSoldTicketsEconomyClassTravelOffice(soldTicketsEconomyClassTravelOffice)
                    .withsoldTicketsFirstClassStaff(soldTicketsFirstClassStaff)
                    .withsoldTicketsBusinessClassStaff(soldTicketsBusinessClassStaff)
                    .withsoldTicketsEconomyClassStaff(soldTicketsEconomyClassStaff)
                    .build();

            saveRevenue(newRevenue);
            resultList.add(newRevenue);
        }
        Revenue[] resultArray = resultList.toArray(new Revenue[resultList.size()]);
        ResponseEntity<Revenue[]> revenueResultArray = utils.createOkResponse(resultArray);
        return revenueResultArray;
    }


    public static List<Flight> convertFlight(Flight[] arr) {
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            flights.add(arr[i]);
        }
        return flights;
    }

    public static List<Ticket> convertTicket(Ticket[] arr) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            tickets.add(arr[i]);
        }
        return tickets;
    }

    public static List<Revenue> convertRevenue(Revenue[] arr) {
        List<Revenue> revenues = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            revenues.add(arr[i]);
        }
        return revenues;
    }

    public ResponseEntity<CalculatedRevenue[]> getCalculatedRevenuesForTime(long startTime, long endTime) {
        String url = utils.getServiceUrl("revenue") + "/revenues/" + startTime + "/" + endTime;
        ResponseEntity<String> restTest = restTemplate.getForEntity(url,String.class);
        LOG.info("ENTITY is : " + restTest.getBody().toString());
        Revenue[] revenues = restTemplate.getForEntity(url, Revenue[].class).getBody();
        LOG.info("ENTITY[] size is : " + revenues.length);
        CalculatedRevenue[] calculatedRevenues = new CalculatedRevenue[revenues.length];
        for (int i = 0; i < revenues.length; i++) {
            Revenue revenue = revenues[i];
            CalculatedPrice calculatedPrice = getCalculatedPrice(revenue.getFlightId()).getBody();
            calculatedRevenues[i] = new CalculatedRevenue(calculatedPrice, revenue);
        }
        return new ResponseEntity<>(calculatedRevenues, HttpStatus.OK);
    }
}
