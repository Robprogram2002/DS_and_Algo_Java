package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.Accumulatable;
import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

/**
 * Visual implementation of the abstract data type for accumulating data values
 */
public class VisualAccumulator implements Accumulatable {
    private double total;
    private int N;

    /**
     * Create an object with default initial values and set the parameters for the axis scales
     * @param trials Maximum number of values that will be sum
     * @param max Maximum value of the y-axis scale
     */
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
