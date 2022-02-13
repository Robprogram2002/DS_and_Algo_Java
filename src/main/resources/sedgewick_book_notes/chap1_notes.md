## FUNDAMENTALS

When we write a computer program, we are generally implementing a method that has been devised previously to solve some
problem. This method is often independent of the particular programming language being used. . It is the method, rather
than the computer program itself, that specifies the steps that we can take to solve the problem.

> The term *algorithm* is used in computer science to describe  a finite, deterministic, and effective problem-solving
> method suitable for implementation as a computer program

We can define an algorithm by describing a procedure for solving a problem in a natural language, or by writing a
computer program that implements the procedure. But it is also important to recognize that a program in a particular
language is just one way to express an algorithm

Most algorithms of interest involve organizing the data involved in the computation. Such organization leads to
**data structures**, which also are central objects of study in computer science. Algorithms and data structures go hand
in hand.

> data structures exist as the byproducts or end products of algorithms and that we must therefore study them in order to understand the algorithms

Simple algorithms can give rise to complicated data structures and, conversely, complicated algorithms can use simple
data structures

The primary reason to learn about algorithms is that this discipline gives us the potential to reap huge savings, even
to the point of enabling us to do tasks that would otherwise be impossible. In an application where we are processing
millions of objects, it is not unusual to be able to make a program millions of times faster by using a well-designed
algorithm By contrast, investing additional money or time to buy and install a new computer holds the potential for
speeding up a program by perhaps a factor of only 10 or 100.

> Careful algorithm design is an extremely effective part of the process of solving a huge problem, whatever the applications area.

The sharing of programs in computer systems is becoming more widespread, so although we might expect to be *using a
large fraction of the algorithms* in this book, we also might expect to have to *implement only a small fraction* of
them.

However, implementing simple versions of basic algorithms helps us to understand them better and thus to more
effectively use and tune advanced versions from a library.

The choice of the best algorithm for a particular task can be a complicated process, perhaps involving sophisticated
mathematical analysis. The branch of computer science that comprises the study of such questions is called
**analysis of algorithms**.

> We should not use  an algorithm without having an idea of what resources it might consume, so we strive to be aware of how our algorithms might be expected to perform.

### 1.1 Programming Model

(...)

APIs A critical component of modular programming is documentation that explains the operation of library methods that
are intended for use by others. We will consistently describe the library methods that we use in this book in
application programming interfaces (APIs) that list the library name and the signatures and short descriptions of each
of the methods that we use. We use the term client to refer to a program that calls a method in another library and the
term implementation to describe the Java code that implements the methods in an API.

The purpose of an API is to separate the client from the implementation: the client should know nothing about the
implementation other than information given in the API, and the implementation should not take properties of any
particular client into account. APIs enable us to separately develop code for various purposes, then reuse it widely. No
Java library can contain all the methods that we might need for a given computation, so this ability is a crucial step
in addressing complex programming applications. Accordingly, programmers normally think of the API as a contract between
the client and the implementation that is a clear specification of what each method is to do. Our goal when developing
an implementation is to honor the terms of the contract. Often, there are many ways to do so, and separating client code
from implementation code gives us the freedom to substitute new and improved implementations. In the study of
algorithms, this ability is an important ingredient in our ability to understand the impact of algorithmic improvements
that we develop.

###### Strings

A String is a sequence of characters (char values). A literal String is a sequence of characters within double quotes,
such as "Hello, World". The data type String is a Java data type but it is not a primitive type. We consider String now
because it is a fundamental data type that almost every Java program uses.

### 1.2 Data Abstraction

> *A data type is a set of values and a set of operations on those values*

In principle, we could write all of our programs using only the built-in primitive types, but it is much more convenient
to write programs at a higher level of abstraction. In this section, we focus on the process of defining and using data
types, which is known as **data abstraction**.

Programming in Java is largely based on building data types known as *reference types*
with the familiar Java class. This style of programming is known as *object-oriented programming*, as it revolves around
the concept of an object, an entity that holds a data type value.

> An **abstract data type (ADT)** is a data type whose representation is hidden from the client.

Implementing an ADT as a Java class is not very different from implementing a function library as a set of static
methods. The primary difference is that we associate data with the function implementations and we hide the
representation of the data from the client.*When using an ADT, we focus on the operations specified in the API and pay
no attention to the data representation*; when implementing an ADT, we focus on the data, then implement operations on
that data

> Abstract data types are important because they support encapsulation in program  design

