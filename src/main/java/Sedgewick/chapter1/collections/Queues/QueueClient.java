package Sedgewick.chapter1.collections.Queues;

import Sedgewick.libraries.In;

public class QueueClient {
    // The problem that this method solves for the client is that the client can get numbers from a file into an array
    // without knowing the file size ahead of time. We enqueue the numbers from the file, use the size() method from
    // Queue to find the size needed for the array, create the array, and then dequeue the numbers to move them to the
    // array. A queue is appropriate because it puts the numbers into the array in the order in which they appear in the
    // file (we might use a Bag if that order is immaterial).

    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new LinkedListQueue<Integer>();
        while (!in.isEmpty())
            q.enqueue(in.readInt());
        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = q.dequeue();
        return a;
    }
}
