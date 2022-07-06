package Sedgewick.chapter2.pq;

public class HeapMinPQ<Key extends Comparable<Key>> extends HeapMaxPQ<Key> {

    public HeapMinPQ() {
        super();
    }

    public HeapMinPQ(int maxN) {
        super(maxN);
    }

    @Override
    protected boolean less(int i, int j) {
        return !super.less(i, j);
    }

    /**
     * @return the smallest key
     */
    public Key min() {
        return super.max();
    }

    /**
     * return and remove the smallest key
     *
     * @return the smallest key
     */
    public Key delMin() {
        return super.delMax();
    }

}
