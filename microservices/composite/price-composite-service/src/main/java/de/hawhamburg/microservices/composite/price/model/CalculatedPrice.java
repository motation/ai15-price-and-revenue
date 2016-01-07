package de.hawhamburg.microservices.composite.price.model;


/**
 * Created by Ole on 07.11.2015.
 */
public class CalculatedPrice {

    private final double FIRST = 50;
    private final double ECONOMY = 20;
    private final double BUSSINESS = 35;

    private final double TRAVELOFFICE = 1.1;
    private final double INTERNET = 1.0;
    private final double COUNTER = 0.8;
    private final double STAFF = 0.7;

    private double basicPrice;
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

    private void calculatePriceBusinessClassByCounter(){
        this.businessClassPriceByCounter = (basicPrice + BUSSINESS) * COUNTER;
    }

    private void calculatePriceBusinessClassByInternet(){
        this.businessClassPriceByInternet = (basicPrice + BUSSINESS) * INTERNET;
    }

    private void calculatePriceBusinessClassByTravelOffice(){
        this.businessClassPriceByTravelOffice = (basicPrice + BUSSINESS) * TRAVELOFFICE;
    }

    private void calculatePriceBusinessClassByStaff(){
        this.businessClassPriceByStaff = (basicPrice + BUSSINESS) * STAFF;
    }

    private void calculatePriceEconomyClassByStaff(){
        this.firstClassPriceByStaff = (basicPrice + FIRST) * STAFF;
    }

    private void calculatePriceFirstClassByStaff(){
        this.economyClassPriceByStaff = (basicPrice + ECONOMY) * STAFF;
    }


    private void buildPrices() {
        calculatePriceFirstClassByInternet();
        calculatePriceEconomyClassByInternet();
        calculatePriceFirstClassByTravelOffice();
        calculatePriceEconomyClassByTravelOffice();
        calculatePriceFirstClassByCounter();
        calculatePriceEconomyClassByCounter();
        calculatePriceBusinessClassByCounter();
        calculatePriceBusinessClassByInternet();
        calculatePriceBusinessClassByTravelOffice();
        calculatePriceBusinessClassByStaff();
        calculatePriceEconomyClassByStaff();
        calculatePriceFirstClassByStaff();
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

    public double getBusinessClassPriceByCounter() { return businessClassPriceByCounter; }

    public double getBusinessClassPriceByInternet() { return businessClassPriceByInternet; }

    public double getBusinessClassPriceByTravelOffice() { return businessClassPriceByTravelOffice; }

    public double getBusinessClassPriceByStaff() { return businessClassPriceByStaff; }

    public double getFirstClassPriceByStaff() { return firstClassPriceByStaff; }

    public double getEconomyClassPriceByStaff() { return economyClassPriceByStaff; }
}
