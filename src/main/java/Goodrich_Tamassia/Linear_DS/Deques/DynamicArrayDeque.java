package Goodrich_Tamassia.Linear_DS.Deques;

/**
 * Implementation of the Deque ADT by means of a dynamic circular array to store the elements
 *
 * @author Roberto M. R.
 */
public class DynamicArrayDeque<E> implements Deque<E> {
    /**
     * Default array initial capacity.
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    // instance variables
    /**
     * Generic array used for storage of Deque elements.
     */
    private E[] data;
    /**
     * Current number of elements in the Deque.
     */
    private int n;

    /**
     * Index of the Deque front element in the array.
     */
    private int f;
    /**
     * Current length of the array use to store Deque elements.
     */
    private int capacity;


    /**
     * Create a deque with default initial capacity
     */
    public DynamicArrayDeque() {
        this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.n = 0;
        this.f = 0;
    }
    // Methods

    /**
     * Returns the number of elements in the deque.
     *
     * @return number of elements in the deque
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Tests whether the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns, but does not remove, the element at the front of the deque.
     *
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    /**
     * Returns, but does not remove, the element at the tail of the deque.
     *
     * @return the last element of the queue (or null if empty)
     */
    @Override
    public E last() {
        if (isEmpty()) return null;
        return data[(f + n) % capacity];
    }

    /**
     * Inserts an element at the head of the deque.
     * This method runs in O(1) time.
     *
     * @param e new element to be inserted
     */
    @Override
    public void addFirst(E e) {
        if (n == capacity) {
            resize(capacity * 2);
        }
        f = (f - 1 + capacity) % capacity;
        data[f] = e;
        n++;
    }

    /**
     * Inserts an element at the rear of the deque.
     * This method runs in O(1) time.
     *
     * @param e new element to be inserted
     */
    @Override
    public void addLast(E e) {
        if (n == capacity) {
            resize(capacity * 2);
        }
        int tail = (f + n) % capacity;
        data[tail] = e;
        n++;
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeFirst() {
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
     * Removes and returns the last element of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        n--;
        int tail = (f + n) % capacity;
        E answer = data[tail];
        data[tail] = null;

        if (this.n < this.capacity / 4) {
            resize(capacity / 2);
        }

        return answer;
    }

    /**
     * Utility method to resize the length of the array use to  store the deque elements
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
     * Returns a string representation of the deque as a list of elements.
     * This method runs in O(n) time, where n is the size of the queue.
     *
     * @return textual representation of the queue.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(data[f]);
        for (int i = f + 1; i < f + n; i++) {
            sb.append(", ");
            sb.append(data[i % data.length]);
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Testing the implementation
     */
    public static void main(String[] args) {
        Deque<String> deque = new DynamicArrayDeque<>();
        System.out.println(deque.isEmpty());
        deque.addLast("Bob");
        deque.addLast("Brandom");
        System.out.println(deque.size());
        deque.addFirst("Aria");
        deque.addFirst("Angela");
        System.out.println(deque);
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque);
    }
}
