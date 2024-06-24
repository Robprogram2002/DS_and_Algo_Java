package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Abstract Data Type for an object that store and manage a date
 */
public interface Datable extends Comparable<Datable> {

    /**
     * @return integer between 1-12 for the month of the year
     */
    int month();

    /**
     * @return integer between 1-31 for the day of the month
     */
    int day();

    /**
     * @return positive integer representing the year
     */
    int year();
}
