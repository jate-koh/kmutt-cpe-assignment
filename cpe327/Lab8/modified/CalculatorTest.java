package cpe327.Lab8.modified;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator cal;
    private double Tolerance = 0.0000;

    @Before
    public void setUp() {
        cal = new Calculator();
        System.out.println("Inditialize calculator");
    }

    @Test
    public void getOperand() {
        /* Test getOperand */
        assertEquals(0.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test getOperand1_pass");
        assertEquals(0.0000, cal.getOperand2(), Tolerance);
        System.out.println("Test getOperand2_pass");
    }

    @Test
    public void setOperand_Input_String_OK() {

        cal.setOperand1("5.00");
        assertEquals(5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_String_OK");
    }

    @Test
    public void setOperand_Input_Integer_OK() {

        cal.setOperand1(5);
        assertEquals(5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_Integer_OK");
    }

    @Test
    public void setOperand_Input_Positive_String_OK() {

        cal.setOperand1("+5.00");
        assertEquals(5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_Positive_String_OK");
    }

    @Test(expected = NumberFormatException.class)
    public void setOperand_Input_Multiply_Sign_String() {

        cal.setOperand1("*5.00");
        assertEquals(5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_Positive_String_OK");
    }

    @Test(expected = NumberFormatException.class)
    public void setOperand_Input_Divide_Sign_String() {

        cal.setOperand1("/5.00");
        assertEquals(5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_Positive_String_OK");
    }

    @Test
    public void setOperand_Input_Negative_String_OK() {

        cal.setOperand1("-5.00");
        assertEquals(-5.0000, cal.getOperand1(), Tolerance);
        System.out.println("Test setOperand_Input_Negative_String_OK");
    }

    @Test(expected = Exception.class) // คาดหวังว่า code จะ error เลย
    public void setOperand_Wrong_Input_Sign_Behind_String() {

        cal.setOperand1("5.00+");
        fail("Wrong input cant continue run");

    }
    /*
     * @Test(expected = Exception.class) // คาดหวังว่า code จะ error เลย
     * public void setOperand_Wrong_Input_Negative_String() {
     * 
     * 
     * cal.setOperand1("5.00-");
     * fail("Wrong input cant continue run");
     * 
     * }
     */

    @Test(expected = Exception.class) // คาดหวังว่า code จะ error เลย
    public void setOperand_Wrong_Type_Input_String() {
        /* Test getOperand */

        cal.setOperand1("a5.00");
        fail("Wrong input cant continue run");

    }

    @Test
    public void resetError() {
        cal.resetError();
        assertFalse(cal.getError());
    }

    @Test
    public void blankConstruction() {
        /* Test blank Constructor */

        assertEquals(0.0, cal.getOperand1(), Tolerance);
        assertEquals(0.0, cal.getOperand2(), Tolerance);
        assertNull(cal.getOperator());

    }

    @Test
    public void valueStringConstruction() {
        /* Test input String value Constructor expected double variable */
        cal = new Calculator("5.0", "4.0", "+");
        assertEquals(5.0, cal.getOperand1(), Tolerance);
        assertEquals(4.0, cal.getOperand2(), Tolerance);
        assertEquals("+", cal.getOperator());

    }

    /*
     * @Test
     * public void getOperandTest() {
     * NumberFormatException error = new NumberFormatException();
     * 
     * // 2 2.0
     * 
     * cal.setOperand1("2");
     * assertEquals(2.0, cal.getOperand1(), Tolerance);
     * cal.setOperand1("2.50");
     * assertEquals(2.50, cal.getOperand1(), 0.00);
     * cal.setOperand1("+2.0");
     * assertEquals(2.0, cal.getOperand1(), 0.0);
     * cal.setOperand1("-2.0");
     * assertEquals(-2.0, cal.getOperand1(), 0.0);
     * cal.setOperand1("+2.50");
     * assertEquals(2.50, cal.getOperand1(), 0.00);
     * cal.setOperand1("2.50+");
     * assertEquals(error, cal.getOperand1());
     * cal.setOperand1("2.50-");
     * assertEquals(error, cal.getOperand1());
     * 
     * }
     */
    @Test
    public void TestDoubleContruction() {
        cal.setOperand1(2.0);
        assertEquals(2.0, cal.getOperand1(), 0.0);
        cal.setOperand1(2.50);
        assertEquals(2.50, cal.getOperand1(), 0.00);
        cal.setOperand1(-2.0);
        assertEquals(-2.0, cal.getOperand1(), 0.0);
        cal.setOperand1(-2.50);
        assertEquals(-2.50, cal.getOperand1(), 0.00);

    }

    @Test
    public void Plus_Both_Positive() {
        cal = new Calculator("2.5", "3.2", "+");
        assertEquals(5.7, cal.calculate(), Tolerance);

    }

    @Test
    public void Plus_Positve_Negative() {
        cal = new Calculator("2.5", "-3.2", "+");
        assertEquals(-0.7, cal.calculate(), 0.00);

    }

    @Test
    public void Plus_Negative_Positve() {
        cal = new Calculator("-2.5", "3.2", "+");
        assertEquals(0.7, cal.calculate(), Tolerance);

    }

    @Test
    public void Plus_Both_Negative() {
        cal = new Calculator("-2.5", "-3.2", "+");
        assertEquals(-5.7, cal.calculate(), Tolerance);

    }

    // ++ +- -+ --
    @Test
    public void Multiply_Both_Positive() {
        cal = new Calculator("2.5", "3.2", "*");
        assertEquals(8.00, cal.calculate(), Tolerance);

    }

    @Test
    public void Multiply_Positve_Negative() {
        cal = new Calculator("2.5", "-3.2", "*");
        assertEquals(-8.00, cal.calculate(), Tolerance);
    }

    @Test
    public void Multiply_Negative_Positve() {
        cal = new Calculator("-2.5", "3.2", "*");
        assertEquals(-8.00, cal.calculate(), Tolerance);
    }

    @Test
    public void Multiply_Both_Negative() {
        cal = new Calculator("-2.5", "-3.2", "*");
        assertEquals(8.00, cal.calculate(), Tolerance);

    }

    @Test
    public void Minus_Both_Positive() {
        cal = new Calculator("2.5", "3.2", "-");
        assertEquals(-0.7, cal.calculate(), Tolerance);

    }

    @Test
    public void Minus_Positve_Negative() {
        cal = new Calculator("2.5", "-3.2", "-");
        assertEquals(5.7, cal.calculate(), Tolerance);
    }

    @Test
    public void Minus_Negative_Positve() {
        cal = new Calculator("-2.5", "3.2", "-");
        assertEquals(-5.7, cal.calculate(), Tolerance);
    }

    @Test
    public void Minus_Both_Negative() {
        cal = new Calculator("-2.5", "-3.2", "-");
        assertEquals(0.7, cal.calculate(), Tolerance);

    }

    @Test
    public void divide_Both_Positive() {
        cal = new Calculator("2.5", "3.2", "/");
        assertEquals(0.78125, cal.calculate(), Tolerance);

    }

    @Test
    public void divide_Positve_Negative() {
        cal = new Calculator("2.5", "-3.2", "/");
        assertEquals(-0.78125, cal.calculate(), Tolerance);
    }

    @Test
    public void divide_Negative_Positve() {
        cal = new Calculator("-2.5", "3.2", "/");
        assertEquals(-0.78125, cal.calculate(), Tolerance);
    }

    @Test
    public void divide_Both_Negative() {
        cal = new Calculator("-2.5", "-3.2", "/");
        assertEquals(0.78125, cal.calculate(), Tolerance);

    }

    @Test
    public void divide_By_Zero() {
        cal = new Calculator("-2.5", "0", "/");
        /*
         * Mean calculate method detect the operand2 is zero. So method return 0 for
         * is errors.
         */
        assertEquals(0, cal.calculate(), Tolerance);

    }

    @Test
    public void divide_Zero_by_Anynumber() {
        cal = new Calculator("0", "2.5", "/");

        assertEquals(0, cal.calculate(), Tolerance);

    }

    @After
    public void clear() {
        System.out.println("clear everything");
    }

}
