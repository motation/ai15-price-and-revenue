package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class Passenger {
    private UUID passengerId;
    private String passengerName;

    public Passenger(UUID passengerId, String passengerName) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
    }

    public Passenger(PassengerDTO passengerDTO) {
        this.passengerId = passengerDTO.getPassengerID();
        this.passengerName = passengerDTO.getPassengerName();
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(UUID passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (passengerId != passenger.passengerId) return false;
        return passengerName.equals(passenger.passengerName);

    }

    @Override
    public int hashCode() {
        int result = passengerId != null ? passengerId.hashCode() : 0;
        result = 31 * result + (passengerName != null ? passengerName.hashCode() : 0);
        return result;
    }
}
