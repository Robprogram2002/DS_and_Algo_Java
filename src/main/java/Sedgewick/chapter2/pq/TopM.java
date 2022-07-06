package Sedgewick.chapter2.pq;

import Sedgewick.chapter1.Simple_ADTs.Transaction;
import Sedgewick.chapter1.collections.Stacks.DynamicArrayStack;
import Sedgewick.chapter1.collections.Stacks.Stack;
import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Given an integer M from the command line and an input stream where each line contains a transaction, this MinPQ
// client prints the M lines whose numbers are the highest

// to build a priority queue using the numbers as keys, deleting the minimum after each insertion once the size of the
// priority queue reaches M. Once all the transactions have been processed, the top M come off the priority queue in
// increasing order, so this code puts them on a stack, then iterates through the stack to reverse the order and print
// them in increasing order

public class TopM {

    public static void main(String[] args) {
        // Print the top M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        HeapMinPQ<Transaction> pq = new HeapMinPQ<Transaction>(M + 1);
        try {
            //the file to be opened for reading
            FileInputStream fis = new FileInputStream("src/main/resources/data/tinyBatch.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned
            while (sc.hasNextLine()) {
                // Create an entry from the next line and put on the PQ.
                pq.insert(new Transaction(sc.nextLine()));
                if (pq.size() > M)
                    pq.delMin();   // Remove minimum if M+1 entries on the PQ.
            } // Top M entries are on the PQ.
            sc.close();     //closes the scanner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Stack<Transaction> stack = new DynamicArrayStack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);
    }
}
