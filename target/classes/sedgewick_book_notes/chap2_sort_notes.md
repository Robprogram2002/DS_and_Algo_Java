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
*upper bound* on the difficulty of the sorting problem in the sense that a better algorithm would have to guarantee to
use a smaller number of compares. Proposition I asserts that no sorting algorithm can guarantee to use fewer than ~ N lg
N compares. It is a *lower bound* on the difficulty of the sorting problem in the sense that even the best possible
algorithm must use at least that many compares in the worst case. Together, they imply:

> **Proposition J.** Mergesort is an asymptotically optimal compare-based sorting algorithm.

> **Proof:** Precisely, we mean by this statement that *both the number of compares used by mergesort in the worst case
> and the minimum number of compares that any compare-based sorting algorithm can guarantee are ~N lg N*. Propositions H
> and I establish these facts.

## 2.3 Quicksort

Quicksort is popular because it is not difficult to implement, works well for a variety of different kinds of input
data, and is substantially faster than any other sorting method in typical applications. The quicksort algorithm’s
desirable features are that it is in-place (uses only a small auxiliary stack) and that it requires time proportional to
N log N on the average to sort an array of length N

Its primary drawback is that it is fragile in the sense that some care is involved in the implementation to be sure to
avoid bad performance.

**The basic algorithm.** Quicksort is a divide-and-conquer method for sorting. It works by partitioning an array into
two subarrays, then sorting the subarrays independently. Quicksort is complementary to mergesort: for mergesort, we
break the array into two subarrays to be sorted and then combine the ordered subarrays to make the whole ordered array;
for quicksort, we rearrange the array such that, when the two subarrays are sorted, the whole array is ordered. In the
first instance, we do the two recursive calls before working on the whole array; in the second instance, we do the two
recursive calls after working on the whole array. For mergesort, the array is divided in half; for quicksort, the
position of the partition depends on the contents of the array.

The crux of the method is the partitioning process, which rearranges the array to make the following three conditions
hold:

* The entry a[j] is in its final place in the array, for some j.
* No entry in a[lo] through a[j-1] is greater than a[j].
* No entry in a[j+1] through a[hi] is less than a[j].

We achieve a complete sort by partitioning, then recursively applying the method.

Because the partitioning process always fixes one item into its position, a formal proof by induction that the recursive
method constitutes a proper sort is not difficult to develop: if the left subarray and the right subarray are both
properly sorted, then the result array, made up of the left subarray (in order, with no entry larger than the
partitioning item), the partitioning item, and the right subarray (in order, with no entry smaller that the partitioning
item), is in order.

It is a randomized algorithm, because it randomly shuffles the array before sorting it. Our reason for doing so is to be
able to predict (and depend upon) its performance characteristics.

**Partitioning.** We use the following general strategy: First, we arbitrarily choose a[lo] to be the partitioning
item—the one that will go into its final position. Next, we scan from the left end of the array until we find an entry
greater than (or equal to) the partitioning item, and we scan from the right end of the array until we find an entry
less than (or equal to) the partitioning item. The two items that stopped the scans are out of place in the final
partitioned array, so we exchange them. Continuing in this way, we ensure that no array entries to the left of the left
index i are greater than the partitioning item, and no array entries to the right of the right index j are less than the
partitioning item. When the scan indices cross, all that we need to do to complete the partitioning process is to
exchange the partitioning item a[lo] with the rightmost entry of the left subarray (a[j]) and return its index j.

##### Possible issues

**Partitioning in place.** If we use an extra array, partitioning is easy to implement, but not so much easier that it
is worth the extra cost of copying the partitioned version back into the original

**Staying in bounds.** If the smallest item or the largest item in the array is the partitioning item, we have to take
care that the pointers do not run off the left or right ends of the array, respectively. Our partition() implementation
has explicit tests to guard against this circumstance

**Preserving randomness.** The random shuffle puts the array in random order. Since it treats all items in the subarrays
uniformly, the implementation has the property that its two subarrays are also in random order. This fact is crucial to
the predictability of the algorithm’s running time. . An alternate way to preserve randomness is to choose a random item
for partitioning within partition().