Our primary reason for studying different algorithms for the same task is that performance characteristics differ.
Abstract data types are an appropriate framework for the study of algorithms because they allow us to put knowledge of
algorithm performance to immediate use: we can substitute one algorithm for another to improve performance for all
clients without changing any client code.

##### Using abstract data types

> You do not need to know how a data type is implemented in order to be able to use it

So we begin by describing how to write programs that use a simple data type named **Counter** whose values are a name
and a nonnegative integer and whose operations are create and initialize to zero, increment by one, and examine the
current value. This abstraction is useful in many contexts. (we might use a Counter to keep track of fundamental
operations when analyzing the performance of algorithms)

**API for an abstract data type**. To specify the behavior of an abstract data type, we use an *application programming
interface (API)*, which is a list of constructors and instance methods (operations), with an informal description of the
effect of each, as in this API for Counter:

    public class Counter

                Counter(String id)      create a counter named id
        void    increment()             increment the counter by one
        int     tally()                 number of increments since creation
        String toString()               string representation

Even though the basis of a data-type definition is a set of values,* the role of the values is not visible from the API,
only the operations on those values*

As with APIs for libraries of static methods, an API for an abstract data type is a contract with all clients and,
therefore, the starting point both for developing any client code and for developing any data-type implementation. In
this case, the API tells us that to use Counter, we have available the Counter() constructor, the increment()
and tally() instance methods, and the inherited toString() method.

**Inherited methods**. Various Java conventions enable a data type to take advantage of built-in language mechanisms by
including specific methods in the API. For example, all Java data types inherit a toString() method that returns a
String representation of the data-type values. Java calls this method when any data-type value is to be concatenated
with a String value with the + operator. The default implementation is not particularly useful (it gives a string
representation of the memory address of the data-type value), so we often provide an implementation that overrides the
default, and include toString() in the API whenever we do so. Other examples of such methods include equals(),
compareTo(), and hashCode()

**Client code.** the API allows us to write client code without knowing details of the implementation (and to write
implementation code without knowing details of any particular client). All of the benefits of modular programming
follow. By encapsulating all the code that implements a data type within a single Java class, we enable the development
of client code at a higher level of abstraction. To develop client code, you need to be able to declare variables,
create objects to hold datatype values, and provide access to the values for instance methods to operate on them. These
processes are different from the corresponding processes for primitive types, though you will notice many similarities.

**Objects.** A *reference* is a mechanism for accessing an object. Java nomenclature makes clear the distinction from
primitive types (where variables are associated with values) by using the term *reference types* for nonprimitive types.
The details of implementing references vary in Java implementations, but it is useful to think of a reference as a
memory address,

Each data-type value is stored in an object. A constructor has no return type because it always returns a reference to
an object of its data type. Each time that a client uses new(), the system

- Allocates memory space for the object

- Invokes the constructor to initialize its value

- Returns a reference to the object

In client code we typically create objects in an initializing declaration that associates a variable with the object, as
we often do with variables of primitive types.

Invoking instance methods. The purpose of an instance method is to operate on datatype values, so the Java language
includes a special mechanism to invoke instance methods that emphasizes a connection to an object. . Instance methods
have all the properties of static methods

> *The primary purpose of static methods is to implement functions; the primary purpose of non-static (instance)
methods is to implement data-type operations*

**Assignment statements**. An assignment statement with a reference type creates a copy of the reference. The assignment
statement does not create a new object, just another reference to an existing object. This situation is known as **
aliasing**: both variables refer to the same object. The effect of aliasing is a bit unexpected, because it is different
for variables holding values of a primitive type. Be sure that you understand the difference.

**Objects as arguments**. You can pass objects as arguments to methods. This ability typically simplifies client code.
For example, when we use a Counter as an argument, we are essentially passing both a name and a tally, but need only
specify one variable. When we call a method with arguments, the effect in Java is as if each argument value were to
appear on the right-hand side of an assignment statement with the corresponding argument name on the left. That is, Java
passes a copy of the argument value from the calling program to the method. This arrangement is known as **pass by
value**

> One important consequence is that the method cannot change the value of a caller’s variable.

For primitive types, this policy is what we expect (the two variables are independent), but each time that we use a
reference type as a method argument we create an alias, so we must be cautious (the program pass the object by
reference)

For example, if we pass a reference to an object of type Counter, the method cannot change the original reference
(make it point to a different Counter), but it can change the value of the object, for example by using the reference to
call increment().

**Objects as return values**. Naturally, you can also use an object as a return value from a method. This capability is
important because Java methods allow only one return value—using objects enables us to write code that, in effect,
returns multiple values.

**Arrays are objects**. In Java, every value of any nonprimitive type is an object. In particular, arrays are objects.

