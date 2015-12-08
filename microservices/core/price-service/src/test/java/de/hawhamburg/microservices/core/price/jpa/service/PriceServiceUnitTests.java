package de.hawhamburg.microservices.core.price.jpa.service;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.repository.PriceRepository;
import de.hawhamburg.microservices.core.price.jpa.service.PriceService;
import de.hawhamburg.microservices.core.price.jpa.service.PriceServiceImpl;
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


/**
 * Created by Michael B. on 18.11.2015.
 */
public class PriceServiceUnitTests {

    @InjectMocks
    private PriceService priceService = new PriceServiceImpl();

    @Mock
    private PriceRepository priceRepository;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestPriceForFlight() {

        //UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692cb");
        UUID id3 = UUID.fromString("2b4acc1d-3439-4b67-905a-1f7a4bb692cc");

        //Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder()
                .withId(1)
                .withFlightId(id)
                .withValue(100.0)
                .build();
        Price price2 = new Price.PriceBuilder()
                .withId(2)
                .withFlightId(id2)
                .withValue(100.0)
                .build();
        Price price3 = new Price.PriceBuilder()
                .withId(3)
                .withFlightId(id3)
                .withValue(200.0).build();

        //Objekte & Methoden mocken
        Mockito.when(priceRepository.findByFlightId(id)).thenReturn(price);
        Mockito.when(priceRepository.findByFlightId(id2)).thenReturn(price2);
        Mockito.when(priceRepository.findByFlightId(id3)).thenReturn(price3);

        //Tests
        Assert.assertEquals(100.0, priceService.priceForFlight(id).getValue());
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
        Mockito.when(priceRepository.findAll()).thenReturn(allPrices);
        List<Price> priceList2 = priceService.findAllPrices();

        //Tests
        Assert.assertEquals(100.0, priceList2.get(0).getValue());
        Assert.assertEquals(100.0, priceList2.get(1).getValue());
        Assert.assertEquals(200.0,priceList2.get(2).getValue());
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

        Mockito.when(priceRepository.save(price)).thenReturn(price);
        Mockito.when(priceRepository.save(price2)).thenReturn(price2);
        Mockito.when(priceRepository.save(price3)).thenReturn(price3);

        //Tests
        Assert.assertEquals(100.0, priceService.createPrice(price).getValue());
        Assert.assertEquals(100.0, priceService.createPrice(price2).getValue());
        Assert.assertEquals(200.0, priceService.createPrice(price3).getValue());

    }

    @Test
    public void TestRemovePrice() {

        // UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id2 = UUID.fromString("1b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        UUID id3 = UUID.fromString("1c4acc1d-3439-4b67-905a-1f7a4bb692ca");

        // Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(500.0).build();
        Price price2 = new Price.PriceBuilder().withFlightId(id).withValue(600.0).build();
        Price price3 = new Price.PriceBuilder().withFlightId(id).withValue(600.0).build();

        // Objekte & Methoden mocken
        Mockito.when(priceRepository.findByFlightId(id)).thenReturn(price);
        Mockito.when(priceRepository.findByFlightId(id2)).thenReturn(price2);
        Mockito.doNothing().when(priceRepository).delete(price);
        Mockito.doNothing().when(priceRepository).delete(price2);

        // Tests
        Assert.assertTrue(priceService.removePrice(id));
        Assert.assertTrue(priceService.removePrice(id2));
        Assert.assertFalse(priceService.removePrice(id3));
    }

    @Test
    public void TestUpdatePrice() {
        // UUID anlegen
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");

        //Preisobjekt mit einer UUID anlegen
        Price price = new Price.PriceBuilder()
                .withFlightId(id)
                .withId(1)
                .withValue(500.0)
                .build();

        Price price2 = new Price.PriceBuilder()
                .withFlightId(id)
                .withValue(300.0)
                .withId(1)
                .build();

        //Objekte & Methoden mocken
        Mockito.when(priceRepository.findByFlightId(id)).thenReturn(price);
        //OF we have to use price2 to because update will change the value
        Mockito.when(priceRepository.save(price2)).thenReturn(price2);

        //Tests
        Assert.assertTrue(priceService.updatePrice(id, 300.0));
    }
}
