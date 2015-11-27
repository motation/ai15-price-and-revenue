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

//import org.junit.BeforeClass;
//import org.junit.Test;

/**
 * Created by Michael B. on 18.11.2015.
 */
public class PriceServiceUnitTests {

    private PriceService priceService;

    @Before
    public void init(){
        this.priceService = new PriceServiceImpl();
    }

    @Test
    public void TestPriceForFlight() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");
        //Preisobjekt mit Preis:100 und einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(100).build();
        Price price2 = new Price.PriceBuilder().withFlightId(id2).withValue(100).build();
        Price price3 = new Price.PriceBuilder().withFlightId(id3).withValue(200).build();

        List<Price> allPrices = new ArrayList<>();
        allPrices.add(price);
        allPrices.add(price2);
        allPrices.add(price3);


        //Objekte & Methoden mocken
        priceService = Mockito.mock(PriceServiceImpl.class);
        Mockito.when(priceService.createPrice(price)).thenReturn(price2);
        Mockito.when(priceService.createPrice(price2)).thenReturn(price);
        Mockito.when(priceService.createPrice(price3)).thenReturn(price3);
        Mockito.when(priceService.priceForFlight(id)).thenReturn(price);
        Mockito.when(priceService.findAllPrices()).thenReturn(allPrices);

        //Tests
        Assert.assertEquals(price2.getValue(), priceService.createPrice(price).getValue());
        Assert.assertEquals(100.0, priceService.priceForFlight(id).getValue());
        Assert.assertEquals(allPrices ,priceService.findAllPrices());
    }
}
