package Goodrich_Tamassia.Linear_DS.Deques;


import Goodrich_Tamassia.Fundamental_DS.LinkedLists.DoublyLinkedList;
import Goodrich_Tamassia.Fundamental_DS.LinkedLists.DoublyLinkedListInterface;

/**
 * Implementation of a Deque as an adaptation of a DoublyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Roberto M. R.
 * @see DoublyLinkedList
 */
public class LinkedDeque<E> implements Deque<E> {
    /**
     * The primary storage for elements of the deque
     */
    private final DoublyLinkedListInterface<E> list = new DoublyLinkedList<>();   // an empty list

    /**
     * Constructs an initially empty queue.
     */
    public LinkedDeque() {
    }

    /**
     * Returns the number of elements in the deque.
     *
     * @return number of elements in the deque
     */
    @Override
    public int size() {
        return (int) list.size();
    }

    /**
     * Tests whether the deque is empty.
     *
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the front of the deque.
     *
     * @return the first element of the queue (or null if empty)
     */
    @Override
    public E first() {
        return list.first();
    }

    /**
     * Returns, but does not remove, the element at the tail of the deque.
     *
     * @return the last element of the queue (or null if empty)
     */
    @Override
    public E last() {
        return list.last();
    }

    /**
     * Inserts an element at the head of the deque.
     * This method runs in O(1) time.
     *
     * @param e new element to be inserted
     */
    @Override
    public void addFirst(E e) {
        list.addFirst(e);
    }

    /**
     * Inserts an element at the rear of the deque.
     * This method runs in O(1) time.
     *
     * @param e new element to be inserted
     */
    @Override
    public void addLast(E e) {
        list.addLast(e);
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeFirst() {
        return list.removeFirst();
    }

    /**
     * Removes and returns the last element of the queue.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E removeLast() {
        return list.removeLast();
    }

    /**
     * Returns a string representation of the deque as a list of elements.
     * This method runs in O(n) time, where n is the size of the queue.
     *
     * @return textual representation of the queue.
     */
    public String toString() {
        return list.toString();
    }

    /**
     * Testing the implementation
     */
    public static void main(String[] args) {
        Deque<String> deque = new LinkedDeque<>();
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
