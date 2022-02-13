# Object-Oriented Design

** Object-Oriented Design Goals.**
Software implementations should achieve robustness, adaptability, and reusability.

**Object-Oriented Design Principles**
They are intended to facilitate the goals outlined above

- Abstraction
- Encapsulation
- Modularity

An **ADT** specifies what each operation does, but not how it does it. In Java, an ADT can be expressed by an **
interface**, which is simply a list of method declarations, where each method has an empty body.

An ADT is realized by a concrete data structure, which is modeled in Java by a class. A class defines the data being
stored and the operations supported by the objects that are instances of the class. Also, unlike interfaces, classes
specify how the operations are performed in the body of each method. A Java class is said to **implement an interface**
if its methods include all the methods declared in the interface, thus providing a body for them. However, a class can
have more methods than those of the interface.

## 2.2 Inheritance

In object-oriented programming, the mechanism for a modular and hierarchical organization is a technique known as
**inheritance**. This allows a new class to be defined based upon an existing class as the starting point. In
object-oriented terminology, the existing class is typically described as the **base class**, **parent class**, or
superclass, while the newly defined class is known as the **subclass** or **child class**. We say that the subclass
**extends** the superclass.

When inheritance is used, the subclass automatically inherits, as its starting point, all methods from the superclass (
other than constructors). The subclass can differentiate itself from its superclass in two ways. It may **augment** the
superclass by adding new fields and new methods. It may also **specialize** existing behaviors by providing a new
implementation that **overrides** an existing method.

Constructors are never inherited in Java. When a child instance is created, all of its fields must be properly
initialized, including any inherited fields. For this reason, the first operation performed within the body of a
constructor must be to invoke a constructor of the superclass, which is responsible for properly initializing the fields
defined in the superclass. In Java, a constructor of the superclass is invoked by using the keyword **super**
with appropriate parameterization,

If a constructor for a subclass does not make an explicit call to super or this as its first command, then an implicit
call to `super( )`, the zero-parameter version of the superclass constructor, will be made.

### Polymorphism and Dynamic Dispatch

In the context of object-oriented design,polymorphism it refers to the ability of a reference variable to take different
forms. Consider, for example, the declaration of a variable having CreditCard as its type:

    CreditCard card;

Because this is a reference variable, the statement declares the new variable, which does not yet refer to any card
instance. While we have already seen that we can assign it to a newly constructed instance of the CreditCard class, Java
also allows us to assign that variable to refer to an instance of the PredatoryCreditCard subclass.

    CreditCard card = new PredatoryCreditCard(...)

This is a demonstration of what is known as the **Liskov Substitution Principle**, which states that a variable (or
parameter) with a declared type can be assigned an instance from any direct or indirect subclass of that type.
Informally, this is a manifestation of the “is a” relationship modeled by inheritance, as a predatory credit card is a
credit card (but a credit card is not necessarily predatory).

We say that the variable, card, is **polymorphic**; it may take one of many forms, depending on the specific class of
the object to which it refers. Because card has been declared with type CreditCard, that variable may only be used to
call methods that are declared as part of the CreditCard definition (if not a compile error will raise by java)

An interesting (and important) issue is how Java handles a call such as

    card.charge(100)

Recall that there are distinct implementations of the charge method: CreditCard.charge and PredatoryCreditCard.charge.

Java uses a process known as **dynamic dispatch**, deciding at runtime to call the version of the method that is most
specific to the actual type of the referenced object(not the declared type). So, if the object is
a `PredatoryCreditCard` instance, it will execute the `PredatoryCreditCard.charge` method, even if the reference
variable has a declared type of `CreditCard`.

Java also provides an **instanceof** operator that tests, at runtime, whether an instance satisfies as a particular
type. For example, the evaluation of the boolean condition,`(card instanceof PredatoryCreditCard)`, produces true if the
object currently referenced by the variable card belongs to the `PredatoryCreditCard` class, or any further subclass of
that class.

Although a subclass may not inherit from multiple superclasses in Java, a superclass may have many subclasses. In fact,
it is quite common in Java to develop complex inheritance hierarchies to maximize the reusability of code.

## Interfaces and Abstract Classes

In order for two objects to interact, they must “know” about the methods each object supports. To enforce this
“knowledge,” the object-oriented design paradigm asks that classes specify the **application programming interface
(API)**, or simply interface, that their objects present to other objects

