package problems.hotelbooking.services;

import java.util.Random;
import java.util.Scanner;
import problems.hotelbooking.models.PaymentDetails;
import problems.hotelbooking.models.PaymentMethod;

public class UPIPaymentStrategy extends PaymentStrategy {

    UPIPaymentStrategy() {
        super(PaymentMethod.UPI);
    }

    @Override
    public boolean pay(PaymentDetails paymentDetails) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your upi number: ");
        String crediCardNumber = scanner.next();

        System.out.printf("Processing payment of %s on upi %s\n", paymentDetails.getAmount(), crediCardNumber);
        return new Random().nextBoolean();
    }
}
