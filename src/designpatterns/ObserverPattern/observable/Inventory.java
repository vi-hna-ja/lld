package designpatterns.ObserverPattern.observable;

import designpatterns.ObserverPattern.observer.Notifier;

public interface Inventory {

    void add(Notifier notifier);

    void remove(Notifier notifier);

    void setData(int newStock);

    int getData();

    void notifyUser();

}
