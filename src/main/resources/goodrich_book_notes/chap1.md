# Java Primer

In Java, executable statements are placed in functions, known as **methods**, that belong to *class definitions*. The
Universe class, in our first example, is extremely simple; its only method is a static one named `main`, which is the
first method to be executed when running a Java program.

    public class Universe {
        public static void main(String[] args) {
            System.out.println("Hello world");
        }
    }

For the most commonly used data types, Java provides the following **base types**
(also called **primitive types**):

- **boolean** a boolean value: true or false
- **char** 16-bit Unicode character
- **byte** 8-bit signed two’s complement integer
- **short** 16-bit signed two’s complement integer
- **int** 32-bit signed two’s complement integer
- **long** 64-bit signed two’s complement integer
- **float** 32-bit floating-point number (IEEE 754-1985)
- **double** 64-bit floating-point number (IEEE 754-1985)

A variable having one of these types simply stores a value of that type. Integer constants, like 14 or 195, are of type
int, unless followed immediately by an ‘L’ or ‘l’, in which case they are of type long. Floating-point constants, like
3.1416 or 6.022e23, are of type double, unless followed immediately by an ‘F’ or ‘f’, in which case they are of type
float.

Every object is an instance of a class, which serves as the type of the object and as a blueprint, defining the data
which the object stores and the methods for accessing and modifying that data. The critical members of a class in Java
are the following:

- **Instance variables** represent the data associated with an object of a class. Instance variables must have a `type`,
  which can either be a base type or any class type (also known as a *reference type* ).
- **Methods** in Java are blocks of code that can be called to perform actions. (similar to functions and procedures in
  other high-level languages). A method that returns information to the caller without changing any instance variables
  is known as an **accessor method**, while an **update method**
  is one that may change one or more instance variables when called

Classes are known as **reference types** in Java, and a variable of that type is known as a **reference variable**. A
reference variable is capable of storing the location (i.e., **memory address**) of an object from the declared class.
So we might assign it to reference an existing instance or a newly constructed instance. A reference variable can also
store a special value, **null**, that represents the lack of an object.

Three events occur as part of the creation of a new instance of a class:

- A new object is dynamically allocated in memory, and all instance variables are initialized to standard default
  values. The default values are null for reference variables and 0 for all base types except boolean variables (which
  are false by default).
- The constructor for the new object is called with the parameters specified. The constructor may assign more meaningful
  values to any of the instance variables, and perform any additional computations that must be done due to the creation
  of this object.
- After the constructor returns, the **new** operator returns a reference (that is, a memory address) to the newly
  created object. If the expression is in the form of an assignment statement, then this address is stored in the object
  variable, so the object variable **refers** to this newly created object.

A method’s name combined with the number and types of its parameters is called a method’s **signature**, for it takes
all of these parts to determine the actual method to perform for a certain method call. Note, however, that the
signature of a method in Java does not include the type that the method returns, so Java does not allow two methods with
the same signature to return different types.

There can be many references to the same object, and each reference to a specific object can be used to call methods on
that object. Such a situation would correspond to our having many remote controls that all work on the same device. If
one object reference variable is used to change the state of the object, then its state changes for all the references
to it. This behavior comes from the fact that there are many references, but they all point to the same object.

### Class modifiers

Immediately before the definition of a class, instance variable, or method in Java, keywords known as **modifiers** can
be placed to convey additional stipulations about that definition. The first set of modifiers we discuss are known as
**access control modifiers**, as they control the level of access (also known as **visibility**) that the defining class
grants to other classes in the context of a larger Java program. The ability to limit access among classes supports a
key principle of object-orientation known as **encapsulation**.

**Public.** The public class modifier designates that all classes may access the defined aspect.

If a class is public all other classes are allowed to construct new instances of this class, as well as to declare
variables and parameters of its type. In Java, each public class must be defined in a separate file with the same name
of the class.

The designation of public access for a particular method of a class allows any other class to make a call to that
method. If an instance variable is declared as public, dot notation can be used to directly access the variable by code
in any other class that possesses a reference to an instance of this class.

**Protected** The protected class modifier designates that access to the defined aspect is only granted to the following
groups of other classes:

- Classes that are designated as **subclasses** of the given class through inheritance.
- Classes that belong to the same **package** as the given class.

**Private.** The private class modifier designates that access to a defined member of a class be granted only to code
within that class. Neither subclasses nor any other classes have access to such members

Finally, we note that if no explicit access control modifier is given, the defined aspect has what is known as
**package-private** access level. This allows other classes in the same package to have access, but not any classes or
subclasses from other packages.

#### The static Modifier

The static modifier in Java can be declared for any variable or method of a class (or for a nested class). When a
variable of a class is declared as **static**, its value is associated with the class as a whole, rather than with each
individual instance of that class. Static variables are used to store “global” information about a class. Static
variables exist even if no instance of their class exists.

