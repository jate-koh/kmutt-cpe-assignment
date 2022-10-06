package cpe327.Lab8.source;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator cal;

    @Before
    public void setUp() {
        cal = new Calculator();
    }

    @Test
    public void constructorTest() {
        /* Test Blank Constructor */
        cal = new Calculator();
        assertEquals("","0",String.valueOf(cal.getOperand1()));
        assertEquals("","0",String.valueOf(cal.getOperand2()));
        assertEquals("","0",String.valueOf(cal.calculate()));

        /* Test String Constructor */
        cal = new Calculator("2","3","+");
        assertEquals("","2",String.valueOf(cal.getOperand1()));
        assertEquals("","3",String.valueOf(cal.getOperand1()));
        assertEquals("","5",String.valueOf(cal.calculate()));

        cal = new Calculator(5,7,"-");
        assertEquals("","5",String.valueOf(cal.getOperand1()));
        assertEquals("","7",String.valueOf(cal.getOperand1()));
        assertEquals("","-2",String.valueOf(cal.calculate()));
    }

    public void accuracyTest() {
        cal = new Calculator();
    }

    public void errorTest() {

    }

}
