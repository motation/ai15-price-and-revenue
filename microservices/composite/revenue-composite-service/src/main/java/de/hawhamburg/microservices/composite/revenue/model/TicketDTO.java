package de.hawhamburg.microservices.composite.revenue.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
public class TicketDTO {

    private UUID ticketId;
    private PassengerDTO bookerDTO;
    private List<ReservationDTO> reservationDTOs;
    private Enums.BookingType bookingType;
    private Date timeStamp;

    public TicketDTO(UUID ticketId, PassengerDTO bookerDTO, List<ReservationDTO> reservationDTOs, Enums.BookingType bookingType, Date timeStamp) {
        this.ticketId = ticketId;
        this.bookerDTO = bookerDTO;
        this.reservationDTOs = reservationDTOs;
        this.bookingType = bookingType;
        this.timeStamp = timeStamp;
    }

    public TicketDTO(PassengerDTO bookerDTO, List<ReservationDTO> reservationDTOs, Enums.BookingType bookingType, Date timeStamp) {
        this.bookerDTO = bookerDTO;
        this.reservationDTOs = reservationDTOs;
        this.bookingType = bookingType;
        this.timeStamp = timeStamp;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public PassengerDTO getBookerDTO() {
        return bookerDTO;
    }

    public void setBookerDTO(PassengerDTO bookerDTO) {
        this.bookerDTO = bookerDTO;
    }

    public List<ReservationDTO> getReservationDTOs() {
        return reservationDTOs;
    }

    public void setReservationDTOs(List<ReservationDTO> reservationDTOs) {
        this.reservationDTOs = reservationDTOs;
    }

    public Enums.BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(Enums.BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDTO ticketDTO = (TicketDTO) o;

        if (ticketId != ticketDTO.ticketId) return false;
        if (bookerDTO != null ? !bookerDTO.equals(ticketDTO.bookerDTO) : ticketDTO.bookerDTO != null)
            return false;
        if (reservationDTOs != null ? !reservationDTOs.equals(ticketDTO.reservationDTOs) : ticketDTO.reservationDTOs != null)
            return false;
        if (bookingType != ticketDTO.bookingType) return false;
        return !(timeStamp != null ? !timeStamp.equals(ticketDTO.timeStamp) : ticketDTO.timeStamp != null);

    }

    @Override
    public int hashCode() {
        int result = ticketId != null ? ticketId.hashCode() : 0;
        result = 31 * result + (bookerDTO != null ? bookerDTO.hashCode() : 0);
        result = 31 * result + (reservationDTOs != null ? reservationDTOs.hashCode() : 0);
        result = 31 * result + (bookingType != null ? bookingType.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }
}
