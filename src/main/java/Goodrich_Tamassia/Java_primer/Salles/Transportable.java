package Goodrich_Tamassia.Java_primer.Salles;

/**
 * Interface for objects that can be transported.
 */
public interface Transportable {
    /**
     * @return Returns the weight in grams.
     */
    public int weight();

    /**
     * @return Returns whether the object is hazardous.
     */
    public boolean isHazardous();
}
