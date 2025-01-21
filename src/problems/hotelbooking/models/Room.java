package problems.hotelbooking.models;

import java.util.UUID;
import problems.hotelbooking.services.Utils;

public class Room {
    private String roomId;
    private RoomType roomType;
    private boolean available;
    private double pricePerNight;

    public Room(RoomType roomType, double pricePerNight) {
        this.roomId = Utils.generateId("ROO");
        this.roomType = roomType;
        this.available = true;
        this.pricePerNight = pricePerNight;
    }

    public void markOccupied() {
        this.available = false;
    }

    public void markAvailable() {
        this.available = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
