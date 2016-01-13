package de.hawhamburg.microservices.composite.revenue.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class Flight {

    private UUID id;

    private String flightnumber;

    private Date delay;

    private Date startTime;
    private UUID aircraft;

    private FlightBlueprint blueprint;
    private boolean deleted;

    public Flight(UUID id, String flightnumber, Date delay, Date startTime, UUID aircraft, FlightBlueprint blueprint, boolean deleted) {
        this.id = id;
        this.flightnumber = flightnumber;
        this.delay = delay;
        this.startTime = startTime;
        this.aircraft = aircraft;
        this.blueprint = blueprint;
        this.deleted = deleted;
    }

    public Flight() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public Date getDelay() {
        return delay;
    }

    public void setDelay(Date delay) {
        this.delay = delay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public UUID getAircraft() {
        return aircraft;
    }

    public void setAircraft(UUID aircraft) {
        this.aircraft = aircraft;
    }

    public FlightBlueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(FlightBlueprint blueprint) {
        this.blueprint = blueprint;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
