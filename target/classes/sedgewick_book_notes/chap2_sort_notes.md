# SORTING

> Sorting is the process of rearranging a sequence of objects to put them in some logical order.

## 2.1 ELEMENTARY SORTS

Among the reasons for studying these relatively simple algorithms in detail are the following: First, they provide
context in which we can learn terminology and basic mechanisms. Second, these simple algorithms are more effective in
some applications than the sophisticated algorithms that we shall discuss later. Third, they are useful in improving the
efficiency of more sophisticated algorithms

Our primary concern is algorithms for rearranging arrays of items where each item contains a key. *The objective of the
sorting algorithm is to rearrange the items such that their keys are ordered according to some well-defined ordering
rule* (usually numerical or alphabetical order). We want to rearrange the array so that each entry’s key is no smaller
than the key in each entry with a lower index and no larger than the key in each entry with a larger index. Specific
characteristics of the keys and the items can vary widely across applications.

The class Example on the facing page illustrates the conventions that we shall use:
we put our sort code in a sort() method within a single class along with private helper functions less() and exch() (and
perhaps some others) and a sample client main().

To differentiate sorting methods, we give our various sort classes different names. Clients can call different
implementations by name: Insertion.sort(), Merge.sort(), Quick.sort(), and so forth.

> the method less() compares items and the method exch()  exchanges them. The exch() method is easy to implement, and
> the Comparable interface makes it easy to implement less().

Restricting data access to these two operations makes our code readable and portable, and makes it easier for us certify
that algorithms are correct, to study performance and to compare algorithms.

**Sorting cost model.** When studying sorting algorithms, we count compares and exchanges. For algorithms that do not
use exchanges, we count array accesses

**Extra memory.** The amount of extra memory used by a sorting algorithm is often as important a factor as running time.
The sorting algorithms divide into two basic types: those that *sort in place* and use no extra memory except perhaps
for a small function call stack or a constant number of instance variables, and those that need *enough extra memory to
hold another copy* of the array to be sorted

**Types of data.** Our sort code is effective for any item type that implements the *Comparable interface*. Adhering to
Java’s convention in this way is convenient because many of the types of data that you might want to sort implement
Comparable. For example, Java’s numeric wrapper types such as Integer and Double implement Comparable, as do String and
various advanced types such as File or URL. Thus, you can just call one of our sort methods with an array of these types
as argument

When we create types of our own, we can enable client code to sort that type of data by implementing the Comparable
interface. To do so, we just need to implement a *compareTo() method* that defines an ordering on objects of that type
known as *the natural order* for that type, as shown here for our Date data type.

Java’s convention is that the call <code>v.compareTo(w)</code> returns an integer that is negative, zero, or positive
(usually -1, 0, or +1) when <code>v < w, v = w, or v > w </code>, respectively. For economy, we use standard notation
like v>w as shorthand for code like <code>v.compareTo(w) > 0 </code>. By convention, <code>v.compareTo(w)</code> throws
an exception if v and w are incompatible types or either is null. Furthermore, compareTo() must implement a total order:
it must be

- Reflexive (for all v, v = v)
- Antisymmetric (for all v and w, if v < w then w > v and if v = w then w = v)
- Transitive (for all v, w, and x, if v <= w and w <= x then v <=x )

In short, compareTo() implements our key abstraction—it defines the ordering of the items (objects) to be sorted, which
can be any type of data that implements Comparable. Note that compareTo() need not use all the instance variables.
Indeed, the key might be a small part of each item

### Selection Sort

This method is called selection sort because it works by repeatedly selecting the smallest remaining item.

> First, find the smallest item in the array and exchange it with the first entry (itself if the first entry is already
> the smallest). Then, find the next smallest item and exchange it with the second entry. Continue in this way until
> the entire array is sorted

The inner loop of selection sort is just a compare to test a current item against the smallest item found so far
(plus the code necessary to increment the current index and to check that it does not exceed the array bounds); it could
hardly be simpler. The work of moving the items around falls outside the inner loop: *each exchange puts an item into
its final position, so the number of exchanges is N*. Thus, *the running time is dominated by the number of compares*.

