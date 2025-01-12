package problems.vendingmachine.states;

import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public interface VendingMachineState {
    void selectProduct(int code, int quantity);
    void insertNote(Note note);
    void insertCoin(Coin coin);
    void dispenseProduct();
    void returnChange();
    void cancelTransaction();
}
