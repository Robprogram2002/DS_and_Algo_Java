package Sedgewick.chapter1;


// Compute the greatest common divisor of two nonnegative integers p and q as follows:
// If q is 0, the answer is p. If not, divide p by q and take the reminder r. The answer
// is the greatest common divisor of q and r.


import Sedgewick.libraries.StdDraw;
import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

import java.util.Arrays;

public class Test {

    // Euclidean algorithm
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = q % p;
        return gcd(p, r);
    }

    // typical array-processing functions
    public static void array_methods(int[] a) {
        // find the maximum of the array values
        double max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) max = a[i];

        StdOut.println("Max number in the array is %s".formatted(max));

        // compute the average of the array values
        int N = a.length;
        double sum = 0.0;
        for (int i = 0; i < N; i++)
            sum += a[i];
        double average = sum / N;
        StdOut.println("Average of the values in the array is %s".formatted(average));

        // copy to another array
        double[] b = new double[N];
        for (int i = 0; i < N; i++)
            b[i] = a[i];
        StdOut.println("The contents of the copy array are %s".formatted(Arrays.toString(b)));

        // reverse the elements within an array
        for (int i = 0; i < N / 2; i++) {
            int temp = a[i];
            a[i] = a[N - 1 - i];
            a[N - i - 1] = temp;
        }
        StdOut.println("The contents of the reverse array are %s".formatted(Arrays.toString(a)));

        // matrix-matrix multiplication (square matrices)
//        int[][] c = new int[N][N];
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//            { // Compute dot product of row i and column j.
//                for (int k = 0; k < N; k++)
//                    c[i][j] += a[i][k]*b[k][j];
//            }
    }

    // create-and-initialize idiom for arrays
    public static double[][] zero_matrix(int M, int N) {
        double[][] a = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = 0.0;
        return a;
    }

    // Other examples of static methods

    public static int abs(int x) {
        // absolute value of an int value
        if (x < 0) return -x;
        else return x;
    }

    public static double abs(double x) {
        // absolute value of a double value
        if (x < 0.0) return -x;
        else return x;
    }

    public static boolean isPrime(int N) {
        // Prime test
        if (N < 2) return false;
        for (int i = 2; i * i <= N; i++)
            if (N % i == 0) return false;
        return true;
    }

    public static double H(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++) sum += 1.0 / i;
        return sum;
    }

    public static void main(String[] args) {
        int result = gcd(5, 20);
        StdOut.println(result);

        // example of StdOut format print method
        StdOut.printf("PI is approximately %.2f\n", Math.PI);

        // example of StdIn library : Average the numbers on StdIn.
        double sum = 0.0;
        int count = 0;
        while (!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            count++;
        }
        double avg = sum / count;
        StdOut.printf("Average is %.5f\n", avg);
    }
}


