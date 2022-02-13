package Sedgewick.chapter1.collections;

import java.util.Arrays;
import java.util.Iterator;

// A generic dynamic array implementation

public class DynamicArray<Item> implements Iterable<Item> {

    private Item[] array;
    private int length; // length user think array is
    private int size;  // actual array size


    public DynamicArray(int initial_size) {
        if (initial_size < 0) throw new IllegalArgumentException("Illegal size " + initial_size);
        this.size = initial_size;
        this.array = (Item[]) new Object[initial_size];
    }

    public DynamicArray() {
        this(16);
    }


    public int size() {
        return this.length;
    }

    public boolean is_empty() {
        return size() == 0;
    }

    public Item get(int index) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        return this.array[index];
    }

    public void set(int index, Item element) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        this.array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < this.length; i++) array[i] = null;
        this.length = 0;
    }

    public void append(Item element) {
        // check if there is no free space
        if (this.length + 1 >= this.size) {
            this.resize();
        }

        // there is space, then add the element at the end
        this.array[this.length] = element;
        this.length += 1;
    }

    public void insert(int index, Item element) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        // check if there is no free space
        if (this.length + 1 >= this.size) {
            this.resize();
        }

        Item[] new_array = (Item[]) new Object[this.size];
        for (int i = 0, j = 0; i < this.length; i++, j++) {
            if (i < index) {
                new_array[i] = this.array[i];
            } else if (j == index) {
                new_array[i] = element;
                i--;
            } else {
                new_array[j] = this.array[i];
            }
        }
        this.array = new_array;
        this.length += 1;
    }

    public Item removeAt(int index) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        Item element = this.array[index];
        for (int i = index; i < this.length; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.length -= 1;
        return element;
    }

    public int indexOf(Item element) {
        for (int i = 0; i < this.length; i++) {
            if (element == null) {
                if (this.array[i] == null) return i;
            } else if (element.equals(this.array[i])) {
                return i;
            }
        }
        // if we get here, the element was not found
        return -1;
    }

    public boolean remove(Item element) {
        int index = indexOf(element);
        if (index == -1) return false;
        removeAt(index);
        return true;
    }

    public Item pop() {
        int last = this.length - 1;
        Item element = this.array[last];
        this.array[last] = null;

        this.length -= 1;
        return element;
    }

    public boolean contains(Item element) {
        return indexOf(element) != -1;
    }

    public Item[] slice(int low, int high) {
        if (low < 0 || high >= this.length) throw new IndexOutOfBoundsException();
        Item[] temp = (Item[]) new Object[high - low];

        for (int i = low; i < high; i++) {
            temp[i] = this.array[i];
        }

        return temp;
    }

    public void print_real_array() {
        System.out.println(Arrays.toString(this.array));
    }


    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index + 1 < length;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("There is no next element");
                }
                return array[index++];

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (this.length == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(this.length).append("[");
            for (int i = 0; i < this.length - 1; i++) sb.append(this.array[i]).append(", ");
            return sb.append(this.array[this.length - 1]).append("]").toString();
        }
    }

    private void resize() {
        // double the array size
        this.size *= 2;

        // create a  temporary array
        Item[] temp = (Item[]) new Object[this.size];
        // copy the array items
        for (int i = 0; i < this.length; i++) {
            temp[i] = this.array[i];
        }

        // change the reference to the new extended array
        this.array = temp;
    }

    // test the ADT
    public static void main(String[] args) {
        var my_array = new DynamicArray<String>();
        System.out.println(my_array.is_empty());
        my_array.append("something new");
        my_array.append("second string");
        String name = "Robert";
        my_array.append(name);
        System.out.println(my_array);
        my_array.insert(1, "a new city");
        my_array.print_real_array();

        System.out.println(my_array.size());
        System.out.println(my_array.get(1));
        my_array.set(1, "a new world");
        System.out.println(my_array);


        System.out.println(my_array.removeAt(0));
        my_array.remove(name);
        System.out.println(my_array.pop());
        System.out.println(my_array);
    }
}
