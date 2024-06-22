9# Searching

## 3.1 SYMBOL TABLES

The primary purpose of a symbol table is to associate a value with a key. The client can insert key-value pairs into the
symbol table with the expectation of later being able to search for the value associated with a given key, from among
all of the key-value pairs that have been put into the table

> **Definition.** A *symbol table* is a data structure for key-value pairs that supports two operations: insert (put) a
> new pair into the table and search for (get) the value associated with a given key.

For symbol tables, we emphasize the separate roles played by keys and values in search by specifying the key and value
types explicitly instead of viewing keys as implicit in items as we did for priority queues. we will consider an
extension for the typical case when keys are Comparable, which enables numerous additional methods.

We adopt the following conventions in all of our implementations:

* Only one value is associated with each key (no duplicate keys in a table).
* When a client puts a key-value pair into a table already containing that key (and an associated value), the new value
  replaces the old one.

These conventions define the **associative array abstraction**, where you can think of a symbol table as being just like
an array, where keys are indices and values are array entries. In a conventional array, keys are integer indices that we
use to quickly access array values; in an associative array (symbol table), keys are of arbitrary type, but we can still
use them to quickly access values.

**Null keys.** Keys must not be null. As with many mechanisms in Java, use of a null key results in an exception at
runtime

**Null values.** We also adopt the convention that no key can be associated with the value null. This convention is
directly tied to our specification in the API that get() should return null for keys not in the table, effectively
associating the value null with every key not in the table. This convention has two (intended) consequences: First, we
can test whether or not the symbol table defines a value associated with a given key by testing whether get() returns
null. Second, we can use the operation of calling put() with null as its second (value) argument to implement deletion.

**Deletion.** Deletion in symbol tables generally involves one of two strategies: lazy deletion, where we associate keys
in the table with null, then perhaps remove all such keys at some later time; and eager deletion, where we remove the
key from the table immediately

As just discussed, the code put(key, null) is an easy (lazy) implementation of delete(key). When we give an (eager)
implementation of delete(), it is intended to replace this default.

In our symbol-table implementations that do not use the default delete(), the put() implementations begin with the
defensive code

    if (val == null) { delete(key); return; } 

to ensure that no key in the table is associated with null.

Key equality. Determining whether or not a given key is in a symbol table is based on the concept of *object equality*.

### Ordered symbol tables.

In typical applications, keys are Comparable objects. Several symbol-table implementations take advantage of order among
the keys that is implied by Comparable to provide efficient implementations of the put()
and get() operations. More important, in such implementations, we can think of the symbol table as keeping the keys in
order and consider a significantly expanded API that defines numerous natural and useful operations involving relative
key order

**Minimum and maximum.** Perhaps the most natural queries for a set of ordered keys are to ask for the smallest and
largest keys. In ordered symbol tables, we also have methods to delete the maximum and minimum keys (and their
associated values). With this capability, the symbol table can operate like the IndexMinPQ(). The primary differences
are that equal keys are allowed in priority queues but not in symbol tables and that ordered symbol tables support a
much larger set of operations.

**Floor and ceiling.** Given a key, it is often useful to be able to perform the floor operation (find the largest key
that is less than or equal to the given key) and the ceiling operation (find the smallest key that is greater than or
equal to the given key).

**Rank and selection.** The basic operations for determining where a new key fits in the order are the rank operation (
find the number of keys less than a given key) and the select operation (find the key with a given rank).

**Range queries.** How many keys fall within a given range (between two given keys)? Which keys fall in a given range?
The capability to handle such queries is one prime reason that ordered symbol tables are so widely used in practice.

**Exceptional cases.** When a method is to return a key and there is no key fitting the description in the table, our
convention is to throw an exception (an alternate approach, which is also reasonable, would be to return null in such
cases). For example, min(), max(), deleteMin(), deleteMax(), floor(), and ceiling() all throw exceptions if the table is
empty, as does select(k) if k is less than 0 or not less than size().

The best practice in Java is to make compareTo() consistent with equals() in all Comparable types. That is, for every
pair of values a and b in any given Comparable type, it should be the case that (a.compareTo(b) == 0) and a.equals(b)
have the same value. To avoid any potential ambiguities, we avoid the use of equals() in ordered symbol-table
implementations. Instead, we use compareTo() exclusively to compare keys: we take the boolean expression a.compareTo(b)
== 0 to mean “Are a and b equal ?”

**Cost Model.** we use the term compare to refer to the operation of comparing a symboltable entry against a search key.
In most symbol-table implementations, this operation is in the inner loop. In the few cases where that is not the case,
we also count array accesses

