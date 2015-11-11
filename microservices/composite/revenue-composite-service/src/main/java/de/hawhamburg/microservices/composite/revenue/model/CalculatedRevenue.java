package de.hawhamburg.microservices.composite.revenue.model;

import de.hawhamburg.microservices.composite.price.model.CalculatedPrice;

import java.util.UUID;

/**
 * Created by Ole on 07.11.2015.
 */

public class CalculatedRevenue {

    private double basicPrice;
    private UUID id;
    private double firstClassPriceByInternet;
    private double economyClassPriceByInternet;
    private double firstClassPriceByTravelOffice;
    private double economyClassPriceByTravelOffice;
    private double firstClassPriceByCounter;
    private double economyClassPriceByCounter;


    private double revenue;
    private int soldTicketsFirstClassInternet = 10;
    private int soldTicketsEconomyClassInternet = 20;
    private int soldTicketsFirstClassTravelOffice = 5;
    private int soldTicketsEconomyClassTravelOffice = 30;
    private int soldTicketsFirstClassCounter = 2;
    private int soldTicketsEconomyClassCounter = 6;

    public int getSoldTicketsEconomyClassCounter() {
        return soldTicketsEconomyClassCounter;
    }

    public int getSoldTicketsFirstClassCounter() {
        return soldTicketsFirstClassCounter;
    }

    public int getSoldTicketsEconomyClassTravelOffice() {
        return soldTicketsEconomyClassTravelOffice;
    }

    public int getSoldTicketsFirstClassTravelOffice() {
        return soldTicketsFirstClassTravelOffice;
    }

    public int getSoldTicketsEconomyClassInternet() {
        return soldTicketsEconomyClassInternet;
    }

    public int getSoldTicketsFirstClassInternet() {
        return soldTicketsFirstClassInternet;
    }

    public double getRevenue() {
        return revenue;
    }

    public CalculatedRevenue(){

    }

    public CalculatedRevenue(CalculatedPrice price){
        this.basicPrice = price.getBasicPrice();
        this.firstClassPriceByInternet = price.getFirstClassPriceByInternet();
        this.economyClassPriceByInternet = price.getEconomyClassPriceByInternet();
        this.firstClassPriceByCounter = price.getFirstClassPriceByCounter();
        this.economyClassPriceByCounter = price.getEconomyClassPriceByCounter();
        this.firstClassPriceByTravelOffice = price.getFirstClassPriceByTravelOffice();
        this.economyClassPriceByTravelOffice = price.getEconomyClassPriceByTravelOffice();
        buildRevenue();
    }

    private void buildRevenue() {
        double revenueForInternet = firstClassPriceByInternet * soldTicketsFirstClassInternet + economyClassPriceByInternet * soldTicketsEconomyClassInternet;
        double revenueForCounter = firstClassPriceByCounter * soldTicketsFirstClassCounter + economyClassPriceByCounter * soldTicketsEconomyClassCounter;
        double revenueForTravelOffice = firstClassPriceByTravelOffice * soldTicketsFirstClassTravelOffice + economyClassPriceByTravelOffice * soldTicketsEconomyClassTravelOffice;
        this.revenue = revenueForCounter + revenueForInternet + revenueForTravelOffice;
    }

}
