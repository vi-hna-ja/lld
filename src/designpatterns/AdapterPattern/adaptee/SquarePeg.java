package designpatterns.AdapterPattern.adaptee;

public class SquarePeg {
    private final int width;
    public SquarePeg(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }
}
