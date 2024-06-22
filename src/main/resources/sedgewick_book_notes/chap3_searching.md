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

### Analysis

The running times of algorithms on binary search trees depend on the shapes of the trees, which, in turn, depend on the
order in which keys are inserted. In the best case, a tree with N nodes could be perfectly balanced, with ~ lg N nodes
between the root and each null link. In the worst case there could be N nodes on the search path. The balance in typical
trees turns out to be much closer to the best case than the worst case

For many applications, the following simple model is reasonable: We assume that the keys are (uniformly) random, or,
equivalently, that they are inserted in random order. Analysis of this model stems from the observation that BSTs are
dual to quicksort. The node at the root of the tree corresponds to the first partitioning item in quicksort (no keys to
the left are larger, and no keys to the right are smaller) and the subtrees are built recursively, corresponding to
quicksort’s recursive subarray sorts. This observation leads us to the analysis of properties of the trees.

> **Proposition C.** Search hits in a BST built from N random keys require ~ 2 ln N (about 1.39 lg N) compares, on the
> average.

> **Proposition D.** Insertions and search misses in a BST built from N random keys require ~ 2 ln N (about 1.39 lg N)
> compares, on the average.

**Proof:** Insertions and search misses take one more compare, on the average, than search hits

Proposition C says that we should expect the BST search cost for random keys to be about 39 percent higher than that for
binary search. Proposition D says that the extra cost is well worthwhile, because the cost of inserting a new key is
also expected to be logarithmic—flexibility not available with binary search in an ordered array, where the number of
array accesses required for an insertion is typically linear. As with quicksort, the standard deviation of the number of
compares is known to be low, so that these formulas become increasingly accurate as N increases.

#### Order-based methods and deletion

**Minimum and maximum.** If the left link of the root is null, the smallest key in a BST is the key at the root; if the
left link is not null, the smallest key in the BST is the smallest key in the subtree rooted at the node referenced by
the left link. This statement is both a description of the recursive min() method and an inductive proof that it finds
the smallest key in the BST.

The computation is equivalent to a simple iteration (move left until finding a null link), but we use recursion for
consistency. We might have the recursive method return a Key instead of a Node, but we will later have a need to use
this method to access the Node containing the minimum key. Finding the maximum key is similar, moving to the right
instead of to the left

**Floor and ceiling.** If a given key key is less than the key at the root of a BST, then the floor of key (the largest
key in the BST less than or equal to key) must be in the left subtree. If key is greater than the key at the root, then
the floor of key could be in the right subtree, but only if there is a key smaller than or equal to key in the right
subtree; if not (or if key is equal to the key at the root), then the key at the root is the floor of key. Again, this
description serves both as the basis for the recursive floor() method and for an inductive proof that it computes the
desired result. Interchanging right and left (and less and greater) gives ceiling().

**Selection.** Suppose that we seek the key of rank k. If the number of keys t in the left subtree is larger than k, we
look (recursively) for the key of rank k in the left subtree; if t is equal to k, we return the key at the root; and if
t is smaller than k, we look (recursively) for the key of rank k - t - 1 in the right subtree.

**Rank.** if the given key is equal to the key at the root, we return the number of keys t in the left subtree; if the
given key is less than the key at the root, we return the rank of the key in the left subtree (recursively computed);
and if the given key is larger than the key at the root, we return t plus one (to count the key at the root) plus the
rank of the key in the right subtree .

**Delete the minimum/maximum.** we write a recursive method that takes a link to a Node as argument and returns a link
to a Node, so that we can reflect changes to the tree by assigning the result to the link used as argument. For
deleteMin() we go left until finding a Node that has a null left link and then replace the link to that node by its
right link (simply by returning the right link in the recursive method). The deleted node, with no link now pointing to
it, is available for garbage collection. Our standard recursive setup accomplishes, after the deletion, the task of
setting the appropriate link in the parent and updating the counts in all nodes in the path to the root. The symmetric
method works for deleteMax().

