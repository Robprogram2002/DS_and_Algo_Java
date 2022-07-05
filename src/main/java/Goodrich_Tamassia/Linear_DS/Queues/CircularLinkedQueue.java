package Goodrich_Tamassia.Linear_DS.Queues;

import Goodrich_Tamassia.Fundamental_DS.LinkedLists.CircularLinkedList;
import Goodrich_Tamassia.Fundamental_DS.LinkedLists.CircularLinkedListInterface;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Implementation of a Queue as an adaptation of a CircularLinkedList.
 * All operations are performed in constant time.
 *
 * @author Roberto M. R.
 * @see CircularLinkedList
 */
public class CircularLinkedQueue<E> implements CircularQueue<E> {
    /**
     * The primary storage for elements of the queue
     */
    private final CircularLinkedListInterface<E> list = new CircularLinkedList<>();   // an empty list

    /**
     * Constructs an initially empty queue.
     */
    public CircularLinkedQueue() {
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue
     */
    @Override
    public int size() {
        return (int) list.size();
    }

    /**
     * Tests whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * Inserts an element at tail of the queue.
     *
     * @param e the element to be inserted
     */
    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    /**
     * Return the element at the front of the queue
     *
     * @return the element at the front
     */
    @Override
    public E first() {
        return list.first();
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    /**
     * Rotate the element at the front of the queue to the back.
     */
    @Override
    public void rotate() {
        list.rotate();
    }

    /**
     * Produces a string representation of the contents of the queue.
     * (ordered from front to tail)
     * <p>
     * This exists for debugging purposes only.
     *
     * @return textual representation of the queue
     */
    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
