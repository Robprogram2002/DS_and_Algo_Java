package Goodrich_Tamassia.Java_primer.Progresions;

public class FibonacciProgression extends Progresion {
    private long prev;


    /**
     * Constructs traditional Fibonacci, starting 0, 1, 1, 2, 3, ...
     */
    public FibonacciProgression() {
        this(0, 1);
    }

    /**
     * Constructs traditional Fibonacci, starting 0, 1, 1, 2, 3, ...
     *
     * @param first  the first term of the progression
     * @param second the second term of the progression
     */
    public FibonacciProgression(long first, long second) {
        super(first);
        prev = second - first;
    }

    /**
     * Replaces (prev,current) with (current, current+prev).
     */
    @Override
    protected void advance() {
        long temp = prev;
        prev = current;
        current += prev;
    }
}
