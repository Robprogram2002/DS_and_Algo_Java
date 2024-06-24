package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * ADT for an object that keep track of the total of a sum of values and their mean
 */
public interface Accumulatable {
    /**
     * Add a new value to the sum total
     * @param value the double value to be added to the total
     */
    void addDataValue(double value);

    /**
     * @return The mean of all data values
     */
    double mean();
}
