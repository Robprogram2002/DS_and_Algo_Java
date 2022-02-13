package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

public class Acomulator {
    private double total;
    private int N;

    public Acomulator() {
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

    // Test code
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Acomulator a = new Acomulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());
        StdOut.println(a);
    }
}


