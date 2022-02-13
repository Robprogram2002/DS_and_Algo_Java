package Sedgewick.chapter1.collections.Stacks;

public interface Stack<T> extends Iterable<T> {
    // return the number of elements in the stack
    public int size();

    // return if the stack is empty
    public boolean isEmpty();

    // push the element on the stack
    public void push(T elem);

    // pop the element off the stack
    public T pop();

    // get the top element
    public T peek();
}
