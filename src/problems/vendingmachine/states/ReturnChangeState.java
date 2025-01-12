package problems.vendingmachine.states;

import problems.vendingmachine.VendingMachine;
import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

public class ReturnChangeState  implements VendingMachineState {

    private VendingMachine vendingMachine;

    public ReturnChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(int code, int quantity) {
        System.out.print("product already selected. please make payment\n");
    }

    @Override
    public void insertNote(Note note) {
        System.out.print("payment already done.\n");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.print("payment already done.\n");
    }

    @Override
    public void dispenseProduct() {
        System.out.print("payment already done.\n");
    }

    @Override
    public void returnChange() {
        int change = vendingMachine.getAmount() - vendingMachine.getProduct().getPrice();
        if (change > 0) {
            vendingMachine.setTotalAmount(change);
            vendingMachine.returnChange();
        }

        vendingMachine.resetAmount();
        vendingMachine.resetSelectedProduct();
        vendingMachine.changeState(vendingMachine.getIdleState());
    }

    @Override
    public void cancelTransaction() {
        System.out.print("cancelling transaction\n");
        if (vendingMachine.getAmount() > 0) {
            System.out.print("returning the money\n");
            returnChange();
        }
        vendingMachine.resetAmount();
        vendingMachine.resetSelectedProduct();
    }
}
