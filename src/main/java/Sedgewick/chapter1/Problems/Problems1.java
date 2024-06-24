package Sedgewick.chapter1.Problems;

public class Problems1 {

    // 1.1.5 Write a code fragment that prints true if the double variables x and y are both
    //strictly between 0 and 1 and false otherwise.
    public static void check(double x, double y) {
        if ((x > 0 && y > 0) && (x < 1 && y < 1)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    // 1.1.9 Write a code fragment that puts the binary representation of a positive integer N
    //into a String s.
    public static String toBinaryString(int N) {
        if (N < 0) return "Bad Input";
//      int digits = (int)(Math.log(N) / Math.log(2) + 1);
//        StringBuilder binary = new StringBuilder();
//        while (N > 0) {
//            binary.append(N % 2);
//            N /= 2;
//        }
//        return binary.reverse().toString();

        String s = "";
        while(N > 0 ) {
            s = N%2 + s;
            N = N/2;
        }
        return s;
    }

    // 1.1.11 Write a code fragment that prints the contents of a two-dimensional boolean
    //array, using * to represent true and a space to represent false. Include row and column
    //numbers.

    // 1.1.13 Write a code fragment to print the transposition (rows and columns changed)
    //of a two-dimensional array with M rows and N columns.

    // 1.1.14 Write a static method lg() that takes an int value N as argument and returns
    //the largest int not larger than the base-2 logarithm of N.

    // 1.1.15 Write a static method histogram() that takes an array a[] of int values and
    //an integer M as arguments and returns an array of length M whose ith entry is the num-
    //ber of times the integer i appeared in the argument array. If the values in a[] are all
    //between 0 and M–1, the sum of the values in the returned array should be equal to
    //a.length.

    // 1.1.20 Write a recursive static method that computes the value of ln (N !)

    // 1.1.21 Write a program that reads in lines from standard input with each line containing
    // a name and two integers and then uses printf() to print a table with a column of
    //the names, the integers, and the result of dividing the first by the second, accurate to
    //three decimal places.

    // 1.1.24 Give the sequence of values of p and q that are computed when Euclid’s algorithm
    // is used to compute the greatest common divisor of 105 and 24. Extend the code
    //given on page 4 to develop a program Euclid that takes two integers from the command
    //line and computes their greatest common divisor, printing out the two arguments for
    //each call on the recursive method. Use your program to compute the greatest common
    //divisor or 1111111 and 1234567.

    // 1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array
    //a[][] such that a[i][j] is true if i and j are relatively prime (have no common factors),
    // and false otherwise.

    // 1.1.31 Random connections. Write a program that takes as command-line arguments
    //an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
    //.05 on the circumference of a circle, and then, with probability p for each pair of points,
    //draws a gray line connecting them.

    // 1.1.32 Histogram. Suppose that the standard input stream is a sequence of double
    //values. Write a program that takes an integer N and two double values l and r from the
    //command line and uses StdDraw to plot a histogram of the count of the numbers in the
    //standard input stream that fall in each of the N intervals defined by dividing (l , r) into
    //N equal-sized intervals.

    //Matrix library. Write a library Matrix that implements the following API:
    //    static double       dot(double[] x, double[] y)vector dot product
    //    static double[][]   mult(double[][] a, double[][] b)matrix-matrix product
    //    static double[][]   transpose(double[][] a)transpose
    //    static double[]     mult(double[][] a, double[] x)matrix-vector product
    //    static double[]     mult(double[] y, double[][] a)vector-matrix product



    // 1.1.3 Write a program that takes three integer command-line arguments and prints
    //equal if all three are equal, and not equal otherwise.
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a == b && a == c) System.out.println("True");
        else System.out.println("False");

        check(0.2, 0.6);
        check(0.2, -0.2);
        check(1.2, -0.1);

        System.out.println(toBinaryString(1001));
    }

}
