package Goodrich_Tamassia.Linear_DS.Queues;

import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Implementation of the Queue ADT by means of a dynamic circular array to store the elements
 *
 * @author Roberto M. R.
 */
public class DynamicArrayQueue<E> implements CircularQueue<E> {
    /**
     * Default array initial capacity.
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    // -------------------- instance variables

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
     * Whether this stack has a fixed length
     */
    private final boolean fixed;


    // -------------------- Constructors

    /**
     * Creates an empty dynamic queue with default initial capacity.
     */
    public DynamicArrayQueue() {
        this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.n = 0;
        this.f = 0;
        this.fixed = false;
    }

    /**
     * Creates an empty fixed length queue
     *
     * @param maxLength Maximum number of elements that this queue can store
     * @throws IllegalArgumentException if maxLength is a negative integer
     */
    public DynamicArrayQueue(int maxLength) {
        if (maxLength < 0) throw new IllegalArgumentException("Maximum length must be a non-negative integer");
        this.data = (E[]) new Object[maxLength];
        this.n = 0;
        this.f = 0;
        this.fixed = true;
    }

    /**
     * Creates a dynamic queue with initial elements given by the array. The elements are added in the order they are
     * iterated
     *
     * @param c Array whose elements would be used to initialize the stack
     */
    public DynamicArrayQueue(E[] c) {
        this.data = (E[]) new Object[c.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < c.length; i++) {
            this.data[i] = c[i];
        }
        this.n = c.length;
        this.f = 0;
        this.fixed = false;
    }

    // -------------------- instance methods

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
     * @throws FullException if there exist a max length limit violation
     */
    @Override
    public void enqueue(E e) {
        if (n == this.data.length) {
            if (this.fixed) {
                throw new FullException("The queue is full cannot add new elements");
            }
            resize(this.data.length * 2);
        }
        int tail = (f + n) % this.data.length;
        data[tail] = e;
        n++;
    }

    /**
     * Returns, but does not remove, the first element of the queue.
     *
     * @return the first element of the queue (or null if empty)
     * @throws EmptyException if the queue is empty
     */
    @Override
    public E first() {
        if (isEmpty()) throw new EmptyException("queue is empty");
        return data[f];
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * @return element removed (or null if empty)
     * @throws EmptyException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) throw new EmptyException("queue is empty");

        E answer = data[f];
        data[f] = null;
        f = (f + 1) % this.data.length;
        n--;

        if (!this.fixed && this.n < this.data.length / 4) {
            resize(this.data.length / 2);
        }

        return answer;
    }

    /**
     * Rotates the front element of the queue to the back of the queue.
     * This does nothing if the queue is empty.
     */
    @Override
    public void rotate() {
        if (this.n != this.data.length) {
            E temp = this.data[this.f];
            this.data[this.f] = null;
            this.data[(this.f + this.n) % this.data.length] = temp;
        }
        // if list is full just advance the front pointer
        this.f = (this.f + 1) % this.data.length;
    }

    // -------------------- utility methods

    /**
     * Utility method to resize the length of the array use to  store the queue elements
     *
     * @param new_capacity integer representing the new capacity of the array
     */
    private void resize(int new_capacity) {
        E[] new_array = (E[]) new Object[new_capacity];
        for (int i = 0; i < this.n; i++) {
            new_array[i] = data[(f + i) % this.data.length];
        }
        data = new_array;
        this.f = 0;
    }

    // -------------------- inherited methods

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

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    /**
     * Iterator that go through the elements in the array from the oldest to the newest
     */
    private class QueueIterator implements Iterator<E> {
        private int index = f;

        @Override
        public boolean hasNext() {
            return index < f + n;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("there is not next element");
            }
            E answer = data[index % data.length];
            index++;
            return answer;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public CircularQueue<E> clone() {
        CircularQueue<E> temp = new DynamicArrayQueue<>();
        for (int i = 0; i < this.n; i++) {
            temp.enqueue(this.data[(this.f + i) % this.data.length]);
        }
        return temp;
    }

    public static void main(String[] args) {

    }


}
