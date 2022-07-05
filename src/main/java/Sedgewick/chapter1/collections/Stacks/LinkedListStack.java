package Sedgewick.chapter1.collections.Stacks;

import Sedgewick.chapter1.collections.LinkedLists.LinkedList;
import Sedgewick.chapter1.collections.LinkedLists.SingleLinkedList;
import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

import java.util.Iterator;

public class LinkedListStack<Item> implements Stack<Item> {

    private final LinkedList<Item> list = new SingleLinkedList<Item>();

    // Create an empty stack
    public LinkedListStack() {
    }

    // Create a Stack with an initial element
    public LinkedListStack(Item firstElem) {
        this.push(firstElem);
    }

    // return the size of the stack, O(1)
    @Override
    public int size() {
        return list.size();
    }

    // return whether the stack is empty or not, O(1)
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // add an item to the stack, O(1)
    @Override
    public void push(Item elem) {
        list.add(elem);
    }

    //  remove an item from the stack, O(1)
    @Override
    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Cannot remove an element from an empty stack");
        return list.removeHead();
    }

    // return the item at the top of the stack, O(1)
    @Override
    public Item peek() {
        if (isEmpty()) throw new RuntimeException("empty stack");
        return list.peekHead();
    }

    // Searches for the element starting from top of the stack
    // Returns -1 if the element is not present in the stack, O(n)
    public int search(Item element) {
        return list.indexOf(element);
    }

    // iterate in a LIFO order
    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    // test
    public static void main(String[] args) {
        var my_stack = new LinkedListStack<Integer>();
        System.out.println(my_stack.isEmpty());
        my_stack.push(12);
        my_stack.push(-2);
        my_stack.push(30);
        my_stack.push(-100);
        my_stack.push(200);
        System.out.println(my_stack.size());
        System.out.println(my_stack.peek());
        my_stack.pop();
        System.out.println(my_stack.pop());
        System.out.println(my_stack);

        for (int item : my_stack) System.out.println(item);

//
//        LinkedListStack<String> s = new LinkedListStack<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) s.push(item);
//            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
//        }
//        StdOut.println("(" + s.size() + " left on stack)");
    }

}
