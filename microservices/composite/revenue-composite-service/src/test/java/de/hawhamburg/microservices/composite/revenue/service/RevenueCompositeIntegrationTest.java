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
    public void testGetCalculatedPrice() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
        Price price = new Price.PriceBuilder()
                .withFlightId(flightId)
                .withValue(150)
                .build();
        CalculatedPrice calculatedPrice = new CalculatedPrice(price);

        ResponseEntity<CalculatedPrice> calculatedPriceResponseEntity = new ResponseEntity<CalculatedPrice>(calculatedPrice, HttpStatus.OK);

        String url = "http://localhost:8080/price/" + flightId;

        Mockito.when(utils.getServiceUrl("pricecomposite")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.getForEntity(url,CalculatedPrice.class)).thenReturn(calculatedPriceResponseEntity);
        Mockito.when(utils.createOkResponse(calculatedPrice)).thenReturn(new ResponseEntity<CalculatedPrice>(calculatedPrice,HttpStatus.OK));

        ResponseEntity<CalculatedPrice> responseEntity = revenueCompositeIntegration.getCalculatedPrice(flightId);
        CalculatedPrice calcPriceToCheck = responseEntity.getBody();

        Float delta = 0.0000000000003f;

        Assert.assertEquals(calcPriceToCheck.getBasicPrice(), 150.0, delta);
        Assert.assertEquals(calcPriceToCheck.getBusinessClassPriceByCounter(), 148.0, delta);
        Assert.assertEquals(calcPriceToCheck.getBusinessClassPriceByInternet(), 185.0, delta);
        Assert.assertEquals(calcPriceToCheck.getBusinessClassPriceByStaff(), 129.5, delta);
        Assert.assertEquals(calcPriceToCheck.getBusinessClassPriceByTravelOffice(), 203.5, delta);
        Assert.assertEquals(calcPriceToCheck.getFirstClassPriceByCounter(), 160.0, delta);
        Assert.assertEquals(calcPriceToCheck.getFirstClassPriceByInternet(), 200.0, delta);
        Assert.assertEquals(calcPriceToCheck.getFirstClassPriceByStaff(), 140.0, delta);
        Assert.assertEquals(calcPriceToCheck.getFirstClassPriceByTravelOffice(), 220.0, delta);
        Assert.assertEquals(calcPriceToCheck.getEconomyClassPriceByCounter(), 136.0, delta);
        Assert.assertEquals(calcPriceToCheck.getEconomyClassPriceByInternet(), 170.0, delta);
        Assert.assertEquals(calcPriceToCheck.getEconomyClassPriceByStaff(), 119.0, delta);
        Assert.assertEquals(calcPriceToCheck.getEconomyClassPriceByTravelOffice(), 187.0, delta);
    }


    @Test
    public void testsaveRevenue() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();
        Revenue revenue = new Revenue.RevenueBuilder()
                .withFlightId(flightId)
                .withsoldTicketsBusinessClassCounter(10)
                .withSoldTicketsBusinessClassInternet(20)
                .withsoldTicketsBusinessClassTravelOffice(30)
                .withSoldTicketsFirstClassCounter(40)
                .withSoldTicketsFirstClassInternet(50)
                .withSoldTicketsFirstClassTravelOffice(60)
                .withSoldTicketsEconomyClassCounter(70)
                .withSoldTicketsEconomyClassInternet(80)
                .withSoldTicketsEconomyClassTravelOffice(90)
                .withsoldTicketsFirstClassStaff(100)
                .withsoldTicketsBusinessClassStaff(110)
                .withsoldTicketsEconomyClassStaff(120)
                .build();

        ResponseEntity<Revenue> revenueResponseEntity = new ResponseEntity<Revenue>(revenue, HttpStatus.OK);

        String url = "http://localhost:8080/revenue/"+flightId;

        Mockito.when(utils.getServiceUrl("revenue")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.postForEntity(url, revenue, Revenue.class)).thenReturn(revenueResponseEntity);
        Mockito.when(utils.createOkResponse(revenue)).thenReturn(new ResponseEntity<Revenue>(revenue,HttpStatus.OK));

        ResponseEntity<Revenue> responseEntity = revenueCompositeIntegration.saveRevenue(revenue);
        Revenue revenueToCheck = responseEntity.getBody();

        Assert.assertEquals(revenueToCheck.getSoldTicketsBusinessClassCounter(), 10.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsBusinessClassInternet(), 20.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsBusinessClassTravelOffice(), 30.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsBusinessClassStaff(), 110.0);

        Assert.assertEquals(revenueToCheck.getSoldTicketsFirstClassCounter(), 40.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsFirstClassInternet(), 50.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsFirstClassTravelOffice(), 60.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsFirstClassStaff(), 100.0);

        Assert.assertEquals(revenueToCheck.getSoldTicketsEconomyClassCounter(), 70.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsEconomyClassInternet(), 80.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsEconomyClassTravelOffice(), 90.0);
        Assert.assertEquals(revenueToCheck.getSoldTicketsEconomyClassStaff(), 120.0);

        Assert.assertEquals(revenueToCheck.getFlightId(), revenue.getFlightId());
        Assert.assertEquals(revenueToCheck, revenue);
    }

    @Test
    public void testGetAllFlightsFromReservation() throws URISyntaxException {
        UUID flightId1 = UUID.randomUUID();
        UUID flightId2 = UUID.randomUUID();

        Flight resonseFlight1 = new Flight(flightId1, false);
        Flight resonseFlight2 = new Flight(flightId2, true);

        Flight[] flightArray = new Flight[2];
        flightArray[0] = resonseFlight1;
        flightArray[1] = resonseFlight2;

        ResponseEntity<Flight[]> flightsResponseEntity = new ResponseEntity<Flight[]>(flightArray, HttpStatus.OK);

        String url = "http://localhost:8080/api/flights";

        Mockito.when(utils.getServiceUrl("reservation")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.getForEntity(url,Flight[].class)).thenReturn(flightsResponseEntity);
        Mockito.when(utils.createOkResponse(flightArray)).thenReturn(new ResponseEntity<Flight[]>(flightArray, HttpStatus.OK));

        ResponseEntity<Flight[]> responseEntity = revenueCompositeIntegration.getAllFlightsFromReservation();
        Flight[] flightArrayToCheck = responseEntity.getBody();

        Assert.assertEquals(flightArrayToCheck[0].getFlightId(), flightId1);
        Assert.assertEquals(flightArrayToCheck[0].isCheckInReady(), false);
        Assert.assertEquals(flightArrayToCheck[1].getFlightId(), flightId2);
        Assert.assertEquals(flightArrayToCheck[1].isCheckInReady(), true);
    }

    @Test
    public void testGetTicketsFromReservation() throws URISyntaxException {
        UUID flightId = UUID.randomUUID();

        //      ---------------------------- 1. Reservierung anlegen ------------------------------------
        UUID passengerId1 = UUID.randomUUID();
        UUID reservationId1_1 = UUID.randomUUID();
        Passenger passenger1_1 = new Passenger(passengerId1, "Wolf-Dieter");
        UUID placeId1_1 = UUID.randomUUID();
//        UUID placeId, int floor, int row, String seat, String placeType)
        Place place1_1 = new Place(placeId1_1, 1, 1, "Innen", "Sessel");
//        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
        Reservation reservation1_1 = new Reservation(reservationId1_1, false, flightId, passenger1_1, place1_1,"ECONOMY_CLASS");

        List<Reservation> responseReservationArray1 = new ArrayList<Reservation>();
        responseReservationArray1.add(reservation1_1);

        UUID ticketId1 = UUID.randomUUID();
        Ticket responseTicket1 = new Ticket(ticketId1, passenger1_1, responseReservationArray1, "BOOKING_TYPE_INTERNET", "01-01-2015");

        Ticket[] ticketArray = new Ticket[4];
        ticketArray[0] = responseTicket1;

        ResponseEntity<Ticket[]> ticketsResponseEntity = new ResponseEntity<Ticket[]>(ticketArray, HttpStatus.OK);


        String url = "http://localhost:8080/api/tickets/flight/"+flightId;

        Mockito.when(utils.getServiceUrl("reservation")).thenReturn(new URI("http://localhost:8080"));
        Mockito.when(restTemplate.getForEntity(url,Ticket[].class)).thenReturn(ticketsResponseEntity);
        Mockito.when(utils.createOkResponse(ticketArray)).thenReturn(new ResponseEntity<Ticket[]>(ticketArray, HttpStatus.OK));

        ResponseEntity<Ticket[]> responseEntity = revenueCompositeIntegration.getTicketsFromReservation(flightId);
        Ticket[] ticketArrayToCheck = responseEntity.getBody();

        Assert.assertEquals(ticketArrayToCheck[0].getTicketID(), ticketId1);
        Assert.assertEquals(ticketArrayToCheck[0], responseTicket1);
        Assert.assertEquals(ticketArrayToCheck[0].getBookingType(), "BOOKING_TYPE_INTERNET");
        Assert.assertEquals(ticketArrayToCheck[0].getTicketDate(), "01-01-2015");
        Assert.assertEquals(ticketArrayToCheck[0].getPassenger().getPassengerId(), passengerId1);
    }

