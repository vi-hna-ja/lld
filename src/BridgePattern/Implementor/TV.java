package BridgePattern.Implementor;

public class TV implements Device {

    int volume;
    public TV() {
        this.volume = 0;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public int setVolume(int volume) {
        System.out.println("Setting tv volume to: " + volume);
        this.volume = volume;
        return this.volume;
    }
}
