package de.hawhamburg.microservices.composite.revenue.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by final-work on 11.01.16.
 */
    public class Ticket {
        private UUID ticketID;
        private Passenger passenger;
        private List<Reservation> reservations;
        private String bookingType;
        private String ticketDate;

        public Ticket(UUID ticketID, Passenger passenger, List<Reservation> reservations, String bookingType, String ticketDate) {
            this.ticketID = ticketID;
            this.passenger = passenger;
            this.reservations = reservations;
            this.bookingType = bookingType;
            this.ticketDate = ticketDate;
        }

        public Ticket(TicketDTO ticketDTO) {
            this.ticketID = ticketDTO.getTicketId();
            this.passenger = new Passenger(ticketDTO.getBookerDTO());
            this.reservations = new ArrayList<>();
            for(ReservationDTO reservationDTO : ticketDTO.getReservationDTOs()) {
                this.reservations.add(new Reservation(reservationDTO));
            }
            this.bookingType = ticketDTO.getBookingType().toString();
            this.ticketDate = ticketDTO.getTimeStamp().toString();
        }

        public UUID getTicketID() {
            return ticketID;
        }

        public void setTicketID(UUID ticketID) {
            this.ticketID = ticketID;
        }

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public List<Reservation> getReservations() {
            return reservations;
        }

        public void setReservations(ArrayList<Reservation> reservations) {
            this.reservations = reservations;
        }

        public String getBookingType() {
            return bookingType;
        }

        public void setBookingType(String bookingType) {
            this.bookingType = bookingType;
        }

        public String getTicketDate() {
            return ticketDate;
        }

        public void setTicketDate(String ticketDate) {
            this.ticketDate = ticketDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Ticket ticket = (Ticket) o;

            if (ticketID != ticket.ticketID) return false;
            if (passenger != null ? !passenger.equals(ticket.passenger) : ticket.passenger != null) return false;
            if (reservations != null ? !reservations.equals(ticket.reservations) : ticket.reservations != null)
                return false;
            if (bookingType != null ? !bookingType.equals(ticket.bookingType) : ticket.bookingType != null) return false;
            return !(ticketDate != null ? !ticketDate.equals(ticket.ticketDate) : ticket.ticketDate != null);

        }

        @Override
        public int hashCode() {
            int result = ticketID != null ? ticketID.hashCode() : 0;
            result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
            result = 31 * result + (reservations != null ? reservations.hashCode() : 0);
            result = 31 * result + (bookingType != null ? bookingType.hashCode() : 0);
            result = 31 * result + (ticketDate != null ? ticketDate.hashCode() : 0);
            return result;
        }
}