//    @Test
//    public void testUpdateStatistic() throws URISyntaxException {
//        UUID flightId = UUID.randomUUID();
////      FlugObjekt anlegen
//        Flight resonseFlight = new Flight(flightId, false);
//
////      TODO - Ticket 1 anlegen - INTERNET + Economy
//
//        List<Reservation> responseReservationArray1 = new ArrayList<Reservation>();
//
////      ---------------------------- 1. Reservierung anlegen ------------------------------------
//        UUID passengerId1 = UUID.randomUUID();
//        UUID reservationId1_1 = UUID.randomUUID();
//        Passenger passenger1_1 = new Passenger(passengerId1, "Wolf-Dieter");
//        UUID placeId1_1 = UUID.randomUUID();
////        UUID placeId, int floor, int row, String seat, String placeType)
//        Place place1_1 = new Place(placeId1_1, 1, 1, "Innen", "Sessel");
////        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
//        Reservation reservation1_1 = new Reservation(reservationId1_1, false, flightId, passenger1_1, place1_1,"ECONOMY");
////      ---------------------------- 2. Reservierung anlegen ------------------------------------
//        UUID passengerId2 = UUID.randomUUID();
//        UUID reservationId1_2 = UUID.randomUUID();
//        Passenger passenger1_2 = new Passenger(passengerId2, "Günther");
//        UUID placeId1_2 = UUID.randomUUID();
////        UUID placeId, int floor, int row, String seat, String placeType)
//        Place place1_2 = new Place(placeId1_2, 1, 1, "Außen", "Couch");
////        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
//        Reservation reservation1_2 = new Reservation(reservationId1_2, false, flightId, passenger1_2, place1_2,"BUSINESS");
////      ---------------------------- 3. Reservierung anlegen ------------------------------------
//        UUID passengerId3 = UUID.randomUUID();
//        UUID reservationId1_3= UUID.randomUUID();
//        Passenger passenger1_3 = new Passenger(passengerId3, "Klaus");
//        UUID placeId1_3 = UUID.randomUUID();
////        UUID placeId, int floor, int row, String seat, String placeType)
//        Place place1_3 = new Place(placeId1_3, 1, 1, "Mitte", "Tragfläche");
////        UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType
//        Reservation reservation1_3 = new Reservation(reservationId1_3, false, flightId, passenger1_3, place1_3,"FIRST");
////      ---------------------------- Reservierungen hinzugfügen ------------------------------------
//
//        responseReservationArray1.add(reservation1_1);
//        responseReservationArray1.add(reservation1_2);
//        responseReservationArray1.add(reservation1_3);
//
////      ----------------------------- 1. Ticket anlegen -----------------------------------------
//        UUID ticketId1 = UUID.randomUUID();
//        UUID ticketId2 = UUID.randomUUID();
//        UUID ticketId3 = UUID.randomUUID();
//        UUID ticketId4 = UUID.randomUUID();
//
////        UUID ticketID, Passenger passenger, List<Reservation> reservations, String bookingType, String ticketDate
//        Ticket responseTicket1 = new Ticket(ticketId1, passenger1_1, responseReservationArray1, "INTERNET", "01-01-2015");
//        Ticket responseTicket2 = new Ticket(ticketId2, passenger1_2, responseReservationArray1, "AGENCY", "01-01-2015");
//        Ticket responseTicket3 = new Ticket(ticketId3, passenger1_3, responseReservationArray1, "STAFF", "01-01-2015");
//        Ticket responseTicket4 = new Ticket(ticketId4, passenger1_1, responseReservationArray1, "COUNTER", "01-01-2015");
//
////      ---------------------------- Tickets hinzugfügen ------------------------------------
//        Ticket[] ticketArray = new Ticket[4];
//        ticketArray[0] = responseTicket1;
//        ticketArray[1] = responseTicket2;
//        ticketArray[2] = responseTicket3;
//        ticketArray[3] = responseTicket4;
//
//
////      ---------------------------- Array mit einem FlightObjekt ------------------------------------
//
////        Flight testFlight1 = new Flight();
//
//        Flight[] flightArray = new Flight[2];
//        flightArray[0] = resonseFlight;
//
////      -------------------------------------- Mockaufrufe -------------------------------------------
//        ResponseEntity<Ticket[]> ticketsResponseEntity = new ResponseEntity<Ticket[]>(ticketArray, HttpStatus.OK);
//
//        String url = "http://localhost:8080/api/tickets/flight/"+flightId;
//
//        Mockito.when(utils.getServiceUrl("reservation")).thenReturn(new URI("http://localhost:8080"));
//        Mockito.when(restTemplate.getForEntity(url,Ticket[].class)).thenReturn(ticketsResponseEntity);
//        Mockito.when(utils.createOkResponse(ticketArray)).thenReturn(new ResponseEntity<Ticket[]>(ticketArray, HttpStatus.OK));
//
//        ResponseEntity<Flight[]> flightsResponseEntity = new ResponseEntity<Flight[]>(flightArray, HttpStatus.OK);
//
//        String url2 = "http://localhost:8080/api/flights";
//
//        Mockito.when(utils.getServiceUrl("reservation")).thenReturn(new URI("http://localhost:8080"));
//        Mockito.when(restTemplate.getForEntity(url2,Flight[].class)).thenReturn(flightsResponseEntity);
//        Mockito.when(utils.createOkResponse(flightArray)).thenReturn(new ResponseEntity<Flight[]>(flightArray, HttpStatus.OK));
//
//
//
//        String url3 = "http://localhost:8080/revenue/" + flightId;
//
//        Revenue revenue = new Revenue.RevenueBuilder()
//                .withFlightId(flightId)
//                .withsoldTicketsBusinessClassCounter(1)
//                .withSoldTicketsBusinessClassInternet(1)
//                .withsoldTicketsBusinessClassTravelOffice(1)
//                .withSoldTicketsFirstClassCounter(1)
//                .withSoldTicketsFirstClassInternet(1)
//                .withSoldTicketsFirstClassTravelOffice(1)
//                .withSoldTicketsEconomyClassCounter(1)
//                .withSoldTicketsEconomyClassInternet(1)
//                .withSoldTicketsEconomyClassTravelOffice(1)
//                .withsoldTicketsFirstClassStaff(1)
//                .withsoldTicketsBusinessClassStaff(1)
//                .withsoldTicketsEconomyClassStaff(1)
//                .build();
//
//        ResponseEntity<Revenue> revenueResponseEntity = new ResponseEntity<Revenue>(revenue, HttpStatus.OK);
//
//        Mockito.when(utils.getServiceUrl("revenue")).thenReturn(new URI("http://localhost:8080"));
//        Mockito.when(restTemplate.postForEntity(url3, revenue, Revenue.class)).thenReturn(revenueResponseEntity);
//        Mockito.when(utils.createOkResponse(revenue)).thenReturn(new ResponseEntity<Revenue>(revenue, HttpStatus.OK));
//
////      -------------------------------------- Testaufrufe -------------------------------------------
//
////        ResponseEntity<Ticket[]> responseEntityTickets = revenueCompositeIntegration.getTicketsFromReservation(flightId);
//        ResponseEntity<Revenue[]> responseEntityRevenuesArray = revenueCompositeIntegration.updateStatistic();
//        List<Revenue> responseEntityRevenueList = revenueCompositeIntegration.convertRevenue(responseEntityRevenuesArray.getBody());
////        Ticket ticketToCheck = responseEntityTickets.getBody()[0];
//
//
//
//
////      -------------------------------------- Tests -------------------------------------------
//
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassInternet(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassInternet(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassInternet(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassTravelOffice(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassTravelOffice(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassTravelOffice(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassCounter(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassCounter(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassCounter(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsEconomyClassStaff(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsBusinessClassStaff(), 1);
//        Assert.assertEquals(responseEntityRevenueList.get(0).getSoldTicketsFirstClassStaff(), 1);
//}

}
