package Goodrich_Tamassia.Linear_DS.Deques;

import java.util.Iterator;

public class TestExtendedArrayDeque {

    public static void main(String[] args) {
        ExtendedDeque<Double> dequeA = new ExtendedArrayDeque<>();
        ExtendedDeque<Double> dequeB = new ExtendedArrayDeque<>(3, false);
        ExtendedDeque<Double> dequeC = new ExtendedArrayDeque<>(6, true);
        ExtendedDeque<Double> dequeD = new ExtendedArrayDeque<>(new Double[]{20.0, 10.0, 0.0, -10.0, -20.0});

        System.out.println(dequeA.size());
        System.out.println(dequeD.size());
        System.out.println(dequeA.isEmpty());
        System.out.println(dequeD.isEmpty());

        System.out.println(dequeD.getFirst());
        System.out.println(dequeD.getLast());
        System.out.println(dequeA.peekFirst());
        System.out.println(dequeA.peekLast());
        //        System.out.println(dequeA.getFirst());  // throw error

        dequeB.addFirst(100.0);
        dequeB.addFirst(10.0);
        dequeB.addLast(-10.0);
        System.out.println(dequeB);
        dequeB.offerFirst(5.0);
        dequeB.offerLast(5.0);
//        dequeB.addFirst(5.0);  // throw error

        System.out.println(dequeD);
        System.out.println(dequeD.pollFirst());
        System.out.println(dequeD.pollLast());
        System.out.println(dequeD);

        System.out.println(dequeC.pollFirst());
        System.out.println(dequeC.pollLast());
//        System.out.println(dequeC.removeFirst()); // throw error

        dequeC.addFirst(1.0);
        dequeC.addFirst(2.0);
        dequeC.addFirst(3.0);
        dequeC.addFirst(4.0);
        dequeC.addFirst(5.0);
        dequeC.addFirst(6.0);
        dequeC.addFirst(3.0);
        dequeC.addFirst(4.0);
        System.out.println(dequeC);
        System.out.println(dequeC.removeFirstOccurrence(3.0));
        System.out.println(dequeC.removeFirstOccurrence(10.0));
        System.out.println(dequeC.removeLastOccurrence(4.0));
        System.out.println(dequeC);
        System.out.println(dequeC.contains(5.0));
        System.out.println(dequeC.contains(10.0));
        dequeC.rotate(1);
        System.out.println(dequeC);
        dequeC.rotate(2);
        System.out.println(dequeC);

        System.out.println(dequeB);
        dequeB.clear();
        System.out.println(dequeB);

        System.out.println("Sequential iterator");
        for (Double num : dequeC) {
            System.out.println(num);
        }

        System.out.println("Reverse iterator");
        Iterator<Double> reverse = dequeC.reverseIterator();
        while (reverse.hasNext()) {
            Double value = reverse.next();
            System.out.println(value);
        }
    }
}
