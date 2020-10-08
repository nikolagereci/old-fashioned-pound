package hr.ngereci.oldfashionedpound.calc;

import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalc;
import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalcImpl;
import hr.ngereci.oldfashionedpound.model.OldFashionedPound;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class OldFashionedPoundCalcImplTest {

    private OldFashionedPoundCalc calc;

    @Before
    public void before(){
        calc = new OldFashionedPoundCalcImpl();
    }

    @Test
    public void testSum() {
        OldFashionedPound arg1 = new OldFashionedPound(5,17 ,8);
        OldFashionedPound arg2 = new OldFashionedPound(3,4 ,10);
        OldFashionedPound result = calc.sum(arg1, arg2);
        assertEquals("9p 2s 6d", calc.print(result));
    }

    @Test
    public void testSub() {
        OldFashionedPound arg1 = new OldFashionedPound(5,17 ,8);
        OldFashionedPound arg2 = new OldFashionedPound(3,4 ,10);
        OldFashionedPound result = calc.sub(arg1, arg2);
        assertEquals("2p 12s 10d", calc.print(result));
    }

    @Test
    public void testMult() {
        OldFashionedPound arg1 = new OldFashionedPound(5,17 ,8);
        OldFashionedPound result = calc.mult(arg1, 2);
        assertEquals("11p 15s 4d", calc.print(result));
    }

    @Test
    public void testDiv() {
        OldFashionedPound arg1 = new OldFashionedPound(5,17 ,8);
        OldFashionedPound result1 = calc.div(arg1, 3);
        assertEquals("1p 19s 2d (2d)", calc.print(result1));
        OldFashionedPound arg2 = new OldFashionedPound(18,16 ,1);
        OldFashionedPound result2 = calc.div(arg2, 15);
        assertEquals("1p 5s 0d (1s 1d)", calc.print(result2));
    }
    @Test
    public void testCalc() {
        assertEquals("9p 2s 6d", calc.calc("5p 17s 8d + 3p 4s 10d"));
        assertEquals("2p 12s 10d", calc.calc("5p 17s 8d - 3p 4s 10d"));
        assertEquals("-1p 12s 10d", calc.calc("5p 17s 8d - 6p 4s 10d"));
        assertEquals("11p 15s 4d", calc.calc("5p 17s 8d * 2"));
        assertEquals("1p 19s 2d (2d)", calc.calc("5p 17s 8d / 3"));
        assertEquals("1p 5s 0d (1s 1d)", calc.calc("18p 16s 1d / 15"));
    }
}