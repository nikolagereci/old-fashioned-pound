package hr.ngereci.oldfashionedpound.model;

/**
 * Model class representing the pound onder the old money system.
 * Under the old money system of UK, before 1970, there were 12 pence in a shilling and 20 shillings, or 240
 * pence, in a pound. Thus, a price in the OldUK Money system was expressed in Pounds, Shillings and
 * Pence. For example "12p 6s 10d" is 12 Pounds, 6 Shillings and 10 Pence.
 *
 * Also contains a remainder for division purposes.
 */
public class OldFashionedPound {
    private int pounds;
    private int shillings;
    private int pence;
    private int rem;

    public int getPounds() {
        return pounds;
    }

    public void setPounds(int pounds) {
        this.pounds = pounds;
    }

    public int getShillings() {
        return shillings;
    }

    public void setShillings(int shillings) {
        this.shillings = shillings;
    }

    public int getPence() {
        return pence;
    }

    public int getRem() { return rem; }

    public void setRem(int rem) { this.rem = rem; }

    public void setPence(int pence) {
        this.pence = pence;
    }

    public OldFashionedPound(int pounds, int shillings, int pence) {
        this.pounds = pounds;
        this.shillings = shillings;
        this.pence = pence;
    }

    public OldFashionedPound(int pounds, int shillings, int pence, int rem) {
        this.pounds = pounds;
        this.shillings = shillings;
        this.pence = pence;
        this.rem = rem;
    }
}