Symbol-table implementations are generally characterized by their underlying data structures and their implementations
of get() and put().

**Performance client.** FrequencyCounter is a symbol-table client that finds the number of occurrences of each string (
having at least as many characters as a given threshold length) in a sequence of strings from standard input, then
iterates through the keys to find the one that occurs the most frequently.

This client answers a simple question: Which word (no shorter than a given length) occurs most frequently in a given
text? we measure performance of this client with three reference inputs: the first five lines of C. Dickens’s Tale of
Two Cities (tinyTale.txt), the full text of the book (tale.txt), and a popular database of 1 million sentences taken at
random from the web that is known as the Leipzig Corpora Collection (leipzig1M.txt).

The basic question that this client and these examples raise is the following: Can we develop a symbol-table
implementation that can handle a huge number of get() operations on a large table, which itself was built with a large
number of intermixed get() and put() operations? If you are only doing a few searches, any implementation will do, but
you cannot make use of a client like FrequencyCounter for large problems without a good symbol-table implementation.

FrequencyCounter is surrogate for a very common situation. Specifically, it has the following characteristics, which are
shared by many other symbol-table clients:

* Search and insert operations are intermixed.
* The number of distinct keys is not small.
* Substantially more searches than inserts are likely.
* Search and insert patterns, though unpredictable, are not random.

Our goal is to develop symbol-table implementations that make it feasible to use such clients to solve typical practical
problems.

### Sequential search in an unordered linked list

linked list of nodes that contain keys and values. To implement get(), we scan through the list, using equals() to
compare the search key with the key in each node in the list. If we find the match, we return the associated value; if
not, we return null. To implement put(), we also scan through the list, using equals() to compare the client key with
the key in each node in the list. If we find the match, we update the value associated with that key to be the value
given in the second argument; if not, we create a new node with the given key and value and insert it at the beginning
of the list. This method is known as **sequential search**: we search by considering the keys in the table one after
another

analyzing symbol-table algorithms is more complicated than analyzing sorting algorithms because of the difficulty of
characterizing the sequence of operations that might be invoked by a given client. the most common situation is that,
while search and insert patterns are unpredictable, they certainly are not random. For this reason, we pay careful
attention to worst-case performance.

> **Proposition A.** Search misses and insertions in an (unordered) linked-list symbol table having N key-value pairs both
> require N compares, and search hits N compares in the worst case. In particular, inserting N distinct keys into an
> initially empty linked-list symbol table uses `~ N^2 /2` compares.

It is true that searches for keys that are in the table need not take linear time. One useful measure is to compute the
total cost of searching for all of the keys in the table, divided by N. This quantity is precisely the expected number
of compares required for a search under the condition that searches for each key in the table are equally likely. We
refer to such a search as a *random search hit*. Though client search patterns are not likely to be random, they often
are well-described by this model

It is easy to show that the average number of compares for a random search hit is ~ N/2: the get() method uses 1 compare
to find the first key, 2 compares to find the second key, and so forth, for an average cost
of `(1 + 2 + ... + N )/ N = (N + 1)/2 ~ N/2`.

### Binary search in an ordered array

The underlying data structure is a pair of parallel arrays, one for the keys and one for the values. The implementation
keeps Comparable keys in order in the array, then uses array indexing to enable fast implementation of get() and other
operations.

The heart of the implementation is the rank() method, which returns the number of keys smaller than a given key. For
get(), the rank tells us precisely where the key is to be found if it is in the table (and, if it is not there, that it
is not in the table).

For put(), the rank tells us precisely where to update the value when the key is in the table, and precisely where to
put the key when the key is not in the table. We move all larger keys over one position to make room (working from back
to front) and insert the given key and value into the proper positions in their respective arrays

The reason that we keep keys in an ordered array is so that we can use array indexing to dramatically reduce the number
of compares required for each search, using the classic and venerable binary search algorithm.

* If key is in the table, rank() returns its index in the table, which is the same as the number of keys in the table
  that are smaller than key.
* If key is not in the table, rank() also returns the number of keys in the table that are smaller than key.

Since the keys are kept in an ordered array, most of the order-based operations are compact and straightforward.

> **Proposition B.** Binary search in an ordered array with N keys uses no more than `lg N + 1` compares for a search (
> successful or unsuccessful).

Despite its guaranteed logarithmic search, BinarySearchST still does not enable us to use clients like FrequencyCounter
to solve huge problems, because the put() method is too slow. Binary search reduces the number of compares, but not the
running time, because its use does not change the fact that the number of array accesses required to build a symbol
table in an ordered array is quadratic in the size of the array when keys are randomly ordered.

