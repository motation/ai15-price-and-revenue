package de.hawhamburg.microservices.composite.revenue.model;

/**
 * Created by final-work on 11.01.16.
 */
public class Enums {
    public enum BookingType{
        BOOKING_TYPE_INTERNET,
        BOOKING_TYPE_COUNTER,
        BOOKING_TYPE_AGENCY,
        BOOKING_TYPE_STAFF;

        public static BookingType bookingTypeFromString(String text) {
            return BookingType.valueOf("BOOKING_TYPE_" + text.toUpperCase());
        }
    }

    public enum Classes {
        ECONOMY_CLASS,
        BUSINESS_CLASS,
        FIRST_CLASS;

        public static Classes classesFromString(String text) {
            return Classes.valueOf(text.toUpperCase() + "_CLASS");
        }
    }
}