**Terminating the loop.** Properly testing whether the pointers have crossed is a bit trickier than it might seem at
first glance. A common error is to fail to take into account that the array might contain other items with the same key
value as the partitioning item.

**Handling items with keys equal to the partitioning item’s key.** It is best to stop the left scan for items with keys
greater than or equal to the partitioning item’s key and the right scan for items with key less than or equal to the
partitioning item’s key. Even though this policy might seem to create unnecessary exchanges involving items with keys
equal to the partitioning item’s key, it is crucial to avoiding quadratic running time in certain typical applications

##### Performance characteristics

The inner loop of quicksort (in the partitioning method) increments an index and compares an array entry against a fixed
value. This simplicity is one factor that makes quicksort quick: it is hard to envision a shorter inner loop in a
sorting algorithm. For example, mergesort and shellshort are typically slower than quicksort because they also do data
movement within their inner loops.

The second factor that makes quicksort quick is that it uses few compares. Ultimately, the efficiency of the sort
depends on how well the partitioning divides the array, which in turn depends on the value of the partitioning item’s
key. Partitioning divides a large randomly ordered array into two smaller randomly ordered subarrays, but the actual
split is equally likely (for distinct keys) to be anywhere in the array.

The best case for quicksort is when each partitioning stage divides the array exactly in half. This circumstance would
make the number of compares used by quicksort satisfy the divide-and-conquer recurrence `C_N = 2 C_{N/2} + N`.
The `2C_{N/2}` term covers the cost of sorting the two subarrays; the N is the cost of examining each entry, using one
partitioning index or the other

we know that this recurrence has the solution `CN ~ N lg N`. Although things do not always go this well, it is true that
the partition falls in the middle on the average. Taking into account the precise probability of each partition position
makes the recurrence more complicated and more difficult to solve, but the final result is similar. The proof of this
result is the basis for our confidence in quicksort.

> **Proposition K.** Quicksort uses `~ 2N ln N` compares (and one-sixth that many exchanges) on the average to sort an
> array of length N with distinct keys.

When keys may not be distinct, as is typical in practical applications, precise analysis is considerably more
complicated, but it is not difficult to show that the average number of compares is no greater than CN , even when
duplicate keys may be present.

the basic quicksort program has one potential liability: it can be extremely inefficient if the partitions are
unbalanced. For example, it could be the case that the first partition is on the smallest item, the second partition on
the next smallest item, and so forth, so that the program will remove just one item for each call, leading to an
excessive number of partitions of large subarrays. Avoiding this situation is the primary reason that we randomly
shuffle the array before using quicksort. This action makes it so unlikely that bad partitions will happen consistently
that we need not worry about the possibility.

> **Proposition L.** Quicksort uses `~ N^2 / 2` compares in the worst case, but random shuffling protects against this
> case.

quicksort is typically faster than mergesort because (even though it does 39 percent more compares) it does much less
data movement.

##### Algorithm improvements

> Quicksort was invented in 1960 by C. A. R. Hoare

the algorithm is so well-balanced that the effects of improvements can be more than offset by unexpected side effects,
but a few of them, which we now consider, are quite effective. As noted, you need to run experiments to determine the
effectiveness of these improvements and to determine the best choice of parameters for your implementation.

**Cutoff to insertion sort.** As with most recursive algorithms, an easy way to improve the performance of quicksort is
based on the following two observations:

* Quicksort is slower than insertion sort for tiny subarrays.
* Being recursive, quicksort’s sort() is certain to call itself for tiny subarrays.

Accordingly, it pays to switch to insertion sort for tiny subarrays. The optimum value of the cutoff M is
system-dependent, but any value between 5 and 15 is likely to work well in most situations

**Median-of-three partitioning.** A second easy way to improve the performance of quicksort is to use the median of a
small sample of items taken from the subarray as the partitioning item. Doing so will give a slightly better partition,
but at the cost of computing the median. It turns out that most of the available improvement comes from choosing a
sample of size 3 and then partitioning on the middle item. As a bonus, we can use the sample items as sentinels at the
ends of the array and remove both array bounds tests in partition().

