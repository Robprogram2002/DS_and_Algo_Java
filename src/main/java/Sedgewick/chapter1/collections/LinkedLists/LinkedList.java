package Sedgewick.chapter1.collections.LinkedLists;

public interface LinkedList<Item> extends Iterable<Item> {

    public boolean is_empty();
    public int size();
    public void clear();

    public void append(Item new_item);
    public void insert(int index, Item new_item) throws IndexOutOfBoundsException;
    public void add(Item new_item);

    public Item peekHead() throws RuntimeException;
    public Item peekTail() throws RuntimeException;
    public Item get(int index) throws RuntimeException;


    public Item removeHead() throws RuntimeException;
    public Item removeTail() throws RuntimeException;
    public boolean remove(Item value);
    public Item removeAt(int index) throws RuntimeException;

    public boolean search(Item value);
    public int indexOf(Item value);
    public boolean contains(Item element);

}