An array of objects in Java is an array of references to objects, not the objects themselves. If the objects are large,
then we may gain efficiency by not having to move them around, just their references. If they are small, we may lose
efficiency by having to follow a reference each time we need to get to some information.

**Summary.** A data type is a set of values and a set of operations defined on those values. We implement data types in
independent Java class modules and write client programs that use them. An object is an entity that can take on a
data-type value or an instance of a data type. Objects are characterized by three essential properties: *state,
identity, and behavior*

writing code that embraces data abstraction (defining and using data types, with data-type values held in objects)
is widely referred to as *object-oriented programming*.

###### Example:  Geometric objects.

A natural example of object-oriented programming is designing data types for geometric objects. Following we define
abstract data types for three familiar geometric objects: Point2D (points in the plane), Interval1D (intervals on the
line), and Interval2D (twodimensional intervals in the plane, or axis-aligned rectangles). As usual, the APIs are
essentially self-documenting and lead immediately to easily understood client code.

    public class Point2D 
                Point2D(double x, double y)             create a point
        double  x()                                     x coordinate
        double  y()                                     y coordinate
        double  r()                                     radius ( polar coordinates)
        double  theta()                                 angle (polar coordinates)
        double  distTo(Point2D that)                    Euclidean distance from this point to that
        void    draw()                                  draw the point on StdDraw


    public class Interval1D 
                Interval1D(double lo, double hi)                create an interval
        double  length()                                        length of the interval
        boolean contains(double x)                              does the interval contain x?
        boolean intersects(Interval1D that)                     does the interval intersect that?
        void    draw()                                          draw the interval on StdDraw


    public class Interval2D
                Interval2D(Interval1D x, Interval1D y)          create a 2D interval
        double  area()                                          area of the 2D interval
        boolean contains(Point p)                               does the 2D interval contain p?
        boolean intersects(Interval2D that)                     does the 2D interval intersect that?
        void    draw()                                          draw the 2D interval on StdDraw

Of course, we can define APIs for other geometric objects such as line segments, triangles, polygons, circles, and so
forth, though implementing operations on them can be challenging.

###### Implementing an abstract data type

The first statements in the file declare instance variables that define the data-type values. Following the instance
variables are the constructor and the instance methods that implement operations on data-type values. Instance methods
may be public (specified in the API) or private (used to organize the computation and not available to clients). A
data-type definition may have multiple constructors and may also include definitions of static methods.

As a first example, we consider an implementation of the Counter ADT that we defined before.

**Scope.** In summary, the Java code that we write to implement instance methods uses three kinds of variables:

- Parameter variables
- Local variables
- Instance variables

The first two of these are the same as for static methods: parameter variables are specified in the method signature and
initialized with client values when the method is called, and local variables are declared and initialized within the
method body. The scope of parameter variables is the entire method; the scope of local variables is the following
statements in the block where they are defined. Instance variables are completely different: they hold data-type values
for objects in a class, and their scope is the entire class (whenever there is an ambiguity, you can use the "*this*"
prefix to identify instance variables). Understanding the distinctions among these three kinds of variables in instance
methods is a key to success in object-oriented programming.

> ***API, clients, and implementations**. These are the basic components that you need to understand to be able to
> build and use abstract data types in Java*

To fully understand a data type, we need the API, typical client code, and an implementation- To emphasize the
separation of client and implementation, we normally present each client as a separate class containing a static method
main() and reserve test client’s main() in the data-type definition for minimal unit testing and development (calling
each instance method at least once). In each data type that we develop, we go through the same steps

we think about the needs of a client, then accommodate them in an ADT, following these three steps:

- **Specify an API.** The purpose of the API is to separate clients from implementations, to enable modular programming.
  First, we want to enable clear and correct client code. Second, we want to be able to implement the operations.

- **Implement a Java class that meets the API specifications.** First we choose the instance variables, then we write
  constructors and the instance methods.

- **Develop multiple test clients**, to validate the design decisions made in the first two steps.

What operations do clients need to perform, and what data-type values can best support those operations? These basic
decisions are at the heart of every implementation that we develop.

###### More ADT implementations

    public class Date implements Comparable<Date> 
              Date(int month, int day, int year)        create a date 
              Date(String date)                         create a date (parse constructor)
      int     month() 
      int     day() 
      int     year()
      String  toString()
      boolean equals(Object that)           
      int     compareTo(Date that) 
      int     hashCode()

**Maintaining multiple implementations.** Multiple implementations of the same API can present maintainence and
nomenclature issues. Indeed, a prime goal of this book is to consider in depth several implementations of each of a
number of fundamental ADTs, generally with different performance characteristics.

