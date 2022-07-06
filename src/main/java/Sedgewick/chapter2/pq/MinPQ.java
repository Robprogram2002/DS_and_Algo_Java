package Sedgewick.chapter2.pq;

public interface MinPQ<Key extends Comparable<Key>> {
    /**
     * insert a key into the priority queue
     *
     * @param k the key to be inerted
     */
    void insert(Key k);

    /**
     * @return the smallest key
     */
    Key min();

    /**
     * return and remove the smallest key
     *
     * @return the smallest key
     */
    Key delMin();

    /**
     * Tests whether the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return number of elements in the priority queue
     */
    int size();
}
