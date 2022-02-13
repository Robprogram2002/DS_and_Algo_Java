package Goodrich_Tamassia.Java_primer.Salles;

/**
 * Class for photographs that can be sold.
 */
public class Photograph implements Sellable {
    private String description;    // description of this photo
    private int price;          // the price we are setting
    private boolean color;      // true if photo is in color
    private int discount;

    public Photograph(String description, int price, boolean color) {
        this.description = description;
        this.price = price;
        this.color = color;
        this.discount = 0;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public int listPrice() {
        return price;
    }

    @Override
    public int lowestPrice() {
        if (discount > 0) {
            return price - (price * discount);
        } else {
            return price;
        }

    }

    @Override
    public boolean isSale() {
        return discount > 0;
    }
}
