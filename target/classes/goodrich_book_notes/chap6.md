## Stacks

Formally, a stack is an

- abstract data type (ADT) that supports the following two update methods:
- push(e): Adds element e to the top of the stack.
- pop( ): Removes and returns the top element from the stack (or null if the stack is empty).

Additionally, a stack supports the following accessor methods for convenience:

- top( ): Returns the top element of the stack, without removing it
  (or null if the stack is empty).
- size( ): Returns the number of elements in the stack.
- isEmpty( ): Returns a boolean indicating whether the stack is empty.

By convention, we assume that elements added to the stack can have arbitrary type and that a newly created stack is
empty.

In order to formalize our abstraction of a stack, we define its application programming interface (API)
in the form of a Java interface, which describes the names of the methods that the ADT supports and how they are to be
declared and used.

##### Implementing a Stack with a Singly Linked List

Unlike our array-based implementation, the linked-list approach has memory usage that is always proportional to the
number of actual elements currently in the stack, and without an arbitrary capacity limit.

**The Adapter Pattern.**

The adapter design pattern applies to any context where we effectively want to modify an existing class so that its
methods match those of a related, but different, class or interface. One general way to apply the adapter pattern is to
define a new class in such a way that it contains an instance of the existing class as a hidden field, and then to
implement each method of the new class using methods of this hidden instance variable. By applying the adapter pattern
in this way, we have created a new class that performs some of the same functions as an existing class, but repackaged
in a more convenient way.

In the context of the stack ADT, we can adapt our SinglyLinkedList class to define a new LinkedStack class.

## Queue

Formally, the queue abstract data type defines a collection that keeps objects in a sequence, where element access and
deletion are restricted to the first element in the queue, and element insertion is restricted to the back of the
sequence.

The queue abstract data type (ADT) supports the following two update methods:

- enqueue(e): Adds element e to the back of queue.
- dequeue( ): Removes and returns the first element from the queue (or null if the queue is empty).

The queue ADT also includes the following accessor methods (with first being analogous to the stack’s top method):

- first( ): Returns the first element of the queue, without removing it (or null if the queue is empty).
- size( ): Returns the number of elements in the queue.
- isEmpty( ): Returns a boolean indicating whether the queue is empty.

By convention, we assume that elements added to the queue can have arbitrary type and that a newly created queue is
empty.

## Double-Ended Queues

To provide a symmetrical abstraction, the deque ADT is defined to support the following update methods:

- addFirst(e): Insert a new element e at the front of the deque.
- addLast(e): Insert a new element e at the back of the deque.
- removeFirst( ): Remove and return the first element of the deque (or null if the deque is empty).
- removeLast( ): Remove and return the last element of the deque (or null if the deque is empty).

Additionally, the deque ADT will include the following accessors:

- first( ): Returns the first element of the deque, without removing it (or null if the deque is empty).
- last( ): Returns the last element of the deque, without removing it (or null if the deque is empty).
- size( ): Returns the number of elements in the deque.
- isEmpty( ): Returns a boolean indicating whether the deque is empty.