package Sedgewick.chapter1.Simple_ADTs.Interfaces;

public interface Transactiable {

    /**
     * @return customer name for this transaction
     */
    String who();

    /**
     * @return Date instance representing when the transaction occurs
     */
    SmartDatable when();

    /**
     * @return the amount of the transaction
     */
    double amount();
}
