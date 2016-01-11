package de.hawhamburg.microservices.composite.price.model;


import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tim on 20.10.15.
 */
@Entity
public class Flight {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    private String flightnumber;
    @Temporal(TemporalType.TIME)
    private Date delay;
    @Temporal(TemporalType.DATE)
    private Date startTime;
    private UUID aircraft;
    @ManyToOne
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