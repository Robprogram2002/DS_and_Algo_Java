package Goodrich_Tamassia.Fundamental_DS.LinkedLists;

public class TestLinkedList {
    private static final SinglyLinkedListInterface<String> single = new SinglyLinkedList<>();
    private static final CircularLinkedListInterface<String> circular = new CircularLinkedList<>();
    private static final DoublyLinkedListInterface<String> doubly = new DoublyLinkedList<>();

    public static void main(String[] args) {

        System.out.println("--------------------Singly Linked List-------------------");
        System.out.println(single.isEmpty());
        single.addFirst("Bob");
        single.addFirst("Amanda");
        System.out.println(single.size());
        single.addLast("Jacob");
        single.addLast("Sebastian");
        System.out.println(single);
        System.out.println(single.removeFirst());
        System.out.println(single.removeFirst());
//        System.out.println(single.removeLast());
        System.out.println(single.size());

        System.out.println("--------------------Circularly Linked List-------------------");
        System.out.println(single.isEmpty());
        circular.addFirst("Bob");
        circular.addFirst("Amanda");
        System.out.println(circular.size());
        circular.addLast("Jacob");
        circular.addLast("Sebastian");
        System.out.println(circular);
        System.out.println(circular.removeFirst());
        System.out.println(circular.size());
        System.out.println(circular);
        circular.rotate();
        System.out.println(circular);
        circular.rotate();
        System.out.println(circular);

        System.out.println("--------------------Doubly Linked List-------------------");
        System.out.println(doubly.isEmpty());
        doubly.addFirst("Bob");
        doubly.addFirst("Amanda");
        System.out.println(doubly.size());
        doubly.addLast("Jacob");
        doubly.addLast("Sebastian");
        System.out.println(doubly);
        System.out.println(doubly.removeFirst());
        System.out.println(doubly.removeLast());
        System.out.println(doubly.size());
        System.out.println(doubly);
    }
}
