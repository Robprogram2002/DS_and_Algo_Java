package Goodrich_Tamassia.Linear_DS.Deques;

import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;

import java.util.Collection;
import java.util.Iterator;

/**
 * Interface for a double-ended queue: a collection of elements that can be inserted
 * and removed at both ends.
 */
public interface ExtendedDeque<E> extends Cloneable, Iterable<E> {

    /**
     * Returns the number of elements in the deque.
     *
     * @return number of elements in the deque
     */
    int size();

    /**
     * Tests whether the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns (but does not remove) the first element of the deque.
     *
     * @return first element of the deque (or throw an exception if empty)
     * @throws EmptyException if this deque is empty
     */
    E getFirst() throws EmptyException;

    /**
     * Returns (but does not remove) the last element of the deque.
     *
     * @return last element of the deque (or throw an exception if empty)
     * @throws EmptyException if this deque is empty
     */
    E getLast() throws EmptyException;

    /**
     * Returns (but does not remove) the first element of the deque.
     *
     * @return first element of the deque (or null if empty)
     */
    E peekFirst();

    /**
     * Returns (but does not remove) the last element of the deque.
     *
     * @return last element of the deque (or null if empty)
     */
    E peekLast();


    /**
     * Inserts an element at the front of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws FullException      if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    void addFirst(E e) throws FullException, ClassCastException;

    /**
     * Inserts an element at the back of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws FullException      if the element cannot be added at this time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    void addLast(E e) throws FullException, ClassCastException;

    /**
     * Inserts an element at the front of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    void offerFirst(E e) throws ClassCastException;

    /**
     * Inserts an element at the back of the deque unless it would violate capacity restrictions.
     *
     * @param e the new element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this deque
     */
    void offerLast(E e) throws ClassCastException;

    /**
     * Removes and returns the first element of the deque unless this is empty.
     *
     * @return element removed
     * @throws EmptyException if this deque is empty
     */
    E removeFirst() throws EmptyException;

    /**
     * Removes and returns the last element of the deque unless this is empty.
     *
     * @return element removed
     * @throws EmptyException if this deque is empty
     */
    E removeLast() throws EmptyException;

    /**
     * Removes and returns the first element of the deque.
     *
     * @return element removed (or null if empty)
     */
    E pollFirst();

    /**
     * Removes and returns the last element of the deque.
     *
     * @return element removed (or null if empty)
     */
    E pollLast();

    /**
     * Removes the first occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    boolean removeFirstOccurrence(E o);

    /**
     * Removes the last occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    boolean removeLastOccurrence(Object o);

    /**
     * Adds all of the elements in the specified collection at the end of this deque, as if by calling addLast on each
     * one, in the order that they are returned by the collection's iterator.
     *
     * @param c the elements to be inserted into this deque
     * @return true if this deque changed as a result of the call
     */
    boolean addAll(Collection<? extends E> c);


    /**
     * Removes the first occurrence of the specified element from this deque. If the deque does not contain the element,
     * it is unchanged.
     *
     * @param o element to be removed from this deque, if present
     * @return true if an element was removed as a result of this call
     */
    boolean remove(E o);


    /**
     * Returns true if this deque contains the specified element
     *
     * @param o element whose presence in this deque is to be tested
     * @return true if this deque contains the specified element
     */
    boolean contains(Object o);


    /**
     * Removes all the elements from this collection .
     * The collection will be empty after this method returns.
     */
    void clear();

    /**
     * Shift front element to the back of deque
     *
     * @param k number of shift positions to rotate
     */
    void rotate(int k);


    /**
     * Returns an iterator over the elements in this deque in proper sequence. The elements will be returned in order
     * from first (head) to last (tail).
     *
     * @return an iterator over the elements in this deque in proper sequence
     */
    Iterator<E> iterator();


    /**
     * Returns an iterator over the elements in this deque in reverse sequential order. The elements will be returned
     * in order from last (tail) to first (head).
     *
     * @return an iterator over the elements in this deque in reverse sequence
     */
    Iterator<E> reverseIterator();
}
