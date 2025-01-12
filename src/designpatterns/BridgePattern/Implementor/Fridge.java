package designpatterns.BridgePattern.Implementor;

public class
Fridge implements Device {
    int volume;
    public Fridge() {
        this.volume = 0;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public int setVolume(int volume) {
        this.volume = volume;
        return this.volume;
    }
}
