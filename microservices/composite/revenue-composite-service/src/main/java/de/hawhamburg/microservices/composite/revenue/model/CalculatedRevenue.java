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
    private double businessClassPriceByCounter;
    private double businessClassPriceByInternet;
    private double businessClassPriceByTravelOffice;
    private double businessClassPriceByStaff;
    private double firstClassPriceByStaff;
    private double economyClassPriceByStaff;
    private long startime;


    private double revenue;

    private double soldTicketsFirstClassInternet;
    private double soldTicketsBusinessClassInternet;
    private double soldTicketsEconomyClassInternet;
    private double soldTicketsFirstClassTravelOffice;
    private double soldTicketsBusinessClassTravelOffice;
    private double soldTicketsEconomyClassTravelOffice;
    private double soldTicketsFirstClassCounter;
    private double soldTicketsBusinessClassCounter;
    private double soldTicketsEconomyClassCounter;
    private double soldTicketsEconomyClassStaff;
    private double soldTicketsFirstClassStaff;
    private double soldTicketsBusinessClassStaff;


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

    public double getSoldTicketsBusinessClassInternet() { return soldTicketsBusinessClassInternet; }

    public double getSoldTicketsBusinessClassTravelOffice() { return soldTicketsBusinessClassTravelOffice; }

    public double getSoldTicketsBusinessClassCounter() { return soldTicketsBusinessClassCounter; }

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
        this.businessClassPriceByCounter = price.getBusinessClassPriceByCounter();
        this.businessClassPriceByInternet = price.getBusinessClassPriceByInternet();
        this.businessClassPriceByTravelOffice = price.getBusinessClassPriceByTravelOffice();
        this.businessClassPriceByStaff = price.getBusinessClassPriceByStaff();
        this.firstClassPriceByStaff = price.getFirstClassPriceByStaff();
        this.economyClassPriceByStaff = price.getEconomyClassPriceByStaff();

        this.soldTicketsFirstClassInternet = revenue.getSoldTicketsFirstClassInternet();
        this.soldTicketsFirstClassTravelOffice = revenue.getSoldTicketsFirstClassTravelOffice();
        this.soldTicketsFirstClassCounter = revenue.getSoldTicketsFirstClassCounter();
        this.soldTicketsEconomyClassInternet = revenue.getSoldTicketsEconomyClassInternet();
        this.soldTicketsEconomyClassTravelOffice = revenue.getSoldTicketsEconomyClassTravelOffice();
        this.soldTicketsEconomyClassCounter = revenue.getSoldTicketsEconomyClassCounter();
        this.soldTicketsBusinessClassCounter = revenue.getSoldTicketsBusinessClassCounter();
        this.soldTicketsBusinessClassInternet = revenue.getSoldTicketsBusinessClassInternet();
        this.soldTicketsBusinessClassTravelOffice = revenue.getSoldTicketsBusinessClassTravelOffice();
        this.soldTicketsBusinessClassStaff = revenue.getSoldTicketsBusinessClassStaff();
        this.soldTicketsEconomyClassStaff = revenue.getSoldTicketsEconomyClassStaff();
        this.soldTicketsFirstClassStaff = revenue.getSoldTicketsFirstClassStaff();
        this.startime = revenue.getTimestamp();
        buildRevenue();
    }
    private void buildRevenue() {
        double revenueForInternet = (firstClassPriceByInternet * soldTicketsFirstClassInternet) + (economyClassPriceByInternet * soldTicketsEconomyClassInternet) + (businessClassPriceByInternet * soldTicketsBusinessClassInternet);
        double revenueForCounter = (firstClassPriceByCounter * soldTicketsFirstClassCounter) + (economyClassPriceByCounter * soldTicketsEconomyClassCounter) + (businessClassPriceByCounter * soldTicketsBusinessClassCounter);
        double revenueForTravelOffice = (firstClassPriceByTravelOffice * soldTicketsFirstClassTravelOffice) + (economyClassPriceByTravelOffice * soldTicketsEconomyClassTravelOffice) + (businessClassPriceByTravelOffice * soldTicketsBusinessClassTravelOffice);
        double revenueForStaff = (firstClassPriceByStaff * soldTicketsFirstClassStaff) + (economyClassPriceByStaff * soldTicketsEconomyClassStaff) + (businessClassPriceByStaff * soldTicketsBusinessClassStaff);
        this.revenue = revenueForCounter + revenueForInternet + revenueForTravelOffice + revenueForStaff;
    }

}