This specification is, in turn, enforced by the compiler or runtime system, which requires that the types of parameters
that are actually passed to methods rigidly conform with the type specified in the interface. This requirement is known
as **strong typing**. Having to define interfaces and then having those definitions enforced by strong typing admittedly
places a burden on the programmer, but this burden is offset by the rewards it provides, for it enforces the
encapsulation principle and often catches programming errors that would otherwise go unnoticed

The main structural element in Java that enforces an API is an interface. An interface is a collection of method
declarations with no data and no bodies. That is, the methods of an interface are always empty; they are simply method
signatures. Interfaces do not have constructors and they cannot be directly instantiated.

When a class implements an interface, it must implement all the methods declared in the interface. In this way,
interfaces enforce requirements that an implementing class has methods with certain specified signatures.

another feature of classes and interfaces in Java is that a class can implement multiple interfaces (even though it may
only extend one other class). This allows us a great deal of flexibility when defining classes that should conform to
multiple APIs.

#### Multiple Inheritance for Interfaces

The ability of extending from more than one type is known as **multiple inheritance**. In Java, multiple inheritance is
allowed for interfaces but not for classes. The reason for this rule is that interfaces do not define fields or method
bodies, yet classes typically do. Thus, if Java were to allow multiple inheritance for classes, there could be a
confusion if a class tried to extend from two classes that contained fields with the same name or methods with the same
signatures. Since there is no such confusion for interfaces, and there are times when multiple inheritance of interfaces
is useful, Java allows interfaces to use multiple inheritance

In particular, we can use multiple inheritance of interfaces as a mechanism for “mixing” the methods from two or more
unrelated interfaces to define an interface that combines their functionality, possibly adding more methods of its own.
Returning to our example of the antique objects, we could define an interface for insurable items.
(so, java does not have mixin classes, but it has multiple inheritance for interfaces that allow adds more functionality
to a given class like a mixin)

#### Abstract Classes

In Java, an **abstract class** serves a role somewhat between that of a traditional class and that of an interface. Like
an interface, an abstract class may define signatures for one or more methods without providing an implementation of
those method bodies; such methods are known as **abstract methods**. However, unlike an interface, an abstract class may
define one or more fields and any number of methods with implementation (so-called **concrete methods**). An abstract
class may also extend another class and be extended by further subclasses.

As is the case with interfaces, an abstract class may not be instantiated, that is, no object can be created directly
from an abstract class. In a sense, it remains an incomplete class. A subclass of an abstract class must provide an
implementation for the abstract methods of its superclass, or else remain abstract.

In comparing the use of interfaces and abstract classes, it is clear that abstract classes are more powerful, as they
can provide some concrete functionality. However, the use of abstract classes in Java is limited to **single
inheritance**, so a class may have at most one superclass, whether concrete or abstract

The commonality between a family of classes can be placed within an abstract class, which serves as a superclass to
multiple concrete classes. In this way, the concrete subclasses need only implement the additional functionality that
differentiates themselves from each other

As a tangible example, we reconsider the progression hierarchy. We did not intend for users to directly create instances
of the Progression class; in fact, the sequence that it produces is simply a special case of an arithmetic progression
with increment one. The primary purpose of the Progression class is to provide common functionality to all three
subclasses: the declaration and initialization of the current field, and the concrete implementations of the nextValue
and printProgression methods.

The most important aspect in specializing that class was in overriding the protected advance method.

Although our abstract class cannot be instantiated, the constructors can be invoked within the subclass constructors
using the super keyword. (We do just that, within all three of our progression subclasses.)

Even though we have not implemented the advance method as part of the AbstractProgression class, it is legal to call it
from within the body of nextValue. This is an example of an object-oriented design pattern known as the **template
method pattern**, in which an abstract base class provides a concrete behavior that relies upon calls to other abstract
behaviors. Once a subclass provides definitions for the missing abstract behaviors, the inherited concrete behavior is
well-defined.

## Exceptions

Exceptions are unexpected events that occur during the execution of a program. In Java, exceptions are objects that can
be **thrown** by code that encounters an unexpected situation, or by the Java Virtual Machine, for example, if running
out of memory. An exception may also be **caught** by a surrounding block of code that “handles” the problem in an
appropriate fashion. If uncaught, an exception causes the virtual machine to stop executing the program and to report an
appropriate message to the console. The stack trace shows the series of nested method calls that were active at the time
the exception occurred.

