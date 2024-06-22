package Sedgewick.chapter3;

import Sedgewick.libraries.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BaseST<Key, Value> implements ST<Key, Value> {
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
        return null;
    }

    public static void main(String[] args) {
        ST<String, Integer> st;
        st = new SequentialSearchST<>();

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
