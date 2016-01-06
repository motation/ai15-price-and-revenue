package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by Ole on 01.12.2015.
 */
public class Revenue {
    private Long id;

    private UUID flightId;

    private double value;

    private double soldTicketsFirstClassInternet;
    private double soldTicketsEconomyClassInternet;
    private double soldTicketsFirstClassTravelOffice;
    private double soldTicketsEconomyClassTravelOffice;
    private double soldTicketsFirstClassCounter;
    private double soldTicketsEconomyClassCounter;

    protected Revenue(){

    }

    public UUID getFlightId() {
        return flightId;
    }

    public double getSoldTicketsFirstClassInternet() {
        return soldTicketsFirstClassInternet;
    }

    public double getSoldTicketsEconomyClassInternet() {
        return soldTicketsEconomyClassInternet;
    }

    public double getSoldTicketsFirstClassTravelOffice() {
        return soldTicketsFirstClassTravelOffice;
    }

    public double getSoldTicketsEconomyClassTravelOffice() {
        return soldTicketsEconomyClassTravelOffice;
    }

    public double getSoldTicketsFirstClassCounter() {
        return soldTicketsFirstClassCounter;
    }

    public double getSoldTicketsEconomyClassCounter() {
        return soldTicketsEconomyClassCounter;
    }

    public double getValue() {
        return this.value;
    }



    public static class RevenueBuilder{
        private Revenue revenue;

        public RevenueBuilder(){
            this.revenue = new Revenue();
        }

        public RevenueBuilder withValue(double value){
            revenue.value = value;
            return this;
        }

        public RevenueBuilder withSoldTicketsFirstClassInternet(double soldTicketsFirstClassInternet){
            this.revenue.soldTicketsFirstClassInternet = soldTicketsFirstClassInternet;
            return this;
        }

        public RevenueBuilder withSoldTicketsEconomyClassInternet(double  soldTicketsEconomyClassInternet){
            this.revenue.soldTicketsEconomyClassInternet = soldTicketsEconomyClassInternet;
            return this;
        }

        public RevenueBuilder withSoldTicketsFirstClassTravelOffice(double soldTicketsFirstClassTravelOffice){
            this.revenue.soldTicketsFirstClassTravelOffice = soldTicketsFirstClassTravelOffice;
            return this;
        }

        public RevenueBuilder withSoldTicketsEconomyClassTravelOffice(double soldTicketsEconomyClassTravelOffice){
            this.revenue.soldTicketsEconomyClassTravelOffice = soldTicketsEconomyClassTravelOffice;
            return this;
        }

        public RevenueBuilder withSoldTicketsFirstClassCounter(double soldTicketsFirstClassCounter){
            this.revenue.soldTicketsFirstClassCounter = soldTicketsFirstClassCounter;
            return this;
        }

        public RevenueBuilder withSoldTicketsEconomyClassCounter(double soldTicketsEconomyClassCounter){
            this.revenue.soldTicketsEconomyClassCounter = soldTicketsEconomyClassCounter;
            return this;
        }

        public RevenueBuilder withFlightId(UUID flightId){
            this.revenue.flightId = flightId;
            return this;
        }

        public Revenue build(){
            return this.revenue;
        }
    }

    public Long getId() {
        return id;
    }
}
