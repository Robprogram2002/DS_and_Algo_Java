package Goodrich_Tamassia.Java_primer;

import java.util.Scanner;

public class Planet {
    //A nice feature of Java is that when base-type variables are declared as instance
    //variables of a class Java ensures initial default values if not explicitly  initialized.
    // In particular, all numeric types are initialized to zero, boolean is initialized to false,
    // and a character is initialized to the null character by default.

    String name;
    long age;
    int moons;
    Boolean live;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your age in years: ");
        double age = input.nextDouble();
        System.out.print("Enter your maximum heart rate: ");
        double rate = input.nextDouble();
        double fb = (rate - age) * 0.65;
        System.out.println("Your ideal fat-burning heart rate is " + fb);
    }

    private static void initializing() {
        //Note that it is possible to declare (and initialize) multiple variables of the same
        //type in a single statement. But Variables declared
        //locally within a block of code must be initialized before they are first used.

        boolean flag = true;
        boolean verbose, debug;     // two variables declared, but not yet initialized
        char grade = 'A';
        byte b = 12;
        short s = 24;
        int i, j, k = 257;          // three variables declared; only k initialized
        long l = 890L;              // note the use of ”L” here
        float pi = 3.1416F;         // note the use of ”F” here
        double e = 2.71828, a = 6.022e23;       // both variables are initialized
    }

    private static void wrappers() {
        int j = 8;
        Integer a = 12;
        int k = a; // implicit call to a.intValue()
        int m = j + a; // a is automatically unboxed before the addition
        a = 3 * m; // result is automatically boxed before assignment
        Integer b = Integer.parseInt("-135");
        int n = Integer.parseInt("2013"); // using static method of Integer class
    }
}
