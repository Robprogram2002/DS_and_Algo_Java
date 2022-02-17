package Goodrich_Tamassia.Linear_DS.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestExtendedArrayList {
    private static ExtendedList<Double> number_list = new ArrayListExtended<>();
    private static ExtendedList<Character> char_list = new ArrayListExtended<>();


    public static void main(String[] args) {
        Double[] temp = {30.0, 22.50, -12.8, 100.0, 1.0, 0.0, -1.50};
        ExtendedList<Double> initialized = new ArrayListExtended<>(temp);
        System.out.println(number_list);
        System.out.println(initialized);
        System.out.println(number_list.isEmpty());
        System.out.println(temp.length == initialized.size());
        System.out.println(initialized.contains(200.0));
        System.out.println(initialized.contains(100.0));
        number_list.add(22.5);
        number_list.add(16.4);
        number_list.add(3.33);
        number_list.add(-12.8);
        number_list.add(-1.5);
        System.out.println(number_list);
        System.out.println(number_list.size());
        System.out.println(number_list.remove(3.33));
        System.out.println(number_list.remove(5.4));
        System.out.println(number_list.remove(1));
        System.out.println(number_list);
        System.out.println(initialized.containsAll(number_list));
        number_list.add(200.0);
        System.out.println(initialized.containsAll(number_list));
        System.out.println(initialized.addAll(number_list));
        System.out.println(initialized);
        ExtendedList<Double> remover = new ArrayListExtended<>(new Double[]{30.0, 200.0, 100.0, -33.3});
        System.out.println(initialized.removeAll(remover));
        System.out.println(initialized);
        System.out.println(initialized.removeAll(-1.5));
        System.out.println(initialized);
        System.out.println(initialized.removeAll(-1.0));
        System.out.println(initialized);
        System.out.println(initialized.replace(1.0, -1.0));
        System.out.println(initialized);
        remover = new ArrayListExtended<>(new Double[]{0.0, 22.5, 72.0});
        System.out.println(initialized.replace(remover, 50.0));
        System.out.println(initialized);
        System.out.println(number_list);
        number_list.clear();
        System.out.println(number_list);
        initialized.set(1, 22.2);
        System.out.println(initialized.get(1));
        initialized.add(1, -14.0);
        initialized.add(0, 0.0);
        System.out.println(initialized);
        initialized.remove(6);
        System.out.println(initialized);
        System.out.println(initialized.indexOf(50.0));
        System.out.println(initialized.lastIndexOf(50.0));
        System.out.println(initialized.indexOf(60.0));

        System.out.println("Order iterator");
        for (Double num : initialized) {
            System.out.print(num);
            System.out.print(", ");
        }
        System.out.println("");
        System.out.println("Reverse iterator");
        Iterator<Double> iter = initialized.reverseIterator();
        while (iter.hasNext()) {
            Double value = iter.next();
            System.out.print(value);
            System.out.print(", ");
        }


        ExtendedList<Double> sublist = initialized.subList(1,5);
//        System.out.println(sublist);
    }
}
