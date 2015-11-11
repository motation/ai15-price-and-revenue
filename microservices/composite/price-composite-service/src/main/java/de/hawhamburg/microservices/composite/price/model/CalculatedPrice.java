package de.hawhamburg.microservices.composite.price.model;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;

/**
 * Created by Ole on 07.11.2015.
 */
public class CalculatedPrice {

    private final double FIRST = 50;
    private final double ECONOMY = 20;
    private final double INTERNET = 1.0;
    private final double TRAVELOFFICE = 1.2;
    private final double COUNTER = 0.8;

    private double basicPrice;
    private double firstClassPriceByInternet;
    private double economyClassPriceByInternet;
    private double firstClassPriceByTravelOffice;
    private double economyClassPriceByTravelOffice;
    private double firstClassPriceByCounter;
    private double economyClassPriceByCounter;

    public CalculatedPrice(Price price){
        this.basicPrice = price.getValue();
        buildPrices();
    }

    public CalculatedPrice(){

    }

    private void calculatePriceFirstClassByInternet(){
        this.firstClassPriceByInternet = (basicPrice + FIRST) * INTERNET;
    }

    private void calculatePriceEconomyClassByInternet(){
        this.economyClassPriceByInternet = (basicPrice + ECONOMY) * INTERNET;
    }

    private void calculatePriceFirstClassByTravelOffice(){
        this.firstClassPriceByTravelOffice = (basicPrice + FIRST) * TRAVELOFFICE;
    }

    private void calculatePriceEconomyClassByTravelOffice(){
        this.economyClassPriceByTravelOffice = (basicPrice + ECONOMY) * TRAVELOFFICE;
    }

    private void calculatePriceFirstClassByCounter(){
        this.firstClassPriceByCounter = (basicPrice + FIRST) * COUNTER;
    }

    private void calculatePriceEconomyClassByCounter(){
        this.economyClassPriceByCounter = (basicPrice + ECONOMY) * COUNTER;
    }

    private void buildPrices() {
        calculatePriceFirstClassByInternet();
        calculatePriceEconomyClassByInternet();
        calculatePriceFirstClassByTravelOffice();
        calculatePriceEconomyClassByTravelOffice();
        calculatePriceFirstClassByCounter();
        calculatePriceEconomyClassByCounter();
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public double getFirstClassPriceByInternet() {
        return firstClassPriceByInternet;
    }

    public double getEconomyClassPriceByInternet() {
        return economyClassPriceByInternet;
    }

    public double getFirstClassPriceByTravelOffice() {
        return firstClassPriceByTravelOffice;
    }

    public double getEconomyClassPriceByTravelOffice() {
        return economyClassPriceByTravelOffice;
    }

    public double getFirstClassPriceByCounter() {
        return firstClassPriceByCounter;
    }

    public double getEconomyClassPriceByCounter() {
        return economyClassPriceByCounter;
    }
}
