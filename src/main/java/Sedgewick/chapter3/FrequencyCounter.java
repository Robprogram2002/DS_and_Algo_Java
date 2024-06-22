package Sedgewick.chapter3;

import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

// This ST client counts the frequency of occurrence of the strings in standard input, then prints out one that occurs
// with highest frequency. The command-line argument specifies a lower bound on the length of keys considered.

public class FrequencyCounter {

    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        ST<String, Integer> st = new SequentialSearchST<>();
        while (!StdIn.isEmpty()) {
            // Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) continue;               // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max)) max = word;
        StdOut.println(max + " " + st.get(max));
    }
}
