package de.hawhamburg.microservices.composite.revenue.model;

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
    private double soldTicketsFirstClassInternet = 10;
    private double soldTicketsEconomyClassInternet = 20;
    private double soldTicketsFirstClassTravelOffice = 5;
    private double soldTicketsEconomyClassTravelOffice = 30;
    private double soldTicketsFirstClassCounter = 2;
    private double soldTicketsEconomyClassCounter = 6;

    public double getSoldTicketsEconomyClassCounter() {
        return soldTicketsEconomyClassCounter;
    }

    public double getSoldTicketsFirstClassCounter() {
        return soldTicketsFirstClassCounter;
    }

    public double getSoldTicketsEconomyClassTravelOffice() {
        return soldTicketsEconomyClassTravelOffice;
    }

    public double getSoldTicketsFirstClassTravelOffice() {
        return soldTicketsFirstClassTravelOffice;
    }

    public double getSoldTicketsEconomyClassInternet() {
        return soldTicketsEconomyClassInternet;
    }

    public double getSoldTicketsFirstClassInternet() {
        return soldTicketsFirstClassInternet;
    }

    public double getRevenue() {
        return revenue;
    }

    public CalculatedRevenue(){

    }

    public CalculatedRevenue(CalculatedPrice price, Revenue revenue){
        this.basicPrice = price.getBasicPrice();
        this.firstClassPriceByInternet = price.getFirstClassPriceByInternet();
        this.economyClassPriceByInternet = price.getEconomyClassPriceByInternet();
        this.firstClassPriceByCounter = price.getFirstClassPriceByCounter();
        this.economyClassPriceByCounter = price.getEconomyClassPriceByCounter();
        this.firstClassPriceByTravelOffice = price.getFirstClassPriceByTravelOffice();
        this.economyClassPriceByTravelOffice = price.getEconomyClassPriceByTravelOffice();

        this.soldTicketsFirstClassInternet = revenue.getSoldTicketsFirstClassInternet();
        this.soldTicketsFirstClassTravelOffice = revenue.getSoldTicketsFirstClassTravelOffice();
        this.soldTicketsFirstClassCounter = revenue.getSoldTicketsFirstClassCounter();
        this.soldTicketsEconomyClassInternet = revenue.getSoldTicketsEconomyClassInternet();
        this.soldTicketsEconomyClassTravelOffice = revenue.getSoldTicketsEconomyClassTravelOffice();
        this.soldTicketsEconomyClassCounter = revenue.getSoldTicketsEconomyClassCounter();
        buildRevenue();
    }
    private void buildRevenue() {
        double revenueForInternet = firstClassPriceByInternet * soldTicketsFirstClassInternet + economyClassPriceByInternet * soldTicketsEconomyClassInternet;
        double revenueForCounter = firstClassPriceByCounter * soldTicketsFirstClassCounter + economyClassPriceByCounter * soldTicketsEconomyClassCounter;
        double revenueForTravelOffice = firstClassPriceByTravelOffice * soldTicketsFirstClassTravelOffice + economyClassPriceByTravelOffice * soldTicketsEconomyClassTravelOffice;
        this.revenue = revenueForCounter + revenueForInternet + revenueForTravelOffice;
    }

}
