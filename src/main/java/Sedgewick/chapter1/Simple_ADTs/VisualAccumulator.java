package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

/**
 * An abstract data type for accumulating data values (visual version)
 */
public class VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total / N);
    }

    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + " values): "
                + String.format("%7.5f", this.mean());
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        VisualAccumulator a = new VisualAccumulator(T, 1.0);
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());
        StdOut.println(a);
    }
}
