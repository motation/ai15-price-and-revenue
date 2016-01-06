package de.hawhamburg.microservices.composite.price.service;

import de.hawhamburg.microservices.composite.price.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.price.model.Price;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;


public class CalculatedPriceTest {


    @Test public void TestCalculatePriceFirstClassByInternet() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double firstClassPriceByInternet = 150.0;
        Assert.assertEquals(firstClassPriceByInternet,calculatedPrice.getFirstClassPriceByInternet());

    }

    @Test public void TestCalculatePriceEconomyClassByInternet() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double economyPriceByInternet = 120.0;
        Assert.assertEquals(economyPriceByInternet,calculatedPrice.getEconomyClassPriceByInternet());

    }

    @Test public void TestCalculatePriceFirstClassByTravelOffice() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double firstClassPriceByTravelOffice = 150.0 * 1.2;
        Assert.assertEquals(firstClassPriceByTravelOffice,calculatedPrice.getFirstClassPriceByTravelOffice());

    }

    @Test public void TestCalculatePriceEconomyClassByTravelOffice() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double economyPriceByTravelOffice = 120.0 * 1.2;
        Assert.assertEquals(economyPriceByTravelOffice,calculatedPrice.getEconomyClassPriceByTravelOffice());

    }

    @Test public void TestCalculatePriceFirstClassByCounter() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double firstClassPriceByCounter = 150.0 * 0.8;
        Assert.assertEquals(firstClassPriceByCounter,calculatedPrice.getFirstClassPriceByCounter());

    }

    @Test public void TestCalculatePriceEconomyClassByCounter() {

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);

        double economyPriceByCounter = 120.0 * 0.8;
        Assert.assertEquals(economyPriceByCounter,calculatedPrice.getEconomyClassPriceByCounter());

    }
}
