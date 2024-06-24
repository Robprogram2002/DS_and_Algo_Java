package Sedgewick.chapter1.Simple_ADTs.Interfaces;

/**
 * Abstract Data Type for an object that store a date and have different methods for comparing
 * and validating dates
 */
public interface SmartDatable extends Comparable<SmartDatable> {
    /**
     * @return integer between 1-12 for the month of the year
     */
    int getMonth();

    /**
     * @return positive integer representing the year
     */
    int getYear();

    /**
     * @return integer between 1-31 for the day of the month
     */
    int getDay();

    /**
     * Returns the next date in the calendar.
     *
     * @return a SmartDate object that represents the next day after this day
     */
    SmartDatable next();

    /**
     * Compares two dates chronologically.
     *
     * @param other the other date
     * @return {@code true} if this date is after the other date; {@code false} otherwise
     */
    boolean isAfter(SmartDatable other);

    /**
     * Compares two dates chronologically.
     *
     * @param other the other date
     * @return {@code true} if this date is before the other date; {@code false} otherwise
     */
    boolean isBefore(SmartDatable other);
}
