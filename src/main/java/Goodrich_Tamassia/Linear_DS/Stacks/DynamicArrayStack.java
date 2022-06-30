package Goodrich_Tamassia.Linear_DS.Stacks;

import Goodrich_Tamassia.Exceptions.EmptyException;
import Goodrich_Tamassia.Exceptions.FullException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Implementation of the Stack ADT by means of a dynamic array to store the elements
 *
 * @author Roberto M. R.
 */
public class DynamicArrayStack<E> implements ExtendedStack<E> {
    /**
     * Default array initial capacity.
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    // -------------------- instance variables
    /**
     * Generic array used for storage of stack elements.
     */
    private E[] data;
    /**
     * Current number of elements in the stack.
     */
    private int n;

    /**
     * Whether this stack has a fixed length
     */
    private final boolean fixed;


    // ------------------- constructors

    /**
     * Creates an empty stack with default initial capacity.
     */
    public DynamicArrayStack() {
        this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.n = 0;
        this.fixed = false;
    }

    /**
     * Creates an empty fixed length stack
     *
     * @param maxLength Maximum number of elements that this stack can store
     */
    public DynamicArrayStack(int maxLength) {
        if (maxLength < 0) throw new IllegalArgumentException("Maximum length must be a non-negative integer");
        this.data = (E[]) new Object[maxLength];
        this.n = 0;
        this.fixed = true;
    }

    /**
     * Creates a dynamic stack with initial elements given by the collection object in the order they are iterated
     *
     * @param c Collection object whose elements would be used to initialize the stack
     */
    public DynamicArrayStack(Collection<E> c) {
        this((E[]) c.toArray());
    }

    /**
     * Creates a dynamic stack with initial elements given by the array in the order they are iterated
     *
     * @param c Array whose elements would be used to initialize the stack
     */
    public DynamicArrayStack(E[] c) {
        this.data = (E[]) new Object[c.length + DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < c.length; i++) {
            this.data[i] = c[i];
        }
        this.n = c.length;
        this.fixed = false;
    }

    // -------------------- Instance methods

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
     * @throws FullException if there is max length limit violation
     */
    @Override
    public void push(E e) {

        if (this.n == this.data.length) {
            if (this.fixed) {
                throw new FullException("The stack is full cannot add new elements");
            }
            resize(this.data.length * 2);
        }

        this.data[n] = e;
        n++;
    }

    /**
     * Return the element at the top of the stack without removing it from the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyException if the stack is empty
     */
    @Override
    public E top() {
        if (this.isEmpty()) {
            throw new EmptyException("The stack is empty");
        }
        return this.data[n - 1];
    }

    /**
     * Removes the element at the top of the stack and return it.
     *
     * @return the recently removed element at the top of the stack
     * @throws EmptyException if the stack is empty
     */
    @Override
    public E pop() {
        if (isEmpty()) throw new EmptyException("The stack is empty");
        E answer = this.data[n - 1];
        n--;
        this.data[n] = null;   //  assist Java’s garbage collection mechanism

        if (!this.fixed && this.n < this.data.length / 4) {
            resize(this.data.length / 2);
        }

        return answer;
    }

    /**
     * Transfer the elements of the current stack into other
     *
     * @param T the stack that would be used to transfer the elements
     */
    @Override
    public void transfer(ExtendedStack<E> T) {
        if (this.isEmpty()) return;

        for (int i = 0; i < this.n; i++) {
            T.push(this.data[i]);
            this.data[i] = null;
        }

        this.n = 0;
        if (!this.fixed) {
            this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }
    }

    /**
     * Reverse the elements of the stack. If in_place is false, then return a new Stack with the same elements but
     * in reverse order
     *
     * @param in_place whether the modifications would be made on the current stack or in a new instance.
     * @return New instance of a stack if in_place is false. Otherwise, return nothing (null).
     * @throws EmptyException if the stack is empty.
     */
    @Override
    public ExtendedStack<E> reverse(Boolean in_place) {
        if (this.isEmpty()) throw new EmptyException("Cannot reverse an empty stack");

        if (in_place) {
            this.reverse_array(0, this.n - 1);
            return null;
        } else {
            ExtendedStack<E> temp = new DynamicArrayStack<>();
            for (E e : this) {
                temp.push(e);
            }
            return temp;
        }
    }

    /**
     * Add all the elements of the given stack into the current stack
     *
     * @param T the stack that holds the elements to be appended
     */
    @Override
    public void extend(ExtendedStack<E> T) {
        if (T.isEmpty()) return;
        ExtendedStack<E> temp = T.reverse(false);
        if (this.n + temp.size() > this.data.length) {
            this.resize(this.n + temp.size());
        }

        for (E e : temp) {
            this.push(e);
        }
    }

    /**
     * Remove all the elements in the stack
     */
    @Override
    public void clear() {
        if (this.isEmpty()) return;
        if (this.fixed) {
            for (int i = 0; i < this.n; i++) {
                this.data[i] = null;
            }
        } else {
            this.data = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }
        this.n = 0;
    }

    // --------------- Utility methods

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

    }

    /**
     * Reverse the elements in the array used to store the elements of the stack
     *
     * @param start index of the first element
     * @param last  index of the last element
     */
    private void reverse_array(int start, int last) {
        if (start < last) {
            E temp = this.data[start];
            this.data[start] = this.data[last];     // swap first and last
            this.data[last] = temp;
            this.reverse_array(start + 1, last - 1);    // recur on rest
        }
    }

    // ----------------- inherited methods

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DynamicArrayStack{");
        sb.append("data=").append(Arrays.toString(data));
        sb.append(", n=").append(n);
        sb.append(", capacity=").append(this.data.length);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }


    /**
     * Iterator that go through the elements in tge array from last to first (LIFO order)
     */
    private class StackIterator implements Iterator<E> {
        // Support LIFO iteration.
        private int index = n;

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("there is not next element");
            }
            return data[--index];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Creates and returns a copy of this object.  The precise meaning
     */
    @Override
    public ExtendedStack<E> clone() {
        DynamicArrayStack<E> temp = new DynamicArrayStack<>();
        for (int i = 0; i < this.n; i++) {
            temp.push(this.data[i]);
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println("DEFAULT EMPTY STACK TEST");
        ExtendedStack<Integer> S = new DynamicArrayStack<>(); // contents: ()
        S.push(5); // contents: (5)
        S.push(3); // contents: (5, 3)
        System.out.println(S.size()); // contents: (5, 3) outputs 2
        System.out.println(S.pop()); // contents: (5) outputs 3
        System.out.println(S.isEmpty()); // contents: (5) outputs false
        System.out.println(S.pop()); // contents: () outputs 5
        System.out.println(S.isEmpty()); // contents: () outputs true

        // System.out.println(S.pop()); // contents: () throw empty error

        System.out.println("INITIAL ARRAY STACK TEST");
        ExtendedStack<Integer> T = new DynamicArrayStack<>(new Integer[]{-2, 5, 8, 2});
        for (Integer e : T) System.out.println(e);
        T.push(100);
        T.push(0);
        System.out.println(T.pop());
        System.out.println(T.size());
        System.out.println(T);

        System.out.println("FIXED LENGTH STACK TEST");
        ExtendedStack<Character> P = new DynamicArrayStack<>(4);
        P.push('a');
        P.push('b');
        P.push('c');
        P.push('d');
        System.out.println(P.top());
        P.pop();
        System.out.println(P.top());
        P.push('e');
        System.out.println(P);

        // P.push('f');  // throws full exception
    }
}