**Delete.**  We can proceed in a similar manner to delete any node that has one child (or no children), but what can we
do to delete a node that has two children? The answer is to delete a node x by replacing it with its successor. Because
x has a right child, its successor is the node with the smallest key in its right subtree. The replacement preserves
order in the tree because there are no keys between x.key and the successor’s key. We can accomplish the task of
replacing x by its successor in four (!) easy steps:

* Save a link to the node to be deleted in t.
* Set x to point to its successor min(t.right).
* Set the right link of x (which is supposed to point to the BST containing all the keys larger than x.key) to
  deleteMin(t.right), the link to the BST containing all the keys that are larger than x.key after the deletion.
* Set the left link of x (which was null) to t.left (all the keys that are less than both the deleted key and its
  successor).

Our standard recursive setup accomplishes, after the recursive calls, the task of setting the appropriate link in the
parent and decrementing the node counts in the nodes on the path to the root.

While this method does the job, it has a flaw that might cause performance problems in some practical situations. The
problem is that the choice of using the successor is arbitrary and not symmetric. Why not use the predecessor? In
practice, it is worthwhile to choose at random between the predecessor and the successor.

**Range queries.** To implement the keys() method that returns the keys in a given range, we begin with a basic
recursive BST traversal method, known as *inorder traversal*. To implement the two-argument keys() method that returns
to a client all the keys in a specified range, we modify this code to add each key that is in the range to a Queue, and
to skip the recursive calls for subtrees that cannot contain keys in the range

#### Analysis.

How efficient are the order-based operations in BSTs? To study this question, we consider the *tree height* (the maximum
depth of any node in the tree). Given a tree, its height determines the worst-case cost of all BST operations (except
for range search).

> **Proposition E.** In a BST, all operations take time proportional to the height of the tree, in the worst case.

The average height of a BST built from random keys was shown to be logarithmic by J. Robson in 1979, and L. Devroye
later showed that the value approaches 2.99 lg N for large N.

## 3.3 BALANCED SEARCH TREES

We introduce in this section a type of binary search tree where costs are guaranteed to be logarithmic, no matter what
sequence of keys is used to construct them. Ideally, we would like to keep our binary search trees perfectly balanced.
In an N-node tree, we would like the height to be ~lg N so that we can guarantee that all searches can be completed in ~
lg N compares, just as for binary search.

Unfortunately, maintaining perfect balance for dynamic insertions is too expensive. we consider a data structure that
slightly relaxes the perfect balance requirement to provide guaranteed logarithmic performance for all the ordered
symbol table API operations (except range search).

### 2-3 search trees

Specifically, referring to the nodes in a standard BST as 2-nodes (they hold two links and one key), we now also allow
3-nodes, which hold three links and two keys. Both 2-nodes and 3-nodes have one link for each of the intervals subtended
by its keys.

**Definition.** A 2-3 search tree is a tree that is either empty or

* A 2-node, with one key (and associated value) and two links, a left link to a 2-3 search tree with smaller keys, and a
  right link to a 2-3 search tree with larger keys
* A 3-node, with two keys (and associated values) and three links, a left link to a 2-3 search tree with smaller keys, a
  middle link to a 2-3 search tree with keys between the node’s keys, and a right link to a 2-3 search tree with larger
  keys

As usual, we refer to a link to an empty tree as a null link.

A **perfectly balanced 2-3 search tree** is one whose null links are all the same distance from the root. To be concise,
we use the term 2-3 tree to refer to a perfectly balanced 2-3 search tree.

> **Proposition F.** Search and insert operations in a 2-3 tree with N keys are guaranteed to visit at most lg N nodes.

Thus, we can guarantee good worst-case performance with 2-3 trees. The amount of time required at each node by each of
the operations is bounded by a constant, and both operations examine nodes on just one path, so the total cost of any
search or insert is guaranteed to be logarithmic.

