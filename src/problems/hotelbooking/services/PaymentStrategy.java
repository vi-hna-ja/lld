package problems.hotelbooking.services;

import problems.hotelbooking.models.Guest;
import problems.hotelbooking.models.PaymentDetails;
import problems.hotelbooking.models.PaymentMethod;
import problems.hotelbooking.models.Reservation;

public abstract class PaymentStrategy {
    protected PaymentMethod paymentMethod;
    PaymentStrategy(PaymentMethod method) {
        this.paymentMethod = method;
    }

    public abstract boolean pay(PaymentDetails paymentDetails);

}
