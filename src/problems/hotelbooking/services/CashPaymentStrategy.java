package problems.hotelbooking.services;

import problems.hotelbooking.models.PaymentDetails;
import problems.hotelbooking.models.PaymentMethod;

public class CashPaymentStrategy extends PaymentStrategy {

    CashPaymentStrategy() {
        super(PaymentMethod.CASH);
    }

    @Override
    public boolean pay(PaymentDetails paymentDetails) {
        return true;
    }
}
