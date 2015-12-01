package de.hawhamburg.microservices.core.price.product;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.service.PriceService;
import de.hawhamburg.microservices.core.price.jpa.service.PriceServiceImpl;
import org.junit.Before;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Michael B. on 18.11.2015.
 */
public class PriceServiceUnitTests {

    private PriceService priceService;

    @Before
    public void init(){
        this.priceService = new PriceServiceImpl();
    }

    @Before
    public void setup() {
        priceService = Mockito.mock(PriceServiceImpl.class);
    }

    @Test
    public void TestPriceForFlight() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        //Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(100.0).build();
        Price price2 = new Price.PriceBuilder().withFlightId(id2).withValue(100.0).build();
        Price price3 = new Price.PriceBuilder().withFlightId(id3).withValue(200.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.when(priceService.priceForFlight(id)).thenReturn(price);
        Mockito.when(priceService.priceForFlight(id2)).thenReturn(price2);
        Mockito.when(priceService.priceForFlight(id3)).thenReturn(price3);

        //Tests
        Assert.assertEquals(price.getValue(), priceService.priceForFlight(id).getValue());
        Assert.assertEquals(100.0, priceService.priceForFlight(id2).getValue());
        Assert.assertEquals(200.0, priceService.priceForFlight(id3).getValue());
    }

    @Test
    public void TestfindAllPrices() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        //Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(100.0).build();
        Price price2 = new Price.PriceBuilder().withFlightId(id2).withValue(100.0).build();
        Price price3 = new Price.PriceBuilder().withFlightId(id3).withValue(200.0).build();

        //Vergleichsliste erzeugen
        List<Price> allPrices = new ArrayList<>();
        allPrices.add(price);
        allPrices.add(price2);
        allPrices.add(price3);

        //Objekte & Methoden mocken
        setup();
        Mockito.when(priceService.findAllPrices()).thenReturn(allPrices);

        //Tests
        Assert.assertEquals(allPrices ,priceService.findAllPrices());

    }

    @Test
    public void TestCreatePrice() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        //Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(100.0).build();
        Price price2 = new Price.PriceBuilder().withFlightId(id2).withValue(100.0).build();
        Price price3 = new Price.PriceBuilder().withFlightId(id3).withValue(200.0).build();

        //Objekte & Methoden mocken
        setup();
        Mockito.when(priceService.createPrice(price)).thenReturn(price);
        Mockito.when(priceService.createPrice(price2)).thenReturn(price2);
        Mockito.when(priceService.createPrice(price3)).thenReturn(price3);

        //Tests
        Assert.assertEquals(price.getValue(), priceService.createPrice(price).getValue());
        Assert.assertEquals(price2.getValue(), priceService.createPrice(price2).getValue());
        Assert.assertEquals(200.0, priceService.createPrice(price3).getValue());

    }

//    @Test
//    public void TestRemovePrice(UUID flightId) {
//    }
//
//    @Test
//    public void TestUpdatePrice(Price price)
//        //UUID anlegen
//        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
//        //UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
//
//        //Preisobjekt mit einer UUID anlegen
//        Price price = new Price.PriceBuilder().withFlightId(id).withValue(500.0).build();
//        Price priceForUpdate = price;
//        //Objekte & Methoden mocken
//        setup();
//        Mockito.doCallRealMethod().when(priceService).updatePrice(priceForUpdate);
//
//        //Preis ändern der die gleiche UUID hat
//        priceService.updatePrice(priceForUpdate);
//
//        //Tests
//        Assert.assertEquals(priceForUpdate.getValue(), priceService.updatePrice(priceForUpdate));
//        Assert.assertNotEquals(priceForUpdate.getValue(), );
//    }
}
