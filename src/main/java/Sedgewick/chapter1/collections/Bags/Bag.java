package Sedgewick.chapter1.collections.Bags;

import Sedgewick.chapter1.collections.DynamicArray;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private final DynamicArray<Item> data = new DynamicArray<>();

    public Bag() {
    }

    public void add(Item item) {
        this.data.append(item);
    }

    public boolean isEmpty() {
        return this.data.is_empty();
    }

    public int size() {
        return this.data.size();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return this.data.iterator();
    }
}
