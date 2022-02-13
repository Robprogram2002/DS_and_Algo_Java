package Goodrich_Tamassia.Java_primer.Generics;

//we can implement a pair class using formal type
//parameters to represent the two relevant types in our composition.
//Angle brackets are used to enclose the sequence of formal type parameters


public class Pair<A, B> {
    A first;
    B second;

    public Pair(A a, B b) {
        first = a;
        second = b;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
