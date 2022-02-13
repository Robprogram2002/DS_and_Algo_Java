package Goodrich_Tamassia.Fundamental_DS.Arrays;

import java.util.Arrays;
import java.util.Random;

//By using a pseudorandom number generator to determine program values, we
//get a different input to our program each time we run it. This feature is, in fact, what
//makes pseudorandom number generators useful for testing code, particularly when
//dealing with arrays. Even so, we should not use random test runs as a replacement
//for reasoning about our code, as we might miss important special cases in test runs.
//Note, for example, that there is a slight chance that the orig and data arrays will be
//equal even after data is sorted, namely, if orig is already ordered. The odds of this
//occurring are less than 1 in 3 million, so it’s unlikely to happen during even a few
//thousand test runs; however, we need to reason that this is possible

/**
 * Program showing some array uses
 */
public class ArrayTest {

    // we can implement our own method by cloning the individual rows of an array
    public static int[][] deepClone(int[][] original) {
        int[][] backup = new int[original.length][];        // create top-level array of arrays
        for (int k = 0; k < original.length; k++)
            backup[k] = original[k].clone();                // copy row k
        return backup;
    }

    private static boolean array_equals(Object[] a, Object[] b) {
        if (a == b) return true;
        if (a.length != b.length) return false;
        if (a.getClass() != b.getClass()) return false;

        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        Random rand = new Random(); // a pseudo-random number generator
        rand.setSeed(System.currentTimeMillis()); // use current time as a seed
        // fill the data array with pseudo-random numbers from 0 to 99, inclusive
        for (int i = 0; i < data.length; i++)
            data[i] = rand.nextInt(100); // the next pseudo-random number
        int[] orig = Arrays.copyOf(data, data.length); // make a copy of the data array
        System.out.println("arrays equal before sort: " + Arrays.equals(data, orig));
        Arrays.sort(data); // sorting the data array (orig is unchanged)
        System.out.println("arrays equal after sort: " + Arrays.equals(data, orig));
        System.out.println("orig = " + Arrays.toString(orig));
        System.out.println("data = " + Arrays.toString(data));

        Double[] array_a = {2.25, 1.59, 0.0, -49.0, 100.0};
        Double[] array_b = {2.25, 1.59, 0.0, -49.0, 100.0};
        Float[] array_c = {2.25f, 1.59f, 0.0f, -49.0f, 100.0f};
        System.out.println(array_equals(array_a, array_b));
        System.out.println(array_equals(array_a, array_c));
    }
}
