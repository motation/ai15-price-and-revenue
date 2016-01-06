package de.hawhamburg.microservices.core.revenue.jpa.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
@Entity
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type="uuid-char")
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

    public double getValue() {
        return value;
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



    public static class RevenueBuilder{
        private Revenue revenue;

        public RevenueBuilder(){
            this.revenue = new Revenue();
        }

        public RevenueBuilder withValue(double value){
            this.revenue.value = value;
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
