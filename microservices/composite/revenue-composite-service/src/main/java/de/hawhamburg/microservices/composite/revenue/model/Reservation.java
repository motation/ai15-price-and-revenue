package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class Reservation {
    private UUID reservationId;
    private boolean checkedIn;
    private UUID flightId;
    private Passenger traveler;
    private Place place;
    private String reservationType;

    public Reservation(UUID reservationId, boolean checkedIn, UUID flightId, Passenger traveler, Place place, String reservationType) {
        this.reservationId = reservationId;
        this.checkedIn = checkedIn;
        this.flightId = flightId;
        this.traveler = traveler;
        this.place = place;
        this.reservationType = reservationType;
    }

    public Reservation(ReservationDTO reservationDTO) {
        this.reservationId = reservationDTO.getReservationId();
        this.checkedIn = reservationDTO.isCheckedIn();
        this.flightId = reservationDTO.getFlightId();
        this.traveler = new Passenger(reservationDTO.getPassengerDTO());
        this.place = new Place(new UUID(1,1), 1, 1, "A", "Business"); //Achtung bitte AircraftTypeCore ueberarbeiten!!
        this.reservationType = "Internet"; //Achtung bitte ReservationCore ueberarbeiten!!
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public Passenger getTraveler() {
        return traveler;
    }

    public void setTraveler(Passenger traveler) {
        this.traveler = traveler;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (reservationId != that.reservationId) return false;
        if (checkedIn != that.checkedIn) return false;
        if (flightId != that.flightId) return false;
        if (traveler != null ? !traveler.equals(that.traveler) : that.traveler != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        return !(reservationType != null ? !reservationType.equals(that.reservationType) : that.reservationType != null);

    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (checkedIn ? 1 : 0);
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        result = 31 * result + (traveler != null ? traveler.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (reservationType != null ? reservationType.hashCode() : 0);
        return result;
    }

}
