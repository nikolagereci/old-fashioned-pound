package hr.ngereci.oldfashionedpound;

import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalc;
import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalcImpl;

public class OldFashionedPoundApp {
    public static void main(String[] args) {
        OldFashionedPoundCalc calc = new OldFashionedPoundCalcImpl();
        System.out.println(calc.calc(args[0]));
    }
}
