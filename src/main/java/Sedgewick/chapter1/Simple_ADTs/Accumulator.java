package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.Accumulatable;
import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

/**
 * Implementation of the Accumulatable interface
 */
public class Accumulator implements Accumulatable {
    private double total;
    private int N;

    /**
     * Creates an accumulator object with default initial values.
     */
    public Accumulator() {
    }

    public void addDataValue(double val) {
        this.total += val;
        N += 1;
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
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());
        StdOut.println(a);
    }
}


