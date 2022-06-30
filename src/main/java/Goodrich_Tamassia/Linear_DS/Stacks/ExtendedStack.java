package Goodrich_Tamassia.Linear_DS.Stacks;


import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;

/**
 * Abstract data type of Stack with more methods and behaviors than the elementary.
 *
 * @param <E> Generic Parameter type of the elements to be store in the stack
 */
public interface ExtendedStack<E> extends Cloneable, Iterable<E> {

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    int size();

    /**
     * Tests whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to be inserted
     * @throws FullException if try to add a new element in a fix capacity stack that is full
     */
    void push(E e) throws FullException;

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return top element in the stack (or null if empty)
     * @throws EmptyException if try to retrieve an element from an empty stack
     */
    E top() throws EmptyException;

    /**
     * Removes and returns the top element from the stack.
     *
     * @return element removed (or null if empty)
     * @throws EmptyException if try to remove an element from an empty stack
     */
    E pop() throws EmptyException;


    /**
     * Transfer the elements of the current stack into other
     *
     * @param T the stack that would be used to transfer the elements
     */
    void transfer(ExtendedStack<E> T);


    /**
     * Reverse the elements of the stack. If in_place is True, then return a new Stack with the same elements but
     * in reverse order
     *
     * @param in_place whether the modifications would be made on the current stack or in a new instance.
     * @return
     */
    ExtendedStack<E> reverse(Boolean in_place);


    /**
     * Add all the elements of the given stack into the current stack
     *
     * @param T the stack that holds the elements to be appended
     */
    void extend(ExtendedStack<E> T);

    /**
     * Remove all the elements in the stack
     */
    void clear();

}