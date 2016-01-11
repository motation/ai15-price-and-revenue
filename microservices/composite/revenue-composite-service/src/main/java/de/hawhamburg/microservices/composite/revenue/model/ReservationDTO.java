package de.hawhamburg.microservices.composite.revenue.model;

import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class ReservationDTO {
    private UUID reservationId;

    private UUID flightId;

    private UUID placeId;

    private boolean isCheckedIn;

    private PassengerDTO passengerDTO;

    public ReservationDTO(UUID reservationId, UUID flightId, UUID placeId, boolean isCheckedIn, PassengerDTO passengerDTO) {
        this.reservationId = reservationId;
        this.flightId = flightId;
        this.placeId = placeId;
        this.isCheckedIn = isCheckedIn;
        this.passengerDTO = passengerDTO;
    }

    public ReservationDTO(UUID flightId, boolean isCheckedIn, PassengerDTO passengerDTO) {
        this.flightId = flightId;
        this.isCheckedIn = isCheckedIn;
        this.passengerDTO = passengerDTO;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public UUID getPlaceId() {
        return placeId;
    }

    public void setPlaceId(UUID placeId) {
        this.placeId = placeId;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public PassengerDTO getPassengerDTO() {
        return passengerDTO;
    }

    public void setPassengerDTO(PassengerDTO passengerDTO) {
        this.passengerDTO = passengerDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationDTO that = (ReservationDTO) o;

        if (reservationId != that.reservationId) return false;
        if (flightId != that.flightId) return false;
        if (placeId != that.placeId) return false;
        if (isCheckedIn != that.isCheckedIn) return false;
        return !(passengerDTO != null ? !passengerDTO.equals(that.passengerDTO) : that.passengerDTO != null);

    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (isCheckedIn ? 1 : 0);
        result = 31 * result + (passengerDTO != null ? passengerDTO.hashCode() : 0);
        return result;
    }
}