In this book, we often compare the performance of a single client using two different implementations of the same API.
For this reason, we generally adopt an informal naming convention where we:

- Identify different implementations of the same API by prepending a descriptive modifier.
- Maintain a reference implementation with no prefix that makes a choice that should be suitable for most clients

In a large system, this solution is not ideal, as it might involve changing client code. Java has various advanced
language mechanisms for maintaining multiple implementations without needing to change client code, but we use them
sparingly because their use is challenging (and even controversial) even for experts, especially in conjuction with
other advanced language features that we do value (generics and iterators).

**Accumulator.** The accumulator API shown next defines an abstract data type that provides to clients the ability to
maintain a running average of data values. For example, we use this data type frequently in this book to process
experimental results.

The implementation is straightforward: it maintains a int instance variable counts the number of data values seen so far
and a double instance variable that keeps track of the sum of the values seen so far; to compute the average it divides
the sum by the count. Note that the implementation does not save the data values—it could be used for a huge number of
them (even on a device that is not capable of holding that many), or a huge number of accumulators could be used on a
big system. This performance characteristic is subtle and might be specified in the API, because an implementation that
does save the values might cause an application to run out of memory.

    public class Accumulator 
              Accumulator()
      void    addDataValue(double val)          add a new data value
      double  mean()                            mean of all data values
      String  toString()                        string representation

##### Data-type Design

> An abstract data type is a data type whose representation is hidden from the client. This idea has had a powerful
> effect on modern programming.

**Encapsulation.** A hallmark of object-oriented programming is that it enables us to encapsulate data types within
their implementations, to facilitate separate development of clients and data type implementations. Encapsulation
enables modular programming, allowing us to

- Independently develop of client and implementation code
- Substitute improved implementations without affecting clients
- Support programs not yet written (the API is a guide for any future client)

Encapsulation also isolates data-type operations, which leads to the possibility of

- Limiting the potential for error
- Adding consistency checks and other debugging tools in implementations
- Clarifying client code

> breaking large programs into small modules that can be developed and debugged independently.

This approach improves the resiliency of our software by limiting and localizing the effects of making changes, and it
promotes code reuse by making it possible to substitute new implementations of a data type to improve performance,
accuracy, or memory footprint.

The key to success in modular programming is to *maintain independence among modules*. We do so by insisting on the API
being the only point of dependence between client and implementation. You do not need to know how a data type is
implemented in order to use it and you can assume that a client knows nothing but the API when implementing a data type.
Encapsulation is the key to attaining both of these advantages.

**Algorithms and ADTs.** Data abstraction is naturally suited to the study of algorithms, because it helps us provide a
framework within which we can precisely specify both what an algorithm needs to accomplish and how a client can make use
of an algorithm. Typically, in this book, an algorithm is an implementation of an instance method in an abstract data
type

**Interface inheritance.** The first inheritance mechanism that we consider is known as *subtyping*, which allows us to
specify a relationship between otherwise unrelated classes by specifying in an **interface** a set of common methods
that each implementing class must contain. An interface is nothing more than a list of instance methods.

For example, instead of using our informal API, we might have articulated an interface for Date:

    public interface Datable {
        int month(); 
        int day(); 
        int year();
    }

and then referred to the interface in our implementation code

    public class Date implements Datable {
        // implementation code (same as before)
    }

so that the Java compiler will check that it matches the interface. This arrangement is known as *interface inheritance*
—an implementing class inherits the interface. Interface inheritance allows us to *write client programs that can
manipulate objects of any type that implements the interface* (even a type to be created in the future), by invoking
methods in the interface. We might have used interface inheritance in place of our more informal APIs, but chose not to
do so to avoid dependence on specific high-level language mechanisms that are not critical to the understanding of
algorithms and to avoid the extra baggage of interface files.

**Implementation inheritance**. Java also supports another inheritence mechanism known as *subclassing*, which is a
powerful technique that enables a programmer to change behavior and add functionality without rewriting an entire class
from scratch. The idea is to define a new class ( subclass, or derived class) that inherits instance methods and
instance variables from another class ( superclass, or base class). The subclass contains more methods than the
superclass. Moreover, the subclass can redefine or override methods in the superclass. Subclassing is widely used by
systems programmers to build so-called extensible libraries—one programmer (even you) can add methods to a library built
by another programmer (or, perhaps, a team of systems programmers), effectively reusing the code in a potentially huge
library.

The use of subclassing is controversial among systems and applications programmers (its advantages over interface
inheritance are debatable), and we avoid it in this book because it generally works against encapsulation.

