package de.hawhamburg.microservices.composite.price.model;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (Double.compare(price.value, value) != 0) return false;
        if (id != null ? !id.equals(price.id) : price.id != null) return false;
        return !(flightId != null ? !flightId.equals(price.flightId) : price.flightId != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        return result;
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