Although it is possible to write code that performs transformations on distinct data types representing 2- and 3-nodes,
most of the tasks that we have described are inconvenient to implement in this direct representation because there are
numerous cases to be handled. We would need to maintain two different types of nodes, compare search keys against each
of the keys in the nodes, copy links and other information from one type of node to another, convert nodes from one type
to another, and so forth. Not only is there a substantial amount of code involved, but the overhead incurred could make
the algorithms slower than standard BST search and insert. The primary purpose of balancing is to provide insurance
against a bad worst case, but we would prefer the overhead cost for that insurance to be low

Fortunately, we can do the transformations in a uniform way using little overhead.

## Red-black BSTs

The insertion algorithm for 2-3 trees just described is not difficult to understand; now, we will see that it is also
not difficult to implement. We will consider a simple representation known as a red-black BST that leads to a natural
implementation. In the end, not much code is required, but understanding how and why the code gets the job done requires
a careful look.

The basic idea behind red-black BSTs is to encode 2-3 trees by starting with standard BSTs (which are made up of
2-nodes) and adding extra information to encode 3-nodes. We think of the links as being of two different types: **red
links**, which bind together two 2-nodes to represent 3-nodes, and **black links**, which bind together the 2-3 tree.
Specifically, we represent 3-nodes as two 2-nodes connected by a single red link that leans left (one of the 2-nodes is
the left child of the other).

One advantage of using such a representation is that it allows us to use our get() code for standard BST search without
modification. Given any 2-3 tree, we can immediately derive a corresponding BST, just by converting each node as
specified. We refer to BSTs that represent 2-3 trees in this way as red-black BSTs.

**An equivalent definition**. Another way to proceed is to define red-black BSTs as BSTs having red and black links and
satisfying the following three restrictions:

* Red links lean left.
* No node has two red links connected to it.
* The tree has perfect black balance : every path from the root to a null link has the same number of black links.

There is a 1-1 correspondence between red-black BSTs defined in this way and 2-3 trees. If we draw the red links
horizontally in a red-black BST, all of the null links are the same distance from the root, and if we then collapse
together the nodes connected by red links, the result is a 2-3 tree.

Conversely, if we draw 3-nodes in a 2-3 tree as two 2-nodes connected by a red link that leans left, then no node has
two red links connected to it, and the tree has perfect black balance, since the black links correspond to the 2-3 tree
links, which are perfectly balanced by definition.

> Whichever way we choose to define them, red-black BSTs are both BSTs and 2-3 trees.

Thus, if we can implement the 2-3 tree insertion algorithm by maintaining the 1-1 correspondence, then we get the best
of both worlds: the simple and efficient search method from standard BSTs and the efficient insertion-balancing method
from 2-3 trees.

**Color representation.** For convenience, since each node is pointed to by precisely one link (from its parent), we
encode the color of links in nodes, by adding a boolean instance variable color to our Node data type, which is true if
the link from the parent is red and false if tit is black. By convention, null links are black. For clarity in our code,
we define constants RED and BLACK for use in setting and testing this variable. We use a private method isRed() to test
the color of a node’s link to its parent. When we refer to the color of a node, we are referring to the color of the
link pointing to it, and vice versa.

### Rotations.

The implementation that we will consider might allow right-leaning red links or two red links in a row during an
operation, but it always corrects these conditions before completion, through judicious use of an operation called
rotation that switches the orientation of red links.

Whether left or right, every rotation leaves us with a link. We always use the link returned by rotateRight() or
rotateLeft() to reset the appropriate link in the parent (or the root of the tree). This link may be red or black. This
might allow two red links in a row to occur within the tree, but our algorithms will also use rotations to correct this
condition when it arises.

We can use rotations to help maintain the 1-1 correspondence between 2-3 trees and red-black BSTs as new keys are
inserted because they preserve the two defining properties of red-black BSTs: order and perfect black balance. That is,
we can use rotations on a red-black BST without having to worry about losing its order or its perfect black balance.
Next, we see how to use rotations to preserve the other two defining properties of redblack BSTs (no consecutive red
links on any path and no right-leaning red links).

