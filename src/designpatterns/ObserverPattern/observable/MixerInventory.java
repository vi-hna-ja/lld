package designpatterns.ObserverPattern.observable;

import designpatterns.ObserverPattern.observer.Notifier;

import java.util.ArrayList;
import java.util.List;

public class MixerInventory implements Inventory {

    List<Notifier> notifierList = new ArrayList<>();
    int currentStock;

    @Override
    public void add(Notifier notifier) {
        notifierList.add(notifier);
    }

    @Override
    public void remove(Notifier notifier) {
        notifierList.remove(notifier);
    }

    @Override
    public void setData(int newStock) {
        System.out.println("Adding a new stock of: " + newStock);
        if (newStock == 0) {
            currentStock = 0;
            return;
        } else {
            currentStock += newStock;
        }

        notifyUser();
    }

    @Override
    public int getData() {
        return currentStock;
    }

    @Override
    public void notifyUser() {
        System.out.println("Notifying all users on iPhone product");
        for (Notifier notifier : notifierList) {
            notifier.update();
        }
    }
}
