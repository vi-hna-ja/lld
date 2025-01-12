package problems.vendingmachine.models;

public class Product {
    private final int code;

    private final int quantity;
    private final int price;

    public Product(int code, int quantity, int price) {
        this.price = price;
        this.quantity = quantity;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