> **Proposition A.** Selection sort uses *~ N^2/2 compares* and *N exchanges* to sort an array of length N.

> **Proof:** More precisely, examination of the codereveals that, for each i from 0 to N - 1, there is one exchange and
> N - 1 - i compares, so the totals are N exchanges and (N - 1) + (N - 2) + . . . + 2 + 1+ 0 = N(N - 1) / 2 ~ N^2 / 2
> compares.

In summary, selection sort is characterized by the following two signature properties:

**Running time is insensitive to input.** The process of finding the smallest item on one pass through the array does
not give much information about where the smallest item might be on the next pass. This property can be disadvantageous
in some situations. For example, the person using the sort client might be surprised to realize that *it takes about as
long to run selection sort for an array that is already in order or for an array with all keys equal as it does for a
randomly-ordered array!* As we shall see, other algorithms are better able to take advantage of initial order in the
input.

**Data movement is minimal.** Each of the N exchanges changes the value of two array entries, so selection sort uses N
exchanges—the number of array exchanges is a linear function of the array size. None of the other sorting algorithms
that we consider have this property (most involve linearithmic or quadratic growth).

### Insertion sort

In a computer implementation, we need to make space to insert the current item by moving larger items one position to
the right, before inserting the current item into the vacated position. As in selection sort, the items to the left of
the current index are in sorted order during the sort, but they are not in their final position, as they may have to be
moved to make room for smaller items encountered later. The array is, however, fully sorted when the index reaches the
right end.

Unlike that of selection sort, the running time of insertion sort depends on the initial order of the items in the
input. For example, if the array is large and its entries are already in order (or nearly in order), then insertion sort
is much, much faster than if the entries are randomly ordered or in reverse order.

> **Proposition B.** Insertion sort uses N^2 /4 compares and N^2 /4 exchanges to sort a randomly ordered array of length
> N with distinct keys, on the average. The worst case is N^2 /2 compares and N^2 /2 exchanges and the best case is N - 1
> compares and 0 exchanges.

> **Proof:** (make for yourself)

Insertion sort works well for certain types of nonrandom arrays that often arise in practice, even if they are huge. For
example, as just mentioned, consider what happens when you use insertion sort on an array that is already sorted. Each
item is immediately determined to be in its proper place in the array, and the total running time is linear.
(The running time of selection sort is quadratic for such an array.) The same is true for arrays whose keys are all
equal (hence the condition in Proposition B that the keys must be distinct).

More generally, we consider the concept of a *partially sorted array*. If the number of *inversions* in an array is less
than a constant multiple of the array size, we say that the array is *partially sorted*. Typical examples of partially
sorted arrays are the following:

- An array where each entry is not far from its final position
- A small array appended to a large sorted array
- An array with only a few entries that are not in place

Insertion sort is an efficient method for such arrays; selection sort is not. Indeed, when the number of inversions is
low, insertion sort is likely to be faster than any sorting method that we consider in this chapter.

> **Proposition C.** The number of exchanges used by insertion sort is equal to the number of inversions in the array,
> and the number of compares is at least equal to the number of inversions and at most equal to the number of inversions
> plus the array size minus 1.

> **Proof:** Every exchange involves two inverted adjacent entries and thus reduces the number of inversions by one,
> and the array is sorted when the number of inversions reaches zero. Every exchange corresponds to a compare, and an
> additional compare might happen for each value of i from 1 to N-1 (when a[i] does not reach the left end of the array).

It is not difficult to speed up insertion sort substantially, by shortening its inner loop to move the larger entries to
the right one position rather than doing full exchanges (thus cutting the number of array accesses in half).

In summary, insertion sort is an excellent method for partially sorted arrays and is also a fine method for tiny arrays.
These facts are important not just because such arrays frequently arise in practice, but also because both types of
arrays arise in intermediate stages of advanced sorting algorithms, so we will be considering insertion sort again in
relation to such algorithms.

#### Comparing two sorting algorithms

Now that we have two implementations, we are naturally interested in knowing which one is faster: selection sort or
insertion sort. Generally,we compare algorithms by

- Implementing and debugging them
- Analyzing their basic properties
- Formulating a hypothesis about comparative performance
- Running experiments to validate the hypothesis