Entropy-optimal sorting. Arrays with large numbers of duplicate keys arise frequently in applications. In such
situations, the quicksort implementation that we have considered has acceptable performance, but it can be substantially
improved. For example, a subarray that consists solely of items that are equal (just one key value) does not need to be
processed further, but our implementation keeps partitioning down to small subarrays. In a situation where there are
large numbers of duplicate keys in the input array, the recursive nature of quicksort ensures that subarrays consisting
solely of items with keys that are equal will occur often. There is potential for significant improvement, from the
linearithmic-time performance of the implementations seen so far to linear-time performance.

One straightforward idea is to partition the array into three parts, one each for items with keys smaller than, equal
to, and larger than the partitioning item’s key. Various different methods have been suggested for the task.

Dijkstra’s solution it is based on a single left-to-right pass through the array that maintains a pointer lt such that
a[lo..lt-1] is *less than v*, a pointer gt such that a[gt+1, hi] is *greater than v*, and a pointer i such that
a[lt..i-1] are *equal to v* and a[i..gt] are *not yet examined*.

Starting with `i` equal to `lo`, we process a[i] using the 3-way comparison given us by the Comparable interface (
instead of using less()) to directly handle the three possible cases:

* a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
* a[i] greater than v: exchange a[i] with a[gt] and decrement gt
* a[i] equal to v: increment i.

Each of these operations both maintains the invariant and decreases the value of gt-i (so that the loop terminates).
Furthermore, every item encountered leads to an exchange except for those items with keys equal to the partitioning
item’s key.

Though this code was developed not long after quicksort in the 1970s, it fell out of favor because it uses many more
exchanges than the standard 2-way partitioning method for the common case when the number of duplicate keys in the array
is not high.

1990s J. Bentley and D. McIlroy developed a clever implementation that overcomes this problem, and observed that 3-way
partitioning makes quicksort asymptotically faster than mergesort and other methods in practical situations involving
large numbers of equal keys.

Mergesort does not guarantee optimal performance for any given distribution of duplicates in the input: for example,
mergesort is linearithmic for a randomly ordered array that has only a constant number of distinct key values, but
quicksort with 3-way partitioning is linear for such an array.

> quicksort with 3-way partitioning is entropy-optimal, in the sense that the average number of compares used by the best
> possible compare-based sorting algorithm and the average number of compares used by 3-way quicksort are within a
> constant factor of one another, for any given distribution of input key values

As with standard quicksort, the running time tends to the average as the array size grows, and large deviations from the
average are extremely unlikely, so that you can depend on 3-way quicksort’s running time to be proportional to N times
the entropy of the distribution of input key values. This property of the algorithm is important in practice because it
reduces the time of the sort from linearithmic to linear for arrays with large numbers of duplicate keys.

The order of the keys is immaterial, because the algorithm shuffles them to protect against the worst case. The
distribution of keys defines the entropy and no compare-based algorithm can use fewer compares than defined by the
entropy. This ability to adapt to duplicates in the input makes 3-way quicksort the algorithm of choice for a library
sort—clients that sort arrays containing large numbers of duplicate keys are not unusual.

## 2.4 PRIORITY QUEUES

Many applications require that we process items having keys in order, but not necessarily in full sorted order and not
necessarily all at once. Often, we collect a set of items, then process the one with the largest key, then perhaps
collect more items, then process the one with the current largest key, and so forth.

An appropriate data type in such an environment supports two operations: remove the maximum and insert. Such a data type
is called a **priority queue**.

We use the method names `delMax()` for remove the maximum and `insert()` for insert. By convention, we will compare keys
only with a helper less() method, as we have been doing for sorting. Thus, if items can have duplicate keys, maximum
means any item with the largest key value

For flexibility, we use a generic implementation with a parameterized type Key that implements the Comparable interface.
This choice eliminates our distinction between items and keys and enables clearer and more compact descriptions of data
structures and algorithms. For example, we refer to the “largest key” instead of the “largest item” or the “item with
the largest key.”

