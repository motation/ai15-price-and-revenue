package de.hawhamburg.microservices.composite.price.model;

import java.util.UUID;

/**
 * Created by tim on 20.10.15.
 */

public class FlightBlueprint {

    private UUID id;
    private UUID aircraftModel;
    private UUID destination;
    private UUID departure;
    private String duration;
    private boolean deleted;

    public FlightBlueprint(UUID id, UUID aircraftModel, UUID destination, UUID departure, String duration, boolean deleted) {
        this.id = id;
        this.aircraftModel = aircraftModel;
        this.destination = destination;
        this.departure = departure;
        this.duration = duration;
        this.deleted = deleted;
    }

    public FlightBlueprint() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(UUID aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public UUID getDestination() {
        return destination;
    }

    public void setDestination(UUID destination) {
        this.destination = destination;
    }

    public UUID getDeparture() {
        return departure;
    }

    public void setDeparture(UUID departure) {
        this.departure = departure;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}