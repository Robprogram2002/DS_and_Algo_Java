package Goodrich_Tamassia.Java_primer.Salles;

/**
 * Interface for items that can be salle and transport
 */
public interface Insurable extends Sellable, Transportable {
    /**
     * @return Returns insured value in cents
     */
    public int insuredValue();
}