the API includes three constructors, which enable clients to build priority queues of an initial fixed size (perhaps
initialized with a given array of keys). To clarify client code, we will use a separate class MinPQ whenever
appropriate, which is the same as MaxPQ except that it has a delMin() method that deletes and returns an item with the
smallest key in the queue. Any MaxPQ implementation is easily converted into a MinPQ implementation and vice versa,
simply by reversing the sense of the comparison in less().

        public class MaxPQ< Key extends Comparable<Key>> 
            MaxPQ()             create a priority queue
            MaxPQ(int max)      create a priority queue of initial capacity max 
            MaxPQ(Key[] a)      create a priority queue from the keys in a[]
            void insert(Key v)  insert a key into the priority queue 
            Key max()           return the largest key 
            Key delMax()        return and remove the largest key
            boolean isEmpty()   is the priority queue empty? 
            int size()          number of keys in the priority queue

**A priority-queue client.** To appreciate the value of the priority-queue abstraction, consider the following problem:
You have a huge input stream of N strings and associated integer values, and your task is to find the largest or
smallest M integers (and associated strings) in the input stream. In some applications, the size of the input stream is
so huge that it is best to consider it to be unbounded. One way to address this problem would be to sort the input
stream and take the M largest keys from the result, but we have just stipulated that the input stream is too large for
that. Another approach would be to compare each new key against the M largest seen so far, but that is also likely to be
prohibitively expensive unless M is small.

With priority queues, we can solve the problem provided that we can develop efficient implementations of both insert()
and delMin().

#### Elementary implementations

The basic data structures that we discussed in Chapter 1 provide us with four immediate starting points for implementing
priority queues. We can use an array or a linked list, kept in order or unordered. These implementations are useful for
small priority queues, situations where one of the two operations are predominant, or situations where some assumptions
can be made about the order of the keys involved in the operations

Using unordered sequences is the prototypical lazy approach to this problem, where we defer doing work until necessary (
to find the maximum); using ordered sequences is the prototypical eager approach to the problem, where we do as much
work as we can up front (keep the list sorted on insertion) to make later operations efficient.

### Heaps

The binary heap is a data structure that can efficiently support the basic priority-queue operations. This ordering is
easy to see if we view the keys as being in a binary tree structure with edges from each key to the two keys known to be
smaller.

> **Definition.** A binary tree is **heap-ordered** if the key in each node is larger than or equal to the keys in that node’s
> two children (if any).

Equivalently, the key in each node of a heap-ordered binary tree is smaller than or equal to the key in that node’s
parent (if any). Moving up from any node, we get a nondecreasing sequence of keys; moving down from any node, we get a
nonincreasing sequence of keys. In particular:

> **Proposition O.** The largest key in a heap-ordered binary tree is found at the root.

**Binary heap representation.** It is particularly convenient to use a *complete binary tree*. We draw such a structure
by placing the root node and then proceeding down the page and from left to right, drawing and connecting two nodes
beneath each node on the previous level until we have drawn N nodes. Complete trees provide the opportunity to use a
compact array representation that does not involve explicit links. Specifically, we represent complete binary trees
sequentially within an array by putting the nodes in *level order*.

> **Definition.** A binary heap is a collection of keys arranged in a complete heap-ordered binary tree, represented in
> level order in an array (not using the first entry).

In a heap, the parent of the node in position k is in position ⎣k /2⎦ and, conversely, the two children of the node in
position k are in positions 2k and 2k + 1. Instead of using explicit links we can travel up and down by doing simple
arithmetic on array indices: to move up the tree from a[k] we set k to k/2; to move down the tree we set k to 2*k or 2*
k+1.

Complete binary trees represented as arrays (heaps) are rigid structures, but they have just enough flexibility to allow
us to implement efficient priority-queue operations. Specifically, we will use them to develop logarithmic-time insert
and remove the maximum implementations. These algorithms take advantage of the capability to move up and down paths in
the tree without pointers and have guaranteed logarithmic performance because of the following property of complete
binary trees:

