package Goodrich_Tamassia.Linear_DS.Queues;

import java.util.Random;

public class QueueApplications {
    private static final Random rand = new Random();

    public static <E> E Josephus(E[] players, int k) {
        CircularQueue<E> queue = buildQueue(players);
        if (queue.isEmpty()) return null;
        while (queue.size() > 1) {
            for (int i = 0; i < k - 1; i++) // skip past k-1 elements
                queue.rotate();
            E removed = queue.dequeue();
            System.out.println(" " + removed + " is out");
        }
        return queue.dequeue();
    }

    public static <E> E RandomJosephus(E[] players) {
        CircularQueue<E> queue = buildQueue(players);
        if (queue.isEmpty()) return null;
        while (queue.size() > 1) {
            int k = rand.nextInt(players.length * 3);
            for (int i = 0; i < k - 1; i++) // skip past k-1 elements
                queue.rotate();
            E removed = queue.dequeue();
            System.out.println(" " + removed + " is out");
        }
        return queue.dequeue();
    }


    /**
     * Builds a circular queue from an array of objects.
     */
    public static <E> CircularQueue<E> buildQueue(E[] a) {
        CircularQueue<E> queue = new CircularLinkedQueue<>();
        for (E e : a) queue.enqueue(e);
        return queue;
    }

    public static void main(String[] args) {
        String[] a1 = {"Alice", "Bob", "Cindy", "Doug", "Ed", "Fred"};
        String[] a2 = {"Gene", "Hope", "Irene", "Jack", "Kim", "Lance"};
        String[] a3 = {"Mike", "Roberto"};
        System.out.println("First winner is " + Josephus(a1, 3));
        System.out.println("Second winner is " + Josephus(a2, 10));
        System.out.println("Third winner is " + Josephus(a3, 7));
        System.out.println("-------------- Random game ----------------");
        System.out.println("First winner is " + RandomJosephus(a1));
        System.out.println("Second winner is " + RandomJosephus(a2));
        System.out.println("Third winner is " + RandomJosephus(a3));
    }
}
