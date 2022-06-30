package Sedgewick.chapter1.collections.Stacks;

import Sedgewick.chapter1.collections.DynamicArray;

import java.util.Iterator;

public class DynamicArrayStack<Item> implements Stack<Item> {

    private final DynamicArray<Item> data = new DynamicArray<>();

    // create an empty stack
    public DynamicArrayStack() {
    }

    // Create a Stack with an initial element
    public DynamicArrayStack(Item firstElem) {
        this.push(firstElem);
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.is_empty();
    }

    @Override
    public void push(Item elem) {
        this.data.append(elem);
    }

    @Override
    public Item pop() {
        return this.data.pop();
    }

    @Override
    public Item peek() {
        return this.data.get(this.size() - 1);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // iterate in a LIFO order
    private class ReverseArrayIterator implements Iterator<Item> {
        // Support LIFO iteration.
        private int index = data.size();

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("there is not next element");
            }
            return data.get(--index);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // test
    public static void main(String[] args) {
        var my_stack = new DynamicArrayStack<Integer>();
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
