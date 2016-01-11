package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class Flight {
    private UUID flightId;
    private boolean checkInReady;

    public Flight(UUID flightId, boolean checkInReady) {
        this.flightId = flightId;
        this.checkInReady = checkInReady;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public boolean isCheckInReady() {
        return checkInReady;
    }

    public void setCheckInReady(boolean checkInReady) {
        this.checkInReady = checkInReady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (flightId != flight.flightId) return false;
        return checkInReady == flight.checkInReady;

    }

    @Override
    public int hashCode() {
        int result = flightId.hashCode();
        result = 31 * result + (checkInReady ? 1 : 0);
        return result;
    }
}