> **Proposition P.** The height of a complete binary tree of size N is ⎣ lg N ⎦ .

#### Heap algorithms

The heap operations that we consider work by first making a simple modification that could violate the heap condition,
then traveling through the heap, modifying the heap as required to ensure that the heap condition is satisfied
everywhere. We refer to this process as *reheapifying*, or *restoring heap order*.

There are two cases. When the priority of some node is increased (or a new node is added at the bottom of a heap), we
have to travel up the heap to restore the heap order. When the priority of some node is decreased (for example, if we
replace the node at the root with a new node that has a smaller key), we have to travel down the heap to restore the
heap order.

**Bottom-up reheapify (swim).** If the heap order is violated because a node’s key becomes larger than that node’s
parent’s key, then we can make progress toward fixing the violation by exchanging the node with its parent. After the
exchange, the node is larger than both its children (one is the old parent, and the other is smaller than the old parent
because it was a child of that node) but the node may still be larger than its parent. We can fix that violation in the
same way, and so forth, moving up the heap until we reach a node with a larger key, or the root.

The loop in swim() preserves the invariant that the only place the heap order could be violated is when the node at
position k might be larger than its parent. Therefore, when we get to a place where that node is not larger than its
parent, we know that the heap order is satisfied throughout the heap.

**Top-down reheapify (sink).** If the heap order is violated because a node’s key becomes smaller than one or both of
that node’s children’s keys, then we can make progress toward fixing the violation by exchanging the node with the
larger of its two children. This switch may cause a violation at the child; we fix that violation in the same way, and
so forth, moving down the heap until we reach a node with both children smaller (or equal), or the bottom.

These sink() and swim() operations provide the basis for efficient implementation of the priority-queue API.

* **Insert.** We add the new key at the end of the array, increment the size of the heap, and then swim up through the
  heap with that key to restore the heap condition.
* **Remove the maximum.** We take the largest key off the top, put the item from the end of the heap at the top,
  decrement the size of the heap, and then sink down through the heap with that key to restore the heap condition.

> **Proposition Q.** In an N-key priority queue, the heap algorithms require no more than 1 + lg N compares for insert
> and no more than 2 lg N compares for remove the maximum.

**Proof:** By Proposition P, both operations involve moving along a path between the root and the bottom of the heap
whose number of links is no more than lg N. The remove the maximum operation requires two compares for each node on the
path (except at the bottom): one to find the child with the larger key, the other to decide whether that child needs to
be promoted.

a heap-based implementation provides a guarantee that both operations complete in logarithmic time. This improvement can
make the difference between solving a problem and not being able to address it at all.

**Immutability of keys.** The priority queue contains objects that are created by clients but assumes that client code
does not change the keys (which might invalidate the heap-order invariant). It is possible to develop mechanisms to
enforce this assumption, but programmers typically do not do so because they complicate the code and are likely to
degrade performance.

#### Multiway heaps.

It is not difficult to modify our code to build heaps based on an array representation of complete heap-ordered ternary
trees, with an entry at position k larger than or equal to entries at positions 3k-1, 3k, and 3k+1 and smaller than or
equal to entries at position ⎣(k+1)  3⎦, for all indices between 1 and N in an array of N items, and not much more
difficult to use d-ary heaps for any given d. There is a tradeoff between the lower cost from the reduced tree height (
log d N) and the higher cost of finding the largest of the d children at each node. This tradeoff is dependent on
details of the implementation and the expected relative frequency of operations.

#### Index priority queue.

In many applications, it makes sense to allow clients to refer to items that are already on the priority queue. One easy
way to do so is to associate a unique integer index with each item. Moreover, it is often the case that clients have a
universe of items of a known size N and perhaps are using (parallel) arrays to store information about the items, so
other unrelated client code might already be using an integer index to refer to items. These considerations lead us to
the following API:

> **Proposition Q (continued).** In an index priority queue of size N, the number of compares required is proportional to at
> most log N for insert, change priority, delete, and remove the minimum.

