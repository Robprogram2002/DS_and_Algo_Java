package Sedgewick.chapter1.collections.LinkedLists;

import java.util.Iterator;

public class DoublyLinkedList<T> implements LinkedList<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // data representation
    private static class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    //O(1)
    @Override
    public boolean is_empty() {
        return size() == 0;
    }

    // O(1)
    @Override
    public int size() {
        return this.size;
    }

    // Empty this linked list, O(n)
    @Override
    public void clear() {
        Node<T> current_node = head;
        while (current_node != null) {
            Node<T> next = current_node.next;
            current_node.prev = current_node.next = null;
            current_node.data = null;
            current_node = next;
        }
        head = tail = null;
        size = 0;
    }

    // add new node at the end, O(1)
    @Override
    public void append(T new_item) {
        if (is_empty()) {
            add_when_empty(new_item);
        } else {
            tail.next = new Node<>(new_item, tail, null);
            tail = tail.next;
        }
        this.size++;
    }

    // add a new node at beginning, O(1)
    @Override
    public void add(T new_item) {
        if (is_empty()) {
            add_when_empty(new_item);
        } else {
            head.prev = new Node<>(new_item, null, head);
            head = head.prev;
        }
        this.size++;
    }

    // Add an element at a specified index
    @Override
    public void insert(int index, T new_item) throws IndexOutOfBoundsException {
        if (index == 0) { // add at the beginning O(1)
            add(new_item);
            return;
        }
        if (index == size - 1) {
            append(new_item);   // add at the end O(1)
            return;
        }

        // traverse to get the node at the index received O(n/2)
        Node<T> current_node = find_node_by_index(index);
        // then add a new node on that place
        current_node.prev.next = new Node<>(new_item, current_node.prev, current_node);
        current_node.prev = current_node.prev.next;
        this.size++;
    }

    // Check the value of the first node if it exists, O(1)
    @Override
    public T peekHead() throws RuntimeException {
        if (is_empty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check the value of the last node if it exists, O(1)
    @Override
    public T peekTail() throws RuntimeException {
        if (is_empty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Check the value of the node at the specified index if it exists, O(n/2)
    @Override
    public T get(int index) throws RuntimeException {

        Node<T> node = find_node_by_index(index);

        return node.data;
    }

    // Remove the first value at the head of the linked list, O(1)
    @Override
    public T removeHead() throws RuntimeException {
        if (is_empty()) throw new RuntimeException("Empty list");
        T data = head.data;
        head = head.next;
        this.size -= 1;

        if (is_empty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        return data;
    }

    // Remove the last value at the tail of the linked list, O(1)
    @Override
    public T removeTail() throws RuntimeException {
        if (is_empty()) throw new RuntimeException("Empty list");

        T data = tail.data;
        tail = tail.prev;
        this.size -= 1;

        if (is_empty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    // Remove the specified value if exits, O(n)
    @Override
    public boolean remove(T value) {
        Node<T> current_node = head;

        while (current_node != null) {
            if (current_node.data == value) {
                remove_node(current_node);
                return true;
            } else {
                current_node = current_node.next;
            }

        }
        return false;
    }

    // Remove a node at a particular index, O(n/2)
    @Override
    public T removeAt(int index) throws RuntimeException {
        Node<T> current = find_node_by_index(index);
        return remove_node(current);
    }

    @Override
    public boolean search(T value) {
        Node<T> current_node = this.head;
        boolean found = false;

        while (current_node != null && !found) {
            if (current_node.data == value) {
                found = true;
            } else {
                current_node = current_node.next;
            }

        }
        return found;
    }

    // Find the index of a particular value in the linked list, O(n)
    @Override
    public int indexOf(T value) {
        int index = 0;
        Node<T> current_node = head;

        while (index < this.size) {
            if (current_node.data == value) {
                return index;
            } else {
                current_node = current_node.next;
                index += 1;
            }
        }

        return -1;
    }

    // Check is a value is contained within the linked list, O(n)
    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) {
                sb.append(", ");
            }
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    // add a node to the list when this is empty, O(1)
    private void add_when_empty(T item) {
        this.head = this.tail = new Node<T>(item, null, null);
    }

    // find a node by its index on the linked list, O(n/2)
    private Node<T> find_node_by_index(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();

        Node<T> current_node;
        int count;
        if (index < this.size / 2) {
            // traverse from the beginning
            current_node = head;
            count = 0;

            while (count < this.size) {
                if (count == index) {
                    break;
                } else {
                    current_node = current_node.next;
                    count += 1;
                }
            }
        } else {
            // traverse from the end
            current_node = tail;
            count = size - 1;
            while (count > 0) {
                if (count == index) {
                    break;
                } else {
                    current_node = current_node.prev;
                    count -= 1;
                }
            }
        }
        return current_node;
    }

    // Remove an arbitrary node from the linked list, O(1)
    private T remove_node(Node<T> node) {
        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.prev == null) return removeHead();
        if (node.next == null) return removeTail();

        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Temporarily store the data we want to return
        T data = node.data;

        // Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;

        this.size -= 1;

        return data;
    }


}
