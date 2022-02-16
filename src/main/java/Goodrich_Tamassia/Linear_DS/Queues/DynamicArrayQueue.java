package Goodrich_Tamassia.Linear_DS.Queues;

/**
 * Implementation of the Queue ADT by means of a dynamic circular array to store the elements
 *
 * @author Roberto M. R.
 */
public class DynamicArrayQueue<E> implements Queue<E> {
    /**
     * Default array initial capacity.
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    // instance variables
    /**
     * Generic array used for storage of queue elements.
     */
    private E[] data;
    /**
     * Current number of elements in the queue.
     */
    private int n;

    /**
     * Index of the top element of the queue in the array.
     */
    private int f;
    /**
     * Current length of the array use to store queue elements.
     */
    private int capacity;

    // constructors

    /**
     * Creates a queue with default initial capacity.
     */
    public DynamicArrayQueue() {
        this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.n = 0;
        this.f = 0;
    }

    // Methods

    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue
     */
    @Override
    public int size() {
        return this.n;
    }

    /**
     * Tests whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Inserts an element at the rear of the queue.
     * This method runs in O(1) time.
     *
     * @param e new element to be inserted
     */
    @Override
    public void enqueue(E e) {
        if (n == capacity) {
            resize(capacity * 2);
        }
        int tail = (f + n) % capacity;
        data[tail] = e;
        n++;
    }

    /**
     * Returns, but does not remove, the first element of the queue.
     *
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        if (isEmpty()) return null;

        E answer = data[f];
        data[f] = null;
        f = (f + 1) % this.capacity;
        n--;

        if (this.n < this.capacity / 4) {
            resize(capacity / 2);
        }

        return answer;
    }

    /**
     * Utility method to resize the length of the array use to  store the queue elements
     *
     * @param new_capacity integer representing the new capacity of the array
     */
    private void resize(int new_capacity) {
        E[] new_array = (E[]) new Object[new_capacity];
        for (int i = 0; i < this.n; i++) {
            new_array[i] = data[(f + i) % capacity];
        }
        data = new_array;
        capacity = new_capacity;
    }

    /**
     * Returns a string representation of the queue as a list of elements.
     * This method runs in O(n) time, where n is the size of the queue.
     *
     * @return textual representation of the queue.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        int k = f;
        for (int j = 0; j < this.n; j++) {
            if (j > 0) sb.append(", ");
            sb.append(data[k]);
            k = (k + 1) % data.length;
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
