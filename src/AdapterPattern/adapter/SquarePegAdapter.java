package AdapterPattern.adapter;

import AdapterPattern.adaptee.RoundPeg;
import AdapterPattern.adaptee.SquarePeg;

public class SquarePegAdapter {

    // essentially this adapter should extend RoundPeg and override the getRadius() method.
    // This should give you the radius
    SquarePeg squarePeg;
    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    public RoundPeg convert() {
        double radius = this.squarePeg.getWidth() * Math.sqrt(2) / 2;
        return new RoundPeg(radius);
    }
}
