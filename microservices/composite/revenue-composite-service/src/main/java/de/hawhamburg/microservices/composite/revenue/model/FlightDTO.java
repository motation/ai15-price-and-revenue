package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class FlightDTO {
    private UUID id;
    private boolean checkInReady;

    public FlightDTO(UUID id, boolean checkInReady) {
        this.id = id;
        this.checkInReady = checkInReady;
    }

    public boolean isCheckInReady() {
        return checkInReady;
    }

    public UUID getId() {
        return id;
    }
}
