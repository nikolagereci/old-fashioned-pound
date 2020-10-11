package hr.ngereci.oldfashionedpound.calc;

import hr.ngereci.oldfashionedpound.model.OldFashionedPound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFashionedPoundCalcImpl implements OldFashionedPoundCalc {

    private final String SUM_SIGN = "+";
    private final String SUB_SIGN = "-";
    private final String MULT_SIGN = "*";
    private final String DIV_SIGN = "/";

    private final String ARG_REGEX = "^(\\d+)p (\\d+)s (\\d+)d$";
    private final String EXPRESSION_REGEX = "^(\\d+p \\d+s \\d+d) ([-+*\\/]) ((?:\\d+p \\d+s \\d+d)|(?:\\d+))$";
    private final String MULT_DIV_REGEX = "\\d+";

    /**
     * Implements the 4 arithmetic operations (sum, subtraction, * multiplication and division) for pre-1970 UK prices.
     * Under the old money system of UK, before 1970, there were 12 pence in a shilling and 20 shillings, or 240
     * pence, in a pound. Thus, a price in the OldUK Money system was expressed in Pounds, Shillings and
     * Pence. For example "12p 6s 10d" is 12 Pounds, 6 Shillings and 10 Pence.
     * Sum and Subtraction should add or subtract two prices respectively.
     * Example SUM: 5p 17s 8d + 3p 4s 10d= 9p 2s 6d
     * Example SUBTRACTION: 5p 17s 8d - 3p 4s 10d= 2p 12s 10d
     * Subtractions giving negative results are managed.
     * Multiplication and division will multiply or divide a price by an integer.
     * Example MULTIPLICATION: 5p 17s 8d * 2 = 11p 15 s 4d
     * Example DIVISION 5p 17s 8d / 3 = 1p 19s 2d (2p)
     * Example DIVISION 18p 16s 1d / 15 = 1p 5s 0d (1s 1d) (1 Shillings and 1 penny as remainder)
     * The library receives and produces strings in the format "Xp Ys Zd", as in the examples
     * @param expression Input String containing the calculation expression. See examples for syntax.
     * @return Output String containing the calculation result
     * @throws IllegalArgumentException Thrown on expression parse errors, indicates expression should be fixed
     */
    public String calc(String expression) throws IllegalArgumentException {
        Pattern expPattern = Pattern.compile(EXPRESSION_REGEX);
        Matcher expMatcher = expPattern.matcher(expression);
        if (expMatcher.matches()){

        }else{
            throw new IllegalArgumentException(expression + " not parsable");
        }
        String strArg1 = expMatcher.group(1);
        String operator = expMatcher.group(2);
        String strArg2 = expMatcher.group(3);
        Pattern argPattern = Pattern.compile(ARG_REGEX);
        Matcher argMatcher = argPattern.matcher(strArg1);
        argMatcher.matches();
        OldFashionedPound arg1 = new OldFashionedPound(
                Integer.parseInt(argMatcher.group(1)),
                Integer.parseInt(argMatcher.group(2)),
                Integer.parseInt(argMatcher.group(3)));
        switch (operator){
            case SUM_SIGN:
            case SUB_SIGN:
                argMatcher = argPattern.matcher(strArg2);
                argMatcher.matches();
                OldFashionedPound arg2 = new OldFashionedPound(
                        Integer.parseInt(argMatcher.group(1)),
                        Integer.parseInt(argMatcher.group(2)),
                        Integer.parseInt(argMatcher.group(3)));
                if (operator.equalsIgnoreCase(SUM_SIGN)){
                    return print(sum(arg1, arg2));
                }else{
                    return print(sub(arg1, arg2));
                }
            case MULT_SIGN:
            case DIV_SIGN:
                argPattern = Pattern.compile(MULT_DIV_REGEX);
                argMatcher = argPattern.matcher(strArg2);
                if(argMatcher.matches()){
                    int argMultDiv = Integer.parseInt(strArg2);
                    if (operator.equalsIgnoreCase(MULT_SIGN)){
                        return print(mult(arg1, argMultDiv));
                    }else{
                        return print(div(arg1, argMultDiv));
                    }
                }else{
                    throw new IllegalArgumentException("Argument" + strArg2 + " illegal for operation");
                }
        }
        return null;
    }

    private OldFashionedPound sum(OldFashionedPound arg1, OldFashionedPound arg2){
        return depencify(pencify(arg1) + pencify(arg2));
    }

    private OldFashionedPound sub(OldFashionedPound arg1, OldFashionedPound arg2){
        return depencify(pencify(arg1) - pencify(arg2));
    }

    private OldFashionedPound mult(OldFashionedPound arg, int factor){
        return depencify(pencify(arg) * factor);
    }

    private OldFashionedPound div(OldFashionedPound arg, int divizor){
        OldFashionedPound result = depencify(pencify(arg) / divizor);
        result.setRem(pencify(arg) % divizor);
        return result;
    }

    /**
     * Prints out an {@link OldFashionedPound} in "Xp Ys Zd" format. Remainders and negative amounts are handled.
     * @param pound
     * @return
     */
    private String print(OldFashionedPound pound){
        boolean negative = pound.getPounds() < 0 || pound.getShillings() < 0 || pound.getPence() < 0;
        return (negative ? "-" : "") +
                print(pound, true) +
                printRem(pound);
    }

    /**
     * Prints out only the remainder from an {@link OldFashionedPound} (in case there's any).
     * @param pound
     * @return
     */
    private String printRem(OldFashionedPound pound){
        if (pound.getRem() > 0){
            OldFashionedPound rem = depencify(pound.getRem());
            return  " (" + print(rem, false) + ")";
        }
        else{
            return "";
        }
    }

    /**
     * Prints out an {@link OldFashionedPound} in "Xp Ys Zd" format.
     * Remainders and negative results are not handled. Use print(OldFashionedPound pound) for that instead.
     * @param pound The {@link OldFashionedPound} that should be printed out
     * @param printZero Should be true when printing out regular results, and false when printing remainders
     * @return Result string in "Xp Ys Zd" format
     */
    private String print (OldFashionedPound pound, boolean printZero){
        return  (pound.getPounds() > 0 || printZero ? Math.abs(pound.getPounds()) + "p" : "") +
                ((pound.getPounds() > 0 && pound.getShillings() > 0) || printZero ? " " : "") +
                (pound.getShillings() > 0  || printZero ? Math.abs(pound.getShillings()) + "s" : "") +
                ((pound.getShillings() > 0 && pound.getPence() > 0) || printZero  ? " " : "") +
                (pound.getPence() > 0 || printZero ? Math.abs(pound.getPence()) + "d" : "");
    }

    /**
     * Calculates the amount of pence in {@link OldFashionedPound}
     * @param pound
     * @return int the amount of pence
     */
    private int pencify(OldFashionedPound pound){
        int p;
        p = pound.getPence() + pound.getShillings() * 12 + pound.getPounds() * 240;
        return p;
    }

    /**
     * Turns an int representing pence into {@link OldFashionedPound}
     * @param pence
     * @return
     */
    private OldFashionedPound depencify(int pence){
        int p; //pounds
        int s; //shillings
        int d; //pence
        p = pence / 240;
        s = pence % 240 / 12;
        d = pence % 240 % 12;
        return new OldFashionedPound(p, s, d);
    }
}
