package de.hawhamburg.microservices.composite.revenue.model;


import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class PlaceDTO {
    private UUID placeId;
    private int floor;
    private int row;
    private UUID aircraftId;
    private Enums.Classes placeType;

    public PlaceDTO(UUID placeId, int floor, int row, UUID aircraftId, Enums.Classes placeType) {
        this.placeId = placeId;
        this.floor = floor;
        this.row = row;
        this.aircraftId = aircraftId;
        this.placeType = placeType;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public int getFloor() {
        return floor;
    }

    public int getRow() {
        return row;
    }

    public UUID getAircraftId() {
        return aircraftId;
    }

    public Enums.Classes getPlaceType() {
        return placeType;
    }
}
