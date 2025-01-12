package designpatterns.AdapterPattern.adaptee;

public class RoundHole implements PlugIn {

    private final double holeRadius;
    public RoundHole(double holeRadius) {
        this.holeRadius = holeRadius;
    }

    @Override
    public boolean fits(RoundPeg peg) {
        return this.holeRadius >= peg.getRadius();
    }
}
