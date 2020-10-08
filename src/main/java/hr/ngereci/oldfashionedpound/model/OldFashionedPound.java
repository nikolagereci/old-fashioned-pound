package hr.ngereci.oldfashionedpound.model;

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
