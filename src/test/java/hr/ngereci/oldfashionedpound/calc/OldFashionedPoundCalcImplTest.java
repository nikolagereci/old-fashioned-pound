package hr.ngereci.oldfashionedpound.calc;

import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalc;
import hr.ngereci.oldfashionedpound.calc.OldFashionedPoundCalcImpl;
import hr.ngereci.oldfashionedpound.model.OldFashionedPound;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class OldFashionedPoundCalcImplTest {

    private OldFashionedPoundCalcImpl calc;

    @Before
    public void before(){
        calc = new OldFashionedPoundCalcImpl();
    }

    @Test
    public void testCalc() {
        assertEquals("9p 2s 6d", calc.calc("5p 17s 8d + 3p 4s 10d"));
        assertEquals("2p 12s 10d", calc.calc("5p 17s 8d - 3p 4s 10d"));
        assertEquals("-1p 0s 0d", calc.calc("5p 17s 12d - 6p 17s 12d"));
        assertEquals("-0p 1s 0d", calc.calc("5p 17s 12d - 5p 18s 12d"));
        assertEquals("-0p 0s 1d", calc.calc("5p 17s 12d - 5p 17s 13d"));
        assertEquals("0p 0s 0d", calc.calc("5p 17s 12d - 5p 17s 12d"));
        assertEquals("11p 15s 4d", calc.calc("5p 17s 8d * 2"));
        assertEquals("1p 19s 2d (2d)", calc.calc("5p 17s 8d / 3"));
        assertEquals("1p 5s 0d (1s 1d)", calc.calc("18p 16s 1d / 15"));
    }
}