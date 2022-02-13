package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

/**
 * A basic doubly linked list implementation.
 *
 * @param <E> The type of each list item
 */
public class DoublyLinkedList<E> implements DoublyLinkedListInterface<E> {
    // instance variables of the SinglyLinkedList
    /**
     * Sentinel node at the beginning of the list
     */
    private final DoublyLinkedList.Node<E> header;
    /**
     * Sentinel node at the end of the list
     */
    private final DoublyLinkedList.Node<E> trailer;
    /**
     * Number of elements in the list (not including sentinels)
     */
    private long size;

    /**
     * Create a new empty linked list
     */
    public DoublyLinkedList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);

        this.header.setNext(trailer);
        this.size = 0;
    }

    /**
     * Inner class to store each list item and the references to its neighbors
     *
     * @param <E> The type of each list item
     */
    //---------------- nested Node class ----------------
    private static class Node<E> {
        /**
         * The element stored at this node
         */
        private E element;
        /**
         * A reference to the subsequent node in the list
         */
        private DoublyLinkedList.Node<E> next;
        /**
         * A reference to the preceding node in the list
         */
        private DoublyLinkedList.Node<E> prev;

        /**
         * Creates a node with the given element and next node.
         *
         * @param e    the element to be stored
         * @param prev reference to a node that should precede the new node
         * @param next reference to a node that should follow the new node
         */
        public Node(E e, DoublyLinkedList.Node<E> prev, DoublyLinkedList.Node<E> next) {
            this.element = e;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public DoublyLinkedList.Node<E> getNext() {
            return next;
        }

        public DoublyLinkedList.Node<E> getPrev() {
            return prev;
        }

        public void setNext(DoublyLinkedList.Node<E> next) {
            this.next = next;
        }

        public void setPrev(DoublyLinkedList.Node<E> prev) {
            this.prev = prev;
        }
    }

    //----------- end of nested Node class -----------


    /**
     * @return Returns the number of elements in the linked list.
     */
    // public access methods
    @Override
    public long size() {
        return size;
    }

    /**
     * @return Tests whether the linked list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return Returns (but does not remove) the first element of the list.
     */
    @Override
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();
    }

    /**
     * @return Returns (but does not remove) the last element of the list
     */
    @Override
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();
    }

    // public update methods

    /**
     * Adds element e to the front of the list.
     *
     * @param element the item to add in the list
     */
    @Override
    public void addFirst(E element) {
        addBetween(element, header, header.next);
    }

    /**
     * Adds element e to the tail of the list.
     *
     * @param element the item to add in the list
     */
    @Override
    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    /**
     * @return Removes and returns the first element of the list.
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        return removeNode(header.getNext());
    }

    /**
     * @return Removes and returns the last element of the list.
     */
    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        return removeNode(trailer.getPrev());
    }

    /**
     * Adds element e to the linked list in between the given nodes
     *
     * @param e        the item to add in the list
     * @param prevNode the node before the new node
     * @param nextNode the node after the new node
     */
    // private utility update methods
    private void addBetween(E e, Node<E> prevNode, Node<E> nextNode) {
        Node<E> newest = new Node<>(e, prevNode, nextNode);
        prevNode.setNext(newest);
        nextNode.setPrev(newest);
        size++;
    }

    /**
     * Removes the given node from the list and returns its element.
     *
     * @param target The node in the list to be removed
     * @return the item in the given node
     */
    private E removeNode(Node<E> target) {
        target.getPrev().setNext(target.getNext());
        target.getNext().setPrev(target.getPrev());
        size--;
        return target.getElement();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
        DoublyLinkedList.Node<?> current = this.header.getNext();
        DoublyLinkedList.Node<?> current_other = other.header.getNext();
        while (current.getNext() != null) {
            if (!current.getElement().equals(current_other.getElement())) return false;
            current = current.getNext();
            current_other = current_other.getNext();
        }
        return true;
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        final StringBuilder sb = new StringBuilder("[");
        DoublyLinkedList.Node<E> current = header.getNext();
        sb.append(current.getElement());
        current = current.getNext();
        while (current != trailer) {
            sb.append(", ");
            sb.append(current.getElement());
            current = current.getNext();
        }
        sb.append(']');
        return sb.toString();
    }
}
