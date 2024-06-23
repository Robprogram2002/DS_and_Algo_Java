package Sedgewick.chapter1;

import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdRandom;

import java.util.Arrays;

public class DrawTest {
    public static void main(String[] args) {
//        StdDraw.point(0.60, 0.50);
//        StdDraw.line(0.0, 0.0, 1.0, 1.0);
//        StdDraw.circle(0.50, 0.50, .30);
//        StdDraw.square(0.50, 0.50, .10);
//        double[] x = {0.20, 0.30, 0.22, 0.26};
//        double[] y = {0.20, 0.30, 0.22, 0.26};
//        StdDraw.polygon(x, y);

        // Draw function graphs
//        int N = 100;
//        StdDraw.setXscale(0, N);
//        StdDraw.setYscale(0, N * N);
//        StdDraw.setPenRadius(.01);
//        for (int i = 1; i <= N; i++) {
//            StdDraw.point(i, i);
//            StdDraw.point(i, i * i);
//            StdDraw.point(i, i * Math.log(i));
//        }

        // array of random values
        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N;
            double y = a[i] / 2.0;
            double rw = 0.5 / N;
            double rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }

        // sorted array of random values
//        a = new double[N];
//        for (int i = 0; i < N; i++)
//            a[i] = StdRandom.uniform();
//        Arrays.sort(a);
//        for (int i = 0; i < N; i++) {
//            double x = 1.0 * i / N;
//            double y = a[i] / 2.0;
//            double rw = 0.5 / N;
//            double rh = a[i] / 2.0;
//            StdDraw.filledRectangle(x, y, rw, rh);
//        }
    }
}
