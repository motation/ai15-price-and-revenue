package de.hawhamburg.microservices.core.revenue.jpa.service;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michael B. on 18.11.2015.
 */
public class RevenueServiceUnitTests {

    private RevenueService revenueService;

    @Before
    public void init(){
        this.revenueService = new RevenueServiceImpl();
    }

    @Before
    public void setup() {
        revenueService = Mockito.mock(RevenueServiceImpl.class);
    }


    @Test
    public void TestRevenueForFlight() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).withValue(100.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.when(revenueService.revenueForFlight(id)).thenReturn(revenue);

        //Tests
        Assert.assertEquals(UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca"), revenueService.revenueForFlight(id).getFlightId());
    }

    @Test
    public void TestFindAllRevenues() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).build();
        Revenue revenue2 = new Revenue.RevenueBuilder().withFlightId(id2).build();
        Revenue revenue3 = new Revenue.RevenueBuilder().withFlightId(id3).build();
        Revenue revenue4 = new Revenue.RevenueBuilder().withFlightId(id).build();
        Revenue revenue5 = new Revenue.RevenueBuilder().withFlightId(id2).build();
        Revenue revenue6 = new Revenue.RevenueBuilder().withFlightId(id3).build();

        //Vergleichsliste erzeugen
        List<Revenue> allRevenues = new ArrayList<>();
        allRevenues.add(revenue);
        allRevenues.add(revenue2);
        allRevenues.add(revenue3);

        List<Revenue> allRevenues2 = new ArrayList<>();
        allRevenues2.add(revenue4);
        allRevenues2.add(revenue5);
        allRevenues2.add(revenue6);

        //Objekte & Methoden mocken
        setup();
        Mockito.when(revenueService.findAllRevenues()).thenReturn(allRevenues);

        //Tests
        for(int i=0;i<allRevenues.size();i++){
            org.testng.Assert.assertEquals(allRevenues.get(i).getFlightId(), allRevenues2.get(i).getFlightId());
        }
    }

    @Test
    public void TestCreateRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Revenueobjekt mit einer UUID anlegen
        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).withValue(100.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.when(revenueService.createRevenue(revenue)).thenReturn(revenue);

        //Tests
        Assert.assertEquals(UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca"), revenueService.createRevenue(revenue).getFlightId());
    }

    @Test
    public void TestRemoveRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Preisobjekt mit einer UUID anlegen
        Revenue price = new Revenue.RevenueBuilder().withFlightId(id).withValue(500.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.when(revenueService.removeRevenue(id2)).thenReturn(true);    //Preis mit id löschen

        //Tests
        Assert.assertFalse(revenueService.removeRevenue(id));
        Assert.assertTrue(revenueService.removeRevenue(id2));
    }

//    @Test
//    public void TestUpdateRevenue(Revenue price) {
            //To-Do
//    }
}