In summary, we achieve the desired result by doing zero, one, or two rotations followed by flipping the colors of the
two children of the root.

**Flipping colors.** In addition to flipping the colors of the children from red to black, we also flip the color of the
parent from black to red. A critically important characteristic of this operation is that, like rotations, it is a local
transformation that preserves perfect black balance in the tree.

Flipping the colors makes the link to the middle node red, which amounts to passing it up to its parent, putting us back
in the same situation with respect to the parent, which we can fix by moving up the tree.

**Passing a red link up the tree.** The 2-3 tree insertion algorithm calls for us to split the 3-node, passing the
middle key up to be inserted into its parent, continuing until encountering a 2-node or the root. In every case we have
considered, we precisely accomplish this objective: after doing any necessary rotations, we flip colors, which turns the
middle node to red. From the point of view of the parent of that node, that link becoming red can be handled in
precisely the same manner as if the red link came from attaching a new node: we pass up a red link to the middle node

> to insert into a 3-node, create a temporary 4-node, split it, and pass a red link to the middle key up to its parent.
> Continuing the same process, we pass a red link up the tree until reaching a 2-node or the root.

In summary, we can maintain our 1-1 correspondence between 2-3 trees and red-black BSTs during insertion by judicious
use of three simple operations: left rotate, right rotate, and color flip. We can accomplish the insertion by performing
the following operations, one after the other, on each node as we pass up the tree from the point of insertion:

* If the right child is red and the left child is black, rotate left.
* If both the left child and its left child are red, rotate right.
* If both children are red, flip colors.

Note that the first operation handles both the rotation necessary to lean the 3-node to the left when the parent is a
2-node and the rotation necessary to lean the bottom link to the left when the new red link is the middle link in a
3-node.

### Deletion

As with insertion, we can define a sequence of local transformations that allow us to delete a node while still
maintaining perfect balance. The process is somewhat more complicated than for insertion, because we do the
transformations both on the way down the search path, when we introduce temporary 4-nodes (to allow for a node to be
deleted), and also on the way up the search path, where we split any leftover 4-nodes (in the same manner as for
insertion).

**Properties of red-black BSTs.** Studying the properties of red-black BSTs is a matter of checking the correspondence
with 2-3 trees and then applying the analysis of 2-3 trees. The end result is that all symbol-table operations in
red-black BSTs are guaranteed to be logarithmic in the size of the tree.

> **Proposition G.** The height of a red-black BST with N nodes is no more than 2 lg N.

> **Property H.** The average length of a path from the root to a node in a red-black BST with N nodes is ~1.00 lg N.

The get() method in red-black BSTs does not examine the node color, so the balancing mechanism adds no overhead; search
is faster than in elementary BSTs because the tree is balanced. Each key is inserted just once, but may be involved in
many, many search operations, so the end result is that we get search times that are close to optimal (because the trees
are nearly balanced and no work for balancing is done during the searches) at relatively little cost (unlike binary
search, insertions are guaranteed to be logarithmic). The inner loop of the search is a compare followed by updating a
link, which is quite short, like the inner loop of binary search (compare and index arithmetic).

**Ordered symbol-table API.** One of the most appealing features of red-black BSTs is that the complicated code is
limited to the put() (and deletion) methods. Our code for the minimum/maximum, select, rank, floor, ceiling and range
queries in standard BSTs can be used without any change, since it operates on BSTs and has no need to refer to the node
color. Moreover, all the methods benefit from the near-perfect balance in the tree because they all require time
proportional to the tree height, at most. Thus Proposition G, in combination with Proposition E, suffices to establish a
logarithmic performance guarantee for all of them.

## 3.4 HASH TABLES

