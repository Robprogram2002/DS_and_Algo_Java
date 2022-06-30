package Goodrich_Tamassia.Linear_DS.Stacks;

import java.util.Arrays;

public class StacksApplications {
    /**
     * A generic method for reversing an array.
     */
    public static <E> void reverse(E[] a) {
        ExtendedStack<E> temp = new DynamicArrayStack<>();
        for (E e : a) {
            temp.push(e);
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = temp.pop();
        }
    }

    /**
     * Tests if delimiters in the given expression are properly matched.
     */
    public static boolean isMatched(String expression) {
        final String opening = "({[";  // opening delimiters
        final String ending = ")}]";   // respective closing delimiters
        ExtendedStack<Character> temp = new DynamicArrayStack<>();
        for (char c :
                expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {  // this is an opening delimiter
                temp.push(c);
            } else if (ending.indexOf(c) != -1) {  // this is an ending delimiter
                if (temp.isEmpty()) {    // nothing to match with
                    return false;
                }
                if (opening.indexOf(temp.pop()) != ending.indexOf(c)) {
                    return false;               // mismatched delimiter
                }
            }
        }
        return temp.isEmpty();   // were all opening delimiters matched?
    }

    /**
     * Tests if every opening tag has a matching closing tag in HTML string.
     */
    public static boolean isHTMLMatched(String expression) {
        ExtendedStack<String> temp = new DynamicArrayStack<>();
        int j = expression.indexOf('<');
        while (j != -1) {
            int k = expression.indexOf('>', j + 1);
            if (k == -1) {
                return false;
            }
            String tag = expression.substring(j + 1, k).split(" ")[0];
            if (!tag.startsWith("/")) {
                temp.push(tag);
            } else {
                if (temp.isEmpty()) return false;
                if (!tag.substring(1).equals(temp.pop())) return false;
            }

            j = expression.indexOf('<', k + 1);
        }

        return temp.isEmpty();
    }


    public static void main(String[] args) {
        Integer[] a = {4, 8, 15, 16, 23, 42}; // autoboxing allows this
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));

        System.out.println("--------------------- CHECK DELIMITERS -----------------");
        System.out.println(isMatched("( )(( )){([( )])}"));  // true
        System.out.println(isMatched("((( )(( )){([( )])}))"));  // true
        System.out.println(isMatched(")(( )){([( )])}")); // false
        System.out.println(isMatched("({[])}"));  // false
    }
}
