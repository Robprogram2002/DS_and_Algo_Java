package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.libraries.StdOut;

import java.util.Objects;

// The straightforward implementation at first maintains the day, month, and year as instance variables, so that the
// instance methods can just return the appropriate value; next the more space-efficient implementation  uses
// only a single int value to represent a date, using a mixed-radix number that represents the date with day d, month
// m, and year y as 512y + 32m + d.

// A way that a client might notice the difference between the two implementations is performance: the implementation
// on the right uses less space to hold data-type values at the cost of more time to provide them to the client in the
// agreed form (one or two arithmetic operations are needed)

// Such tradeoffs are common: one client may prefer one of the implementations and another client might prefer the
// other, so we need to accommodate both.
// One of the key advantages of using data abstraction in our implementations is that we can normally change from one
// implementation to another without changing any client code.

public class Date implements Comparable<Date> {
    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int month; // month (between 1 and 12)
    private final int day;   // day   (between 1 and DAYS[month]
    private final int year;  // positive integer

    public Date(int month, int day, int year) {
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String date) {
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


    /**
     * Returns the next date in the calendar.
     *
     * @return a date that represents the next day after this day
     */
    public Date next() {
        if (isValid(month, day + 1, year)) return new Date(month, day + 1, year);
        else if (isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
        else return new Date(1, 1, year + 1);
    }

    /**
     * Compares two dates chronologically.
     *
     * @param that the other date
     * @return {@code true} if this date is after that date; {@code false} otherwise
     */
    public boolean isAfter(Date that) {
        return compareTo(that) > 0;
    }

    /**
     * Compares two dates chronologically.
     *
     * @param that the other date
     * @return {@code true} if this date is before that date; {@code false} otherwise
     */
    public boolean isBefore(Date that) {
        return compareTo(that) < 0;
    }


    // is the given date valid?
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12) return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date date)) return false;
        return getMonth() == date.getMonth() && getDay() == date.getDay() && getYear() == date.getYear();

//        other way of do this is
//        if (this == o) return true;
//        if (o == null) return false;
//        if (this.getClass() != o.getClass()) return false;
//        Date that = (Date) o;
//        if (this.day != that.day) return false;
//        if (this.month != that.month) return false;
//        if (this.year != that.year) return false;
//
//        return true;

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
     * the argument date; and a positive ineger if this date is chronologically
     * after the argument date
     */
    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return +1;
        return 0;
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

        Date date;
        if (args[0].length() > 2) {
            date = new Date(args[0]);
        } else {
            int m = Integer.parseInt(args[0]);
            int d = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            date = new Date(m, d, y);
        }

        StdOut.println(date);

        Date today = new Date(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
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