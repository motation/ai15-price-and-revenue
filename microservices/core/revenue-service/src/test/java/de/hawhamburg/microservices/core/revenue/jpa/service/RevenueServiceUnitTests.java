package de.hawhamburg.microservices.core.revenue.jpa.service;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import de.hawhamburg.microservices.core.revenue.jpa.repository.RevenueRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RevenueServiceUnitTests {

    @InjectMocks
    private RevenueService revenueService = new RevenueServiceImpl();
    private float delta = (float) 0.000000001;

    @Mock
    private RevenueRepository revenueRepository;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestRevenueForFlight() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
//        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).withValue(100.0).build();
        Revenue revenue = new Revenue.RevenueBuilder()
                .withFlightId(id)
                .withsoldTicketsBusinessClassStaff(120)
                .build();

        //Objekte & Methoden mocken

        Mockito.when(revenueRepository.findByFlightId(id)).thenReturn(revenue);


        //Tests
        Assert.assertEquals(UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca"), revenueService.revenueForFlight(id).getFlightId());
        Assert.assertEquals(120.0, revenueService.revenueForFlight(id).getSoldTicketsBusinessClassStaff(), delta);


    }

    @Test
    public void TestFindAllRevenues() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id)
                .withsoldTicketsBusinessClassCounter(200.0)
                .build();
        Revenue revenue2 = new Revenue.RevenueBuilder().withFlightId(id2)
                .withSoldTicketsBusinessClassInternet(300.0)
                .build();
        Revenue revenue3 = new Revenue.RevenueBuilder().withFlightId(id3)
                .withsoldTicketsBusinessClassStaff(400.0)
                .build();
        Revenue revenue4 = new Revenue.RevenueBuilder().withFlightId(id)
                .withsoldTicketsBusinessClassTravelOffice(500.0)
                .build();
        Revenue revenue5 = new Revenue.RevenueBuilder().withFlightId(id2)
                .withSoldTicketsEconomyClassCounter(600.0)
                .build();
        Revenue revenue6 = new Revenue.RevenueBuilder().withFlightId(id3)
                .withSoldTicketsEconomyClassInternet(100.0)
                .build();

        //Vergleichsliste erzeugen
        List<Revenue> allRevenues = new ArrayList<>();
        allRevenues.add(revenue);
        allRevenues.add(revenue2);
        allRevenues.add(revenue3);
        allRevenues.add(revenue4);
        allRevenues.add(revenue5);
        allRevenues.add(revenue6);

        //Objekte & Methoden mocken
        Mockito.when(revenueRepository.findAll()).thenReturn(allRevenues);
        List<Revenue> revenueList = revenueService.findAllRevenues();

        //Tests
        Assert.assertEquals(200.0, revenueList.get(0).getSoldTicketsBusinessClassCounter(), delta);
        Assert.assertEquals(300.0, revenueList.get(1).getSoldTicketsBusinessClassInternet(), delta);
        Assert.assertEquals(400.0, revenueList.get(2).getSoldTicketsBusinessClassStaff(), delta);
        Assert.assertNotSame(700.0, revenueList.get(5).getSoldTicketsEconomyClassCounter());
        Assert.assertEquals(100.0, revenueList.get(5).getSoldTicketsEconomyClassInternet(), delta);
    }


    @Test
    public void TestCreateRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id)
                .withSoldTicketsFirstClassInternet(100.0)
                .build();

        //Objekte & Methoden mocken
        Mockito.when(revenueService.createRevenue(revenue)).thenReturn(revenue);

        //Tests
        Assert.assertEquals(UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca"), revenueService.createRevenue(revenue).getFlightId());
        Assert.assertEquals(100.0, revenueService.createRevenue(revenue).getSoldTicketsFirstClassInternet(), delta);

    }

    @Test
    public void TestRemoveRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id3 = UUID.fromString("1c4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id)
                .withsoldTicketsFirstClassStaff(500.0)
                .build();
        Revenue revenue2 = new Revenue.RevenueBuilder().withFlightId(id)
                .withSoldTicketsFirstClassTravelOffice(600.0)
                .build();

        Revenue revenue3 = null;

        //Objekte & Methoden mocken
        Mockito.when(revenueRepository.findByFlightId(id)).thenReturn(revenue);
        Mockito.when(revenueRepository.findByFlightId(id2)).thenReturn(revenue2);
        Mockito.when(revenueRepository.findByFlightId(id3)).thenReturn(revenue3);


        Mockito.doThrow(new NullPointerException()).when(revenueRepository).delete(revenue3);

        //Tests

        Assert.assertTrue(revenueService.removeRevenue(id2));
        Assert.assertTrue(revenueService.removeRevenue(id));
        Assert.assertFalse(revenueService.removeRevenue(id3));
    }

    @Test
    public void TestUpdateRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id)
                .withSoldTicketsFirstClassInternet(500.0)
                .build();

        //Objekte & Methoden mocken
        Mockito.when(revenueRepository.findByFlightId(id)).thenReturn(revenue);

        //Tests
        Assert.assertTrue(revenueService.updateRevenue(revenue));
    }
}
