package problems.vendingmachine;

import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Inventory;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;
import problems.vendingmachine.states.*;

public class VendingMachine {

    private Inventory inventory;
    private int totalAmount;
    private Product product;
    private final IdleState idleState;
    private final ReadyState readyState;
    private final DispenseState dispenseState;
    private final ReturnChangeState returnChangeState;
    private final CancelTransactionState cancelTransactionState;
    private VendingMachineState currentState;

    private static VendingMachine vendingMachine;

    private VendingMachine() {
        this.inventory = new Inventory();
        this.totalAmount = 0;
        this.product = null;
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);
        cancelTransactionState = new CancelTransactionState(this);
        currentState = idleState;
    }

    public static VendingMachine getVendingMachineInstance() {
        if (vendingMachine == null) {
            synchronized (VendingMachine.class) {
                if (vendingMachine == null) {
                    vendingMachine = new VendingMachine();
                }
            }
        }

        return vendingMachine;
    }

    public void changeState(VendingMachineState nextState) {
        this.currentState = nextState;
    }

    public void selectProduct(int code, int quantity) {
        currentState.selectProduct(code, quantity);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
        inventory.removeProduct(this.product);
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public void cancel() {
        currentState.cancelTransaction();
    }

    public int getAmount() {
        return this.totalAmount;
    }

    public Product getProduct() {
        return this.product;
    }

    public IdleState getIdleState() {
        return idleState;
    }

    public ReadyState getReadyState() {
        return readyState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public ReturnChangeState getReturnChangeState() {
        return returnChangeState;
    }

    public CancelTransactionState getCancelTransactionState() {
        return cancelTransactionState;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTotalAmount(int amount) {
        this.totalAmount = amount;
    }

    public void resetAmount() {
        this.totalAmount = 0;
    }

    public void resetSelectedProduct() {
        this.product = null;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
