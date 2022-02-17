package Goodrich_Tamassia.Linear_DS.Queues;

import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;

/**
 * Interface for a queue: a collection of elements that are inserted
 * and removed according to the first-in first-out principle, with
 * more methods and extended behaviors
 */
public interface ExtendedQueue<E> extends Cloneable, Iterable<E> {
    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue
     */
    int size();

    /**
     * Tests whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param e the element to be inserted
     * @throws FullException if try to add a new element in a fix capacity queue that is full
     */
    void enqueue(E e) throws FullException;

    /**
     * Returns, but does not remove, the first element of the queue.
     *
     * @return the first element of the queue (or null if empty)
     * @throws EmptyException if try to retrieve an element from an empty stack
     */
    E first() throws EmptyException;

    /**
     * Removes and returns the first element of the queue.
     *
     * @return element removed (or null if empty)
     * @throws EmptyException if try to remove an element from an empty stack
     */
    E dequeue() throws EmptyException;
}