However, before a program is terminated, each method on the stack trace has an opportunity to catch the exception.
Starting with the most deeply nested method in which the exception occurs, each method may either catch the exception,
or allow it to pass through to the method that called it.

A typical syntax for a **try-catch statement** in Java is as follows:

        try {
            guardedBody
        } catch (exceptionType1 variable1) {
            remedyBody1
        } catch (exceptionType2 variable2) {
            remedyBody2
        }

If the block, guardedBody, generates an exception at some point, the execution of that block immediate terminates and
execution jumps to the catch block whose exceptionType most closely matches the exception thrown (if any). The variable
for this catch statement references the exception object itself, which can be used in the block of the matching catch
statement. Once execution of that catch block completes, control flow continues with the first statement beyond the
entire try-catch construct.

If an exception occurs during the execution of the block, guardedBody, that does not match any of the exception types
declared in the catch statements, that exception is rethrown in the surrounding context.

There can be an optional **finally** clause with a body that will be executed whether an exception happens in the
original guarded body; this can be useful, for example, to close a file before proceeding onward

Also, as of Java SE 7, each catch statement can designate multiple exception types that it handles; previously, a
separate clause would be needed for each one, even if the same remedy were applied in each case.

Exceptions originate when a piece of Java code finds some sort of problem during execution and throws an exception
object. This is done by using the **throw** keyword followed by an instance of the exception type to be thrown. It is
often convenient to instantiate an exception object at the time the exception has to be thrown. Thus, a throw statement
is typically written as follows:

        throw new exceptionType(parameters);

where exceptionType is the type of the exception and the parameters are sent to that type’s constructor; most exception
types offer a version of a constructor that accepts an error message string as a parameter.

###### The throws clause

When a method is declared, it is possible to explicitly declare, as part of its signature, the possibility that a
particular exception type may be thrown during a call to that method. It does not matter whether the exception is
directly from a throw statement in that method body, or propagated upward from a secondary method call made from within
the body. For example, the `parseInt` method of the Integer class has the following formal signature:

    public static int parseInt(String s) throws NumberFormatException;

The designation “throws NumberFormatException” warns users about the possibility of an exceptional case, so that they
might be better prepared to handle an exception that may arise. If one of many exception types may possibly be thrown,
all such types can be listed, separated with commas. Alternatively, it may be possible to list an appropriate superclass
that encompasses all specific exceptions that may be thrown

The use of a `throws` clause in a method signature does not take away the responsibility of properly documenting all
possible exceptions through the use of the @throws tag within a javadoc comment.

> The type and reasons for any potential exceptions should always be properly declared in the documentation for a method.

In contrast, the use of the throws clause in a method signature is optional for many types of exceptions.

Java defines a rich inheritance hierarchy of all objects that are deemed `Throwable`. The hierarchy is intentionally
divided into two subclasses: **Error** and **Exception**. Errors are typically thrown only by the Java Virtual Machine
and designate the most serious situations that are unlikely to be recoverable, such as when the virtual machine is asked
to execute a corrupt class file, or when the system runs out of memory. In contrast, exceptions designate situations in
which a running program might reasonably be able to recover,

**Checked and Unchecked Exceptions.**

Java provides further refinement by declaring the **RuntimeException** class as an important subclass of Exception. All
subtypes of RuntimeException in Java are officially treated as **unchecked exceptions**, and any exception type that is
not part of the RuntimeException is a **checked exception**.

The intent of the design is that runtime exceptions occur entirely due to mistakes in programming logic, such as using a
bad index with an array, or sending an inappropriate value as a parameter to a method. While such programming errors
will certainly occur as part of the software development process, they should presumably be resolved before software
reaches production quality. Therefore, it is not in the interest of efficiency to explicitly check for each such mistake
at runtime, and thus these are designated as “unchecked” exceptions.

In contrast, other exceptions occur because of conditions that cannot easily be detected until a program is executing,
such as an unavailable file or a failed network connection. Those are typically designated as “checked” exceptions in
Java (and thus, not a subtype of RuntimeException).

