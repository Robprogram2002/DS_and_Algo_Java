package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

public interface SinglyLinkedListInterface<E> {
    long size();

    boolean isEmpty();

    E first();

    E last();

    void addFirst(E element);

    void addLast(E element);

    E removeFirst();

//  E removeLast();
}