> **Proposition B (continued).** Inserting a new key into an ordered array of size N uses ~ 2N array accesses in the worst
> case, so inserting N keys into an initially empty table uses ~ N^2 array accesses in the worst case.

Binary search is typically far better than sequential search and is the method of choice in numerous practical
applications. For a static table (with no insert operations allowed), it is worthwhile to initialize and sort the table

Even when the bulk of the key-value pairs is known before the bulk of the searches (a common situation in applications),
it is worthwhile to add to BinarySearchST a constructor that initializes and sorts the table. Still, binary search is
infeasible for use in many other applications.

As we have emphasized, typical modern search clients require symbol tables that can support fast implementations of both
search and insert. That is, we need to be able to build huge tables where we can insert (and perhaps remove) key-value
pairs in unpredictable patterns, intermixed with searches.

To support efficient insertion, it seems that we need a linked structure. But a singly linked list forecloses the use of
binary search. To combine the efficiency of binary search with the flexibility of linked structures, we need more
complicated data structures.

## 3.2 BINARY SEARCH TREES

We are working with data structures made up of nodes that contain links that are either null or references to other
nodes. In a binary tree, we have the restriction that every node is pointed to by just one other node, which is called
its parent (except for one node, the root, which has no nodes pointing to it), and that each node has exactly two links,
which are called its left and right links, that point to nodes called its left child and right child, respectively.

Although links point to nodes, we can view each link as pointing to a binary tree, the tree whose root is the referenced
node. Thus, we can define a binary tree as a either a null link or a node with a left link and a right link, each
references to (disjoint) subtrees that are themselves binary trees.

In a binary search tree, each node also has a key and a value, with an ordering restriction to support efficient search.

> **Definition.** A binary search tree (BST) is a binary tree where each node has a Comparable key (and an associated value)
> and satisfies the restriction that the key in any node is larger than the keys in all nodes in that node’s left subtree
> and smaller than the keys in all nodes in that node’s right subtree.

### Basic implementation

We define a private nested class to define nodes in BSTs, just as we did for linked lists. Each node contains a key, a
value, a left link, a right link, and a node count. The instance variable N gives the node count in the subtree rooted
at the node. This field facilitates the implementation of various ordered symbol-table operations,

The private size() method is implemented to assign the value 0 to null links, so that we can maintain this field by
making sure that the invariant

    size(x) = size(x.left) + size(x.right) + 1

holds for every node x in the tree.

A BST represents a set of keys (and associated values), and there are many different BSTs that represent the same set.
If we project the keys in a BST such that all keys in each node’s left subtree appear to the left of the key in the node
and all keys in each node’s right subtree appear to the right of the key in the node, then we always get the keys in
sorted order. We take advantage of the flexibility inherent in having many BSTs represent this sorted order to develop
efficient algorithms for building and using BSTs.

A recursive algorithm to search for a key in a BST follows immediately from the recursive structure: if the tree is
empty, we have a search miss; if the search key is equal to the key at the root, we have a search hit. Otherwise, we
search (recursively) in the appropriate subtree, moving left if the search key is smaller, right if it is larger.

The recursive get() implements this algorithm directly. It takes a node (root of a subtree) as first argument and a key
as second argument, starting with the root of the tree and the search key. The code maintains the invariant that no
parts of the tree other than the subtree rooted at the current node can have a node whose key is equal to the search
key.

The procedure stops either when a node containing the search key is found (search hit) or when the current subtree
becomes empty (search miss). Starting at the top, the search procedure at each node involves a recursive invocation for
one of that node’s children, so the search defines a path through the tree.

**Insert.** a search for a key not in the tree ends at a null link, and all that we need to do is replace that link with
a new node containing the key .

**Recursion.** It is worthwhile to take the time to understand the dynamics of these recursive implementations. You can
think of the code before the recursive calls as happening on the way down the tree: it compares the given key against
the key at each node and moves right or left accordingly. Then, think of the code after the recursive calls as happening
on the way up the tree.

For get() this amounts to a series of return statements, but for put(), it corresponds to resetting the link of each
parent to its child on the search path and to incrementing the counts on the way up the path. In simple BSTs, the only
new link is the one at the bottom, but resetting the links higher up on the path is as easy as the test to avoid setting
them. Also, we just need to increment the node count on each node on the path, but we use more general code that sets
each to one plus the sum of the counts in its subtrees. Later in this section and in the next section, we shall study
more advanced algorithms that are naturally expressed with this same recursive scheme but that can change more links on
the search paths and need the more general node-count-update code.