The designation between checked and unchecked exceptions plays a significant role in the syntax of the language. In
particular,

> ***all checked exceptions that might propagate upward from a method must be explicitly declared in its signature***

A consequence is that if one method calls a second method declaring checked exceptions, then the call to that second
method must either be guarded within a try-catch statement, or else the calling method must itself declare the checked
exceptions in its signature, since there is risk that such an exception might propagate upward from the calling method

## Casting and Generics

#### Widening Conversions

A **widening conversion** occurs when a type T is converted into a “wider” type U. The following are common cases of
widening conversions:

- T and U are class types and U is a superclass of T.
- T and U are interface types and U is a superinterface of T.
- T is a class that implements interface U.

> *Widening conversions are automatically performed to store the result of an expression into a variable, without the
> need for an explicit cast*.

Thus, we can directly assign the result of an expression of type T into a variable v of type U when the conversion from
T to U is a widening conversion

#### Narrowing Conversions

A **narrowing conversion** occurs when a type T is converted into a “narrower” type S. The following are common cases of
narrowing conversions:

- T and S are class types and S is a subclass of T.
- T and S are interface types and S is a subinterface of T.
- T is an interface implemented by class S.

In general, a narrowing conversion of reference types requires an explicit cast. Also, the correctness of a narrowing
conversion may not be verifiable by the compiler. Thus, its validity should be tested by the Java runtime environment
during program execution. Example

    CreditCard card = new PredatoryCreditCard(...); // widening
    PredatoryCreditCard pc = (PredatoryCreditCard) card; // narrowing

where `CreditCard` is a superclass of `PredatoryCreditCard`. the assignment `pc = card` is a narrowing conversion and
requires an explicit cast that will be evaluated at runtime (as not all cards are predatory).

to avoid peppering our code with try-catch blocks every time we perform a cast, Java provides a way to make sure an
object cast will be correct. Namely, it provides an operator, **instanceof**, that allows us to test whether an object
variable is referring to an object that belongs to a particular type.

### Generics

The **generics** framework allows us to define a class in terms of a set of formal type parameters, which can then be
used as the declared type for variables, parameters, and return values within the class definition. Those formal type
parameters are later specified when using the generic class as a type elsewhere in a program.

Often, we wish to treat a pair of related values as a single object, for example, so that the pair can be returned from
a method. A solution is to define a new class whose instances store both values. This is our first example of an
object-oriented design pattern known as the **composition design pattern**.

The goal of generic programming is to be able to write a single class that can represent all such pairs.

The generics framework was not a part of the original Java language; it was added as part of Java SE 5. Prior to that,
generic programming was implemented by relying heavily on Java’s Object class, which is the universal supertype of all
objects (including the wrapper types corresponding to primitives).

Although any valid identifier can be used for a formal type parameter, single-letter uppercase names are conventionally
used (in this example, A and B). We may then use these type parameters within the body of the class definition.

When subsequently declaring a variable with such a parameterize type, we must explicitly specify **actual type
parameters** that will take the place of the generic formal type parameters. For example, to declare a variable that is
a pair holding a stock-ticker string and a price, we write the following:

    Pair<String,Double> bid;

The actual types for generic programming must be object types, which is why we use the wrapper class Double instead of
the primitive type double. (Fortunately, the automatic boxing and unboxing will work in our favor.)

We can subsequently instantiate the generic class using the following syntax:

    bid = new Pair<>("ORCL", 32.07);                            // rely on type inference

An instance of the generic class is created, with the actual types for the formal type parameters determined based upon
the original declaration of the variable to which it is assigned (bid in this example). This process is known as **type
inference**

It is also possible to use a style that existed prior to Java SE 7, in which the generic type parameters are explicitly
specified between angle brackets during instantiation. Using that style, our previous example would be implemented as:

    bid = new Pair<String,Double>("ORCL", 32.07);               // give explicit types

However, it is important that one of the two above styles be used. If angle brackets are entirely omitted, as in the
following example,

        bid = new Pair("ORCL", 32.07); // classic style

this reverts to the classic style, with Object automatically used for all generic type parameters, and resulting in a
compiler warning when assigning to a variable with more specific types.

