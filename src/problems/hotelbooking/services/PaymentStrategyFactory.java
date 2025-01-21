package problems.hotelbooking.services;

import problems.hotelbooking.models.PaymentMethod;

public class PaymentStrategyFactory {

    private CreditCardPaymentStrategy creditCardPaymentStrategy;
    private CashPaymentStrategy cashPaymentStrategy;
    private UPIPaymentStrategy upiPaymentStrategy;

    public PaymentStrategyFactory() {
        this.cashPaymentStrategy = new CashPaymentStrategy();
        this.creditCardPaymentStrategy = new CreditCardPaymentStrategy();
        this.upiPaymentStrategy = new UPIPaymentStrategy();
    }

    public PaymentStrategy getPaymentStrategy(PaymentMethod method) {
        if (method == PaymentMethod.CASH)
            return cashPaymentStrategy;
        if (method == PaymentMethod.UPI)
            return upiPaymentStrategy;
        return creditCardPaymentStrategy;
    }

}
