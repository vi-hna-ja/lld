package problems.hotelbooking;

import java.time.LocalDate;
import java.util.Optional;
import problems.hotelbooking.models.Guest;
import problems.hotelbooking.models.PaymentMethod;
import problems.hotelbooking.models.Reservation;
import problems.hotelbooking.models.Room;
import problems.hotelbooking.models.RoomType;
import problems.hotelbooking.services.HotelManagementSystem;

public class HotelManagementSystemDemo {

    public void run() {
        HotelManagementSystem system = HotelManagementSystem.getHotelManagementSystemInstance();
        Guest guest1 = new Guest("e1@email.com", "00001");
        Guest guest2 = new Guest("e2@email.com", "00002");
        Guest guest3 = new Guest("e3@email.com", "00003");
        system.addGuest(guest1);
        system.addGuest(guest2);
        system.addGuest(guest3);

        Room room1 = new Room(RoomType.STANDARD, 10);
        Room room2 = new Room(RoomType.PREMIUM, 25);
        Room room3 = new Room(RoomType.VIP, 35);

        system.addRoom(room1);
        system.addRoom(room2);
        system.addRoom(room3);

        LocalDate checkInDate = LocalDate.now();
        LocalDate checkOutDate = checkInDate.plusDays(3);
        Optional<Reservation> reservation = system.bookRoom(guest1, room1, checkInDate, checkOutDate, PaymentMethod.CASH);
        if (reservation.isPresent()) {
            System.out.println("Reservation created: " + reservation.get().getReservationId());
        } else {
            System.out.println("Room not available for booking.");
        }

        system.checkIn(reservation.get().getReservationId());
        system.extendStay(reservation.get().getReservationId(), 2);
        system.checkOut(reservation.get().getReservationId());
        system.generateReport(LocalDate.now());
    }

}
