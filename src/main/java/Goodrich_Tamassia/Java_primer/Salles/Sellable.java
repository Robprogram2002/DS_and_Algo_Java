package Goodrich_Tamassia.Java_primer.Salles;

/**
 * Interface for objects that can be sold
 */
public interface Sellable {
    /**
     * @return a description of the object
     */
    public String description();

    /**
     * @return the list price in cents
     */
    public int listPrice();

    /**
     * @return the lowest price in cents we will accept.
     */
    public int lowestPrice();

    public boolean isSale();

}


