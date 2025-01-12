package designpatterns.AdapterPattern;

import designpatterns.AdapterPattern.adaptee.RoundHole;
import designpatterns.AdapterPattern.adaptee.RoundPeg;
import designpatterns.AdapterPattern.adaptee.SquarePeg;
import designpatterns.AdapterPattern.adapter.SquarePegAdapter;

public class AdapterMain {

    // fit round peg into round hole
    // fit square peg into round hole
    public void run() {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);

        SquarePeg smallSquarePeg = new SquarePeg(5);
        SquarePeg bigSquarePeg = new SquarePeg(10);

        System.out.println(roundHole.fits(roundPeg));
        System.out.println(roundHole.fits(new SquarePegAdapter(smallSquarePeg).convert()));
        System.out.println(roundHole.fits(new SquarePegAdapter(bigSquarePeg).convert()));
    }
}
