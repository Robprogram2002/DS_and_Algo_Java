package Sedgewick.chapter2.pq;

public interface IndexMinPQ<Item extends Comparable<Item>> {
    /**
     * insert item ; associate it with k
     *
     * @param k    index
     * @param item item associated with index k
     */
    void insert(int k, Item item);

    /**
     * Change the item associated with k to item
     *
     * @param k    index of an item in the priority queue
     * @param item new value
     */
    void change(int k, Item item);

    /**
     * is k associated with some item?
     *
     * @param k
     * @return whether there is an item associated with the given index
     */
    boolean contains(int k);

    /**
     * remove k and its associated item
     */
    void delete(int k);

    /**
     * return a minimal item
     *
     * @return
     */
    Item min();

    /**
     * return a minimal item’s index
     */
    int minIndex();

    /**
     * remove a minimal item and return its index
     *
     * @return
     */
    int delMin();

    /**
     * is the priority queue empty?
     */
    boolean isEmpty();

    /**
     * number of items in the priority queue
     */
    int size();
}
