package problems.vendingmachine.states;

import problems.vendingmachine.VendingMachine;
import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public class ReadyState  implements VendingMachineState {
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int code, int quantity) {
        System.out.print("product already selected. please make payment\n");
    }

    @Override
    public void insertNote(Note note) {
        int value = note.getValue();
        int currentAmount = vendingMachine.getAmount();
        vendingMachine.setTotalAmount(currentAmount + value);
        checkState();
    }

    @Override
    public void insertCoin(Coin coin) {
        int value = coin.getValue();
        int currentAmount = vendingMachine.getAmount();
        vendingMachine.setTotalAmount(currentAmount + value);
        checkState();
    }

    @Override
    public void dispenseProduct() {
        System.out.print("please make payment\n");
    }

    @Override
    public void returnChange() {
        System.out.print("please make payment\n");
    }

    @Override
    public void cancelTransaction() {
        System.out.print("please make payment\n");
    }

    private void checkState() {
        if (vendingMachine.getAmount() >= vendingMachine.getProduct().getPrice()) {
            vendingMachine.changeState(vendingMachine.getDispenseState());
        }
    }
}
