package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class PassengerDTO {
    private UUID passengerID;
    private String passengerName;

    public PassengerDTO(UUID passengerID, String passengerName) {
        this.passengerID = passengerID;
        this.passengerName = passengerName;
    }


    public UUID getPassengerID() {
        return passengerID;
    }

    public String getPassengerName() {
        return passengerName;
    }
}
