package Sedgewick.chapter1.collections.Stacks;

import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new DynamicArrayStack<Integer>();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readInt());
        for (int i : stack)
            StdOut.println(i);
    }
}
