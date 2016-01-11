package de.hawhamburg.microservices.composite.revenue.service;

import de.hawhamburg.microservices.composite.revenue.model.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.callista.microservices.util.ServiceUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RevenueCompositeIntegrationTest {

    @InjectMocks
    private RevenueCompositeIntegration revenueCompositeIntegration;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ServiceUtils utils;


    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRevenue() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(flightId).withSoldTicketsBusinessClassInternet(200).build();

        ResponseEntity<Revenue> revenueResponseEntity = new ResponseEntity<Revenue>(revenue, HttpStatus.OK);

        String url = "http://localhost:8080/revenue/"+flightId;

        Mockito.when(utils.getServiceUrl("revenue")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.getForEntity(url,Revenue.class)).thenReturn(revenueResponseEntity);
        Mockito.when(utils.createOkResponse(revenue)).thenReturn(new ResponseEntity<Revenue>(revenue,HttpStatus.OK));

        ResponseEntity<Revenue> responseEntity = revenueCompositeIntegration.getRevenue(flightId);
        Revenue revenueToCheck = responseEntity.getBody();

        Assert.assertEquals(revenueToCheck.getSoldTicketsBusinessClassInternet(), 200.0);
    }



    @Test
    public void testUpdateStatistic() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
//      FlugObjekt anlegen
        Flight resonseFlight = new Flight(flightId, false);

//      TODO - Ticket 1 anlegen - INTERNET + Economy

        List<Reservation> responseReservationArray1 = new ArrayList<Reservation>();

//      ---------------------------- 1. Reservierung anlegen ------------------------------------
        UUID passengerId1 = UUID.randomUUID();
        UUID reservationId1_1 = UUID.randomUUID();
        Passenger passenger1_1 = new Passenger(passengerId1, "Wolf-Dieter");
        UUID placeId1_1 = UUID.randomUUID();
//        UUID placeId, int floor, int row, String seat, String placeType)
        Place place1_1 = new Place(placeId1_1, 1, 1, "Innen", "Sessel");
//        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
        Reservation reservation1_1 = new Reservation(reservationId1_1, false, flightId, passenger1_1, place1_1,"ECONOMY");
//      ---------------------------- 2. Reservierung anlegen ------------------------------------
        UUID passengerId2 = UUID.randomUUID();
        UUID reservationId1_2 = UUID.randomUUID();
        Passenger passenger1_2 = new Passenger(passengerId2, "Günther");
        UUID placeId1_2 = UUID.randomUUID();
//        UUID placeId, int floor, int row, String seat, String placeType)
        Place place1_2 = new Place(placeId1_2, 1, 1, "Außen", "Couch");
//        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
        Reservation reservation1_2 = new Reservation(reservationId1_2, false, flightId, passenger1_2, place1_2,"BUSINESS");
//      ---------------------------- 3. Reservierung anlegen ------------------------------------
        UUID passengerId3 = UUID.randomUUID();
        UUID reservationId1_3= UUID.randomUUID();
        Passenger passenger1_3 = new Passenger(passengerId3, "Klaus");
        UUID placeId1_3 = UUID.randomUUID();
//        UUID placeId, int floor, int row, String seat, String placeType)
        Place place1_3 = new Place(placeId1_3, 1, 1, "Mitte", "Tragfläche");
//        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
        Reservation reservation1_3 = new Reservation(reservationId1_3, false, flightId, passenger1_3, place1_3,"FIRST");
//      ---------------------------- Reservierungen hinzugfügen ------------------------------------

        responseReservationArray1.add(reservation1_1);
        responseReservationArray1.add(reservation1_2);
        responseReservationArray1.add(reservation1_3);

//      ----------------------------- 1. Ticket anlegen -----------------------------------------
        UUID ticketId1 = UUID.randomUUID();
        UUID ticketId2 = UUID.randomUUID();
        UUID ticketId3 = UUID.randomUUID();
        UUID ticketId4 = UUID.randomUUID();

//        UUID ticketID, Passenger passenger, List<Reservation> reservations, String bookingType, String ticketDate
        Ticket responseTicket1 = new Ticket(ticketId1, passenger1_1, responseReservationArray1, "INTERNET", "01-01-2015");
        Ticket responseTicket2 = new Ticket(ticketId2, passenger1_2, responseReservationArray1, "AGENCY", "01-01-2015");
        Ticket responseTicket3 = new Ticket(ticketId3, passenger1_3, responseReservationArray1, "STAFF", "01-01-2015");
        Ticket responseTicket4 = new Ticket(ticketId4, passenger1_1, responseReservationArray1, "COUNTER", "01-01-2015");

//      ---------------------------- Tickets hinzugfügen ------------------------------------
        Ticket[] ticketList = new Ticket[4];
        ticketList[0] = responseTicket1;
        ticketList[1] = responseTicket2;
        ticketList[2] = responseTicket3;
        ticketList[3] = responseTicket4;

//
        ResponseEntity<Ticket[]> ticketsResponseEntity = new ResponseEntity<Ticket[]>(ticketList, HttpStatus.OK);

        String url = "http://localhost:8080/reservation/api/tickets/flight/"+flightId;

        Mockito.when(utils.getServiceUrl("reservation")).thenReturn(new URI("http://localhost:8080/"));
        Mockito.when(restTemplate.getForEntity(url,Ticket[].class)).thenReturn(ticketsResponseEntity);
        Mockito.when(utils.createOkResponse(ticketList)).thenReturn(new ResponseEntity<Ticket[]>(ticketList, HttpStatus.OK));

        ResponseEntity<Ticket[]> responseEntityTickets = revenueCompositeIntegration.getTicketsFromReservation(flightId);
        ResponseEntity<Revenue[]> responseEntityRevenuesArray = revenueCompositeIntegration.updateStatistic();
        List<Revenue> responseEntityRevenueList = revenueCompositeIntegration.convertRevenue(responseEntityRevenuesArray.getBody());
//        Ticket ticketToCheck = responseEntityTickets.getBody()[0];

        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassInternet(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassInternet(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassInternet(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassTravelOffice(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassTravelOffice(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassTravelOffice(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassCounter(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassCounter(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassCounter(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassStaff(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassStaff(), 1);
        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassStaff(), 1);
}

}
