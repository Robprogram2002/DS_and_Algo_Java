package Goodrich_Tamassia.Java_primer.Progresions;

public class GeometricProgression extends Progresion {
    private long base;


    /**
     * Constructs progression 1, 2, 4, 8, 16, ..
     */
    public GeometricProgression() {
        this(2, 1);
    }

    /**
     * Constructs progression 1, b, b^2, b^3, b^4, ..
     *
     * @param b a long type representing the base of the progression
     */
    public GeometricProgression(long b) {
        this(b, 1);
    }

    /**
     * Constructs geometric progression with arbitrary base and start
     *
     * @param b     the base of the progression
     * @param start the initial term of the progression
     */
    public GeometricProgression(long b, long start) {
        super(start);
        base = b;
    }


    /**
     * Multiplies the current value by the geometric base.
     */
    @Override
    protected void advance() {
        current *= base;
    }
}
