package hr.ngereci.oldfashionedpound.calc;

import hr.ngereci.oldfashionedpound.model.OldFashionedPound;

public interface OldFashionedPoundCalc {
    OldFashionedPound sum(OldFashionedPound arg1, OldFashionedPound arg2);
    OldFashionedPound sub(OldFashionedPound arg1, OldFashionedPound arg2);
    OldFashionedPound mult(OldFashionedPound arg, int factor);
    OldFashionedPound div(OldFashionedPound arg, int divizor);
    String print(OldFashionedPound arg);
    String calc(String expression) throws IllegalArgumentException;
}
