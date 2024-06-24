package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.SmartDatable;
import Sedgewick.libraries.StdOut;

import java.util.Objects;

public class SmartDate implements SmartDatable {
    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

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
    public SmartDate(int month, int day, int year) {
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Create a date given a string
     *
     * @param date A string representation of a date using the format mm-dd-yyyy
     */
    public SmartDate(String date) {
        // expected mm-dd-yyyy
        String[] array = date.split("-");
        if (array.length != 3) throw new IllegalStateException("Invalid date string format");
        this.month = Integer.parseInt(array[0]);
        this.day = Integer.parseInt(array[1]);
        this.year = Integer.parseInt(array[2]);
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public SmartDate next() {
        if (isValid(month, day + 1, year)) return new SmartDate(month, day + 1, year);
        else if (isValid(month + 1, 1, year)) return new SmartDate(month + 1, 1, year);
        else return new SmartDate(1, 1, year + 1);
    }

    public boolean isAfter(SmartDatable that) {
        return compareTo(that) > 0;
    }

    public boolean isBefore(SmartDatable that) {
        return compareTo(that) < 0;
    }

    /**
     * Check whether the given integer inputs represent a valid date
     *
     * @param m month of the year
     * @param d day of the month
     * @param y year
     * @return a boolean value that specified if the inputs form a valid date
     */
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12) return false;
        if (d < 1 || d > DAYS[m]) return false;
        return m != 2 || d != 29 || isLeapYear(y);
    }

    /**
     * Determine whether the given year is/was a leap year
     *
     * @param y positive integer representing the year
     * @return a boolean value that specified if the given year is/was a leap year
     */
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartDate date)) return false;
        return getMonth() == date.getMonth()
                && getDay() == date.getDay()
                && getYear() == date.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonth(), getDay(), getYear());
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
    public int compareTo(SmartDatable that) {
        if (this.year < that.getYear()) return -1;
        if (this.year > that.getYear()) return +1;
        if (this.month < that.getMonth()) return -1;
        if (this.month > that.getMonth()) return +1;
        return Integer.compare(this.day, that.getDay());
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
     * Unit tests the {@code SmartDate} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        SmartDate date;
        if (args[0].length() > 2) {
            date = new SmartDate(args[0]);
        } else {
            int m = Integer.parseInt(args[0]);
            int d = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            date = new SmartDate(m, d, y);
        }

        StdOut.println(date);

        SmartDate today = new SmartDate(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        SmartDate birthday = new SmartDate(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }
}

