package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;

public class Counter implements Comparable<Counter> {
    // To define data-type values (the state of each object), we declare instance variables

    // In ADT implementations, we use private, using a Java language mechansim to enforce the idea that the
    // representation of an ADT is to be hidden from the client, and also final, if the value is not to be changed once it is initialized

    private final String name;
    private int count;

    // Every Java class has at least one constructor that establishes an object’s identity.
    // Every constructor creates an object and provides to the client a reference to that object.
    // We can overload the name and have multiple constructors with different signatures, just as with methods.


    // The default values of instance variables are 0 for primitive numeric types, false for boolean, and null for
    // reference types. These defaults may be changed by using initializing declarations for instance variables.
    // Java automatically invokes a constructor when a client program uses the keyword new. Overloaded constructors
    // are typically used to initialize instance variables to client-supplied values other than the defaults.

    public Counter(String id) {
        this.name = id;
        this.count = 0;
    }

    // implement data-type instance methods (the behavior of each object)

    public void increment() {
        this.count += 1;
    }

    public int tally() {
        return this.count;
    }

    public String get_name() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(Counter that) {
        // long form
        if (this.count < that.count) return -1;
        else if (this.count > that.count) return +1;
        else return 0;

        // Short form
        // return Integer.compare(this.count, that.count);
    }

    /**
     * Reads two command-line integers n and trials; creates n counters;
     * increments trials counters at random; and prints results.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Counter heads = new Counter("Heads");
        Counter tails = new Counter("Tails");


        heads.increment();
        heads.increment();
        tails.increment();

        System.out.println(heads + "\t" + tails);
        System.out.println(heads.tally() + "\t" + tails.tally());

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        // create n counters
        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment trials counters at random
        for (int t = 0; t < trials; t++) {
            hits[StdRandom.uniform(n)].increment();
        }

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(hits[i]);
        }

    }
}
