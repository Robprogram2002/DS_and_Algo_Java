# Fundamental Data Structures

## Using Arrays

Because arrays are so important, Java provides a class, `java.util.Arrays`, with a number of built-in static methods for
performing common tasks on arrays. For now, we provide an overview of the most commonly used methods of that class, as
follows

- **equals(A, B)**: Returns true if and only if array A and the array B are equal. Two arrays are considered equal if
  they have the same number of elements and every corresponding pair of elements in the two arrays are equal. That is, A
  and B have the same values in the same order.
- **fill(A, x)**: Stores value x in every cell of array A, provided the type of array A is defined so that it is allowed
  to store the value x.
- **copyOf(A, n)**: Returns an array of size n such that the first k elements of this array are copied from A, where k =
  min{n,A.length}. If n > A.length, then the last n − A.length elements in this array will be padded with default values
- **copyOfRange(A, s, t)**: Returns an array of size t − s such that the elements of this array are copied in order
  from `A[s]` to `A[t −1]`, where s < t, padded as with copyOf( ) if t > A.length.
- **toString(A)**: Returns a String representation of the array A, beginning with `[`, ending with `]`, and with
  elements of A displayed separated by string ", ". The string representation of an element is obtained
  using `String.valueOf(A[i])`, which returns the string "null" for a null reference and otherwise
  calls `A[i].toString( )`.
- **sort(A)**: Sorts the array A based on a natural ordering of its elements, which must be comparable.
- **binarySearch(A, x)**: Searches the sorted array A for value x, returning the index where it is found, or else the
  index of where it could be inserted while maintaining the sorted order.

Another feature built into Java, which is often useful when testing programs dealing with arrays, is the ability to
generate pseudorandom numbers, that is, numbers that appear to be random (but are not necessarily truly random). In
particular, Java has a built-in class, **java.util.Random**, whose instances are pseudorandom number generators, that
is, objects that compute a sequence of numbers that are statistically random. These sequences are not actually random,
however, in that it is possible to predict the next number in the sequence given the past list of numbers

Since the next number in a pseudorandom generator is determined by the previous number(s), such a generator always needs
a place to start, which is called its **seed**. *The sequence of numbers generated for a given seed will always be the
same*. The seed for an instance of the `java.util.Random` class can be set in its constructor or with its `setSeed( )`
method.

One common trick to get a different sequence each time a program is run is to use a seed that will be different for each
run.

Methods of the java.util.Random class include the following:

- **nextBoolean( )**: Returns the next pseudorandom boolean value.
- **nextDouble( )**: Returns the next pseudorandom double value, between 0.0 and 1.0.
- **nextInt( )**: Returns the next pseudorandom int value.
- **nextInt(n)**: Returns the next pseudorandom int value in the range from 0 up to but not including n.

#### Simple Cryptography with Character Arrays

Given that strings are immutable, we cannot directly edit an instance to encrypt it. Instead, our goal will be to
generate a new string. A convenient technique for performing string transformations is to create an equivalent array of
characters, edit the array, and then reassemble a (new) string based on the array.

Java has support for conversions from strings to character arrays and vice versa. Given a string S, we can create a new
character array matching S by using the method, `S.toCharArray( )`.

Conversely, there is a form of the String constructor that accepts a character array as a parameter.

If we were to number our letters like array indices, so that A is 0, B is 1, C is 2, then we can represent the
replacement rule as a character array, encoder, such that A is mapped to `encoder[0]`, B is mapped to `encoder[1]`, and
so on. Then, in order to find a replacement for a character in our Caesar cipher, we need to map the characters from A
to Z to the respective numbers from 0 to 25. Fortunately, we can rely on the fact that characters are represented in
Unicode by integer code points, and the code points for the uppercase letters of the Latin alphabet are consecutive

Java allows us to “subtract” two characters from each other, with an integer result equal to their separation distance
in the encoding. Given a variable c that is known to be an uppercase letter, the Java computation, `j = c − 'A'`
produces the desired index j.

The process of **decrypting** the message can be implemented by simply using a different character array to represent
the replacement rule—one that effectively shifts characters in the opposite direction.

## Implementing a Singly Linked List Class

we present a complete implementation of a SinglyLinkedList class, supporting the following methods:

- **size( )**: Returns the number of elements in the list.
- **isEmpty( )**: Returns true if the list is empty, and false otherwise.
- **first( )**: Returns (but does not remove) the first element in the list.
- **last( )**: Returns (but does not remove) the last element in the list.
- **addFirst(e)**: Adds a new element to the front of the list.
- **addLast(e)**: Adds a new element to the end of the list.
- **removeFirst( )**: Removes and returns the first element of the list.

If first( ), last( ), or removeFirst( ) are called on a list that is empty, we will simply return a null reference and
leave the list unchanged. Because it does not matter to us what type of elements are stored in the list, we use Java’s
generics framework to define our class with a formal type parameter E that represents the user’s desired element type.

### Circularly Linked Lists

there are many applications in which data can be more naturally viewed as having a **cyclic order**, with well-defined
neighboring relationships, but no fixed beginning or end.

For example, many multiplayer games are turn-based, with player A taking a turn, then player B, then player C, and so
on, but eventually back to player A again, and player B again, with the pattern repeating.

we design a structure known as a **circularly linked list**, which is essentially a singularly linked list in which the
next reference of the tail node is set to refer back to the head of the list (rather than null).

We use this model to design and implement a new CircularlyLinkedList class, which supports all the public behaviors of
our SinglyLinkedList class and one additional update method:

- **rotate( )**: Moves the first element to the end of the list.

