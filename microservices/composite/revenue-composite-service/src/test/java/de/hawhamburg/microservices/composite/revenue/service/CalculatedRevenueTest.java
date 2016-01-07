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
        double soldTicketsEconomyClassStaff = 14;
        double soldTicketsFirstClassStaff = 18;
        double soldTicketsBusinessClassStaff = 12;
        double soldTicketsBusinessClassInternet = 27;
        double soldTicketsBusinessClassTravelOffice = 36;
        double soldTicketsBusinessClassCounter = 34;

        UUID id1 = UUID.fromString("0b4acc1d-3439-4b67-905a-1f7a4bb692ca");
        Price basicPrice = new Price.PriceBuilder().withFlightId(id1).withValue(100).build();

        CalculatedPrice calculatedPrice;
        calculatedPrice = new CalculatedPrice(basicPrice);
        Revenue revenue = new Revenue.RevenueBuilder()
                .withSoldTicketsFirstClassInternet(soldTicketsFirstClassInternet)
                .withSoldTicketsEconomyClassInternet(soldTicketsEconomyClassInternet)
                .withSoldTicketsFirstClassTravelOffice(soldTicketsFirstClassTravelOffice)
                .withSoldTicketsEconomyClassTravelOffice(soldTicketsEconomyClassTravelOffice)
                .withSoldTicketsFirstClassCounter(soldTicketsFirstClassCounter)
                .withSoldTicketsEconomyClassCounter(soldTicketsEconomyClassCounter)
                .withsoldTicketsEconomyClassStaff(soldTicketsEconomyClassStaff)
                .withsoldTicketsFirstClassStaff(soldTicketsFirstClassStaff)
                .withsoldTicketsBusinessClassStaff(soldTicketsBusinessClassStaff)
                .withSoldTicketsBusinessClassInternet(soldTicketsBusinessClassInternet)
                .withsoldTicketsBusinessClassTravelOffice(soldTicketsBusinessClassTravelOffice)
                .withsoldTicketsBusinessClassCounter(soldTicketsBusinessClassCounter)
                .build();

        CalculatedRevenue calculatedRevenue = new CalculatedRevenue(calculatedPrice, revenue);


        double firstClassPriceByInternet = 150.0;
        double economyClassPriceByInternet = 120.0;
        double businessClassPriceByInternet = 135.0;

        double firstClassPriceByCounter = 150.0 * 0.8;
        double economyClassPriceByCounter = 120.0 * 0.8;
        double businessClassPriceByCounter = 135.0 * 0.8;

        double firstClassPriceByTravelOffice = 150.0 * 1.1;
        double economyClassPriceByTravelOffice = 120.0 * 1.1;
        double businessClassPriceByTravelOffice = 135.0 * 1.1;

        double firstClassPriceByStaff = 150.0 * 0.7;
        double economyClassPriceByStaff = 120.0 * 0.7;
        double businessClassPriceByStaff = 135 * 0.7;

        double revenueForInternet = (firstClassPriceByInternet * soldTicketsFirstClassInternet) + (economyClassPriceByInternet * soldTicketsEconomyClassInternet) + (businessClassPriceByInternet * soldTicketsBusinessClassInternet);
        double revenueForCounter = (firstClassPriceByCounter * soldTicketsFirstClassCounter) + (economyClassPriceByCounter * soldTicketsEconomyClassCounter) + (businessClassPriceByCounter * soldTicketsBusinessClassCounter);
        double revenueForTravelOffice = (firstClassPriceByTravelOffice * soldTicketsFirstClassTravelOffice) + (economyClassPriceByTravelOffice * soldTicketsEconomyClassTravelOffice) + (businessClassPriceByTravelOffice * soldTicketsBusinessClassTravelOffice);
        double revenueForStaff = (firstClassPriceByStaff * soldTicketsFirstClassStaff) + (economyClassPriceByStaff * soldTicketsEconomyClassStaff) + (businessClassPriceByStaff * soldTicketsBusinessClassStaff);

        double revenueTest = revenueForCounter + revenueForInternet + revenueForTravelOffice + revenueForStaff;

        Assert.assertEquals(revenueTest, calculatedRevenue.getRevenue());

    }


}
