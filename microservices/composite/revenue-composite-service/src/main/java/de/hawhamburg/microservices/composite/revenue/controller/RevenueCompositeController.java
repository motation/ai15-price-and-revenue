package de.hawhamburg.microservices.composite.revenue.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<CalculatedRevenue> getRevenue(@PathVariable final UUID flightId){
        //OF TODO refactor this and use business layer to create the result
        ResponseEntity<CalculatedPrice> priceResult = revenueCompositeIntegration.getCalculatedPrice(flightId);
        ResponseEntity<Revenue> revenue = revenueCompositeIntegration.getRevenue(flightId);
        return utils.createOkResponse(new CalculatedRevenue(priceResult.getBody(),revenue.getBody()));
    }

    @RequestMapping(value = "/updateStatistic", method = RequestMethod.GET)
    public void updateStatistic(){

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

        JsonArray allFlightIdsArray = revenueCompositeIntegration.getAllFlightsFromReservation();

        if(allFlightIdsArray.size() >= 1) {

            for (JsonElement jsonElement : allFlightIdsArray) {

                JsonObject jsonFlight = jsonElement.getAsJsonObject();
                UUID tempId = UUID.fromString(jsonFlight.get("flightID").getAsString());

                JsonArray allTicketsForFlightID = revenueCompositeIntegration.getTicket(tempId);

                ResponseEntity<CalculatedPrice> priceResult = revenueCompositeIntegration.getCalculatedPrice(tempId);

                if(allTicketsForFlightID.size() >= 1){

                    boolean internet = false;
                    boolean counter = false;
                    boolean agency = false;
                    boolean staff = false;
                    boolean economy_class = false;
                    boolean business_class = false;
                    boolean first_class = false;

                    JsonObject jsonTicket = jsonElement.getAsJsonObject();
                    String bookingType = jsonTicket.get("bookingType").getAsString();

                    switch(bookingType) {
                        case "BOOKING_TYPE_INTERNET" : internet = true;
                        case "BOOKING_TYPE_COUNTER" : counter = true;
                        case "BOOKING_TYPE_AGENCY" : agency = true;
                        case "BOOKING_TYPE_STAFF" : staff = true;
                    }

                    JsonArray allReservations = jsonTicket.get("reservations").getAsJsonArray();

                    if(allReservations.size() >= 1) {

                        for(JsonElement jsonElement1 : allReservations) {

                            JsonObject reservation = jsonElement1.getAsJsonObject();
                            String reservationType = reservation.get("Classes").getAsString();

                            switch(reservationType) {
                                case "ECONOMY_CLASS" : economy_class = true;
                                case "BUSINESS_CLASS" : business_class = true;
                                case "FIRST_CLASS" : first_class = true;
                            }

                           if(internet == true && economy_class == true) soldTicketsEconomyClassInternet += 1;
                           if(internet == true && business_class == true) soldTicketsBusinessClassInternet += 1;
                           if(internet == true && first_class == true) soldTicketsFirstClassInternet += 1;
                           if(counter == true && economy_class == true) soldTicketsEconomyClassCounter += 1;
                           if(counter == true && business_class == true) soldTicketsBusinessClassCounter += 1;
                           if(counter == true && first_class == true) soldTicketsFirstClassCounter += 1;
                           if(agency == true && economy_class == true) soldTicketsEconomyClassTravelOffice += 1;
                           if(agency == true && business_class == true) soldTicketsBusinessClassTravelOffice += 1;
                           if(agency == true && first_class == true) soldTicketsFirstClassTravelOffice += 1;
                           if(staff == true && economy_class == true) soldTicketsEconomyClassStaff += 1;
                           if(staff == true && business_class == true) soldTicketsBusinessClassStaff += 1;
                           if(staff == true && first_class == true) soldTicketsFirstClassStaff += 1;

                        }
                    }
                }
//          TODO - Speicherung des einzelnen CalcRevenue
                Revenue newRevenue = new Revenue.RevenueBuilder()
                        .withFlightId(tempId)
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

                revenueCompositeIntegration.saveRevenue(newRevenue);
            }
        }
//        TODO - eigentlicher return-Wert???
    }

}
