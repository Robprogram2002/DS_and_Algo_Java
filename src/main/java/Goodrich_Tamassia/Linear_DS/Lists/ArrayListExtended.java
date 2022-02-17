package Goodrich_Tamassia.Linear_DS.Lists;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Implementation of the list ADT by means of a dynamic array. This is an intended version
 * of the java.util.ArrayList class.
 */
public class ArrayListExtended<E> implements ExtendedList<E> {
    // instance variables
    /**
     * Default array capacity.
     */
    public static final int DEFAULT_INITIAL_CAPACITY = 16;     // default array capacity

    /**
     * Generic array used for storage of list elements.
     */
    private E[] data;                        // generic array used for storage

    /**
     * Current number of elements in the list.
     */
    private int size = 0;                    // current number of elements

    // constructors


    /**
     * Creates an array list with default initial capacity.
     */
    public ArrayListExtended() {
        this(DEFAULT_INITIAL_CAPACITY);
    }   // constructs list with default capacity


    /**
     * Creates an array list with given initial capacity.
     */
    public ArrayListExtended(int capacity) {         // constructs list with given capacity
        data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    }


    public ArrayListExtended(E @NotNull [] c) {
        E[] temp = (E[]) new Object[c.length * 2];
        for (int i = 0; i < c.length; i++) {
            temp[i] = c[i];
        }
        data = temp;
        size = c.length;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests whether the array list is empty.
     *
     * @return true if the array list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Return a boolean value representing whether the given element is in the list
     *
     * @param o element whose presence in this list is to be tested
     * @return Returns true if this list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element)
     *
     * @return an array containing all the elements in this list in proper sequence
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param e element to be appended to this list
     */
    @Override
    public void add(E e) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[size] = e;
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     *
     * @param e the element whose first occurrence would be removed from the list if any
     * @return true if the list was modified else otherwise.
     */
    @Override
    public boolean remove(E e) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;  // the given element was not found
        remove(index);
        return true;
    }

    /**
     * @param c collection to be checked for containment in this list
     * @return wheter all the elements in the given collection were in the list.
     */
    @Override
    public boolean containsAll(@NotNull ExtendedList<? extends E> c) {
        boolean answer = true;

        for (E element : c)
            if (!this.contains(element)) answer = false;

        return answer;
    }

    /**
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(@NotNull ExtendedList<? extends E> c) {
        if (data.length < size + c.size()) resize((size + c.size()) * 2);

        for (E element : c) {
            data[size] = element;
            size++;
        }

        return true;
    }

    /**
     * @param c collection containing elements to be removed from this list
     * @return whether the list changed as a result of the method call
     */
    @Override
    public boolean removeAll(@NotNull ExtendedList<? extends E> c) {
        boolean answer = false;

        for (E element : c)
            if (this.remove(element)) answer = true;

        return answer;
    }

    /**
     * @param e the element to be completely removed from this list (all occurrences)
     * @return whether the list changed as a result of the method call
     */
    @Override
    public boolean removeAll(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                removeAll(e, i);

                if (size < data.length / 4) {
                    resize(data.length / 2);
                }

                return true;
            }
        }
        return false;
    }

    private void removeAll(E e, int index) {
        for (int j = index + 1; j < size; j++) {
            if (data[j].equals(e)) {
                removeAll(e, j);
            }
        }

        for (int j = index; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        size--;
        data[size] = null;

    }

    @Override
    public boolean retainAll(Collection<? extends E> c) {
        return false;
    }

    /**
     * @param a the element that would be replaced if it is in the list.
     * @param b the new element that would be place in the list
     * @return whether the list changed as a result of the method call
     */
    @Override
    public boolean replace(E a, E b) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(a)) {
                data[i] = b;
                result = true;
            }
        }
        return result;
    }

    /**
     * @param c collection containing elements to be replaced if they are in this list.
     * @param b the new element that would be place in the list in each replace action.
     * @return whether the list changed as a result of the method call
     */
    @Override
    public boolean replace(ExtendedList<E> c, E b) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                data[i] = b;
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all the elements from this list .
     * The list will be empty after this method returns.
     */
    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns (but does not remove) the element at index i.
     *
     * @param i the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        return data[i];
    }

    /**
     * Replaces the element at the specified index, and returns the element previously stored.
     *
     * @param i the index of the element to replace
     * @param e the new element to be stored
     * @return the previously stored element
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i);
        E answer = data[i];
        data[i] = e;
        return answer;
    }


    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i);
        if (size == data.length) {
            resize(size * 2);
        }

        for (int j = size - 1; j >= i; j--) {  // start by shifting rightmost
            data[j + 1] = data[j];
        }

        data[i] = e;
        size++;
    }

    /**
     * Removes and returns the element at the given index, shifting all subsequent
     * elements in the list one position closer to the front.
     *
     * @param i the index of the element to be removed
     * @return the element that had be stored at the given index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        E answer = data[i];
        for (int j = i; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        size--;
        data[size] = null;

        if (size < data.length / 4) {
            resize(data.length / 2);
        }
        return answer;
    }

    /**
     * @param o element to search for
     * @return the index of the first occurrence if the given object is in the list, else -1.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) return i;
        }
        return -1;
    }

    /**
     * @param o element to search for
     * @return the index of the last occurrence if the given object is in the list, else -1
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(o)) return i;
        }
        return -1;
    }

    //---------------- nested ArrayIterator class ----------------

    /**
     * A (nonstatic) inner class. Note well that each instance contains an implicit
     * reference to the containing list, allowing it to access the list's members.
     */
    private class ArrayIterator implements Iterator<E> {
        /**
         * Index of the next element to report.
         */
        private int j = 0;                   // index of the next element to report
        private boolean removable = false;   // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j < size;
        }   // size is field of outer instance

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true;   // this element can subsequently be removed
            return data[j++];   // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            ArrayListExtended.this.remove(j - 1);  // that was the last one returned
            j--;                         // next element has shifted one cell to the left
            removable = false;           // do not allow remove again until next is called
        }
    } //------------ end of nested ArrayIterator class ------------

    /**
     * Returns an iterator of the elements stored in the list in the order from start to end.
     *
     * @return iterator of the list's elements
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();     // create a new instance of the inner class
    }

    private class ReverseArrayIterator implements Iterator<E> {
        /**
         * Index of the next element to report.
         */
        private int j = size - 1;                   // index of the next element to report
        private boolean removable = false;          // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        @Override
        public boolean hasNext() {
            return j >= 0;
        }

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        @Override
        public E next() {
            if (j < 0) throw new NoSuchElementException("No next element");
            removable = true;   // this element can subsequently be removed
            return data[j--];   // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            ArrayListExtended.this.remove(j + 1);  // that was the last one returned
            removable = false;           // do not allow remove again until next is called
        }
    }

    /**
     * Returns an iterator over the elements in this list in reverse sequential order. The elements will be returned
     * in order from last to first.
     *
     * @return an iterator over the elements in this list in reverse sequence
     */
    @Override
    public Iterator<E> reverseIterator() {
        return new ReverseArrayIterator();
    }

    @Override
    public ExtendedList<E> subList(int fromIndex, int toIndex) {
        E[] temp = (E[]) new Object[toIndex - fromIndex];
        if (toIndex - fromIndex >= 0) System.arraycopy(data, fromIndex, temp, toIndex, toIndex - fromIndex);
        return new ArrayListExtended<E>(temp);
    }

    // utility methods

    /**
     * Checks whether the given index is in the range [0, n-1].
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Illegal index:  " + index);
        }
    }

    /**
     * Resizes internal array to have given capacity >= size.
     */
    private void resize(int new_capacity) {
        E[] temp = (E[]) new Object[new_capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayListExtended<?> that = (ArrayListExtended<?>) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        return result;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        final StringBuilder sb = new StringBuilder("[");
        sb.append(data[0]);
        for (int i = 1; i < size; i++) {
            sb.append(", ");
            sb.append(data[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}
