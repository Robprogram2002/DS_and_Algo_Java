package Sedgewick.chapter3;


import java.util.Iterator;
import java.util.NoSuchElementException;


public class SequentialSearchST<Key, Value> extends BaseST<Key, Value> {

    private Node first;         // first node in the linked list
    private int N;

    private class Node {            // linked-list node
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;            // Search hit: update val.
                return;
            }
        first = new Node(key, val, first);      // Search miss: add new node.
        N++;
    }

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) return x.val;       // search hit
        return null;                // search miss
    }

    @Override
    public void delete(Key key) {
        if (isEmpty()) throw new IllegalStateException("symbol table is empty");
        Node current = first;
        Node prev = null;
        while (current != null) {
            if (key.equals(current.key)) break;
            prev = current;
            current = current.next;
        }
        if (current == null) throw new NoSuchElementException("the given key is not in the symbol table");
        else if (prev == null) first = first.next; // delete first element
        else {
            prev.next = current.next;
            current.key = null;
            current.val = null;
        }
        N--;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<Key> keys() {
        return new Keys<Key>();
    }

    public class Keys<K> implements Iterable<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeysIterator();
        }

        private class KeysIterator implements Iterator<K> {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("there is not next element");
                }
                K result = (K) current.key;
                current = current.next;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }


}
