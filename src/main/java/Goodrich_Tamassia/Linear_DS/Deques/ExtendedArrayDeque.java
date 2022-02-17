package Goodrich_Tamassia.Linear_DS.Deques;

import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;
import Goodrich_Tamassia.Linear_DS.Lists.ArrayListExtended;

import java.util.*;

/**
 * Implementation of the Deque ADT by means of a dynamic circular array to store the elements
 *
 * @author Roberto M. R.
 */
public class ExtendedArrayDeque<E> implements ExtendedDeque<E> {
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
     * Maximum number of elements allow in the deque if it is specified
     */
    private Integer max = null;
    /**
     * Whether when deque is full (max value is specified) must occur overflow (trying to add an element to one end
     * cause a deletion on the other end to make space for the new element)
     */
    private boolean overflow = false;

    /**
     * Create a new empty deque with default initial capacity and without size restrictions.
     */
    public ExtendedArrayDeque() {
        this(DEFAULT_INITIAL_CAPACITY);
    }


    /**
     * Create a new empty deque with the given initial capacity and without size restrictions.
     *
     * @param initialCapacity The initial capacity of the empty deque.
     */
    public ExtendedArrayDeque(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
        this.n = 0;
        this.f = 0;
    }


    /**
     * Create a new empty deque with the given maximum capacity and with size restrictions.
     *
     * @param maxCapacity   The maximum number of elements that can be at the same time in the deque
     * @param allowOverflow Whether when the deque is full, trying to add a new element must remove the element at the
     *                      opposed end to liberate space
     */
    public ExtendedArrayDeque(int maxCapacity, boolean allowOverflow) {
        this(maxCapacity);
        this.max = maxCapacity;
        this.overflow = allowOverflow;
    }

    /**
     * Create a new deque with the given initial values and without size restrictions
     *
     * @param init An array whose elements will be used to initially populate the deque
     */
    public ExtendedArrayDeque(E[] init) {
        data = (E[]) new Object[init.length * 2];
        for (int i = 0; i < init.length; i++) {
            data[i] = init[i];
        }
        this.n = init.length;
        this.f = 0;
    }

    /**
     * Create a new deque with the given initial values and with size restrictions
     *
     * @param init          An array whose elements will be used to initially populate the deque
     * @param maxCapacity   The maximum number of elements that can be at the same time in the deque
     * @param allowOverflow Whether when the deque is full, trying to add a new element must remove the element at the
     *                      opposed end to liberate space
     */
    public ExtendedArrayDeque(E[] init, int maxCapacity, boolean allowOverflow) {
        this(init);
        this.max = maxCapacity;
        this.overflow = allowOverflow;
    }

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
     * Returns (but does not remove) the first element of the deque.
     *
     * @return first element of the deque (or throw an exception if empty)
     * @throws EmptyException if this deque is empty
     */
    @Override
    public E getFirst() throws EmptyException {
        checkEmpty();
        return data[f];
    }

    /**
     * Returns (but does not remove) the last element of the deque.
     *
     * @return last element of the deque (or throw an exception if empty)
     * @throws EmptyException if this deque is empty
     */
    @Override
    public E getLast() throws EmptyException {
        checkEmpty();
        return data[(f + n - 1) % data.length];
    }

