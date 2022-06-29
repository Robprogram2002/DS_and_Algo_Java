package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

public class StringExamples {

    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i = 0; i < N / 2; i++)
            if (s.charAt(i) != s.charAt(N - 1 - i))
                return false;
        return true;
    }

    // check whether an array of strings is in
    //alphabetical order

    public boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        // extract file name
        //and extension from a command-line argument
        String s = args[0];
        int dot = s.indexOf(".");
        String base = s.substring(0, dot);
        String extension = s.substring(dot + 1, s.length());

        // print all lines in standard input that contain a string
        // specified on the command line
        String query = args[0];
        while (!StdIn.isEmpty()) {
            String v = StdIn.readLine();
            if (v.contains(query)) StdOut.println(v);
        }

        // create an array
        //of the strings on StdIn delimited by whitespace
        String input = StdIn.readAll();
        String[] words = input.split("\\s+");
    }

}
