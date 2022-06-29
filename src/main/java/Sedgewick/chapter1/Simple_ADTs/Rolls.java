package Sedgewick.chapter1.Simple_ADTs;

//the code below simulates rolling a die, using an array of Counter objects to keep track of the number of occurrences
// of each possible value

import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

public class Rolls {

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int SIDES = 6;
        Counter[] rolls = new Counter[SIDES + 1];
        for (int i = 1; i <= SIDES; i++)
            rolls[i] = new Counter(i + "'s");

        for (int t = 0; t < T; t++) {
            int result = StdRandom.uniform(1, SIDES + 1);
            rolls[result].increment();
        }

        for (int i = 1; i <= SIDES; i++)
            StdOut.println(rolls[i]);
    }
}
