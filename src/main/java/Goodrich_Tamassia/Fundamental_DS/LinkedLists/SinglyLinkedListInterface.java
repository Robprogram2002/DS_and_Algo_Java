package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

public interface SinglyLinkedListInterface<E> {
    public long size();

    public boolean isEmpty();

    public E first();

    public E last();

    public void addFirst(E element);

    public void addLast(E element);

    public E removeFirst();

//    public E removeLast();
}
