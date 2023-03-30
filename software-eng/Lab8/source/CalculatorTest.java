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
        assertEquals("0.0",String.valueOf(cal.getOperand1()));
        assertEquals("0.0",String.valueOf(cal.getOperand2()));
        assertEquals("0.0",String.valueOf(cal.calculate()));
        assertEquals(true, cal.getError());

        /* Test String Constructor */
        cal = new Calculator("2","3","+");
        assertEquals("2.0",String.valueOf(cal.getOperand1()));
        assertEquals("3.0",String.valueOf(cal.getOperand2()));
        assertEquals("5.0",String.valueOf(cal.calculate()));

        cal = new Calculator(5,7,"-");
        assertEquals("5.0",String.valueOf(cal.getOperand1()));
        assertEquals("7.0",String.valueOf(cal.getOperand2()));
        assertEquals("-2.0",String.valueOf(cal.calculate()));
    }

    @Test
    public void accuracyTest() {

        double max = 20.0; // Specify Maximum Test Case Here

        /* Test Plus Sign */
        cal = new Calculator(0,0,"+");
        for(double i = 0.0; i < max ; i++) {
            cal.setOperand1(i);
            for(double j = 0.0; j < max; j++) {
                cal.setOperand2(j);
                assertEquals((String.valueOf(i+j)), String.valueOf((cal.calculate())));
            }
        }

        /* Test Minus Sign */
        cal = new Calculator(0,0,"-");
        for(double i = 0.0; i < max ; i++) {
            cal.setOperand1(i);
            for(double j = 0.0; j < max; j++) {
                cal.setOperand2(j);
                assertEquals((String.valueOf(i-j)), String.valueOf((cal.calculate())));
            }
        }

        /* Test Multiply */
        cal = new Calculator(0,0,"*");
        for(double i = 0.0; i < max ; i++) {
            cal.setOperand1(i);
            for(double j = 0.0; j < max; j++) {
                cal.setOperand2(j);
                assertEquals((String.valueOf(i*j)), String.valueOf((cal.calculate())));
            }
        }

        cal = new Calculator(0,0,"/");
        for(double i = 1.0; i < max ; i++) {
            cal.setOperand1(i);
            for(double j = 1.0; j < max; j++) {
                cal.setOperand2(j);
                assertEquals((String.valueOf(i/j)), String.valueOf((cal.calculate())));
            }
        }
    }
    
    @Test
    public void errorTest() {
        cal = new Calculator(5,0,"/");
        cal.calculate();
        assertEquals(true, cal.getError());
    }

}
