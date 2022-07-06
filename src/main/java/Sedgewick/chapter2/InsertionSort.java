package Sedgewick.chapter2;

import Sedgewick.libraries.StdOut;

//For each i from 0 to N-1, exchange a[i] with the entries that are smaller in a[0] through a[i-1]. As
//the index i travels from left to right, the entries to its left are in sorted order in the array, so the array
//is fully sorted when i reaches the right end

public class InsertionSort {

    // The idea behind insertion sort is that at the array is already sorted from
    // [0, i] and you want to add the element at position i+1, so
    // you 'insert' it at the appropriate location.
    public static void sort(Comparable[] a) {
        // Sort a[] into increasing order.
        int N = a.length;
        for (int i = 1; i < N; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        // sort a[lo...hi] into increasing order
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }

    // using an optimized version of insertion sort (with half exchanges and a sentinel).
    // In the worst case, this implementation makes ~ 1/2 n^2 compares to sort an array of length n.
    // So, it is not suitable for sorting large arrays (unless the number of inversions is small)

    public static void sort_optimized(Comparable[] a) {
        int n = a.length;

        // put smallest element in position to serve as sentinel
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) return;


        // insertion sort with half-exchanges
        for (int i = 2; i < n; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
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
        assert isSorted(a);
        show(a);

        Integer[] array = {10, 4, -1, 6, 8, 13, 2, 3};
        sort(array);
        assert isSorted(array);
        show(array);

        //////// ***************

        String[] a_2 = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Integer[] array_2 = {10, 4, -1, 6, 8, 13, 2, 3};
        System.out.println("Optimized version \n");
        sort_optimized(a_2);
        assert isSorted(a);
        show(a);

        sort_optimized(array_2);
        assert isSorted(array);
        show(array);

    }
}

