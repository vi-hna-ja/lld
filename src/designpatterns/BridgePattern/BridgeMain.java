package designpatterns.BridgePattern;

import designpatterns.BridgePattern.Abstractor.AdvancedRemote;
import designpatterns.BridgePattern.Abstractor.Controller;
import designpatterns.BridgePattern.Abstractor.Remote;
import designpatterns.BridgePattern.Implementor.Fridge;
import designpatterns.BridgePattern.Implementor.TV;

public class BridgeMain {

    // helps to decouple abstraction and implementation so that both can scale independently

    public void run() {
        Controller tvRemote = new Remote(new TV());
        tvRemote.volumeUp();
        tvRemote.volumeUp();
        tvRemote.volumeDown();

        AdvancedRemote fridgeRemote = new AdvancedRemote(new Fridge());
        fridgeRemote.volumeUp();
        fridgeRemote.volumeUp();
        fridgeRemote.volumeUp();
        fridgeRemote.mute();
    }
}