**Index priority-queue client.** multiway merge problem: it merges together several sorted input streams into one sorted
output stream. If you have the space, you might just read them all into an array and sort them, but with a priority
queue, you can read input streams and put them in sorted order on the output no matter how long they are.

#### Heapsort

We can use any priority queue to develop a sorting method. We insert all the items to be sorted into a minimum-oriented
priority queue, then repeatedly use remove the minimum to remove them all in order. Using a priority queue represented
as an unordered array in this way corresponds to doing a selection sort; using an ordered array corresponds to doing an
insertion sort.

we use the heap to develop a classic elegant sorting algorithm known as heapsort. Heapsort breaks into two phases: **
heap construction**, where we reorganize the original array into a heap, and the **sortdown**, where we pull the items
out of the heap in decreasing order to build the sorted result. For consistency with the code we have studied, we use a
maximum-oriented priority queue and repeatedly remove the maximum. Focusing on the task of sorting, we abandon the
notion of hiding the representation of the priority queue and use swim() and sink() directly. Doing so allows us to sort
an array without needing any extra space, by maintaining the heap within the array to be sorted.

##### Heap construction.

we can accomplish this task in time proportional to N log N, by proceeding from left to right through the array, using
swim() to ensure that the items to the left of the scanning pointer make up a heap-ordered complete tree, like
successive priority-queue insertions.

A clever method that is much more efficient is to proceed from right to left, using sink() to make subheaps as we go.
Every position in the array is the root of a small subheap; sink() works for such subheaps, as well. If the two children
of a node are heaps, then calling sink() on that node makes the subtree rooted at the parent a heap. This process
establishes the heap order inductively.

> **Proposition R.** Sink-based heap construction uses fewer than 2N compares and fewer than N exchanges to construct a
> heap from N items.

**Proof:** This fact follows from the observation that most of the heaps processed are small. For example, to build a
heap of 127 elements, we process 32 heaps of size 3, 16 heaps of size 7, 8 heaps of size 15, 4 heaps of size 31, 2 heaps
of size 63, and 1 heap of size 127, so 32·1 + 16·2 + 8·3 + 4·4 + 2·5 + 1·6 = 120 exchanges (twice as many compares) are
required (at worst).

As the first phase of a sort, heap construction is a bit counterintuitive, because its goal is to produce a heap-ordered
result, which has the largest item first in the array (and other larger items near the beginning), not at the end, where
it is destined to finish.

##### Sortdown.

we remove the largest remaining item from the heap and put it into the array position vacated as the heap shrinks. This
process is a bit like selection sort (taking the items in decreasing order instead of in increasing order), but it uses
many fewer compares because the heap provides a much more efficient way to find the largest item in the unsorted part of
the array.

> **Proposition S.** Heapsort uses fewer than 2N lg N + 2N compares (and half that many exchanges) to sort N items.

**Proof:** The 2 N term covers the cost of heap construction (see Proposition R). The 2 N lg N term follows from
bounding the cost of each sink operation during the sortdown by 2lg N

> the classical heapsort algorithm was invented by J. W. J. Williams and refined by R. W. Floyd in 1964.

**Sink to the bottom, then swim.** Most items reinserted into the heap during sortdown go all the way to the bottom.
Floyd observed in 1964 that we can thus save time by avoiding the check for whether the item has reached its position,
simply promoting the larger of the two children until the bottom is reached, then moving back up the heap to the proper
position. This idea cuts the number of compares by a factor of 2 asymptotically—close to the number used by mergesort (
for a randomly-ordered array). The method requires extra bookkeeping, and it is useful in practice only when the cost of
compares is relatively high

because it is the only method that we have seen that is optimal (within a constant factor) in its use of both time and
space—it is guaranteed to use ~2N lg N compares and constant extra space in the worst case. When space is very tight (
for example, in an embedded system or on a low-cost mobile device) it is popular because it can be implemented with just
a few dozen lines (even in machine code) while still providing optimal performance. However, it is rarely used in
typical applications on modern systems because it has poor cache performance: array entries are rarely compared with
nearby array entries, so the number of cache misses is far higher than for quicksort, mergesort, and even shellsort,
where most compares are with nearby entries.