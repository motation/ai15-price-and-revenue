package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class Place {
    private UUID placeId;
    private int floor;
    private int row;
    private String seat;
    private String placeType;

    public Place(UUID placeId, int floor, int row, String seat, String placeType) {
        this.placeId = placeId;
        this.floor = floor;
        this.row = row;
        this.seat = seat;
        this.placeType = placeType;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (placeId != place.placeId) return false;
        if (floor != place.floor) return false;
        if (row != place.row) return false;
        if (seat != null ? !seat.equals(place.seat) : place.seat != null) return false;
        return !(placeType != null ? !placeType.equals(place.placeType) : place.placeType != null);

    }

    @Override
    public int hashCode() {
        int result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + floor;
        result = 31 * result + row;
        result = 31 * result + (seat != null ? seat.hashCode() : 0);
        result = 31 * result + (placeType != null ? placeType.hashCode() : 0);
        return result;
    }
}
