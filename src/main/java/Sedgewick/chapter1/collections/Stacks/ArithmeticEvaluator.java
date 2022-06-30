package Sedgewick.chapter1.collections.Stacks;

import Sedgewick.libraries.StdIn;
import Sedgewick.libraries.StdOut;

//  Dijkstra’s Two-Stack Algorithm for Expression Evaluation

class ArithmeticEvaluator {

    public static void main(String[] args) {

        Stack<String> ops = new ResizingArrayStack<String>();
        Stack<Double> vals = new ResizingArrayStack<Double>();

        while (!StdIn.isEmpty()) { // Read token, push if operator.
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) { // Pop, evaluate, and push result if token is ")".
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            } // Token not operator or paren: push double value.
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());


    }

}