###### Memory management.

The ability to assign a new value to a reference variable creates the possibility that a program may have created an
object that can no longer be referenced. Such an object is said to be orphaned. Objects are also orphaned when they go
out of scope Accordingly, programming languages and systems need mechanisms to allocate memory for data-type values
during the time they are needed and to free the memory when they are no longer needed (for an object, sometime after it
is orphaned). Memory management turns out to be easier for primitive types because all of the information needed for
memory allocation is known at compile time. Java (and most other systems) takes care of reserving space for variables
when they are declared and freeing that space when they go out of scope. Memory management for objects is more
complicated: the system can allocate memory for an object when it is created, but cannot know precisely when to free the
memory associated with each object because the dynamics of a program in execution determines when objects are orphaned

One of Java’s most significant features is its ability to *automatically manage memory*. The idea is to free the
programmers from the responsibility of managing memory by keeping track of orphaned objects and returning the memory
they use to a pool of free memory. Reclaiming memory in this way is known as **garbage collection**.

One of Java’s characteristic features is its policy that references cannot be modified. This policy enables Java to do
efficient automatic garbage collection. Programmers still debate whether the overhead of automatic garbage collection
justifies the convenience of not having to worry about memory management

**Immutability.** A data type such as Date whose instance variables are all primitive and final is immutable (the value
of an object never changes once constructed.)

You have already encountered this distinction as a client programmer, when using Java arrays (mutable) and Java’s String
data type (immutable). When you pass a String to a method, you do not worry about that method changing the sequence of
characters in the String, but when you pass an array to a method, the method is free to change the contents of the
array. String objects are immutable because we generally do not want String values to change, and Java arrays are
mutable because we generally do want array values to change. There are also situations where we want to have mutable
strings (that is the purpose of Java’s StringBuilder class) and where we want to have immutable arrays
(that is the purpose of the Vector class that we consider later in this section). Generally, immutable types are easier
to use and harder to misuse than mutable types because the scope of code that can change their values is far smaller. It
is easier to debug code that uses immutable types because it is easier to guarantee that variables in client code that
uses them remain in a consistent state.

> When using mutable types, you must always be concerned about where and when their values change

Another downside of immutability stems from the fact that, unfortunately, final guarantees immutability only when
instance variables are primitive types, not reference types. If an instance variable of a reference type has the final
modifier, the value of that instance variable (the reference to an object) will never change—it will always refer to the
same object—but the value of the object itself can change.

> whether a data type is immutable should be specified in the API, so that clients know that object values will not change

###### Design by contract

Java language mechanisms that enables you to verify assumptions about your program as it is running. We use two Java
language mechanisms for this purpose:

- **Exceptions**, which generally handle unforeseen errors outside our control
- **Assertions**, which verify assumptions that we make within code we develop

Liberal use of both exceptions and assertions is good programming practice.

**Exceptions and errors.** Exceptions and errors are disruptive events that occur while a program is running, often to
signal an error. The action taken is known as throwing an exception or throwing an error. A general practice known as *
fail fast programming* suggests that an error is more easily pinpointed if an exception is thrown as soon as an error is
discovered (as opposed to ignoring the error and deferring the exception to sometime in the future).

**Assertions.** An assertion is a boolean expression that you are affirming is true at that point in the program. If the
expression is false, the program will terminate and report an error message. We use assertions both to gain confidence
in the correctness of programs and to document intent. For example, suppose that you have a computed value that you
might use to index into an array. If this value were negative, it would cause an **ArrayIndexOutOfBoundsException**
sometime later. But if you write the code

    assert index >= 0 : "Negative index in method X";

you can pinpoint the place where the error occurred. You can also add an optional detail message to help you locate the
bug. By default, assertions are disabled.

*Assertions are for debugging*: your program should not rely on assertions for normal operation since they may be
disabled. When you take a course in systems programming, you will learn to use assertions to ensure that your code never
terminates in a system error or goes into an infinite loop. One model, known as the **design-by-contract model of
programming** expresses the idea. The designer of a data type expresses a precondition (the condition that the client
promises to satisfy when calling a method), a postcondition (the condition that the implementation promises to achieve
when returning from a method), and side effects (any other change in state that the method could cause). During
development, these conditions can be tested with assertions.

### 1.3 Bags, Queues, Stacks

