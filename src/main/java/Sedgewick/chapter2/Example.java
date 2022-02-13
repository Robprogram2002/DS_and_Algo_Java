package Sedgewick.chapter2;

import Sedgewick.libraries.StdOut;

//This class illustrates our conventions for implementing array sorts. For each sorting algorithm
//that we consider, we present a sort() method for a class like this with Example changed to a name
//that corresponds to the algorithm.

// with this code, our sort methods are effective for any type of data that implements Comparable.

public class Example {
    public static void sort(Comparable[] a) { /* See Algorithms 2.1, 2.2, 2.3, 2.4, 2.5, or 2.7. */ }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
//        String[] a = In.readStrings();
        String[] a = {"S", "O", "M", "E", "A", "R", "R", "A", "Y"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}