package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

public class CircularLinkedList<E> implements CircularLinkedListInterface<E> {
    // instance variables of the CircularLinkedList class
    /**
     * The designated cursor of the list
     */
    private CircularLinkedList.Node<E> tail = null;
    /**
     * Number of nodes in the list
     */
    private long size = 0;

    /**
     * Constructs an initially empty list.
     */
    public CircularLinkedList() {
    }

    //---------------- nested Node class ----------------
    private static class Node<E> {
        private E element;
        private CircularLinkedList.Node<E> next;

        public Node(E e, CircularLinkedList.Node<E> next) {
            this.element = e;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public CircularLinkedList.Node<E> getNext() {
            return next;
        }

        public void setNext(CircularLinkedList.Node<E> next) {
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
        return tail.getNext().getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list
     *
     * @return element at the back of the list (or null if empty)
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
        if (isEmpty()) {
            tail = new Node<>(element, null);
            tail.setNext(tail);     // link to itself circularly
        } else {
            Node<E> newest = new Node<>(element, tail.getNext());
            tail.setNext(newest);
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
        addFirst(element);          // insert new element at front of list
        tail = tail.getNext();
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) return null; // nothing to remove
        Node<E> head = tail.getNext();
        if (head == tail) {
            // there is just one element
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }

    /**
     * Rotate the first element to the back of the list.
     */
    @Override
    public void rotate() {
        if (!isEmpty())
            tail = tail.getNext();
    }

    //    @Override
    //    public E removeLast() {
    //        if (isEmpty()) return null; // nothing to remove
    //        return tail.getElement();
    //
    //    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // implement here
        return true;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "{}";
        final StringBuilder sb = new StringBuilder("{ ");
        sb.append(tail.getNext().getElement());
        Node<E> current = tail.getNext();
        while (current.getNext() != tail.getNext()) {
            sb.append(", ");
            current = current.getNext();
            sb.append(current.element);
        }
        sb.append(" }");
        return sb.toString();

        //        Other implementation
        //        if (tail == null) return "()";
        //        StringBuilder sb = new StringBuilder("(");
        //        Node<E> walk = tail;
        //        do {
        //            walk = walk.getNext();
        //            sb.append(walk.getElement());
        //            if (walk != tail)
        //                sb.append(", ");
        //        } while (walk != tail);
        //        sb.append(")");
        //        return sb.toString();
    }

}
