package designpatterns.ObserverPattern;

import designpatterns.ObserverPattern.observable.IPhoneInventory;
import designpatterns.ObserverPattern.observable.Inventory;
import designpatterns.ObserverPattern.observable.MixerInventory;
import designpatterns.ObserverPattern.observer.EmailNotifier;
import designpatterns.ObserverPattern.observer.MobileNotifier;

public class ObserverMain {

    public void run() {
        // iphone observable
        Inventory iPhoneInventory = new IPhoneInventory();
        iPhoneInventory.add(new EmailNotifier("jahnavimajji06@gmail.com"));
        iPhoneInventory.add(new MobileNotifier("7989220503"));

        iPhoneInventory.setData(10);
        iPhoneInventory.setData(0);
        iPhoneInventory.setData(100);
        System.out.println("Current iphone stock in inventory: " + iPhoneInventory.getData());

        // mixer observable
        Inventory mixerInventory = new MixerInventory();
        mixerInventory.add(new EmailNotifier("jahnavi@gmail.com"));
        mixerInventory.add(new EmailNotifier("jahn@phonepe.com"));
        mixerInventory.add(new MobileNotifier("0123456789"));

        mixerInventory.setData(50);
        mixerInventory.setData(10);
        mixerInventory.setData(1000);
        System.out.println("Current mixer stock in inventory: " + mixerInventory.getData());

    }
}
