package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by Ole on 01.12.2015.
 */
public class Price {
    private Long id;

    private double value;

    private UUID flightId;

    protected Price() {

    }

    public double getValue() {
        return value;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public static class PriceBuilder {
        private Price price;

        public PriceBuilder() {
            this.price = new Price();
        }

        public PriceBuilder withValue(double value) {
            this.price.value = value;
            return this;
        }

        public PriceBuilder withFlightId(UUID flightId) {
            this.price.flightId = flightId;
            return this;
        }

        public Price build() {
            return this.price;
        }
    }

    public Long getId() {
        return id;
    }
}