When a method of a class is declared as **static**, it too is associated with the class itself, and not with a
particular instance of the class. That means that the method is not invoked on a particular instance of the class using
the traditional dot notation. Instead, it is typically invoked using the name of the class as a qualifier.

#### The abstract Modifier

A method of a class may be declared as **abstract**, in which case its signature is provided but without an
implementation of the method body. Abstract methods are an advanced feature of object-oriented programming to be
combined with inheritance. In short, any subclass of a class with abstract methods is expected to provide a concrete
implementation for each abstract method.

A class with one or more abstract methods must also be formally declared as abstract, because it is essentially
incomplete. (It is also permissible to declare a class as abstract even if it does not contain any abstract methods.) As
a result, Java will not allow any instances of an abstract class to be constructed, although reference variables may be
declared with an abstract type.

#### The final Modifier

A variable that is declared with the **final** modifier can be initialized as part of that declaration, but can never
again be assigned a new value. If it is a base type, then it is a **constant**. If a reference variable is final, then
it will always refer to the same object (even if that object changes its internal state). If a member variable of a
class is declared as final, it will typically be declared as static as well, because it would be unnecessarily wasteful
to have every instance store the identical value when that value can be shared by the entire class.

Designating a method or an entire class as final has a completely different consequence, only relevant in the context of
inheritance. A final method cannot be overridden by a subclass, and a final class cannot even be subclassed.

### Declaring methods

A method definition has two parts: the **signature**, which defines the name and parameters for a method, and the
**body**, which defines what the method does. The method signature specifies how the method is called, and the method
body specifies what the object will do when it is called.

When a (nonstatic) method of a class is called, it is invoked on a specific instance of that class and can change the
state of that object.

Java methods can return only one value. To return multiple values in Java, we should instead combine all the values we
want to return in a **compound object**, whose instance variables include all the values we want to return, and then
return a reference to that compound object. In addition, we can change the internal state of an object that is passed to
a method as another way of “returning” multiple results.

***All parameters in Java are passed by value***, that is, any time we pass a parameter to a method, a **copy** of that
parameter is made for use within the method body. So if we pass an int variable to a method, then that variable’s
integer value is copied. The method can change the copy but not the original. If we pass an object reference as a
parameter to a method, then the reference is copied as well. Remember that we can have many different variables that all
refer to the same object. Reassigning the internal reference variable inside a method will not change the reference that
was passed in.

### Defining Constructors

A constructor is a special kind of method that is used to initialize a newly created instance of the class so that it
will be in a consistent and stable initial state.

- Constructors cannot be static, abstract, or final, so the only modifiers that are allowed are those that affect
  visibility (i.e., public, protected, private, or the default package-level visibility).
- The name of the constructor must be identical to the name of the class it constructs
- We don’t specify a return type for a constructor (not even void). Nor does the body of a constructor explicitly return
  anything. The **new** operator is responsible for returning a reference to the new instance to the caller; the
  responsibility of the constructor method is only to initialize the state of the new instance

A class can have many constructors, but each must have a different signature, that is, each must be distinguished by the
type and number of the parameters it takes. If no constructors are explicitly defined, Java provides an implicit default
constructor for the class, having zero arguments and leaving all instance variables initialized to their default values.
However, if a class defines one or more nondefault constructors, no default constructor will be provided.

### The keyword this

Within the body of a (nonstatic) method in Java, the keyword **this** is automatically defined as a reference to the
instance upon which the method was invoked. That is, if a caller uses a syntax such as thing.foo(a, b, c), then within
the body of method foo for that call, the keyword this refers to the object known as thing in the caller’s context.

We can use the `this` reference To allow one constructor body to invoke another constructor body. When one method of a
class invokes another method of that same class on the current instance, that is typically done by using the (
unqualified) name of the other method. But the syntax for calling a constructor is special. Java allows use of the
keyword this to be used as a method within the body of one constructor, so as to invoke another constructor with a
different signature.

This is often useful because all the initialization steps of one constructor can be reused with appropriate
parameterization.

### The main method

Some Java classes are meant to be used by other classes, but are not intended to serve as a self-standing program. The
primary control for an application in Java must begin in some class with the execution of a special method named main

The args parameter is an array of String objects, that is, a collection of indexed strings. Those represent what are
known as **command-line arguments** that are given by a user when the program is executed.

### The String Class

Java’s **char** base type stores a value that represents a single text **character**. In Java, the set of all possible
characters, known as an **alphabet**, is the Unicode international character set, a 16-bit character encoding that
covers most used written languages.

A string instance represents a sequence of zero or more characters. The class provides extensive support for various
text-processing tasks. Each character within a string can be referenced by using an **index**. Java’s String class
supports a method length( ), which returns the length of a string instance, and a method charAt(k), which returns the
character at index k.

#### The StringBuilder Class

