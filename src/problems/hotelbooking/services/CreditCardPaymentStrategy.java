package problems.hotelbooking.services;

import java.util.Random;
import java.util.Scanner;
import problems.hotelbooking.models.PaymentDetails;
import problems.hotelbooking.models.PaymentMethod;

public class CreditCardPaymentStrategy extends PaymentStrategy {

    CreditCardPaymentStrategy() {
        super(PaymentMethod.CREDIT_CARD);
    }

    @Override
    public boolean pay(PaymentDetails paymentDetails) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your credit card number: ");
        String creditCardNumber = scanner.next();

        System.out.printf("Processing payment of %s on card %s\n", paymentDetails.getAmount(), creditCardNumber);
        return new Random().nextBoolean();
    }
}
