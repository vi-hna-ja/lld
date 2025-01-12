package BridgePattern;

import BridgePattern.Abstractor.AdvancedRemote;
import BridgePattern.Abstractor.Controller;
import BridgePattern.Abstractor.Remote;
import BridgePattern.Implementor.Fridge;
import BridgePattern.Implementor.TV;

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
