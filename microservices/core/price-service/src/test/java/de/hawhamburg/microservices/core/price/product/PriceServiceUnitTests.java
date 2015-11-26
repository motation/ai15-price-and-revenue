package de.hawhamburg.microservices.core.price.product;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

//import org.junit.BeforeClass;
//import org.junit.Test;

/**
 * Created by Michael B. on 18.11.2015.
 */
@IntegrationTest
public class PriceServiceUnitTests {

    //private PriceService priceService;
    @Autowired
    private PriceRepository priceRepository;

    //@Test
    //public void test

    //@BeforeClass
    //public void Setuo() {
        //PriceService priceService = new PriceServiceImpl();
    //}


    @Test
    public void TestPriceForFlight(){

        //UUID anlegen
        //UUID id = UUID.randomUUID();
        UUID id = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692cd");
        //Preisobjekt mit Preis:100 und einer UUID anlegen
        Price price = new Price.PriceBuilder().withFlightId(id).withValue(100).build();
        //Preis in der Datenbank abspeichern

        priceRepository.save(price);
        //priceService.createPrice(price);

        //PriceServiceImpl priceServiceImpl = new PriceServiceImpl() {};

        Assert.assertEquals(price,priceRepository.findByFlightId(id));


        //Preis erstellen
        //priceServiceImpl.createPrice(price);



        //priceService.createPrice(price);
        //priceRepository.save(price);

        System.out.println(price.getValue());
        //Assert.assertEquals(110.0,priceService.priceForFlight(id2));
        //Assert.assertEquals(100, 100);
    }


//    Price priceForFlight(UUID flightId);
//
//    List<Price> findAllPrices();
//
//    Price createPrice(Price price);
//
//    void removePrice(UUID flightId);

//    void updatePrice(Price price);


}
