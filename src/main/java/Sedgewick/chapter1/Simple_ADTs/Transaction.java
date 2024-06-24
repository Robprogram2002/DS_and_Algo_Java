package Sedgewick.chapter1.Simple_ADTs;

import Sedgewick.chapter1.Simple_ADTs.Interfaces.Transactiable;

import java.util.Objects;

/**
 * Simple implementation of the Transactiable interface
 */
public class Transaction implements Transactiable, Comparable<Transaction> {

    private final String customer;
    private final SmartDate date;
    private final double amount;

    /**
     * Create a new transaction given a customer, a day and an amount
     *
     * @param who    name of the customer account
     * @param when   Date when the transaction occurs
     * @param amount total amount of the transaction
     * @throws IllegalArgumentException if customer string is empty
     */
    public Transaction(String who, SmartDate when, double amount) {
        if (Objects.equals(who, "") || who.length() < 3) {
            throw new IllegalArgumentException("customer name cannot be empty or lower than 3 characters");
        }
        this.customer = who;
        this.date = when;
        this.amount = amount;
    }

    /**
     * Initializes a new transaction by parsing a string of the form NAME DATE AMOUNT.
     *
     * @param transaction the string to parse
     * @throws IllegalArgumentException if {@code amount}
     *                                  is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY},
     *                                  or {@code Double.NEGATIVE_INFINITY}
     */
    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        this.customer = a[0];
        this.date = new SmartDate(a[1]);
        this.amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
    }

    public String who() {
        return customer;
    }


    public SmartDate when() {
        return date;
    }

    public double amount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount(), amount) == 0 && customer.equals(that.who()) && date.equals(that.when());
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, date, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("customer='").append(customer).append('\'');
        sb.append(", date=").append(date);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares two transactions by amount.
     *
     * @param that the other transaction
     * @return { a negative integer, zero, a positive integer}, depending
     * on whether the amount of this transaction is { less than,
     * equal to, or greater than } the amount of that transaction
     */
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount());
    }

    public static void main(String[] args) {

    }

}