With this new operation, round-robin scheduling can be efficiently implemented by repeatedly performing the following
steps on a circularly linked list C:

1. Give a time slice to process C.first( )
2. C.rotate( )

**Additional Optimization.**

In implementing a new class, we make one additional optimization—we no longer explicitly maintain the head reference. So
long as we maintain a reference to the tail, we can locate the head as `tail.getNext( )`. Maintaining only the tail
reference not only saves a bit on memory usage, it makes the code simpler and more efficient, as it removes the need to
perform additional operations to keep a head reference current. In fact, our new implementation is arguably superior to
our original singly linked list implementation, even if we are not interested in the new rotate method.

Implementing the new rotate method is quite trivial. We do not move any nodes or elements, we simply advance the tail
reference to point to the node that follows it (the implicit head of the list).

We can add a new element at the front of the list by creating a new node and linking it just after the tail of the list.

To implement the addLast method, we can rely on the use of a call to addFirst and then immediately advance the tail
reference so that the newest node becomes the last.

Removing the first node from a circularly linked list can be accomplished by simply updating the next field of the tail
node to bypass the implicit head.

## Doubly Linked Lists

To provide greater symmetry, we define a linked list in which each node keeps an explicit reference to the node before
it and a reference to the node after it. Such a structure is known as a doubly linked list. These lists allow a greater
variety of O(1)-time update operations, including insertions and deletions at arbitrary positions within the list. We
continue to use the term “next” for the reference to the node that follows another, and we introduce the term “prev” for
the reference to the node that precedes it.

Although we could implement a doubly linked list without sentinel nodes the slight extra memory devoted to the sentinels
greatly simplifies the logic of our operations. Most notably, the header and trailer nodes never change—only the nodes
between them change. Furthermore, we can treat all insertions in a unified manner, because a new node will always be
placed between a pair of existing nodes. In similar fashion, every element that is to be deleted is guaranteed to be
stored in a node that has neighbors on each side.

## Equivalence Testing

When working with reference types, there are many different notions of what it means for one expression to be equal to
another. At the lowest level, if a and b are reference variables, then expression a == b tests whether a and b refer to
the same object (or if both are set to the null value).

However, for many types there is a higher-level notion of two variables being considered “equivalent” even if they do
not actually refer to the same instance of the class. For example, we typically want to consider two String instances to
be equivalent to each other if they represent the identical sequence of characters.

To support a broader notion of equivalence, all object types support a method named **equals**. Users of reference types
should rely on the syntax a.equals(b), unless they have a specific need to test the more narrow notion of identity

The author of each class has a responsibility to provide an implementation of the `equals` method, which overrides the
one inherited from Object, if there is a more relevant definition for the equivalence of two instances. For example,
Java’s String class redefines equals to test character-for-character equivalence.

Great care must be taken when overriding the notion of equality, as the consistency of Java’s libraries depends upon the
`equals` method defining what is known as an equivalence relation in mathematics, satisfying the following properties:

- **Treatment of null**: For any nonnull reference variable x, the call x.equals(null)
  should return false (that is, nothing equals null except null).
- **Reflexivity**: For any nonnull reference variable x, the call x.equals(x) should return true (that is, an object
  should equal itself).
- **Symmetry**: For any nonnull reference variables x and y, the calls x.equals(y)
  and y.equals(x) should return the same value.
- **Transitivity**: For any nonnull reference variables x, y, and z, if both calls x.equals(y) and y.equals(z) return
  true, then call x.equals(z)
  must return true as well.

The following provides a summary of the treatment of equivalence for arrays, assuming that variables a and b refer to
array objects:

- **a == b**: Tests if a and b refer to the same underlying array instance.
- **a.equals(b)**: Interestingly, this is identical to a == b. Arrays are not a true class type and do not override the
  `Object.equals` method.
- **Arrays.equals(a,b)**: This provides a more intuitive notion of equivalence, returning true if the arrays have the
  same length and all pairs of corresponding elements are “equal” to each other. More specifically, if the array
  elements are primitives, then it uses the standard == to compare values. If elements of the arrays are a reference
  type, then it makes pairwise comparisons `a[k].equals(b[k])` in evaluating the equivalence.

However, there is an additional complication when using multidimensional arrays. The fact that two-dimensional arrays in
Java are really one-dimensional arrays nested inside a common one-dimensional array raises an interesting issue with
respect to how we think about **compound objects**, which are objects—like a two-dimensional array—that are made up of
other objects. In particular, it brings up the question of *where a compound object begins and ends*.

To support the more natural notion of multidimensional arrays being equal if they have equal contents, the class
provides an additional method:

- **Arrays.deepEquals(a,b)**: Identical to Arrays.equals(a,b) except when the elements of a and b are themselves arrays,
  in which case it calls `Arrays.deepEquals(a[k],b[k])` for corresponding entries, rather than `a[k].equals(b[k])`.

**Implementing equivalence testing.**

we consider two lists to be equivalent if they have the same length and contents that are element-by-element equivalent.
We can evaluate such equivalence by simultaneously traversing two lists, verifying that x.equals(y) for each pair of
corresponding elements x and y.

There is subtlety involving the treatment of Java’s generics framework. Although our SinglyLinkedList class has a
declared formal type parameter <E>, we cannot detect at runtime whether the other list has a matching type. (For those
interested, look online for a discussion of **erasure** in Java.) So we revert to using a more classic approach with
non-parameterized type SinglyLinkedList , and non-parameterized Node declarations . If the two lists have incompatible
types, this will be detected when calling the `equals` method on corresponding elements.