Search algorithms that use hashing consist of two separate parts. The first part is to compute a **hash function** that
transforms the search key into an array index. Ideally, different keys would map to different indices. This ideal is
generally beyond our reach, so we have to face the possibility that two or more different keys may hash to the same
array index. Thus, the second part of a hashing search is a **collision-resolution** process that deals with this
situation.

Hashing is a classic example of a time-space tradeoff. If there were no memory limitation, then we could do any search
with only one memory access by simply using the key as an index in a (potentially huge) array.On the other hand, if
there were no time limitation, then we can get by with only a minimum amount of memory by using sequential search in an
unordered array. Hashing provides a way to use a reasonable amount of both memory and time to strike a balance between
these two extremes. Indeed, it turns out that we can trade off time and memory in hashing algorithms by adjusting
parameters, not by rewriting code. To help choose values of the parameters, we use classical results from probability
theory.

With hashing, you can implement search and insert for symbol tables that require constant (amortized) time per operation
in typical applications, making it the method of choice for implementing basic symbol tables in many situations.

### Hash function

If we have an array that can hold M key-value pairs, then we need a hash function that can transform any given key into
an index into that array: an integer in the range [0, M – 1]. We seek a hash function that both is easy to compute and
uniformly distributes the keys: for each key, every integer between 0 and M – 1 should be equally likely (independently
for every key).

> The hash function depends on the key type. Strictly speaking, we need  a different hash function for each key type
> that we use.

**Positive integers.** The most commonly used method for hashing integers is called **modular hashing** : we choose the
array size M to be prime and, for any positive integer key k, compute the remainder when dividing k by M. This function
is very easy to compute (k % M, in Java) and is effective in dispersing the keys evenly between 0 and M – 1. If M is not
prime, it may be the case that not all of the bits of the key play a role, which amounts to missing an opportunity to
disperse the values evenly.

**Floating-point numbers.** One way to address this situation is to use modular hashing on the binary representation of
the key (this is what Java does).

**Strings.** Modular hashing works for long keys such as strings, too: we simply treat them as huge integers. For
example,

    int hash = 0;
    for (int i = 0; i < s.length(); i++) 
      hash = (R * hash + s.charAt(i)) % M;

recall that charAt() returns a char value in Java, which is a 16-bit nonnegative integer. If R is greater than any
character value, this computation would be equivalent to treating the String as an N-digit base-R integer, computing the
remainder that results when dividing that number by M. A classic algorithm known as **Horner’s method** gets the job
done with N multiplications, additions, and modulus operations. If the value of R is sufficiently small that no overflow
occurs, the result is an integer between 0 and M – 1, as desired. The use of a small prime integer such as 31 ensures
that the bits of all the characters play a role. Java’s default implementation for String uses a method like this.

**Compound keys.** If the key type has multiple integer fields, we can typically mix them together in the way just
described for String values. For example, for our Date ADT

    public int hashCode() { 
      int hash = 17; 
      hash = 31 * hash + who.hashCode(); 
      hash = 31 * hash + when.hashCode(); 
      hash = 31 * hash + ((Double) amount).hashCode();
      return hash; 
    }

**Java conventions.** Java helps us address the basic problem that every type of data needs a hash function by ensuring
that every data type inherits a method called hashCode() that returns a 32-bit integer. The implementation of hashCode()
for a data type must be consistent with equals. That is, if a.equals(b) is true, then a.hashCode() must have the same
numerical value as b.hashCode(). Conversely, if the hashCode() values are different, then we know that the objects are
not equal. If the hashCode() values are the same, the objects may or may not be equal, and we must use equals() to
decide which condition holds. This convention is a basic requirement for clients to be able to use hashCode() for symbol
tables. Note that it implies that you must override both hashCode() and equals() if you need to hash with a user-defined
type.

**Converting a hashCode() to an array index.** Since our goal is an array index, not a 32-bit integer, we combine
hashCode() with modular hashing in our implementations to produce integers between 0 and M – 1, as follows:

    private int hash(Key x) { 
      return (x.hashCode() & 0x7fffffff) % M; 
    }