These steps are nothing more than the time-honored scientific method, applied to the study of algorithms. In the present
context, Algorithm 2.1 and Algorithm 2.2 are evidence of the first step; Propositions A, B, and C constitute the second
step; Property D next constitutes the third step; and the class SortCompare enables the fourth step.

Our brief descriptions mask a substantial amount of effort that is required to properly implement, analyze, and test
algorithms. Every programmer knows that such code is the product of a long round of debugging and refinement, every
mathematician knows that proper analysis can be very difficult, and every scientist knows that formulating hypotheses
and designing and executing experiments to validate them require great care. Full development of such results is
reserved for experts studying our most important algorithms, but every programmer using an algorithm should be aware of
the scientific context underlying its performance properties.

How do we formulate a hypothesis about the running times of insertion sort and selection sort for randomly ordered
arrays?

From Propositions A and B, it follows immediately that the running time of both algorithms should be quadratic for
randomly ordered arrays. That is, the running time of insertion sort for such an input is proportional to some small
constant times N^2 and the running time of selection sort is proportional to some other small constant times N^2 . *The
values of the two constants depend on the cost of compares and exchanges on the particular computer being used.*

For many types of data and for typical computers, it is reasonable to assume that these costs are similar.

> **Property D.** The running times of insertion sort and selection sort are quadratic and within a small constant
> factor of one another for randomly ordered arrays of distinct values

> **Evidence:** Insertion sort was about twice as fast as selection sort when the first edition of this book was written
> in 1980 and it still is today. Is insertion sort a bit faster than selection sort on your computer? To find out, you
> can use the class SortCompare

As usual, we use Stopwatch to compute the running time. The implementation of time() shown here does the job for the
basic sorts in this chapter. The “randomly ordered” input model is embedded in the timeRandomInput() method in
SortCompare, which generates random Double values, sorts them, and returns the total measured time of the sort for the
given number of trials.

We do not dwell further on the comparative performance of insertion sort and selection sort because we are much more
interested in algorithms that can run a hundred or a thousand or a million times faster than either. Still,
understanding these elementary algorithms is worthwhile for several reasons:

-
- They help us work out the ground rules.
- They provide performance benchmarks.
- They are often the method of choice in some specialized situations.
- They can serve as the basis for developing better algorithms.

For these reasons, we will use the same basic approach and consider elementary algorithms for every problem that we
study throughout this book, not just sorting. Programs like SortCompare play a critical role in this incremental
approach to algorithm development. At every step along the way, we can use such a program to help evaluate whether a new
algorithm or an improved version of a known algorithm provides the performance gains that we expect

### Shell-sort

we now consider a fast algorithm based on insertion sort. *Insertion sort is slow for large unordered arrays because the
only exchanges it does involve adjacent entries*, so items can move through the array only one place at a time. For
example, if the item with the smallest key happens to be at the end of the array, N-1 exchanges are needed to get that
one item where it belongs

> **Shell-sort** is a simple extension of insertion sort that gains speed by allowing exchanges of array entries that are
> far apart, to produce partially sorted arrays that can be efficiently sorted, eventually by insertion sort.

The idea is to rearrange the array to give it the property that taking every hth entry (starting anywhere) yields a
sorted subsequence. Such an array is said to be h-sorted. Put another way, a h-sorted array is h independent sorted
subsequences, interleaved together. By h-sorting for some large values of h, we can move items in the array long
distances and thus make it easier to h-sort for smaller values of h.

Using such a procedure for any *sequence of values of h that ends in 1* will produce a sorted array: that is shell-sort.

The implementation uses the sequence of decreasing values ½(3^k-1), starting at the largest increment less than N/3 and
decreasing to 1. We refer to such a sequence as an *increment sequence*. Algorithm 2.3 computes its increment sequence;
another alternative is to store an increment sequence in an array.

One way to implement shell-sort would be, for each h, to use insertion sort independently on each of the h subsequences.
Because the subsequences are independent, we can use an even simpler approach: when h-sorting the array, we insert each
item among the previous items in its h-subsequence by exchanging it with those that have larger keys (moving them each
one position to the right in the subsequence). We accomplish this task by using the insertion-sort code, but modified to
decrement by h instead of 1 when moving through the array. This observation reduces the shell-sort implementation to an
insertion-sort-like pass through the array for each increment