**APIs.** As usual, we begin our discussion of abstract data types for collections by defining their APIs, shown below.
Each contains a no-argument constructor, a method to add an item to the collection, a method to test whether the
collection is empty, and a method that returns the size of the collection. Stack and Queue each have a method to remove
a particular item from the collection. Beyond these basics, these APIs reflect two Java features that we will describe
on the next few pages: *generics* and *iterable collections* .

    public class Bag<Item> implements Iterable<Item> 
        Bag()                                   create an empty bag void 
        add(Item item)                          add an item
        boolean isEmpty()                       is the bag empty?
        int size()                              number of items in the bag

    
    FIFO queue
    public class Queue<Item> implements Iterable<Item> 
        Queue()                                                 create an empty queue
        void enqueue(Item item)                                 add an item
        Item dequeue()                                          remove the least recently added item
        boolean isEmpty()                                       is the queue empty?
        int size()                                              number of items in the queue

    
    Pushdown (LIFO) stack
    public class Stack<Item> implements Iterable<Item> 
        Stack()                                 create an empty stack
        void push(Item item)                    add an item 
        Item pop()                              remove the most recently added item 
        boolean isEmpty()                       is the stack empty?
        int size()                              number of items in the stack

**Generics.** An essential characteristic of collection ADTs is that *we should be able to use them for any type of
data*. A specific Java mechanism known as *generics*, also known as *parameterized types*, enables this capability. The
notation <Item> after the class name in each of our APIs defines the name Item as a type parameter, *a symbolic
placeholder for some concrete type to be used* by the client. You can read Stack<Item> as “stack of items.” When
implementing Stack, we do not know the concrete type of Item, but a client can use our stack for any type of data,
including one defined long after we develop our implementation. This provides exactly the capability that we need

Without generics, we would have to define (and implement) different APIs for each type of data we might need to collect;
with generics, we can use one API (and one implementation) for all types of data, even types that are implemented in the
future

**Autoboxing.** Autoboxing. Type parameters have to be instantiated as reference types, so Java has special mechanisms
to allow generic code to be used with primitive types. Recall that Java’s wrapper types are reference types that
correspond to primitive types: Boolean, Byte, Character, Double, Float, Integer, Long, and Short correspond to boolean,
byte, char, double, float, int, long, and short, respectively. Java automatically converts between these reference types
and the corresponding primitive types—in assignments, method arguments, and arithmetic/logic expressions.

this conversion is helpful because it enables us to use generics with primitive types, as in the following code:

    Stack<Integer> stack = new Stack<Integer>(); 
    stack.push(17);                         // auto-boxing (int -> Integer) 
    int i = stack.pop();                    // auto-unboxing (Integer -> int)

> Automatically casting a primitive type to a wrapper type is known as autoboxing, and automatically casting a wrapper
> type to a primitive type is known as auto-unboxing.

**Iterable Collections.** This paradigm is so important that it has achieved first-class status in Java and many other
modern languages (the programming language itself has specific mechanisms to support it, not just the libraries). With
it, we can write clear and compact code that is free from dependence on the details of a collection’s implementation

For example, suppose that a client maintains a collection of transactions in a Queue, as follows:

    Queue<Transaction> collection = new Queue<Transaction>();

If the collection is iterable, the client can print a transaction list with a single statement:

    for (Transaction t : collection) { StdOut.println(t); }

This construct is known as the *foreach statement*: you can read the for statement as *for each transaction t in the
collection, execute the following block of code*. This client code does not need to know anything about the
representation or the implementation of the collection; it just wants to process each of the items in the collection

The same for loop would work with a Bag of transactions or any other iterable collection. We could hardly imagine client
code that is more clear and compact. As you will see, supporting this capability requires extra effort in the
implementation, but this effort is well worthwhile.

It is interesting to note that the only differences between the APIs for Stack and Queue are their names and the names
of the methods. This observation highlights the idea that we cannot easily specify all of the characteristics of a data
type in a list of method signatures. In this case, the true specification has to do with the English-language
descriptions that specify the rules by which an item is chosen to be removed (or to be processed next in the foreach
statement). Differences in these rules are profound, part of the API, and certainly of critical importance in developing
client code

#### Bag

A bag is a collection where removing items is not supported—its purpose is to provide clients with the ability to
collect items and then to iterate through the collected items (the client can also test if a bag is empty and find its
number of items). The order of iteration is unspecified and should be immaterial to the client. Such a client could use
a stack or a queue, but one way to emphasize that the order in which items are processed is immaterial is to use a Bag

#### FIFO queues

is a collection that is based on the firstin-first-out (FIFO) policy. The policy of doing tasks in the same order that
they arrive is one that we encounter frequently in everyday life. The first idea that comes to mind when most people
think about fairness is that whoever has been waiting the longest should be served first. That is precisely the FIFO
discipline. Queues are a natural model for many everyday phenomena, and they play a central role in numerous
applications. When a client iterates through the items in a queue with the foreach construct, the items are processed in
the order they were added to the queue.

