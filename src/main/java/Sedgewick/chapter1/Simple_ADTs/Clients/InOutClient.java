package Sedgewick.chapter1.Simple_ADTs.Clients;

import Sedgewick.libraries.In;
import Sedgewick.libraries.Out;

public class InOutClient {
    public static void main(String[] args) {
        // Copy input files to out (last argument).
        Out out = new Out(args[args.length - 1]);
        for (int i = 0; i < args.length - 1; i++) {
            // Copy input file named on ith arg to out.
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
        out.close();
    }
}
