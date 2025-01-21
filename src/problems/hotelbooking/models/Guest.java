package problems.hotelbooking.models;

import java.util.UUID;
import problems.hotelbooking.services.Utils;

public class Guest {
    private String guestId;
    private String email;
    private String phoneNumber;

    public Guest(String email, String phoneNumber) {
        this.guestId = Utils.generateId("GU");
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
