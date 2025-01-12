package BridgePattern.Abstractor;

import BridgePattern.Implementor.Device;

public class AdvancedRemote implements Controller {

    Device device;
    public AdvancedRemote(Device device) {
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

    public void mute() {
        System.out.println("Muting the device");
        device.setVolume(0);
    }
}
