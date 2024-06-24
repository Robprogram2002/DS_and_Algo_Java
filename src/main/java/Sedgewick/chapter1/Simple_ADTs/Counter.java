package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.ICounter;
import Sedgewick.libraries.StdOut;
import Sedgewick.libraries.StdRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Simple implementation of the ICounter ADT
 */
public class Counter implements ICounter {

    // Instance Variables
    /**
     * String that stored the public name of the counter
     */
    private final String name;
    /**
     * Integer storing the count value
     */
    private int count;

    // Constructor methods
    /**
     * Create a counter with initial count to 0
     *
     * @param id String represented the name of the counter
     */
    public Counter(String id) {
        this.name = id;
    }

    /**
     * Create a counter with initial given count
     *
     * @param name  String represented the name of the counter
     * @param count Initial count
     */
    public Counter(String name, int count) {
        this(name);
        if (count < 0) {
            throw new IllegalArgumentException("initial count must be a non-negative integer");
        }
        this.count = count;
    }

    // Instance Methods
    public void increment() {
        this.count += 1;
    }

    public int tally() {
        return this.count;
    }

    public String getName() {
        return this.name;
    }

    // Override Methods
    @Override
    public String toString() {
        return "Counter{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(@NotNull ICounter o) {
        return Integer.compare(this.count, o.tally());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter counter = (Counter) o;
        return count == counter.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    // Unit test
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

// To define data-type values (the state of each object), we declare instance variables

// In ADT implementations, we use private, using a Java language mechanism to enforce the idea that the
// representation of an ADT is to be hidden from the client, and also final, if the value is not to be changed
// once it is initialized

// Every Java class has at least one constructor that establishes an object’s identity.
// Every constructor creates an object and provides to the client a reference to that object.
// We can overload the name and have multiple constructors with different signatures, just as with methods.

// The default values of instance variables are 0 for primitive numeric types, false for boolean, and null for
// reference types. These defaults may be changed by using initializing declarations for instance variables.
// Java automatically invokes a constructor when a client program uses the keyword new. Overloaded constructors
// are typically used to initialize instance variables to client-supplied values other than the defaults.
