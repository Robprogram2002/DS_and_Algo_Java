package Goodrich_Tamassia.Linear_DS.Stacks;

import Goodrich_Tamassia.Exceptions.EmptyException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * LIFO Leaky Stack implementation using a circular dynamic array as underlying storage.
 */
public class ArrayLeakyStack<E> implements ExtendedStack<E> {

    // -------------------- instance variables
    /**
     * Generic array used for storage of stack elements.
     */
    private E[] data;

    /**
     * Current number of elements in the stack.
     */
    private int n;

    /**
     * index of the first element in the stack
     */
    private int head;

    // -------------------- Constructors

    /**
     * Create an empty leaky-stack with given maximum length
     *
     * @param maxLength Maximum number of elements that can be stored in the stack
     */
    public ArrayLeakyStack(int maxLength) {
        if (maxLength < 0) throw new IllegalArgumentException("Maximum length argument must be a non-negative integer");
        this.data = (E[]) new Object[maxLength];
        this.n = 0;
        this.head = 0;
    }

    // ----------------- instance methods

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return this.n;
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     */
    @Override
    public void push(E e) {
        if (this.n == this.data.length) {
            this.data[this.head] = e;
            this.head = (this.head + 1) % this.data.length;
            return;
        }
        int next_index = (this.head + this.n) % this.data.length;
        this.data[next_index] = e;
        this.n++;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     * @throws EmptyException if try to retrieve an element from an empty stack
     */
    @Override
    public E top() throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException("The stack is empty");
        }
        return this.data[(this.head + this.n - 1) % this.data.length];
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     * @throws EmptyException if try to remove an element from an empty stack
     */
    @Override
    public E pop() throws EmptyException {
        if (this.isEmpty()) {
            throw new EmptyException("The stack is empty");
        }
        int last = (this.head + this.n - 1) % this.data.length;
        E answer = this.data[last];
        this.data[last] = null;
        this.n--;
        return answer;
    }

    /**
     * Transfer the elements of the current stack into other
     *
     * @param T the stack that would be used to transfer the elements
     */
    @Override
    public void transfer(ExtendedStack<E> T) {
        if (this.isEmpty()) return;

        for (int i = this.head; i < this.head + this.n; i++) {
            int current = i % this.data.length;
            T.push(this.data[current]);
            this.data[current] = null;
        }
        this.n = 0;
        this.head = 0;
    }

    /**
     * Reverse the elements of the stack. If in_place is True, then return a new Stack with the same elements but
     * in reverse order
     *
     * @param in_place whether the modifications would be made on the current stack or in a new instance.
     */
    @Override
    public ExtendedStack<E> reverse(Boolean in_place) {
        if (this.isEmpty()) throw new EmptyException("Cannot reverse an empty stack");

        if (in_place) {
            E[] temp = (E[]) new Object[this.data.length];
            for (int i = 0; i < this.n; i++) {
                temp[i] = this.data[(this.head + i) % this.data.length];
            }
            this.data = temp;
            this.head = 0;
            this.reverse_array(0, this.n - 1);
            return null;
        } else {
            ExtendedStack<E> temp = new ArrayLeakyStack<>(this.data.length);
            for (E e : this) {
                temp.push(e);
            }
            return temp;
        }
    }

    /**
     * Add all the elements of the given stack into the current stack
     *
     * @param T the stack that holds the elements to be appended
     */
    @Override
    public void extend(ExtendedStack<E> T) {
        if (T.isEmpty()) return;
        ExtendedStack<E> temp = T.reverse(false);
        for (E e : temp) {
            this.push(e);
        }
    }

    /**
     * Remove all the elements in the stack
     */
    @Override
    public void clear() {
        if (this.isEmpty()) return;

        this.data = (E[]) new Object[this.data.length];
        this.n = 0;
        this.head = 0;
    }

    // --------------- Utility methods

    /**
     * Reverse the elements in the array used to store the elements of the stack
     *
     * @param start index of the first element
     * @param last  index of the last element
     */
    private void reverse_array(int start, int last) {
        if (start < last) {
            E temp = this.data[start];
            this.data[start] = this.data[last];     // swap first and last
            this.data[last] = temp;
            this.reverse_array(start + 1, last - 1);    // recur on rest
        }
    }

    // ----------------- inherited methods

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new LeakyStackIterator();
    }


    /**
     * Iterator that go through the elements in the stack from most recent to oldest (FIFO order)
     */
    private class LeakyStackIterator implements Iterator<E> {
        private int index = n;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("there is not next element");
            }
            index--;
            return data[(head + index) % data.length];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // test methods
    }

}
