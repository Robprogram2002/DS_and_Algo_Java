package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Interface for a Counter Abstract Data Type that keep record of a count value and
 * a name identifier
 */
public interface ICounter extends Comparable<ICounter> {
    /**
     * Ger the value of the count
     * @return int representing count value
     */
    int tally();

    /**
     * Increment the value of the count by one
     */
    void increment();

    /**
     * Get the name of the counter
     * @return Counter name
     */
    String getName();
}
