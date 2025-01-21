package problems.hotelbooking.models;

public class PaymentDetails {
    private PaymentMethod paymentMethod;
    private String reservationId;
    private double amount;

    public PaymentDetails(PaymentMethod paymentMethod, String reservationId, double amount) {
        this.paymentMethod = paymentMethod;
        this.reservationId = reservationId;
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getReservationId() {
        return reservationId;
    }

    public double getAmount() {
        return amount;
    }
}