Since bid was declared with actual type parameters <String,Double>, the return type of the getFirst( ) method is String,
and the return type of the getSecond( ) method is Double. Unlike the classic style, we can make the following
assignments without any explicit casting (although there is still an automatic unboxing of the Double):

        String stock = bid.getFirst( ); double price = bid.getSecond( )

**Generics and Arrays.**

There is an important caveat related to generic types and the use of arrays. Although Java allows the declaration of an
array storing a parameterized type, it does not technically allow the instantiation of new arrays involving those types.
Fortunately, it allows an array defined with a parameterized type to be initialized with a newly created, nonparametric
array, which can then be cast to the parameterized type. Even so, this latter mechanism causes the Java compiler to
issue a warning, because it is not 100% type-safe. We will see this issue arise in two ways:

- Code outside a generic class may wish to declare an array storing instances of the generic class with actual type
  parameters.
- A generic class may wish to declare an array storing objects that belong to one of the formal parameter types.

As an example of the first use case, we continue with our stock market example and presume that we would like to keep an
array of `Pair<String,Double>` objects. Such an array can be declared with a parameterized type, but it must be
instantiated with an unparameterized type and then cast back to the parameterized type.

    Pair<String,Double>[ ] holdings;
    holdings = new Pair<String,Double>[25];     // illegal; compile error
    holdings = new Pair[25];                    // correct, but warning about unchecked cast
    holdings[0] = new Pair<>("ORCL", 32.07);    // valid element assignment

As an example of the second use case, assume that we want to create a generic Portfolio class that can store a fixed
number of generic entries in an array. If the class uses <T> as a parameterized type, it can declare an array of type
T[ ], but it cannot directly instantiate such an array. Instead, a common approach is to instantiate an array of type
Object[ ], and then make a narrowing cast to type T[ ].

##### Generics Methods

The generics framework allows us to define generic versions of individual methods (as opposed to generic versions of
entire classes). To do so, we include a generic formal type declaration among the method modifiers.

For example, we show below a nonparametric GenericDemo class with a parameterized static method that can reverse an
array containing elements of any object type

    public class GenericDemo {
      public static <T> void reverse(T[ ] data) {
        int low = 0, high = data.length − 1;
        while (low < high) { // swap data[low] and data[high]
          T temp = data[low];
          data[low++] = data[high]; // post-increment of low
          data[high−−] = temp; // post-decrement of high
        }
      }
    }

The method can be called using the syntax, GenericDemo.reverse(books), with type inference determining the generic type,
assuming books is an array of some object type. (This generic method cannot be applied to primitive arrays, because
autoboxing does not apply to entire arrays.)

As an aside, we note that we could have implemented a reverse method equally well using a classic style, acting upon an
Object[ ] array

##### Bounded Generic Types

By default, when using a type name such as T in a generic class or method, a user can specify any object type as the
actual type of the generic. A formal parameter type can be restricted by using the extends keyword followed by a class
or interface. In that case, only a type that satisfies the stated condition is allowed to substitute for the parameter.
The advantage of such a bounded type is that it becomes possible to call any methods that are guaranteed by the stated
bound. Example

    public class ShoppingCart<T extends Sellable> {

## Nested Classes

Java allows a class definition to be **nested** inside the definition of another class. The main use for nesting classes
is when defining a class that is strongly affiliated with another class. This can help increase encapsulation and reduce
undesired name conflicts. Nested classes are a valuable technique when implementing data structures, as an instance of a
nested use can be used to represent a small portion of a larger data structure, or an auxiliary class that helps
navigate a primary data structure.

A nested class has an independent set of modifiers from the outer class. Visibility modifiers (e.g., public, private)
effect whether the nested class definition is accessible beyond the outer class definition. For example, a private
nested class can be used by the outer class, but by no other classes.

A nested class can also be designated as either static or (by default) nonstatic, with significant consequences.

A static nested class is most like a traditional class; its instances have no association with any specific instance of
the outer class. A nonstatic nested class is more commonly known as an **inner class** in Java. An instance of an inner
class can only be created from within a nonstatic method of the outer class, and that inner instance becomes associated
with the outer instance that creates it.

Each instance of an inner class implicitly stores a reference to its associated outer instance, accessible from within
the inner class methods using the syntax `OuterName.this` (as opposed to this, which refers to the inner instance). The
inner instance also has private access to all members of its associated outer instance, and can rely on the formal type
parameters of the outer class, if generic.

