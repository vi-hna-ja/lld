package problems.vendingmachine;

import problems.vendingmachine.models.Coin;
import problems.vendingmachine.models.Note;
import problems.vendingmachine.models.Product;

/*
Requirements:
    The vending machine should support multiple products with different prices and quantities.
    The machine should accept coins and notes of different denominations.
    The machine should dispense the selected product and return change if necessary.
    The machine should keep track of the available products and their quantities.
    The machine should handle multiple transactions concurrently and ensure data consistency.
    The machine should provide an interface for restocking products and collecting money.
    The machine should handle exceptional scenarios, such as insufficient funds or out-of-stock products.
 */
public class VendingMachineDemo {
    public void run() {
        // get the singleton instance
        VendingMachine vendingMachine = VendingMachine.getVendingMachineInstance();

        // initialise the products
        Product coke = new Product(101, 2, 20);
        Product pepsi = new Product(102, 3, 30);
        Product lays = new Product(103, 5, 20);
        Product marbles = new Product(104, 8, 10);

        // update inventory
        vendingMachine.addProduct(coke);
        vendingMachine.addProduct(pepsi);
        vendingMachine.addProduct(lays);
        vendingMachine.addProduct(marbles);

        // invalid product code
        vendingMachine.selectProduct(100, 1);

        vendingMachine.selectProduct(101, 1);
        vendingMachine.insertCoin(Coin.ONE);
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertNote(Note.TWENTY);
        vendingMachine.dispenseProduct();
    }
}
