package Sedgewick.chapter1.Simple_ADTs.Clients;

import Sedgewick.chapter1.Simple_ADTs.Counter;
import Sedgewick.chapter1.Simple_ADTs.geometrics.Interval1D;
import Sedgewick.chapter1.Simple_ADTs.geometrics.Interval2D;
import Sedgewick.chapter1.Simple_ADTs.geometrics.Point2D;
import Sedgewick.libraries.StdOut;

public class Interval2DClient {
    public static void main(String[] args) {
        double xlo = Double.parseDouble(args[0]);
        double xhi = Double.parseDouble(args[1]);
        double ylo = Double.parseDouble(args[2]);
        double yhi = Double.parseDouble(args[3]);
        int T = Integer.parseInt(args[4]);

        Interval1D x = new Interval1D(xlo, xhi);
        Interval1D y = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(x, y);
        box.draw();

        Counter c = new Counter("Hits");
        for (int t = 0; t < T; t++) {
            double xrand = Math.random();
            double yrand = Math.random();
            Point2D p = new Point2D(xrand, yrand);
            if (box.contains(p)) c.increment();
            else p.draw();
        }

        StdOut.println(c);
        StdOut.println(box.area());
    }
}
