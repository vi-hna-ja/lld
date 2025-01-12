package problems.vendingmachine.states;

import problems.vendingmachine.VendingMachine;
import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public class DispenseState  implements VendingMachineState {

    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int code, int quantity) {
        System.out.print("product already selected\n");
    }

    @Override
    public void insertNote(Note note) {
        System.out.print("payment already done. please take your product\n");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.print("payment already done. please take your product\n");
    }

    @Override
    public void dispenseProduct() {
        vendingMachine.dispenseProduct();
        vendingMachine.getInventory().removeProduct(vendingMachine.getProduct());
        vendingMachine.changeState(vendingMachine.getReturnChangeState());
    }

    @Override
    public void returnChange() {
        System.out.print("please take your product first\n");
    }

    @Override
    public void cancelTransaction() {

    }
}
