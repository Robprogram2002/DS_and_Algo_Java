package Sedgewick.libraries;

// Example of a functional library (static methods)

class Operations {

    // Euclid’s algorithm: English-language description
    // Compute the greatest common divisor two non-negative integers p and q as follows:
    //If q is 0, the answer is p. If not, divide p by q and take the remainder r. The answer is the
    //greatest common divisor of q and r.

    // java implementation
    public static int gcd(int p, int q) {
        if (q == 0) return p;

        int rest = p % q;
        return gcd(q, rest);

    }

    // find the maximum of the array values
    public static double find_max(double[] array) {
        double max = array[0];

        for (int i = 1; i < array.length; i++)
            if (array[i] > max) max = array[i];

        return max;
    }

    // compute the average of the array values
    public static double array_avg(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++)
            sum += array[i];

        return sum / array.length;

    }

    // copy to another array
    public static double[] copy_array(double[] array) {
        double[] new_array = new double[array.length];
        for (int i = 0; i < array.length; i++) new_array[i] = array[i];

        return new_array;
    }

    //  reverse the elements within an array

    public static void reverse_array(double[] array) {
        int size = array.length;

        for (int i = 0; i < size / 2; i++) {
            double temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
        ;
    }

    // matrix-matrix multiplication (square matrices)
    public static double[][] matrix_multiplication(double[][] a, double[][] b) {
        int N = a.length;
        double[][] c = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) { // Compute dot product of row i and column j.
                for (int k = 0; k < N; k++)
                    c[i][j] += a[i][k] * b[k][j];
            }
        return c;
    }

    // absolute value of an integer
    public static int abs(int a) {
        if (a < 0) return -a;
        else return a;
    }

    public static double abs(double a) {
        if (a < 0.0) return -a;
        else return a;
    }

    // hypotenuse of a right triangle
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b
        );
    }

    //  Harmonic number
    public static double harmonic(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++)
            sum += 1.0 / i;
        return sum;
    }

}

// Unit testing. A best practice in Java programming is to include a main() in every library of static methods that
// tests the methods in the library Proper unit testing can be a significant programming challenge in itself.
// At a minimum, every module should contain a main() method that exercises the code in the module
//and provides some assurance that it works. As a module matures, we often refine the
//main() method to be a development client that helps us do more detailed tests as we
//develop the code, or a test client that tests all the code extensively. As a client becomes
//more complicated, we might put it in an independent module

