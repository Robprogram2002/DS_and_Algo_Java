package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.Datable;
import Sedgewick.libraries.StdOut;

import java.util.Objects;

/**
 * Simple implementation of the Datable Interface
 */
public class Date implements Datable {

    private final int month;
    private final int day;
    private final int year;

    /**
     * Create a date given integer values for the month, day and year
     *
     * @param month integer between 1-12 representing the month of the year
     * @param day   integer between 1-31 representing the day of the year
     * @param year  positive integer representing the year
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Create a date given a string
     *
     * @param date A string representation of a date using the format mm-dd-yyyy
     */
    public Date(String date) {
        // expected mm-dd-yyyy
        String[] array = date.split("-");
        if (array.length != 3) throw new IllegalStateException("Invalid date string format");
        this.month = Integer.parseInt(array[0]);
        this.day = Integer.parseInt(array[1]);
        this.year = Integer.parseInt(array[2]);
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;

        if (this.day != date.day()) return false;
        if (this.month != date.month()) return false;
        return this.year == date.year();
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    /**
     * Compares two dates chronologically.
     *
     * @return the value {@code 0} if the argument date is equal to this date;
     * a negative integer if this date is chronologically less than
     * the argument date; and a positive integer if this date is chronologically
     * after the argument date
     */
    @Override
    public int compareTo(Datable that) {
        if (this.year < that.year()) return -1;
        if (this.year > that.year()) return +1;
        if (this.month < that.month()) return -1;
        if (this.month > that.month()) return +1;
        return Integer.compare(this.day, that.day());
    }

    /**
     * Returns a string representation of this date.
     *
     * @return the string representation in the format MM/DD/YYYY
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }


    /**
     * Unit tests the {@code Date} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        Date date = new Date(m, d, y);
        StdOut.println(date);
    }
}


// Alternative implementation

//public class Date {
//    private final int value;
//
//    public Date(int m, int d, int y) {
//        value = y * 512 + m * 32 + d;
//    }
//
//    public int month() {
//        return (value / 32) % 16;
//    }
//
//    public int day() {
//        return value % 32;
//    }
//
//    public int year() {
//        return value / 512;
//    }
//
//    public String toString() {
//        return month() + "/" + day() + "/" + year();
//    }
//
//}