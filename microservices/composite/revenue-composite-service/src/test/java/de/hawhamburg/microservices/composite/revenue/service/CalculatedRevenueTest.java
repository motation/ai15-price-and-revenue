package de.hawhamburg.microservices.composite.revenue.service;

import de.hawhamburg.microservices.composite.revenue.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.revenue.model.Price;
import de.hawhamburg.microservices.composite.revenue.model.CalculatedRevenue;
import de.hawhamburg.microservices.composite.revenue.model.Revenue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;


public class CalculatedRevenueTest {


    @Test public void TestCalculatedRevenue() {

        double soldTicketsFirstClassInternet = 10;
        double soldTicketsEconomyClassInternet = 20;
        double soldTicketsFirstClassTravelOffice = 5;
        double soldTicketsEconomyClassTravelOffice = 30;
        double soldTicketsFirstClassCounter = 2;
        double soldTicketsEconomyClassCounter = 6;

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);
        Revenue revenue = new Revenue.RevenueBuilder().withSoldTicketsFirstClassInternet(soldTicketsFirstClassInternet).withSoldTicketsEconomyClassInternet(soldTicketsEconomyClassInternet).withSoldTicketsFirstClassTravelOffice(soldTicketsFirstClassTravelOffice).withSoldTicketsEconomyClassTravelOffice(soldTicketsEconomyClassTravelOffice).withSoldTicketsFirstClassCounter(soldTicketsFirstClassCounter).withSoldTicketsEconomyClassCounter(soldTicketsEconomyClassCounter).build();

        CalculatedRevenue calculatedRevenue = new CalculatedRevenue(calculatedPrice, revenue);


        double firstClassPriceByInternet = 150.0;
        double economyClassPriceByInternet = 120.0;
        double firstClassPriceByTravelOffice = 150.0 * 1.2;
        double economyClassPriceByTravelOffice = 120.0 * 1.2;
        double firstClassPriceByCounter = 150.0 * 0.8;
        double economyClassPriceByCounter = 120.0 * 0.8;

        double revenueForInternet = firstClassPriceByInternet * soldTicketsFirstClassInternet + economyClassPriceByInternet * soldTicketsEconomyClassInternet;
        double revenueForCounter = firstClassPriceByCounter * soldTicketsFirstClassCounter + economyClassPriceByCounter * soldTicketsEconomyClassCounter;
        double revenueForTravelOffice = firstClassPriceByTravelOffice * soldTicketsFirstClassTravelOffice + economyClassPriceByTravelOffice * soldTicketsEconomyClassTravelOffice;
        double revenueTest = revenueForCounter + revenueForInternet + revenueForTravelOffice;

        Assert.assertEquals(revenueTest,calculatedRevenue.getRevenue());

    }


}