Shell-sort gains efficiency by making a tradeoff between size and partial order in the subsequences. At the beginning,
the subsequences are short; later in the sort, the subsequences are partially sorted. In both cases, insertion sort is
the method of choice. The extent to which the subsequences are partially sorted is a variable factor that depends
strongly on the increment sequence. Understanding shell-sort’s performance is a challenge. Indeed, Algorithm 2.3 is the
only sorting method we consider whose performance on randomly ordered arrays has not been precisely characterized.

How do we decide what increment sequence to use? In general, this question is a difficult one to answer. The performance
of the algorithm depends not just on the number of increments, but also on arithmetical interactions among the
increments such as the size of their common divisors and other properties. Many different increment sequences have been
studied in the literature, but no provably best sequence has been found. The increment sequence that is used in
Algorithm 2.3 is easy to compute and use, and performs nearly as well as more sophisticated increment sequences that
have been discovered that have provably better worst-case performance.

Shell-sort is useful even for large arrays, particularly by contrast with selection sort and insertion sort. It also
performs well on arrays that are in arbitrary order (not necessarily random).

As you can learn with SortCompare, shell-sort is much faster than insertion sort and selection sort, and its speed
advantage increases with the array size. You will see that shell-sort makes it possible to address sorting problems that
could not be addressed with the more elementary algorithms.

> *achieving speedups that enable the solution of problems that could not otherwise be solved is one of the prime reasons
> to study algorithm performance and design*

The study of the performance characteristics of shell-sort requires mathematical arguments that are beyond the scope of
this book. the most important result in the present context is the knowledge that *the running time of shell-sort is not
necessarily quadratic*—for example, it is known that the worst-case number of compares for Algorithm 2.3 is proportional
to N^3/2.

That such a simple modification can break the quadratic-running-time barrier is quite interesting, as doing so is a
prime goal for many algorithm design problems.

> No mathematical results are available about the average-case number of compares for shell-sort for randomly ordered input

In practice, you can safely take advantage of the past scientific study of shell-sort just by using the increment
sequence in Algorithm 2.3 (or one of the increment sequences in the exercises at the end of this section)

> **Property E.** The number of compares used by shell-sort with the increments 1, 4, 13, 40, 121, 364, . . . is bounded
> by a small multiple of N times the number of increments used.

> **Evidence:** Instrumenting Algorithm 2.3 to count compares and divide by the number of increments used is a
> straightforward exercise

## 2.2 Mergesort

The algorithms that we consider in this section are based on a simple operation known as **merging** : *combining two
ordered arrays to make one larger ordered array*. This operation immediately leads to a simple recursive sort method
known as **merge sort** : to sort an array, *divide it into two halves, sort the two halves (recursively), and then
merge the results.*

As you will see, one of mergesort’s most attractive properties is that it guarantees to sort any array of N items in
time proportional to **N log N**. Its prime disadvantage is that it *uses extra space proportional to N*.

##### Abstract in-place merge

The straightforward approach to implementing merging is to design a method that merges two disjoint ordered arrays of
Comparable objects into a third array. This strategy is easy to implement:

> create an output array of the requisite size and then choose successively the smallest remaining item from the two
> input arrays to be the next item added to the output array

However, when we mergesort a large array, we are doing a huge number of merges, so the cost of creating a new array to
hold the output every time that we do a merge is problematic. It would be much more desirable to have an in-place method
so that we could sort the first half of the array in place, then sort the second half of the array in place, then do the
merge of the two halves by moving the items around within the array, without using a significant amount of other extra
space

this problem seems to be one that must be simple to solve, but solutions that are known are quite complicated,
especially by comparison to alternatives that use extra space.

Still, the *abstraction of an in-place merge* is useful. Accordingly, we use the method signature <code>merge(a, lo,
mid, hi)</code> to specify a merge method that puts the result of merging the subarrays <code> a[lo..mid] </code>
with <code> a[mid+1..hi] </code>  into a single ordered array, leaving the result in <code>a[lo..hi]</code> .

#### Top-down mergesort

