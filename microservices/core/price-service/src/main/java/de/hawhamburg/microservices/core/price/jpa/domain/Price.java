package de.hawhamburg.microservices.core.price.jpa.domain;

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
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double value;

    @Type(type="uuid-char")
    private UUID flightId;

    protected Price(){

    }

    public double getValue() {
        return value;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public static class PriceBuilder{
        private Price price;

        public PriceBuilder(){
            this.price = new Price();
        }

        public PriceBuilder withValue(double value){
            this.price.value = value;
            return this;
        }

        public PriceBuilder withFlightId(UUID flightId){
            this.price.flightId = flightId;
            return this;
        }

        public Price build(){
            return this.price;
        }
    }

    public Long getId() {
        return id;
    }
}
