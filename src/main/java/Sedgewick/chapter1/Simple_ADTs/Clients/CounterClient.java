package Sedgewick.chapter1.Simple_ADTs.Clients;


import Sedgewick.chapter1.Simple_ADTs.Counter;
import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

public class CounterClient {

    public static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally()) return x;
        else return y;
    }

    /**
     *  Counter client that simulates T rolls of a die using an array of Counter objects
     *  to keep track of the number of occurrences of each possible value.
     * @param T Integer representing the number of times the dice will be thrown
     */
    public static void diceRolls(int T) {
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

    /**
     * Counter client that simulates T coin flips and print whether there were
     * more heads or tails
     * @param T Integer representing the number of coin flips
     */
    public static void coinFlips(int T) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int t = 0; t < T; t++)
            if (StdRandom.bernoulli(0.5)) heads.increment();
            else tails.increment();
//        StdOut.println(heads);
//        StdOut.println(tails);
//        int d = heads.tally() - tails.tally();
//        StdOut.println("delta: " + Math.abs(d));
        if (heads.tally() == tails.tally()) StdOut.println("Tie");
        else StdOut.println(max(heads, tails) + " Wins");
    }


    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        coinFlips(T);
        StdOut.println();
        diceRolls(T);
    }

}