An important trait of Java’s String class is that its instances are **immutable**; once an instance is created and
initialized, the value of that instance cannot be changed. This is an intentional design, as it allows for great
efficiencies and optimizations within the Java Virtual Machine.

However, because String is a class in Java, it is a reference type. Therefore, variables of type String can be
reassigned to another string instance (even if the current string instance cannot be changed),

It is also quite common in Java to use string concatenation to build a new string. However, it is important to remember
that this operation does create a new string instance, copying all the characters of the existing string in the process.

In order to support more efficient editing of character strings, Java provides a **StringBuilder** class, which is
effectively a **mutable version** of a string. This class combines some the accessor methods of the String class, while
supporting additional methods.

### Wrapper Types

There are many data structures and algorithms in Java’s libraries that are specifically designed so that they only work
with object types (not primitives). To get around this obstacle, Java defines a **wrapper class** for each base type. An
instance of each wrapper type stores a single value of the corresponding base type.

Java provides additional support for implicitly converting between base types and their wrapper types through a process
known as **automatic boxing** and **unboxing**.

In any context for which an Integer is expected (for example, as a parameter), an int value k can be expressed, in which
case Java automatically **boxes** the int, with an implicit call to `new Integer(k)`. In reverse, in any context for
which an int is expected, an Integer value v can be given in which case Java automatically **unboxes** it with an
implicit call to `v.intValue( )`. Similar conversions are made with the other base-type wrappers. Finally, all of the
wrapper types provide support for converting back and forth between string literals

### Arrays

Arrays in Java are somewhat unusual, in that they are not technically a base type nor are they instances of a particular
class. With that said, an instance of an array is treated as an object by Java, and variables of an array type are
*reference variables*.

There are two ways for creating an array. The first way to create an array is to use an assignment to a literal form
when initially declaring the array, using a syntax as:

    elementType[] arrayName = {value1, value2, . . . , value(n-1)};

The elementType can be any Java base type or class name, and arrayName can be any valid Java identifier. The initial
values must be of the same type as the array. When using an initializer, an array is created having precisely the
capacity needed to store the indicated values.

The second way to create an array is to use the new operator. However, because an array is not an instance of a class,
we do not use a typical constructor syntax. Instead we use the syntax:

    new elementType[length]

where length is a positive integer denoting the length of the new array. When arrays are created using the new operator,
all of their elements are automatically assigned the default value for the element type.

### Enum Types

Java supports a more elegant approach to representing choices from a finite set by defining what is known as an
enumerated type, or enum for short. These are types that are only allowed to take on values that come from a specified
set of names. They are declared as follows:

    modifier enum name { valueName0 , valueName1 , ..., valueNamen−1 };

where the modifier can be blank, public, protected, or private. For example, an enumerated type definition for days of
the weak might appear as:

    public enum Day { MON, TUE, WED, THU, FRI, SAT, SUN };

Once defined, Day becomes an official type and we may declare variables or parameters with type Day. A variable of that
type can be declared as:

    Day today;

and an assignment of a value to that variable can appear as:

    today = Day.TUE;

## Simple Input and Output

Java provides a built-in static object, called System.out, that performs output to the “standard output” device.

The `System.out` object is an instance of the `java.io.PrintStream` class. This class defines methods for a buffered
output stream, meaning that characters are put in a temporary location, called a **buffer**, which is then emptied when
the console window is ready to print characters. Specifically, the `java.io.PrintStream` class provides the following
methods for performing simple output

- print(String s): Print the string s.
- print(Object o): Print the object o using its toString method.
- print(baseType b): Print the base type value b.
- println(String s): Print the string s, followed by the newline character.
- println(Object o): Similar to print(o), followed by the newline character.
- println(baseType b): Similar to print(b), followed by the newline character

there is also a special object, called `System.in`, for performing input from the Java console window. Technically, the
input is actually coming from the “standard input” device, which by default is the computer keyboard echoing its
characters in the Java console. The `System.in` object is an object associated with the standard input device. A simple
way of reading input with this object is to use it to create a **Scanner** object, using the expression
`new Scanner(System.in)`

The Scanner class has a number of convenient methods that read from the given input stream

### Documentation and Style

In order to encourage good use of block comments and the automatic production of documentation, the Java programming
environment comes with a documentation production program called **javadoc**. This program takes a collection of Java
source files that have been commented using certain keywords, called tags, and it produces a series of HTML documents
that describe the classes, methods, variables, and constants contained in these files.

Each javadoc comment is a block comment that starts with “/**” and ends with “*/”, and each line between these two can
begin with a single asterisk, “*”, which is ignored. The block comment is assumed to start with a descriptive sentence,
which is followed by special lines that begin with javadoc tags. A block comment that comes just before a class
definition, instance variable declaration, or method definition is processed by javadoc into a comment about that class,
variable, or method. 