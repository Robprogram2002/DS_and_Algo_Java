package Sedgewick.chapter2.pq;

import Sedgewick.libraries.In;
import Sedgewick.libraries.StdOut;

// This IndexMinPQ client merges together the sorted input stream given as command-line arguments into a single sorted
//output stream on standard output (see text). Each stream index is associated with a key (the next string in the stream).
//After initialization, it enters a loop that prints the smallest string in the queue and removes the corresponding entry,
//then adds a new entry for the next string in that stream

public class MultiWayMerge {
    public static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new HeapIndexMinPQ<>(N);
        for (int i = 0; i < N; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        while (!pq.isEmpty()) {
            StdOut.println(pq.min());
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        }
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}
