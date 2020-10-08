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

    public OldFashionedPound sum(OldFashionedPound arg1, OldFashionedPound arg2){
        return depencify(pencify(arg1) + pencify(arg2));
    }

    public OldFashionedPound sub(OldFashionedPound arg1, OldFashionedPound arg2){
        return depencify(pencify(arg1) - pencify(arg2));
    }

    public OldFashionedPound mult(OldFashionedPound arg, int factor){
        return depencify(pencify(arg) * factor);
    }

    public OldFashionedPound div(OldFashionedPound arg, int divizor){
        OldFashionedPound result = depencify(pencify(arg) / divizor);
        result.setRem(pencify(arg) % divizor);
        return result;
    }

    public String print(OldFashionedPound pound){
        boolean negative = pound.getPounds() < 0 || pound.getShillings() < 0 || pound.getPence() < 0;
        return (negative ? "-" : "") +
                print(pound, true) +
                printRem(pound);
    }

    private String printRem(OldFashionedPound pound){
        if (pound.getRem() > 0){
            OldFashionedPound rem = depencify(pound.getRem());
            return  " (" + print(rem, false) + ")";
        }
        else{
            return "";
        }
    }

    private String print (OldFashionedPound pound, boolean printZero){
        return  (pound.getPounds() > 0 || printZero ? Math.abs(pound.getPounds()) + "p" : "") +
                ((pound.getPounds() > 0 && pound.getShillings() > 0) || printZero ? " " : "") +
                (pound.getShillings() > 0  || printZero ? Math.abs(pound.getShillings()) + "s" : "") +
                ((pound.getShillings() > 0 && pound.getPence() > 0) || printZero  ? " " : "") +
                (pound.getPence() > 0 || printZero ? Math.abs(pound.getPence()) + "d" : "");
    }

    private int pencify(OldFashionedPound pound){
        int p;
        p = pound.getPence() + pound.getShillings() * 12 + pound.getPounds() * 240;
        return p;
    }

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
