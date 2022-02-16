package Goodrich_Tamassia.Linear_DS.Stacks;

import java.util.Arrays;

/**
 * Implementation of the Stack ADT by means of a dynamic array to store the elements
 *
 * @author Roberto M. R.
 */
public class DynamicArrayStack<E> implements Stack<E> {
    /**
     * Default array initial capacity.
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    // instance variables
    /**
     * Generic array used for storage of stack elements.
     */
    private E[] data;
    /**
     * Current number of elements in the stack.
     */
    private int n;
    /**
     * Current length of the array use to store stack elements.
     */
    private int capacity;

    /**
     * Creates an array with default initial capacity.
     */
    public DynamicArrayStack() {
        this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.n = 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return number of elements in the stack
     */
    @Override
    public int size() {
        return this.n;
    }

    /**
     * Tests whether the array is empty.
     *
     * @return true if the array is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Add the given element at the top of the stack .
     *
     * @param e the new element to be stored
     */
    @Override
    public void push(E e) {
        if (this.n == capacity) {
            resize(capacity * 2);
        }
        this.data[n] = e;
        n++;
    }

    /**
     * Return the element at the top of the stack without removing it from the stack.
     *
     * @return the element at the top of the stack
     */
    @Override
    public E top() {
        return this.data[n - 1];
    }

    /**
     * Removes the element at the top of the stack and return it.
     *
     * @return the recently removed element at the top of the stack
     */
    @Override
    public E pop() {
        if (isEmpty()) return null;
        E answer = this.data[n - 1];
        n--;
        this.data[n] = null;   //  assist Java’s garbage collection mechanism

        if (this.n < this.capacity / 4) {
            resize(this.capacity / 2);
        }

        return answer;
    }


    /**
     * Utility method to resize the length of the array use to  store the stack elements
     *
     * @param new_capacity integer representing the new capacity of the array that stores the stack elements
     */
    private void resize(int new_capacity) {
        E[] new_array = (E[]) new Object[new_capacity];

        for (int i = 0; i < this.n; i++) {
            new_array[i] = this.data[i];
        }

        this.data = new_array;
        this.capacity = new_capacity;

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DynamicArrayStack{");
        sb.append("data=").append(Arrays.toString(data));
        sb.append(", n=").append(n);
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> S = new DynamicArrayStack<>(); // contents: ()
        S.push(5); // contents: (5)
        S.push(3); // contents: (5, 3)
        System.out.println(S.size()); // contents: (5, 3) outputs 2
        System.out.println(S.pop()); // contents: (5) outputs 3
        System.out.println(S.isEmpty()); // contents: (5) outputs false
        System.out.println(S.pop()); // contents: () outputs 5
        System.out.println(S.isEmpty()); // contents: () outputs true
        System.out.println(S.pop()); // contents: () outputs null
        S.push(7); // contents: (7)
        S.push(9); // contents: (7, 9)
        System.out.println(S.top()); // contents: (7, 9) outputs 9
        S.push(4); // contents: (7, 9, 4)
        System.out.println(S.size()); // contents: (7, 9, 4) outputs 3
        System.out.println(S.pop()); // contents: (7, 9) outputs 4
        S.push(6); // contents: (7, 9, 6)
        S.push(8); // contents: (7, 9, 6, 8)
        System.out.println(S.pop()); // contents: (7, 9, 6) outputs 8
        System.out.println(S);
        S.push(10);
        S.push(100);
        S.push(1000);
        System.out.println(S);
    }
}