> A typical reason to use a queue in an application is to *save items in a  collection while at the same time preserving
> their relative order* : they come out in the same order in which they were put in

#### Stacks

is a collection that is based on the last-in-first-out (LIFO) policy. When a client iterates through the items in a
stack with the foreach construct, the items are processed in the reverse of the order in which they were added. A
typical reason to use a stack iterator in an application is to save items in a collection while at the same time
*reversing* their relative order .

###### Arithmetic expression evaluation.

write a Java program that can take a string as input (the expression) and produce the number represented by the
expression as output. For simplicity, we begin with the following explicit recursive definition: an arithmetic
expression is either a number, or a left parenthesis followed by an arithmetic expression followed by an operator
followed by another arithmetic expression followed by a right parenthesis. For simplicity, this definition is for fully
parenthesized arithmetic expressions, which specify precisely which operators apply to which operands

For specificity, we support the familiar binary operators *, +, -, and /, as well as a square-root operator sqrt that
takes just one argument. Our focus is on understanding how to interpret the string to enable performing in the proper
order the low-level arithmetic operations that are available on any computer.

A remarkably simple algorithm that was developed by E. W. Dijkstra in the 1960s uses two stacks (one for operands and
one for operators) to do this job. An expression consists of parentheses, operators, and operands (numbers). Proceeding
from left to right and taking these entities one at a time, we manipulate the stacks according to four possible cases,
as follows:

- Push operands onto the operand stack.
- Push operators onto the operator stack.
- Ignore left parentheses.
- On encountering a right parenthesis, pop an operator, pop the requisite number of operands, and push onto the operand
  stack the result of applying that operator to those operands.

After the final right parenthesis has been processed, there is one value on the stack, which is the value of the
expression.

any time the algorithm encounters a subexpression consisting of two operands separated by an operator, all surrounded by
parentheses, it leaves the result of performing that operation on those operands on the operand stack. The result is the
same as if that value had appeared in the input instead of the subexpression, so we can think of replacing the
subexpression by the value to get an expression that would yield the same result. We can apply this argument again and
again until we get a single value. For example, the algorithm computes the same value for all of these expressions:

    ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
    ( 1 + ( 5 * ( 4 * 5 ) ) )
    ( 1 + ( 5 * 20 ) )
    ( 1 + 100 )
    101

#### Implementing collections

**Generics.** how do we implement a generic stack? declare the class with the following first line of code:

    public class FixedCapacityStack<Item>

The name Item is a **type parameter**, a *symbolic placeholder* for some concrete type to be used by the client. You can
read FixedCapacityStack<Item> as *stack of items*, which is precisely what we want. When implementing
FixedCapacityStack, we do not know the actual type of Item, but a client can use our stack for any type of data by
providing a concrete type when the stack is created. Concrete types must be reference types, but clients can depend on
autoboxing to convert primitive types to their corresponding wrapper types. Java uses the type parameter Item to check
for type mismatch errors—even though no concrete type is yet known, variables of type Item must be assigned values of
type Item, and so forth

For historical and technical reasons beyond our scope, *generic array creation is disallowed in Java*. Instead, we need
to use a cast:

    a = (Item[]) new Object[cap];

This code produces the desired effect (though the Java compiler gives a warning, which we can safely ignore), and we use
this idiom throughout the book.

**Iteration.** As mentioned earlier in this section, one of the fundamental operations on collections is to process each
item by *iterating through the collection* using Java’s foreach statement. This paradigm leads to clear and compact code
that is free from dependence on the details of a collection’s implementation

the ingredients that we need to implement in any iterable collection:

- The collection must implement an *iterator() method* that returns an Iterator object.
- The Iterator class must include two methods: *hasNext()* (which returns a boolean value) and *next()* (which returns a
  generic item from the collection).

In Java, we use the interface mechanism to express the idea that a class implements a specific method. For iterable
collections, the necessary interfaces are already defined for us in Java. To make a class iterable, the first step is to
add the phrase implements Iterable<Item> to its declaration, and to add a method iterator() to the class that returns an
Iterator<Item>.

Iterators are generic, so we can use our parameterized type Item to allow clients to iterate through objects of whatever
type is provided by our client.

> What is an iterator? An object from a class that implements the methods hasNext()
and next(), as defined in the following interface (which is in java.util.Iterator):

    public interface Iterator<Item>
        {
            boolean hasNext();
            Item next();
            void remove();
        }

