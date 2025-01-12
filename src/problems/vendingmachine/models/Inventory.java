package problems.vendingmachine.models;

import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

    private ConcurrentHashMap<Integer, Integer> priceMap;
    private ConcurrentHashMap<Integer, Integer> inventoryMap;
    public Inventory() {
        this.priceMap = new ConcurrentHashMap<>();
        this.inventoryMap = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product) {
        int productCode = product.getCode();
        inventoryMap.put(productCode,
                inventoryMap.getOrDefault(productCode, 0) + product.getQuantity());
        priceMap.put(productCode, product.getPrice());
    }

    public void removeProduct(Product product) {
        int productCode = product.getCode();
        inventoryMap.put(productCode,
                inventoryMap.getOrDefault(productCode, 0) - product.getQuantity());
        if (inventoryMap.get(productCode) == 0) {
            inventoryMap.remove(productCode);
            priceMap.remove(productCode);
        }
    }

    public boolean checkInventory(Product product) {
        return inventoryMap.containsKey(product.getCode())
                && inventoryMap.get(product.getCode()) >= product.getQuantity();
    }

    public Integer getPrice(int code) {
        if (priceMap.containsKey(code))
            return priceMap.get(code);
        return null;
    }
}
