package problems.hotelbooking.services;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import problems.hotelbooking.models.BookingStatus;
import problems.hotelbooking.models.Guest;
import problems.hotelbooking.models.PaymentDetails;
import problems.hotelbooking.models.PaymentMethod;
import problems.hotelbooking.models.Reservation;
import problems.hotelbooking.models.Room;

public class HotelManagementSystem {
    private static HotelManagementSystem hotelManagementSystem;
    private ConcurrentHashMap<String, Room> rooms;
    private ConcurrentHashMap<String, Reservation> reservations;
    private ConcurrentHashMap<String, Guest> guests;
    private PaymentStrategyFactory paymentStrategyFactory;
    private ReentrantLock reentrantLock;

    public HotelManagementSystem() {
        this.rooms = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.guests = new ConcurrentHashMap<>();
        this.paymentStrategyFactory = new PaymentStrategyFactory();
        this.reentrantLock = new ReentrantLock();
    }

    public static HotelManagementSystem getHotelManagementSystemInstance() {
        if (hotelManagementSystem == null) {
            synchronized (HotelManagementSystem.class) {
                if (hotelManagementSystem == null) {
                    hotelManagementSystem = new HotelManagementSystem();
                }
            }
        }
        return hotelManagementSystem;
    }

    public void addRoom(Room room) {
        rooms.put(room.getRoomId(), room);
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public void addGuest(Guest guest) {
        guests.put(guest.getGuestId(), guest);
    }

    public Guest getGuest(String guestId) {
        return guests.get(guestId);
    }

    public Optional<Reservation> bookRoom(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut,
            PaymentMethod paymentMethod) {
        reentrantLock.lock();
        try {
            if (!room.isAvailable()) {
                System.out.printf("Room %s is unavailable to book \n", room.getRoomId());
                return Optional.empty();
            }

            Reservation reservation = new Reservation(guest.getGuestId(), room, checkIn, checkOut,
                    BookingStatus.PENDING);
            double amountDue = reservation.calculatePrice();
            boolean paymentStatus = pay(reservation.getReservationId(), amountDue, paymentMethod);
            if (paymentStatus) {
                System.out.printf("User %s has booked a %s room for %s per night\n", guest.getGuestId(),
                        room.getRoomType(),
                        amountDue);
                reservation.updateBookingStatus(BookingStatus.BOOKED);
                reservation.setAmountDue(0);
            } else {
                System.out.printf("Payment Failed!!!. Could not process booking by guest %s on room %s\n",
                        reservation.getGuestId(), reservation.getRoom().getRoomId());
                reservation.updateBookingStatus(BookingStatus.FAILED);
            }

            reservations.put(reservation.getReservationId(), reservation);
            return Optional.of(reservation);
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void checkIn(String reservationId) {
        reentrantLock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            reservation.getRoom().markOccupied();
            System.out.printf("Guest %s checked in to room %s\n", reservation.getGuestId(), reservation.getReservationId());
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void checkOut(String reservationId) {
        reentrantLock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            reservation.getRoom().markAvailable();
            System.out.printf("Guest %s checked out of room %s\n", reservation.getGuestId(),
                    reservation.getReservationId());
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void extendStay(String reservationId, int days) {
        reentrantLock.lock();
        try {
            Reservation reservation = reservations.get(reservationId);
            double initialAmountDue = reservation.getAmountDue();
            double amountToPayForStayExtension = reservation.getDueAmountForExtension(days);
            System.out.printf("Guest %s requested to extend the stay by %s days for room %s. Payment due: %s\n",
                    reservation.getGuestId(), days, reservation.getRoom().getRoomId(), amountToPayForStayExtension);

            reservation.setAmountDue(amountToPayForStayExtension);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your preferred payment method - ");
            PaymentMethod paymentMethod = PaymentMethod.valueOf(scanner.next().toString());
            boolean paymentStatus = pay(reservationId, amountToPayForStayExtension, paymentMethod);

            if (paymentStatus) {
                reservation.extendReservation(days);
                System.out.printf("Payment succeeded. New checkout date is %s\n", reservation.getCheckOutDate());
                reservation.setAmountDue(0);
            }
            else {
                System.out.printf("Payment Failed!!!. Could not process booking by guest %s on room %s\n",
                        reservation.getGuestId(), reservation.getRoom().getRoomId());
                // rollback stay extension changes
                reservation.setAmountDue(initialAmountDue);
            }
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public synchronized void generateReport(LocalDate date) {
        int totalNumberOfReservations = 0;
        double revenueGenerated = 0;
        for (Entry<String, Reservation> entry : reservations.entrySet()) {
            Reservation res = entry.getValue();
            if (res.getCheckInDate().isAfter(date)) {
                revenueGenerated += res.getTotalAmount();
                totalNumberOfReservations += 1;
            }
        }

        System.out.printf("On date %s, reservations are %s. Revenue generated is %s",
                date, totalNumberOfReservations
                , revenueGenerated);
    }

    private synchronized boolean pay(String reservationId, double amountDue, PaymentMethod paymentMethod) {
        PaymentDetails paymentDetails = new PaymentDetails(paymentMethod, reservationId,
                amountDue);
        return paymentStrategyFactory.getPaymentStrategy(paymentMethod).pay(paymentDetails);
    }


}
