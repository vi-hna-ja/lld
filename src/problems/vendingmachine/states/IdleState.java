package problems.vendingmachine.states;

import problems.vendingmachine.VendingMachine;
import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Inventory;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public class IdleState implements VendingMachineState {
    private final VendingMachine vendingMachine;
    private final Inventory inventory;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.inventory = vendingMachine.getInventory();
    }

    @Override
    public void selectProduct(int code, int quantity) {
        Integer price = inventory.getPrice(code);
        if (price == null) {
            System.out.printf("invalid product code %s\n", code);
            return;
        }

        price *= quantity;
        Product product = new Product(code, quantity, price);

        if (!inventory.checkInventory(product)) {
            System.out.printf("not enough products to select %s. please select another product\n", code);
            return;
        }

        vendingMachine.setProduct(product);
        vendingMachine.changeState(vendingMachine.getReadyState());
    }

    @Override
    public void insertNote(Note note) {
        System.out.print("please select a product first\n");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.print("please select a product first\n");
    }

    @Override
    public void dispenseProduct() {
        System.out.print("please select a product first\n");
    }

    @Override
    public void returnChange() {
        System.out.print("please select a product first\n");
    }

    @Override
    public void cancelTransaction() {
        System.out.print("please select a product first\n");
    }
}
