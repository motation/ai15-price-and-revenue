package de.hawhamburg.microservices.core.revenue.product;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import de.hawhamburg.microservices.core.revenue.jpa.service.RevenueService;
import de.hawhamburg.microservices.core.revenue.jpa.service.RevenueServiceImpl;
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
        Assert.assertEquals(id, revenueService.revenueForFlight(id).getFlightId());
    }

    @Test
    public void TestFindAllRevenues() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).withValue(100.0).build();
        Revenue revenue2 = new Revenue.RevenueBuilder().withFlightId(id2).withValue(200.0).build();
        Revenue revenue3 = new Revenue.RevenueBuilder().withFlightId(id3).withValue(300.0).build();

        //Vergleichsliste erzeugen
        List<Revenue> allRevenues = new ArrayList<>();
        allRevenues.add(revenue);
        allRevenues.add(revenue2);
        allRevenues.add(revenue3);

        //Objekte & Methoden mocken
        setup();
        Mockito.when(revenueService.findAllRevenues()).thenReturn(allRevenues);

        //Tests
        Assert.assertEquals(allRevenues ,revenueService.findAllRevenues());

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
        Assert.assertEquals(id, revenueService.createRevenue(revenue).getFlightId());
    }

    @Test
    public void TestRemoveRevenue() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Preisobjekt mit einer UUID anlegen
        Revenue price = new Revenue.RevenueBuilder().withFlightId(id).withValue(500.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.doCallRealMethod().when(revenueService).removeRevenue(id);    //Preis mit id löschen

        //Tests
        org.testng.Assert.assertNull(revenueService.revenueForFlight(id));
    }
//
//    @Test
//    public void TestUpdateRevenue(Revenue price) {
//        //UUID anlegen
//        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
//
//        //Preisobjekt mit einer UUID anlegen
//        Revenue revenue = new Revenue.RevenueBuilder().withFlightId(id).withValue(500.0).build();
//
//        //Objekte & Methoden mocken
//        setup();
//        Mockito.when(revenueService.revenueForFlight(id)).thenReturn(price);
//        Mockito.doCallRealMethod().when(revenueService).updatePrice(id,200.0);    //Preis von 500.0 auf 200.0 setzen
//
//        //Tests
//        Assert.assertEquals(revenue.getValue(), priceService.priceForFlight(id).getValue());
//    }
    // #### Was für einen Wert wollen wir hier updaten?!? ####
}
