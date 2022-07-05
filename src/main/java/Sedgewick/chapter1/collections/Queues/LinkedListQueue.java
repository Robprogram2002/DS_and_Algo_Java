package Sedgewick.chapter1.collections.Queues;

import Sedgewick.chapter1.collections.LinkedLists.DoublyLinkedList;
import Sedgewick.chapter1.collections.LinkedLists.LinkedList;
import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

import java.util.Iterator;

public class LinkedListQueue<T> implements Queue<T> {

    private final LinkedList<T> list = new DoublyLinkedList<T>();

    // Create an empty queue
    public LinkedListQueue() {
    }

    // Create a Stack with an initial element
    public LinkedListQueue(T firstElem) {
        this.enqueue(firstElem);
    }

    // O(1)
    @Override
    public int size() {
        return list.size();
    }

    // O(1)
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // add an item to the queue at the end of the list, O(1)
    @Override
    public void enqueue(T elem) {
        list.append(elem);
    }

    // remove and return the least recently added element, O(1)
    @Override
    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Cannot remove an element from an empty queue");
        return list.removeHead();
    }

    // return the least recently added element, O(1)
    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("empty queue");
        return list.peekHead();
    }

    // iterate in a FIFO order
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }


    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        Queue<String> q = new LinkedListQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