The next algorithm is a recursive merge sort implementation based on this abstract in-place merge. It is one of the
best-known examples of the utility of the **divide-and-conquer paradigm** for efficient algorithm design. This recursive
code is the basis for an inductive proof that the algorithm sorts the array: if it sorts the two subarrays, it sorts the
whole array, by merging together the subarrays.

To understand mergesort, it is worthwhile to consider carefully the dynamics of the method calls. To sort a[0..15], the
sort() method calls itself to sort a[0..7] then calls itself to sort a[0..3]
and a[0..1] before finally doing the first merge of a[0]
with a[1] after calling itself to sort a[0] and then a[1]. Then the next merge is a[2] with a[3]
and then a[0..1] with a[2..3] and so forth. From this trace, we see that the sort code simply provides an organized way
to sequence the calls to the merge() method.

The recursive code also provides us with the basis for analyzing mergesort’s running time. Because mergesort is a *
prototype of the divide-and-conquer algorithm design paradigm*, we will consider this analysis in detail.

> **Proposition F.** Top-down mergesort uses between ½ N lg N and N lg N compares to sort any array of length N.

> **Proof:** Let C(N) be the number of compares needed to sort an array of length N. We have C(0)= C(1)= 0 and for N > 0
> we can write a recurrence relationship that directly mirrors the recursive sort() method to establish an upper bound:
> C(N ) = C(⎣N/2⎦) + C(⎡N/2⎤) + N
> The first term on the right is the number of compares to sort the left half of the array, the second term is the number
> of compares to sort the right half, and the third term is the number of compares for the merge. The lower bound
> C(N ) = C(⎣N/2⎦) + C(⎡N/2⎤) + ⎣N/2⎦
> follows because the number of compares for the merge is at least ⎣N/2⎦. After mathematics operations we get to
> C(N ) = C(2^n) = n 2^n = N lg N
> This proof is valid no matter what the input values are and no matter in what order they appear.


> **Proposition G.** Top-down mergesort uses at most 6N lg N array accesses to sort an array of length N.

> **Proof:** Each merge uses at most 6N array accesses (2N for the copy, 2N for the move back, and at most 2N for
> compares). The result follows from the same argument as for Proposition F.

PropositionS F and G tell us that we can expect the time required by mergesort to be proportional to N log N. You can
sort millions of items (or more) with mergesort, but not with insertion sort or selection sort. The primary drawback of
mergesort is that it requires extra space proportional to N, for the auxiliary array for merging. If space is at a
premium, we need to consider another method. On the other hand, we can cut the running time of mergesort substantially
with some carefully considered modifications to the implementation

**Use insertion sort for small subarrays.** We can improve most recursive algorithms by handling small cases
differently, because the *recursion guarantees that the method will often be used for small cases*, so improvements in
handling them lead to improvements in the whole algorithm. In the case of sorting, we know that insertion sort (or
selection sort) is simple and therefore likely to be faster than mergesort for tiny subarrays. Switching to insertion
sort for small subarrays (length 15 or less, say) will improve the running time of a typical mergesort implementation by
10 to 15 percent.

**Test whether the array is already in order.** We can reduce the running time to be linear for arrays that are already
in order by adding a test to skip the call to merge() if a[mid] is less than or equal to a[mid+1]. With this change, we
still do all the recursive calls, but the running time for any sorted subarray is linear.

**Eliminate the copy to the auxiliary array.** It is possible to eliminate the time (but not the space) taken to copy to
the auxiliary array used for merging. To do so, we use two invocations of the sort method: one takes its input from the
given array and puts the sorted output in the auxiliary array; the other takes its input from the auxiliary array and
puts the sorted output in the given array. With this approach, in a bit of recursive trickery, we can arrange the
recursive calls such that the computation switches the roles of the input array and the auxiliary array at each level.

When addressing a new problem, your best bet is to use the simplest implementation with which you are comfortable and
then refine it if it becomes a bottleneck. Addressing improvements that decrease running time just by a constant factor
may not otherwise be worthwhile. You need to test the effectiveness of specific improvements by running experiments

In the case of mergesort, the three improvements just listed are simple to implement and are of interest when mergesort
is the method of choice

