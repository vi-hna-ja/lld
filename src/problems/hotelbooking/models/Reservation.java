package problems.hotelbooking.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import problems.hotelbooking.services.Utils;

public class Reservation {

    private String reservationId;
    private String guestId;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private double amountDue;
    private double totalAmount;

    public Reservation(String guestId, Room room, LocalDate checkInDate, LocalDate checkOutDate,
            BookingStatus bookingStatus) {
        this.reservationId = Utils.generateId("RES");
        this.guestId = guestId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
        this.amountDue = calculatePrice();
        this.totalAmount = this.amountDue;
    }

    public double calculatePrice() {
        long diffInDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return room.getPricePerNight() * diffInDays;
    }

    public void bookRoom() {
        room.markOccupied();
    }

    public void updateBookingStatus(BookingStatus status) {
        this.bookingStatus = status;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestId() {
        return guestId;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void extendReservation(int days) {
        LocalDate newCheckOutDate = checkOutDate.plusDays(days);
        this.checkOutDate = newCheckOutDate;
        this.amountDue = days * room.getPricePerNight();
    }

    public double getDueAmountForExtension(int days) {
        return this.amountDue + days * room.getPricePerNight();
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
