package Sedgewick.chapter1.collections.Stacks;

import java.util.Iterator;

/*
implementation of our Stack API that resizes the array, allows
clients to make stacks for any type of data, and supports client use of foreach to iterate
through the stack items in LIFO order.
 */

public class ResizingArrayStack<Item> implements Stack<Item> {

    private Item[] array = (Item[]) new Object[10];     // stack items
    private int N = 0;                                  // number of items

    public ResizingArrayStack() {
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void push(Item elem) {
        // check if there is space
        if (this.array.length == N) {
            resize(this.array.length * 2);
        }

        this.array[N] = elem;
        this.N += 1;
    }

    @Override
    public Item pop() {
        Item element = this.array[--N];
        this.array[N] = null;
        // if there is much waste of space reduce the size of the stack
        if (N > 0 && N == array.length / 4) resize(array.length / 2);
        return element;
    }

    @Override
    public Item peek() {
        return this.array[N - 1];
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // iterate in a LIFO order
    private class ReverseArrayIterator implements Iterator<Item> {
        // Support LIFO iteration.
        private int index = N;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("there is not next element");
            }
            return array[--index];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int max) {
        // Move stack to a new array of size max
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = this.array[i];
        }
        this.array = temp;
    }

    // test
    public static void main(String[] args) {
        var my_stack = new ResizingArrayStack<Integer>();
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
    }
}
