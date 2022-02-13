package Sedgewick.chapter2;

import Sedgewick.libraries.StdOut;

public class MergeBU {

    private MergeBU() {
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    // merge by copying everything to an auxiliary array and then merging back to the original.
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {  // there are four conditions:

            //  left half exhausted (take from the right),
            if (i > mid) a[k] = aux[j++];
                // right half exhausted (take from the left)
            else if (j > hi) a[k] = aux[i++];
                // current key on right less than current key on left (take from the right)
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
                // current key on right greater than or equal to current key on left (take from the left)
            else a[k] = aux[i++];
        }

        // post condition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    //Bottom-up mergesort consists of a sequence of passes over the whole array, doing sz-by-sz merges,
    //starting with sz equal to 1 and doubling sz on each pass. The final subarray is of size sz only when
    //the array size is an even multiple of sz (otherwise it is less than sz).
    public static void sort(Comparable[] a) {
        // Do lg N passes of pairwise merges.
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
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

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
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
    }
}
