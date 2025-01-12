package problems.vendingmachine.states;

import problems.vendingmachine.VendingMachine;
import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public class CancelTransactionState  implements VendingMachineState {

    private VendingMachine vendingMachine;

    public CancelTransactionState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int code, int quantity) {

    }

    @Override
    public void insertNote(Note note) {

    }

    @Override
    public void insertCoin(Coin coin) {

    }

    @Override
    public void dispenseProduct() {

    }

    @Override
    public void returnChange() {

    }

    @Override
    public void cancelTransaction() {

    }
}
