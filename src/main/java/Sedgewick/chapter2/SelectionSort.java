package Sedgewick.chapter2;

import Sedgewick.libraries.StdOut;

public class SelectionSort {

    // For each i, this implementation puts the ith smallest item in a[i]. The entries to the left of position
    // i are the i smallest items in the array and are not examined again.

    public static void sort(Comparable[] a) {
        // Sort a[] into increasing order.
        int N = a.length;

        for (int i = 0; i < N; i++) {
            // Exchange a[i] with smallest entry in a[i+1...N).
            // index of minimal entry.
            int current_min_index = i;

            for (int j = i; j < N; j++) {
                if (less(a[j], a[current_min_index])) {
                    current_min_index = j;
                }
            }
            exch(a, i, current_min_index);
//            System.out.println("Step" + i);
//            System.out.println(Arrays.toString(a));
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) { // Print the array, on a single line.
        for (Comparable comparable : a) StdOut.print(comparable + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) { // Read strings from standard input, sort them, and print.
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a) : "Not a good work";
        show(a);

        Integer[] array = {10, 4, -1, 6, 8, 13, 2, 3};
        sort(array);
        assert isSorted(array);
        show(array);
    }

}
