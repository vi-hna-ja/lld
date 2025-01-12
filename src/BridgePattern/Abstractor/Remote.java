package BridgePattern.Abstractor;

import BridgePattern.Implementor.Device;

public class Remote implements Controller {

    Device device;

    public Remote(Device device) {
        this.device = device;
    }

    @Override
    public void volumeUp() {
        device.setVolume(device.getVolume() + 1);
    }

    @Override
    public void volumeDown() {
        device.setVolume(device.getVolume() - 1);
    }

}
