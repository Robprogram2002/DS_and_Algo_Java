package Sedgewick.chapter2.pq;

public class HeapMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private Key[] pq;
    private int N = 0;      // in pq[1..N] with pq[0] unused

    public HeapMaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public HeapMaxPQ() {
        this(DEFAULT_INITIAL_SIZE);
    }

    /**
     * insert a key into the priority queue
     *
     * @param k the key to be inerted
     */
    @Override
    public void insert(Key k) {
        pq[++N] = k;
        swim(N);
    }

    /**
     * @return the largest key
     */
    @Override
    public Key max() {
        return pq[1];
    }

    /**
     * return and remove the largest key
     *
     * @return the largest key
     */
    @Override
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);        // Avoid loitering.
        return max;        // Restore heap property.
    }

    /**
     * Tests whether the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return number of elements in the priority queue
     */
    @Override
    public int size() {
        return N;
    }


    protected boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}