we have to include *import java.util.Iterator*; at the beginning of the program because (for historical reasons)
Iterator is not part of java.lang (even though Iterable is part of java.lang)

> This arrangement is of critical importance for implementations of fundamental data types  it frees us to switch to
> a totally different representation without having to change any client code. More important, taking the client’s
> point of view, it allows clients to use iteration without having to know any details of the class implementation

In the context of the study of algorithms, Algorithm 1.1 is significant because it almost (but not quite) achieves
optimum performance goals for any collection implementation:

- Each operation should require time independent of the collection size.
- The space used should always be within a constant factor of the collection size.

The flaw in ResizingArrayStack is that some push and pop operations require resizing: this takes time proportional to
the size of the stack. Next, we consider a way to correct this flaw, using a fundamentally different way to structure
data

#### Linked lists

Now we consider the use of a fundamental data structure that is an appropriate choice for representing the data in a
collection ADT implementation.

> **Definition**. A linked list is a recursive data structure that is either empty (null) or a reference to a node
> having a generic item and a reference to a linked list.

The *node* in this definition is an abstract entity that might hold any kind of data, in addition to the node reference
that characterizes its role in building linked lists

We start with a nested class that defines the node abstraction:

    private class Node
        {
            Item item;
            Node next;
        }

A Node has two instance variables: an Item (a parameterized type) and a Node. We define Node within the class where we
want to use it, and make it private because it is not for use by clients. As with any data type, we create an object of
type Node by invoking the (no-argument) constructor with new Node(). The result is a reference to a Node object whose
instance variables are both initialized to the value null.

To emphasize that we are just using the Node class to structure the data, we define no methods and we refer directly to
the instance variables in code: if first is a variable associated with an object of type Node, we can refer to the
instance variables with the code first.item and first.next. Classes of this kind are sometimes called **records**.

However, Node and its client code are in the same class in all of our implementations and not accessible by clients of
that class, so we still enjoy the benefits of data abstraction.

Now, from the recursive definition, we can represent a linked list with a variable of type Node simply by ensuring that
its value is either null or a reference to a Node whose next field is a reference to a linked lis

**Insert at the beginning**. First, suppose that you want to insert a new node into a linked list. The easiest place to
do so is at the beginning of the list. This code for inserting a node at the beginning of a linked list involves just a
few assignment statements, so the amount of time that it takes is independent of the length of the list.

**Remove from the beginning**. Next, suppose that you want to remove the first node from a list. This operation is even
easier. This operation just involves one assignment statement, so its running time is independent of the length of the
list.

**Insert at the end**. How do we add a node to the end of a linked list? To do so, we need a link to the last node in
the list, because that node’s link has to be changed to reference a new node containing the item to be inserted.
Maintaining an extra link is not something that should be taken lightly in linked-list code, because every method that
modifies the list needs code to check whether that variable needs to be modified (and to make the necessary
modifications).

For example, the code that we just examined for removing the first node in the list might involve changing the reference
to the last node in the list, since when there is only one node in the list, it is both the first one and the last one!
Also, this code does not work (it follows a null link) in the case that the list is empty. Details like these make
linked-list code notoriously difficult to debug.

**Other operations.** Other operations, such as the following, are not so easily handled:

- Remove a given node.
- Insert a new node before a given node.

For example, how can we remove the last node from a list? The link last is no help, because we need to set the link in
the previous node in the list (the one with the same value as last) to null. In the absence of any other information,
the only solution is to traverse the entire list looking for the node that links to last

> Such a solution is undesirable because it takes time proportional to the length of the list. The standard solution to
> enable arbitrary insertions and deletions is to use a **doubly-linked list**, where each node has two links, one in
> each direction

We leave the code for these operations as an exercise . We do not need doubly linked lists for any of our
implementations.

###### Stack implementation

It maintains the stack as a linked list, with the top of the stack at the beginning. Thus, to push() an item, we add it
to the beginning of the list, and to pop() an item, we remove it from the beginning of the list.

###### Queue implementation.

An implementation of our Queue API based on the linked-list data structure is also straightforward. Thus, to enqueue()
an item, we add it to the end of the list and to dequeue() an item, we remove it from the beginning of the list.

This implementation uses *the same data-structure* as does Stack—a linked list—but it implements *different algorithms*
for adding and removing items, which make the difference between LIFO and FIFO for the client. Again, the use of linked
lists achieves our optimum design goals: it can be used for any type of data, the space required is proportional to the
number of items in the collection, and the time required per operation is always independent of the size of the
collection.

