package Sedgewick.chapter1.Simple_ADTs;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Transaction implements Comparable<Transaction> {
    private final String customer;
    private final Date date;
    private final double amount;


    /**
     * Create a new transaction given a customer, a day and an amount
     *
     * @param who    name of the customer account
     * @param when   Date when the transaction occurs
     * @param amount total amount of the transaction
     * @throws IllegalArgumentException if customer string is empty
     */
    public Transaction(String who, Date when, double amount) {
        if (Objects.equals(who, "")) {
            throw new IllegalArgumentException("customer name cannot be empty");
        }

        this.customer = who;
        this.date = when;
        this.amount = amount;

    }

    /**
     * @return customer name for this transaction
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @return Date instance representing when the transaction occurs
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.getAmount(), getAmount()) == 0 && getCustomer().equals(that.getCustomer()) && getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getDate(), getAmount());
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

    @Override
    public int compareTo(@NotNull Transaction o) {
        return 0;
    }
}
