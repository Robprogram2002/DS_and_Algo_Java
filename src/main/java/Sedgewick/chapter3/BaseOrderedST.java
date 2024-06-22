package Sedgewick.chapter3;

import Sedgewick.libraries.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BaseOrderedST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {
    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value val) {

    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public void delete(Key key) {
        put(key, null);
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new BaseOrderedST<>();

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
