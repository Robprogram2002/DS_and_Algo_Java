package Goodrich_Tamassia.Linear_DS.Lists;


import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * An own version of the java.util.List interface.
 *
 * @param <E> The type of elements in this list
 */
public interface ExtendedList<E> extends Iterable<E>, Cloneable {
    // Query Operations

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    int size();

    /**
     * Tests whether the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    boolean contains(E o);

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all the elements in this list in proper sequence
     */
    Object[] toArray();

    // update methods

    /**
     * Appends the specified element to the end of this list (optional operation).
     *
     * @param e element to be appended to this list
     * @throws ClassCastException – if the class of the specified element prevents it from being added to this list
     */
    void add(E e) throws ClassCastException;


    /**
     * Removes the first occurrence of the specified element from this list, if it is present (optional operation).
     * If this list does not contain the element, it is unchanged
     *
     * @param e the element whose first occurrence would be removed from the list if any
     * @return true if this list was changed by the call
     */
    boolean remove(E e);

    // Bulk Modification Operations

    /**
     * Returns true if this list contains all the elements of the specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all the elements of the specified collection
     */
    boolean containsAll(ExtendedList<? extends E> c);


    /**
     * Appends all the elements in the specified collection to the end of this list, in the order that they are
     * returned by the specified collection's iterator
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    boolean addAll(ExtendedList<? extends E> c);


    /**
     * Removes from this list all of its elements that are contained in the specified collection
     *
     * @param c collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    boolean removeAll(ExtendedList<? extends E> c);

    /**
     * Removes from this list all occurrences of the given element if any.
     *
     * @param e the element to be completely removed from this list (all occurrences)
     * @return true if this list changed as a result of the call
     */
    boolean removeAll(E e);

    /**
     * removes from this list all of its elements that are not contained in the specified collection
     *
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     */
    boolean retainAll(Collection<? extends E> c);

    /**
     * Replace all the occurrences of the first argument that are in the list, if any, with the second argument element.
     *
     * @param a the element that would be replaced if it is in the list.
     * @param b the new element that would be place in the list
     * @return true if this list changed as a result of the call
     */
    boolean replace(E a, E b);

    /**
     * Replace all the occurrences of the elements in the given collection that are in the list, if any, with the second
     * argument element.
     *
     * @param c collection containing elements to be replaced if they are in this list.
     * @param b the new element that would be place in the list in each replace action.
     * @return true if this list changed as a result of the call
     */
    boolean replace(ExtendedList<E> c, E b);


    /**
     * Removes all the elements of this collection that satisfy the given predicate.
     *
     * @param filter a predicate which returns true for elements to be removed
     * @return true if any elements were removed
     */
    default boolean removeIf(Predicate<? super E> filter) {
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }


    /**
     * Removes all the elements from this list .
     * The list will be empty after this method returns.
     */
    void clear();

    // Positional index operations

    /**
     * Returns (but does not remove) the element at index i.
     *
     * @param i the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified index, and returns the element previously stored.
     *
     * @param i the index of the element to replace
     * @param e the new element to be stored
     * @return the previously stored element
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    E set(int i, E e) throws IndexOutOfBoundsException;

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    void add(int i, E e) throws IndexOutOfBoundsException;

    /**
     * Removes and returns the element at the given index, shifting all subsequent
     * elements in the list one position closer to the front.
     *
     * @param i the index of the element to be removed
     * @return the element that had be stored at the given index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    E remove(int i) throws IndexOutOfBoundsException;

    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not
     * contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not
     * contain the element
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element
     */
    int lastIndexOf(Object o);

    // List Iterators

    /**
     * Returns an iterator over the elements in this list in proper sequence. The elements will be returned in order
     * from first (index 0) to last .
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    Iterator<E> iterator();

    /**
     * Returns an iterator over the elements in this list in reverse sequential order. The elements will be returned
     * in order from last to first.
     *
     * @return an iterator over the elements in this list in reverse sequence
     */
    Iterator<E> reverseIterator();


    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a  view of the specified range within this list
     */
    ExtendedList<E> subList(int fromIndex, int toIndex);
}
