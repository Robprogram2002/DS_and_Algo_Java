package Goodrich_Tamassia.Linear_DS.Stacks;


import Goodrich_Tamassia.Fundamental_DS.LinkedLists.SinglyLinkedList;
import Goodrich_Tamassia.Fundamental_DS.LinkedLists.SinglyLinkedListInterface;

/**
 * Implementation of a stack as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Roberto M. R.
 * @see SinglyLinkedList
 */
public class LinkedStack<E> implements Stack<E> {
    /**
     * The primary storage for elements of the stack
     */
    private final SinglyLinkedListInterface<E> list = new SinglyLinkedList<>();   // an empty list

    /**
     * Constructs an initially empty stack.
     */
    public LinkedStack() {
    }                   // new stack relies on the initially empty list

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return (int) list.size();
    }

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     */
    @Override
    public E top() {
        return list.first();
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * Produces a string representation of the contents of the stack.
     * (ordered from top to bottom)
     * <p>
     * This exists for debugging purposes only.
     *
     * @return textual representation of the stack
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
