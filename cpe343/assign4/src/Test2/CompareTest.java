package src.Test2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import src.*;

public class CompareTest {
    private Square sq1,sq2;
    private ComparableCircle compcir1,compcir2;
    private Rectangle rec1,rec2;
    private Circle cir1,cir2;
    private Triangle tri1,tri2;

    @Before
    public void setUp() {
        //Square Instances
        sq1 = new Square();
        sq2 = new Square();

        //ComparableCircle Instances
        compcir1 = new ComparableCircle();
        compcir2 = new ComparableCircle();

        //Rectangle Instances
        rec1 = new Rectangle();
        rec2 = new Rectangle();
    }

    /* Colorable Interface Test */
    @Test
    public void howToColorTest() {
        sq1 = new Square();
        assertEquals("Color all four sides", sq1.howToColor());
    }

    /* Comparab;e Circle */
    @Test
    public void negativeCompare() {
        compcir1 = new ComparableCircle(5.0);
        compcir2 = new ComparableCircle(2.0);
        assertEquals(-1,compcir1.compareTo(compcir2));
    }

    @Test
    public void positiveCompare() {
        compcir1 = new ComparableCircle(1.0);
        compcir2 = new ComparableCircle(2.0);
        assertEquals(1,compcir1.compareTo(compcir2));
    }

    @Test
    public void equalCompare() {
        compcir1 = new ComparableCircle(2.0);
        compcir2 = new ComparableCircle(2.0);
        assertEquals(0,compcir1.compareTo(compcir2));
    }

    /* Comparable in Rectangle */
    @Test
    public void equalTest() {
        rec1 = new Rectangle(3,6);
        rec2 = new Rectangle(9,2);
        assertEquals(0,rec1.compareTo(rec2));
    }

    @Test
    public void unequalTest() {
        rec1 = new Rectangle(3,6);
        rec2 = new Rectangle(3,2);
        assertEquals(-1,rec1.compareTo(rec2));
        assertEquals(1,rec2.compareTo(rec1));
    }

    /* max() in GeometricObjects' Instanes */
    @Test
    public void maxRectangle() {
        rec1 = new Rectangle(4,7);
        rec2 = new Rectangle(5,6);
        assertEquals(rec2.getUniqueId(),GeometricObject.max(rec1,rec2));
    }
    
    @Test
    public void maxTriangle() {
        tri1 = new Triangle(3,4,5);
        tri2 = new Triangle(7,12,13);
        assertEquals(tri2.getUniqueId(),GeometricObject.max(tri1,tri2));
    }

    @Test
    public void maxSquare() {
        sq1 = new Square(4);
        sq2 = new Square(8);
        assertEquals(sq2.getUniqueId(),GeometricObject.max(sq1,sq2));
    }

    @Test
    public void maxCircle() {
        cir1 = new Circle(4);
        cir2 = new Circle(5);
        assertEquals(cir2.getUniqueId(),GeometricObject.max(cir1,cir2));
    }

    @Test
    public void maxDifferentShape() {
        cir1 = new Circle(6);
        sq1 = new Square(4);
        assertEquals(null,GeometricObject.max(cir1,sq1));
    }
}
