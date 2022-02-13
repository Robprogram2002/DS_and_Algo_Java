package Sedgewick.chapter1.collections.Queues;

public interface Queue<T> extends Iterable<T> {
    // return the number of elements in the Queue
    public int size();

    // return if the queue is empty
    public boolean isEmpty();

    // add an element on the queue
    public void enqueue(T elem);

    // remove the least recently added item
    public T dequeue();

    // get least recently added item without removing it
    public T peek();
}