This code masks off the sign bit (to turn the 32-bit number into a 31-bit nonnegative integer) and then computes the
remainder when dividing by M, as in modular hashing. Programmers commonly use a prime number for the hash table size M
when using code like this, to attempt to make use of all the bits of the hash code.

**Software caching.** If computing the hash code is expensive, it may be worthwhile to cache the hash for each key. That
is, we maintain an instance variable hash in the key type that contains the value of hashCode() for each key object. On
the first call to hashCode(), we have to compute the full hash code (and set the value of hash), but subsequent calls on
hashCode() simply return the value of hash. Java uses this technique to reduce the cost of computing hashCode() for
String objects.

> **Assumption J ( uniform hashing assumption)**. The hash functions that we use uniformly and independently distribute
> keys among the integer values between 0 and M – 1.

A fundamental assumption that we make when using hashing, an idealized model that we do not actually expect to achieve,
but that guides our thinking when implementing hashing algorithms

### Hashing with separate chaining

The second component of a hashing algorithm is collision resolution: a strategy for handling the case when two or more
keys to be inserted hash to the same index. A straightforward and general approach to collision resolution is to build,
for each of the M array indices, a linked list of the key-value pairs whose keys hash to that index. This method is
known as separate chaining because items that collide are chained together in separate linked lists.

The basic idea is to choose M to be sufficiently large that the lists are sufficiently short to enable efficient search
through a two-step process: hash to find the list that could contain the key, then sequentially search through that list
for the key.

One way to proceed is to expand SequentialSearchST to implement separate chaining using linked-list primitives.

A simpler (though slightly less efficient) way to proceed is to adopt a more general approach: we build, for each of the
M array indices, a symbol table of the keys that hash to that index, thus reusing code that we have already developed.
The implementation `SeparateChainingHashST`  maintains an array of SequentialSearchST objects and implements get() and
put() by computing a hash function to choose which SequentialSearchST object can contain the key and then using get()
and put() (respectively) from SequentialSearchST to complete the job.

> Since we have M lists and N keys, the average length of the lists is always N  M, no matter how the keys are
> distributed among the lists Separate chaining is useful in practice because each list is extremely likely to have
> about N/M key-value pairs

> **Proposition K.** In a separate-chaining hash table with M lists and N keys, the probability (under Assumption J) that the
> number of keys in a list is within a small constant factor of N/M is extremely close to 1.

> **Property L.** In a separate-chaining hash table with M lists and N keys, the number of compares (equality tests) for
> search miss and insert is ~N/M.

**Table size.** In a separate-chaining implementation, our goal is to choose the table size M to be sufficiently small
that we do not waste a huge area of contiguous memory with empty chains but sufficiently large that we do not waste time
searching through long chains. One of the virtues of separate chaining is that this decision is not critical: if more
keys arrive than expected, then searches will take a little longer than if we had chosen a bigger table size ahead of
time; if fewer keys are in the table, then we have extra-fast search with some wasted space.

When space is not a critical resource, M can be chosen sufficiently large that search time is constant; when space is a
critical resource, we still can get a factor of M improvement in performance by choosing M to be as large as we can
afford.

**Deletion.** To delete a key-value pair, simply hash to find the SequentialSearchST containing the key, then invoke the
delete() method for that table. Reusing code in this way is preferable to reimplementing this basic operation on linked
lists.

**Ordered operations.** The whole point of hashing is to uniformly disperse the keys, so any order in the keys is lost
when hashing. If you need to quickly find the maximum or minimum key, find keys in a given range, or implement any of
the other operations in the ordered symbol-table API , then hashing is not appropriate, since these operations will all
take linear time.

Hashing with separate chaining is easy to implement and probably the fastest (and most widely used) symbol-table
implementation for applications where key order is not important. When your keys are built-in Java types or your own
type with well-tested implementations of hashCode(), this implementation provides a quick and easy path to fast search
and insert

### Hashing with linear probing