#### Bottom-up Merge sort

In The recursive implementation of mergesort we solve a large problem by dividing it into pieces, solving the
subproblems, then using the solutions for the pieces to solve the whole problem. Even though we are thinking in terms of
merging together two large subarrays, the fact is that most merges are merging together tiny subarrays.

Another way to implement mergesort is to organize the merges so that we do all the merges of tiny subarrays on one pass,
then do a second pass to merge those subarrays in pairs, and so forth, continuing until we do a merge that encompasses
the whole array. This method requires even less code than the standard recursive implementation. We start by doing a
pass of 1-by-1 merges (considering individual items as subarrays of size 1), then a pass of 2-by-2 merges
(merge subarrays of size 2 to make subarrays of size 4), then 4-by-4 merges, and so forth. The second subarray may be
smaller than the first in the last merge on each pass (which is no problem for merge()), but otherwise all merges
involve subarrays of equal size, doubling the sorted subarray size for the next pass.

> **Proposition H.** Bottom-up mergesort uses between ½ N lg N and N lg N compares and at most 6N lg N array accesses to
> sort an array of length N.

> **Proof:** The number of passes through the array is precisely ⎣lg N⎦ (that is precisely the value of n such that
> 2^n <= N < 2^(n+1) ). For each pass, the number of array accesses is exactly 6N and the number of compares is at most
> N and no less than N/ 2.

When the array length is a power of 2, top-down and bottom-up mergesort perform precisely the same compares and array
accesses, just in a different order. When the array length is not a power of 2, the sequence of compares and array
accesses for the two algorithms will be different.

A version of bottom-up mergesort is the method of choice for sorting data organized in a linked list. Consider the list
to be sorted sublists of size 1, then pass through to make sorted subarrays of size 2 linked together, then size 4, and
so forth. This method rearranges the links to sort the list in place (without creating any new list nodes).

Both the top-down and bottom-up approaches to implementing a divide-and conquer algorithm are intuitive. The lesson that
you can take from mergesort is this: Whenever you encounter an algorithm based on one of these approaches, it is worth
considering the other. *Do you want to solve the problem by breaking it up into smaller problems (and solving them
recursively)* as in Merge.sort() or *by building small solutions into larger ones* as in MergeBU.sort()?

#### The complexity of sorting

The first step in a study of complexity is to establish a model of computation. Generally, researchers strive to
understand the simplest model relevant to a problem. For sorting, we study the class of compare-based algorithms that
make their decisions about items only on the basis of comparing keys. A compare-based algorithm can do an arbitrary
amount of computation between compares, but cannot get any information about a key except by comparing it with another
one. Because of our restriction to the Comparable API, all of the algorithms in this chapter are in this class (note
that we are ignoring the cost of array accesses), as are many algorithms that we might imagine.

> **Proposition I**. No compare-based sorting algorithm can guarantee to sort N items with fewer than lg(N !) ~ N lg N compares.

> **Proof:** See the book explanation.

This result serves as a guide for us to know, when designing a sorting algorithm, how well we can expect to do. For
example, without such a result, one might set out to try to design a compare-based sorting algorithm that uses half as
many compares as does mergesort, in the worst case. The lower bound in Proposition I says that such an effort is
futile—*no such algorithm exists*. It is an extremely strong statement that applies to any conceivable compare-based
algorithm.

Proposition H asserts that the number of compares used by mergesort in the worst case is ~ N lg N. This result is an
*upper bound* on the difficulty of the sorting problem in the sense that a better algorithm would have to guarantee to use
a smaller number of compares. Proposition I asserts that no sorting algorithm can guarantee to use fewer than ~ N lg N
compares. It is a *lower bound* on the difficulty of the sorting problem in the sense that even the best possible
algorithm must use at least that many compares in the worst case. Together, they imply:

> **Proposition J.** Mergesort is an asymptotically optimal compare-based sorting algorithm.

> **Proof:** Precisely, we mean by this statement that *both the number of compares used by mergesort in the worst case
> and the minimum number of compares that any compare-based sorting algorithm can guarantee are ~N lg N*. Propositions H 
> and I establish these facts. 

## 2.3 Quicksort


