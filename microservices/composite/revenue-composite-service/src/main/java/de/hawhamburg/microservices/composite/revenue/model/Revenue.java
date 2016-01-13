package de.hawhamburg.microservices.composite.revenue.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Ole on 01.12.2015.
 */
public class Revenue {
    private Long id;

    private UUID flightId;

//    private double value;

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
    private double soldTicketsBusinessClassStaff;
    private double soldTicketsFirstClassStaff;

    private long timestamp;

    protected Revenue(){

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public double getSoldTicketsBusinessClassInternet() { return soldTicketsBusinessClassInternet; }

    public double getSoldTicketsBusinessClassTravelOffice() { return soldTicketsBusinessClassTravelOffice; }

    public double getSoldTicketsBusinessClassCounter() { return soldTicketsBusinessClassCounter; }

    public double getSoldTicketsEconomyClassStaff() { return soldTicketsEconomyClassStaff; }

    public double getSoldTicketsBusinessClassStaff() { return soldTicketsBusinessClassStaff; }

    public double getSoldTicketsFirstClassStaff() { return soldTicketsFirstClassStaff; }

    public void setSoldTicketsFirstClassInternet(double soldTicketsFirstClassInternet) {
        this.soldTicketsFirstClassInternet = soldTicketsFirstClassInternet;
    }

    public void setSoldTicketsBusinessClassInternet(double soldTicketsBusinessClassInternet) {
        this.soldTicketsBusinessClassInternet = soldTicketsBusinessClassInternet;
    }

    public void setSoldTicketsEconomyClassInternet(double soldTicketsEconomyClassInternet) {
        this.soldTicketsEconomyClassInternet = soldTicketsEconomyClassInternet;
    }

    public void setSoldTicketsFirstClassTravelOffice(double soldTicketsFirstClassTravelOffice) {
        this.soldTicketsFirstClassTravelOffice = soldTicketsFirstClassTravelOffice;
    }

    public void setSoldTicketsBusinessClassTravelOffice(double soldTicketsBusinessClassTravelOffice) {
        this.soldTicketsBusinessClassTravelOffice = soldTicketsBusinessClassTravelOffice;
    }

    public void setSoldTicketsEconomyClassTravelOffice(double soldTicketsEconomyClassTravelOffice) {
        this.soldTicketsEconomyClassTravelOffice = soldTicketsEconomyClassTravelOffice;
    }

    public void setSoldTicketsFirstClassCounter(double soldTicketsFirstClassCounter) {
        this.soldTicketsFirstClassCounter = soldTicketsFirstClassCounter;
    }

    public void setSoldTicketsBusinessClassCounter(double soldTicketsBusinessClassCounter) {
        this.soldTicketsBusinessClassCounter = soldTicketsBusinessClassCounter;
    }

    public void setSoldTicketsEconomyClassCounter(double soldTicketsEconomyClassCounter) {
        this.soldTicketsEconomyClassCounter = soldTicketsEconomyClassCounter;
    }

    public void setSoldTicketsEconomyClassStaff(double soldTicketsEconomyClassStaff) {
        this.soldTicketsEconomyClassStaff = soldTicketsEconomyClassStaff;
    }

    public void setSoldTicketsBusinessClassStaff(double soldTicketsBusinessClassStaff) {
        this.soldTicketsBusinessClassStaff = soldTicketsBusinessClassStaff;
    }

    public void setSoldTicketsFirstClassStaff(double soldTicketsFirstClassStaff) {
        this.soldTicketsFirstClassStaff = soldTicketsFirstClassStaff;
    }

    public static class RevenueBuilder{
        private Revenue revenue;

        public RevenueBuilder(){
            this.revenue = new Revenue();
        }

//        public RevenueBuilder withValue(double value){
//            this.revenue.soldTicketsFirstClassInternet = value;
//            return this;
//        }

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

        public RevenueBuilder withSoldTicketsBusinessClassInternet(double soldTicketsBusinessClassInternet){
            this.revenue.soldTicketsBusinessClassInternet = soldTicketsBusinessClassInternet;
            return this;
        }

        public RevenueBuilder withsoldTicketsBusinessClassTravelOffice(double soldTicketsBusinessClassTravelOffice){
            this.revenue.soldTicketsBusinessClassTravelOffice = soldTicketsBusinessClassTravelOffice;
            return this;
        }

        public RevenueBuilder withsoldTicketsBusinessClassCounter(double soldTicketsBusinessClassCounter){
            this.revenue.soldTicketsBusinessClassCounter = soldTicketsBusinessClassCounter;
            return this;
        }

        public RevenueBuilder withsoldTicketsEconomyClassStaff(double soldTicketsEconomyClassStaff){
            this.revenue.soldTicketsEconomyClassStaff = soldTicketsEconomyClassStaff;
            return this;
        }

        public RevenueBuilder withsoldTicketsBusinessClassStaff(double soldTicketsBusinessClassStaff){
            this.revenue.soldTicketsBusinessClassStaff = soldTicketsBusinessClassStaff;
            return this;
        }

        public RevenueBuilder withsoldTicketsFirstClassStaff(double soldTicketsFirstClassStaff){
            this.revenue.soldTicketsFirstClassStaff = soldTicketsFirstClassStaff;
            return this;
        }

        public RevenueBuilder withFlightId(UUID flightId){
            this.revenue.flightId = flightId;
            return this;
        }

        public RevenueBuilder withTimestamp(long timestamp){
            this.revenue.timestamp= timestamp;
            return this;
        }

        public Revenue build(){
            return this.revenue;
        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revenue revenue = (Revenue) o;

        if (Double.compare(revenue.soldTicketsFirstClassInternet, soldTicketsFirstClassInternet) != 0) return false;
        if (Double.compare(revenue.soldTicketsBusinessClassInternet, soldTicketsBusinessClassInternet) != 0)
            return false;
        if (Double.compare(revenue.soldTicketsEconomyClassInternet, soldTicketsEconomyClassInternet) != 0) return false;
        if (Double.compare(revenue.soldTicketsFirstClassTravelOffice, soldTicketsFirstClassTravelOffice) != 0)
            return false;
        if (Double.compare(revenue.soldTicketsBusinessClassTravelOffice, soldTicketsBusinessClassTravelOffice) != 0)
            return false;
        if (Double.compare(revenue.soldTicketsEconomyClassTravelOffice, soldTicketsEconomyClassTravelOffice) != 0)
            return false;
        if (Double.compare(revenue.soldTicketsFirstClassCounter, soldTicketsFirstClassCounter) != 0) return false;
        if (Double.compare(revenue.soldTicketsBusinessClassCounter, soldTicketsBusinessClassCounter) != 0) return false;
        if (Double.compare(revenue.soldTicketsEconomyClassCounter, soldTicketsEconomyClassCounter) != 0) return false;
        if (Double.compare(revenue.soldTicketsEconomyClassStaff, soldTicketsEconomyClassStaff) != 0) return false;
        if (Double.compare(revenue.soldTicketsBusinessClassStaff, soldTicketsBusinessClassStaff) != 0) return false;
        if (Double.compare(revenue.soldTicketsFirstClassStaff, soldTicketsFirstClassStaff) != 0) return false;
        if (id != null ? !id.equals(revenue.id) : revenue.id != null) return false;
        return flightId != null ? flightId.equals(revenue.flightId) : revenue.flightId == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        temp = Double.doubleToLongBits(soldTicketsFirstClassInternet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsBusinessClassInternet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsEconomyClassInternet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsFirstClassTravelOffice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsBusinessClassTravelOffice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsEconomyClassTravelOffice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsFirstClassCounter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsBusinessClassCounter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsEconomyClassCounter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsEconomyClassStaff);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsBusinessClassStaff);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(soldTicketsFirstClassStaff);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
