package Goodrich_Tamassia.Linear_DS.Queues;

public interface CircularQueue<E> extends ExtendedQueue<E> {
    /**
     * Rotates the front element of the queue to the back of the queue.
     * This does nothing if the queue is empty.
     */
    void rotate();
}
