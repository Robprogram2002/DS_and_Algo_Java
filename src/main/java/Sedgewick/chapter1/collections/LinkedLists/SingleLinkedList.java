package Sedgewick.chapter1.collections.LinkedLists;

import java.util.Iterator;

public class SingleLinkedList<Item> implements LinkedList<Item> {

    private int size = 0;
    private Node<Item> head = null;
    private Node<Item> tail = null;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T initial) {
            this.data = initial;
            this.next = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public SingleLinkedList() {
    }

    @Override
    public boolean is_empty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(Item new_item) {
        Node<Item> new_node = new Node<Item>(new_item);
        if (is_empty()) {
            // first node is also the last node
            this.head = new_node;
            this.tail = new_node;
        } else {
            // there is already a first node
            new_node.next = this.head;
            this.head = new_node;
        }

        this.size += 1;
    }

    @Override
    public void append(Item new_item) {
        Node<Item> new_node = new Node<Item>(new_item);

        if (is_empty()) {
            this.head = new_node;
        } else {
            this.tail.next = new_node;
        }
        this.tail = new_node;
        this.size += 1;
    }

    @Override
    public void insert(int index, Item new_item) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            add(new_item);
        } else if (index == this.size - 1) {
            append(new_item);
        } else {
            // add an item in between two others
            Node<Item> new_node = new Node<Item>(new_item);
            Node<Item> current_node = this.head;
            Node<Item> prev_node = null;
            int count = 0;

            while (count < this.size) {
                if (count == index) {
                    prev_node.next = new_node;
                    new_node.next = current_node;
                    return;
                } else {
                    prev_node = current_node;
                    current_node = current_node.next;
                    count += 1;
                }
            }
        }
    }

    @Override
    public Item peekHead() {
        if (is_empty()) throw new RuntimeException("Empty list");
        return this.head.data;
    }

    @Override
    public Item peekTail() {
        if (is_empty()) throw new RuntimeException("Empty list");
        return this.tail.data;
    }

    @Override
    public Item get(int index) throws IndexOutOfBoundsException {
        if (is_empty()) throw new RuntimeException("Empty list");
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        Node<Item> current_node = this.head;
        int count = 0;

        while (count < this.size) {
            if (count == index) {
                break;
            } else {
                current_node = current_node.next;
                count += 1;
            }
        }

        return current_node.data;
    }

    @Override
    public boolean search(Item value) {
        Node<Item> current_node = this.head;
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

    @Override
    public int indexOf(Item value) {
        Node<Item> current_node = this.head;
        boolean found = false;
        int count = 0;

        while (current_node != null && !found) {
            if (current_node.data == value) {
                found = true;
            } else {
                current_node = current_node.next;
                count += 1;
            }

        }

        if (found) return count;
        else return -1;
    }

    @Override
    public boolean contains(Item element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean remove(Item value) {
        // Can't remove data from an empty list
        if (is_empty()) throw new RuntimeException("Empty list");

        Node<Item> current_node = this.head;
        Node<Item> prev_node = null;
        boolean found = false;

        while (current_node != null && !found) {
            if (current_node.data == value) {
                found = true;
            } else {
                prev_node = current_node;
                current_node = current_node.next;
            }
        }

        if (found) {
            if (prev_node == null) {
                // remove the first element
                removeHead();
            } else if (current_node.next == null) {
                // remove the last item
                removeTail();
            } else {
                // remove an item in between
                prev_node.next = current_node.next;
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Item removeTail() {
        // Can't remove data from an empty list
        if (is_empty()) throw new RuntimeException("Empty list");

        if (this.size == 1) {
            return removeHead();
        }

        Node<Item> current_node = this.head;
        Node<Item> prev_node = null;

        while (current_node.next != null) {
            prev_node = current_node;
            current_node = current_node.next;
        }

        // current_node is the last node
        assert prev_node != null;
        prev_node.next = null;
        this.tail = prev_node;
        return current_node.data;

    }

    @Override
    public Item removeHead() {
        // Can't remove data from an empty list
        if (is_empty()) throw new RuntimeException("Empty list");

        // remove from the beginning
        Node<Item> first = this.head;
        this.head = this.head.next;
        return first.data;
    }

    @Override
    public Item removeAt(int index) {
        // Can't remove data from an empty list
        if (is_empty()) throw new RuntimeException("Empty list");
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            return removeHead();
        } else if (index == this.size - 1) {
            return removeTail();
        } else {
            // remove an item in between
            Node<Item> current_node = this.head;
            Node<Item> prev_node = null;
            int count = 0;

            while (count < this.size) {
                if (count == index) {
                    break;
                } else {
                    prev_node = current_node;
                    current_node = current_node.next;
                    count += 1;
                }
            }
            prev_node.next = current_node.next;
            return current_node.data;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new java.util.Iterator<Item>() {
            private Node<Item> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item data = current.data;
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
        Node<Item> trav = head;
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

}
