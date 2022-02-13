package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

import java.util.Objects;

public class SinglyLinkedList<E> implements SinglyLinkedListInterface<E>, Cloneable {

    // instance variables of the SinglyLinkedList
    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /**
     * The last node of the list
     */
    private Node<E> tail = null;               // last node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    /**
     * Constructs an initially empty list.
     */
    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //---------------- nested Node class ----------------
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    //----------- end of nested Node class -----------

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    @Override
    public long size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    @Override
    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    @Override
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    // update methods

    /**
     * Adds an element to the front of the list.
     *
     * @param element the new element to add
     */
    @Override
    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (size == 0) {   // special case: new node becomes tail also
            tail = head;
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the new element to add
     */
    @Override
    public void addLast(E element) {
        Node<E> newest = new Node<>(element, null);
        if (size == 0) {
            // special case: previously empty list
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) return null; // nothing to remove
        E answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0) tail = null; // special case as list is now empty
        return answer;
    }

//    @Override
//    public E removeLast() {
//        if (isEmpty()) return null; // nothing to remove
//        Node<E> current = head;
//        Node<E> prev = null;
//        while (current.getNext() != null) {
//            prev = current;
//            current = current.getNext();
//        }
//        if (prev == null) {
//            // there is just one element in the list
//            head = null;
//            tail = null;
//        } else {
//            prev.setNext(null);
//            tail = prev;
//        }
//        size--;
//        return current.getElement();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglyLinkedList<?> other = (SinglyLinkedList<?>) o;
        Node<?> current = this.head;
        Node<?> current_other = other.head;
        while (current != null) {
            if (!current.getElement().equals(current_other.getElement())) return false;
            current = current.getNext();
            current_other = current_other.getNext();
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Node<?> walk = head; walk != null; walk = walk.getNext()) {
            h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
            h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
        }
        return h;
    }

    @Override
    public SinglyLinkedList<E> clone() {
        try {
            // always use inherited Object.clone() to create the initial copy
            SinglyLinkedList<E> clone = (SinglyLinkedList<E>) super.clone(); // safe cast
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            if (size > 0) {                    // we need independent chain of nodes
                clone.head = new Node<>(head.getElement(), null);
                Node<E> walk = head.getNext();      // walk through remainder of original list
                Node<E> otherTail = clone.head;     // remember most recently created node
                while (walk != null) {              // make a new node storing same element
                    Node<E> newest = new Node<>(walk.getElement(), null);
                    otherTail.setNext(newest);     // link previous node to this one
                    otherTail = newest;
                    walk = walk.getNext();
                }
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    @Override
    public String toString() {
        if (isEmpty()) return "{}";
        final StringBuilder sb = new StringBuilder("{");
        sb.append(head.getElement());
        Node<E> current = head.getNext();
        while (current != null) {
            sb.append(", ");
            sb.append(current.getElement());
            current = current.getNext();
        }
        sb.append('}');
        return sb.toString();
    }
}