    /**
     * Returns (but does not remove) the first element of the deque.
     *
     * @return first element of the deque (or null if empty)
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return getFirst();
    }

    /**
     * Returns (but does not remove) the last element of the deque.
     *
     * @return last element of the deque (or null if empty)
     */
    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return getLast();
    }

    /**
     * Inserts an element at the front of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws FullException      if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    @Override
    public void addFirst(E e) throws FullException, ClassCastException {
        checkSizeRestriction();

        if (n == data.length && max == null) {
            resize(data.length * 2);
        }

        this.f = (f - 1 + data.length) % data.length;
        data[f] = e;
        n++;
    }

    /**
     * Inserts an element at the back of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws FullException      if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    @Override
    public void addLast(E e) throws FullException, ClassCastException {
        if (max != null && n == max) {
            // deque is full
            if (!overflow) throw new FullException("kjaks");
            data[f] = e;
            f = (f + 1) % data.length;
            return;
        }

        if (n == data.length && max == null) {
            resize(data.length * 2);
        }

        int tail = (f + n) % data.length;
        data[tail] = e;
        n++;
    }

    /**
     * Inserts an element at the front of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    @Override
    public void offerFirst(E e) throws ClassCastException {
        if (max != null && n == max && !overflow) return;
        addFirst(e);
    }

    /**
     * Inserts an element at the back of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    @Override
    public void offerLast(E e) throws ClassCastException {
        if (max != null && n == max & !overflow) return;
        addLast(e);
    }

    /**
     * Removes and returns the first element of the deque unless this is empty.
     *
     * @return element removed
     * @throws EmptyException if this deque is empty
     */
    @Override
    public E removeFirst() throws EmptyException {
        checkEmpty();
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        n--;
        if (n < data.length / 4 && max == null) {
            resize(data.length / 2);
        }
        return answer;
    }

    /**
     * Removes and returns the last element of the deque unless this is empty.
     *
     * @return element removed
     * @throws EmptyException if this deque is empty
     */
    @Override
    public E removeLast() throws EmptyException {
        checkEmpty();
        int last = (f + n - 1) % data.length;
        E answer = data[last];
        data[last] = null;
        n--;
        if (n < data.length / 4 && max == null) {
            resize(data.length / 2);
        }
        return answer;
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        return removeFirst();
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        return removeLast();
    }

    /**
     * Removes the first occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    @Override
    public boolean removeFirstOccurrence(E o) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (data[(i + f) % data.length].equals(o)) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        for (int i = index; i < n - 1; i++) {
            data[(i + f) % data.length] = data[(i + 1 + f) % data.length];
        }
        data[(f + n - 1) % data.length] = null;
        n--;
        if (n < data.length / 4 && max == null) {
            resize(data.length / 2);
        }
        return true;
    }

    /**
     * Removes the last occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    @Override
    public boolean removeLastOccurrence(Object o) {
        int index = -1;
        for (int i = f + n - 1; i >= f; i--) {
            if (data[i % data.length].equals(o)) {
                index = i;
            }
        }
        if (index == -1) return false;
        for (int i = index; i < f + n - 1; i++) {
            data[i % data.length] = data[(i + 1) % data.length];
        }
        data[(f + n - 1) % data.length] = null;
        n--;
        if (n < data.length / 4 && max == null) {
            resize(data.length / 2);
        }
        return true;
    }

    /**
     * Adds all the elements in the specified collection at the end of this deque, as if by calling addLast on each
     * one, in the order that they are returned by the collection's iterator.
     *
     * @param c the elements to be inserted into this deque
     * @return true if this deque changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (max == null) {
            if (n + c.size() > data.length) resize((n + c.size()) * 2);
            int index = f + n;
            for (E e : c) {
                data[index] = e;
                index++;
            }
            n = n + c.size();
            return true;
        }

        for (E e : c) {
            this.offerLast(e);
        }
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged.
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    @Override
    public boolean remove(E o) {
        return removeFirstOccurrence(o);
    }

    /**
     * Returns true if this deque contains the specified element
     *
     * @param o element whose presence in this deque is to be tested
     * @return true if this deque contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        boolean found = false;
        for (int i = f; i < n + f; i++) {
            if (data[i % data.length].equals(o)) found = true;
        }

        return found;
    }

    /**
     * Removes all the elements from this collection .
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        if (max == null) {
            data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        } else {
            data = (E[]) new Object[max.intValue()];
        }
        n = 0;
        f = 0;
    }

    /**
     * Shift front element to the back of deque
     *
     * @param k number of shift positions to rotate
     */
    @Override
    public void rotate(int k) {
        if (k > 1) {
            rotate(k - 1);
        }

        E temp = data[f];
        data[f] = null;
        data[(f + n) % data.length] = temp;
        f = (f + 1) % data.length;
    }

    //---------------- nested Iterator class ----------------

    /**
     * Nested iterator class to travers the elements of the deque in order from start to end
     */
    private class DequeIterator implements Iterator<E> {
        /**
         * Index of the next element to report.
         */
        private int j = 0;                   // index of the next element to report

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j < n;
        }

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == n) throw new NoSuchElementException("No next element");
            E temp = data[(j + f) % data.length];
            j++;        // post-increment j, so it is ready for future call to next
            return temp;
        }

    } //------------ end of nested ArrayIterator class ------------


    /**
     * Returns an iterator over the elements in this deque in proper sequence. The elements will be returned in order
     * from first (head) to last (tail).
     *
     * @return an iterator over the elements in this deque in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new DequeIterator();
    }


    //---------------- nested Reverse Iterator class ----------------

    /**
     * Nested iterator class to travers the elements of the deque in reverse order from end to start
     */
    private class ReverseDequeIterator implements Iterator<E> {
        /**
         * Index of the next element to report.
         */
        private int j = n - 1;                   // index of the next element to report

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j >= 0;
        }

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j < 0) throw new NoSuchElementException("No next element");
            E temp = data[(j + f) % data.length];
            j--;        // post-decrement j, so it is ready for future call to next
            return temp;
        }

    } //------------ end of nested ArrayIterator class ------------


    /**
     * Returns an iterator over the elements in this deque in reverse sequential order. The elements will be returned
     * in order from last (tail) to first (head).
     *
     * @return an iterator over the elements in this deque in reverse sequence
     */
    @Override
    public Iterator<E> reverseIterator() {
        return new ReverseDequeIterator();
    }

    private void checkEmpty() throws EmptyException {
        if (isEmpty()) throw new EmptyException("Empty deque, there is no elements");
    }

    private void checkSizeRestriction() throws FullException {
        if (max != null && n == max) {
            // the deque is full
            if (!overflow) throw new FullException("Cannot add new items, the deque is full");
            n--;
        }
    }

    /**
     * Utility method to resize the length of the array use to  store the deque elements
     *
     * @param new_capacity integer representing the new capacity of the array
     */
    private void resize(int new_capacity) {
        E[] new_array = (E[]) new Object[new_capacity];
        for (int i = 0; i < this.n; i++) {
            new_array[i] = data[(f + i) % data.length];
        }
        data = new_array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedArrayDeque<?> that = (ExtendedArrayDeque<?>) o;
        for (int i = 0; i < n; i++) {
            if (!data[(this.f + i) % data.length].equals(that.data[(that.f + i) % that.data.length])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    /**
     * Returns a string representation of the deque as a list of elements.
     * This method runs in O(n) time, where n is the size of the queue.
     *
     * @return textual representation of the queue.
     */
    public String toString() {
        if (isEmpty()) return "()";
        StringBuilder sb = new StringBuilder("(");
        sb.append(data[f]);
        for (int i = f + 1; i < f + n; i++) {
            sb.append(", ");
            sb.append(data[i % data.length]);
        }
        sb.append(")");
        return sb.toString();
    }

}
