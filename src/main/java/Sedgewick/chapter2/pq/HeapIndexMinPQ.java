package Sedgewick.chapter2.pq;

import java.util.NoSuchElementException;

public class HeapIndexMinPQ<Item extends Comparable<Item>> implements IndexMinPQ<Item> {
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private int[] pq;       // binary heap using 1-based indexing
    private Item[] keys;    // keys[i] = priority of i
    private int[] qp;       // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private int N = 0;      // number of elements on PQ

    public HeapIndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        N = 0;
        keys = (Item[]) new Comparable[maxN + 1];    // make this of length maxN??
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }


    /**
     * insert item ; associate it with k
     *
     * @param k    index
     * @param item item associated with index k
     */
    @Override
    public void insert(int k, Item item) {
        validateIndex(k);
        if (contains(k)) throw new IllegalArgumentException("index is already in the priority queue");
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = item;
        swim(N);
    }

    /**
     * Change the item associated with k to item
     *
     * @param k    index of an item in the priority queue
     * @param item new value
     */
    @Override
    public void change(int k, Item item) {
        validateIndex(k);
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        keys[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    /**
     * is k associated with some item?
     *
     * @param k
     * @return whether there is an item associated with the given index
     */
    @Override
    public boolean contains(int k) {
        validateIndex(k);
        return qp[k] != -1;
    }

    /**
     * remove k and its associated item
     *
     * @param k
     */
    @Override
    public void delete(int k) {

    }

    /**
     * return a minimal item
     *
     * @return
     */
    @Override
    public Item min() {
        return null;
    }

    /**
     * return a minimal item’s index
     */
    @Override
    public int minIndex() {
        return 0;
    }

    /**
     * remove a minimal item and return its index
     *
     * @return
     */
    @Override
    public int delMin() {
        return 0;
    }

    /**
     * is the priority queue empty?
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * number of items in the priority queue
     */
    @Override
    public int size() {
        return N;
    }


    protected boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // throw an IllegalArgumentException if i is an invalid index
    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
        if (i >= this.keys.length) throw new IllegalArgumentException("index >= capacity: " + i);
    }
}
