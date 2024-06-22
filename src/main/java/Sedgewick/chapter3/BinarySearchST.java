package Sedgewick.chapter3;

import Sedgewick.libraries.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinarySearchST<Key extends Comparable<Key>, Value> extends BaseOrderedST<Key, Value> {
    private final Key[] keys;
    private final Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        if (isEmpty()) return null;
        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) return null;
        return keys[N - 1];
    }

    @Override
    public Key floor(Key key) {
        if (isEmpty()) return null;
        int r = rank(key);
        if (key.compareTo(keys[r]) == 0) return key;
        return keys[r - 1];
    }

    @Override
    public Key ceiling(Key key) {
        if (isEmpty()) return null;
        int r = rank(key);
        return keys[r];
    }

    @Override
    public int rank(Key key) {
//        return rank(key, 0, N - 1);
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0)
            return rank(key, lo, mid - 1);
        else if (cmp > 0)
            return rank(key, mid + 1, hi);
        else return mid;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }


    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        int start = rank(lo);
        int end = rank(hi);
        if (keys[end].compareTo(hi) > 0) end--;
        return new KeysIterable<Key>(start, end);
    }

    @Override
    public void put(Key key, Value val) {
        int r = rank(key);
        if (r < N && key.compareTo(keys[r]) == 0) {
            vals[r] = val;
            return;
        }
        for (int i = N; i > r; i--) {
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }
        keys[r] = key;
        vals[r] = val;
        N++;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        int r = rank(key);
        if (r < N && key.compareTo(keys[r]) == 0) return vals[r];
        return null;
    }

    @Override
    public void delete(Key key) {
        if (isEmpty()) throw new IllegalStateException("Symbol table is empty");
        int r = rank(key);
        if (r < N && key.compareTo(keys[r]) == 0) {
            // remove elements at r index in keys and vals
            for (int i = r; i < N - 1; i++) {
                keys[i] = keys[i + 1];
                vals[i] = vals[i + 1];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            N--;
        }
        throw new NoSuchElementException("given key is not in the symbol table");
    }

    @Override
    public Iterable<Key> keys() {
        return new KeysIterable<Key>(0, N - 1);
    }

    public class KeysIterable<K> implements Iterable<K> {
        private int lo;
        private int hi;

        public KeysIterable(int start, int end) {
            lo = start;
            hi = end;
        }

        @Override
        public Iterator<K> iterator() {
            return new KeysIterator();
        }

        private class KeysIterator implements Iterator<K> {
            int index = lo;

            @Override

            public boolean hasNext() {
                return index != hi;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("there is not next element");
                }
                return (K) keys[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }


    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new BinarySearchST<>(11);

        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream("src/main/resources/data/tinyST.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
            for (int i = 0; sc.hasNext(); i++) {
                String key = sc.next();
                st.put(key, i);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String s : st.keys()) StdOut.println(s + " " + st.get(s));
    }

}
