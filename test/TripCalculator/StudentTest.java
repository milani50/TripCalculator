package TripCalculator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ali
 */
public class StudentTest {
    
    public StudentTest() {
    }
    
    /**
     * Test of getSpend method, of class Student.
     */
    @Test
    public void testGetSpend() {
        System.out.println("getSpend");
        Student instance = new Student("David", 12);
        double expResult = 12.0;
        double result = instance.getSpend();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpend method, of class Student.
     */
    @Test
    public void testSetSpend() {
        System.out.println("setSpend");
        double spend = 25.0;
        Student instance = new Student("David", 12);
        instance.setSpend(spend);
        double result = instance.getSpend();
        assertEquals(spend, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Student.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Student instance = new Student("David", 12);
        String expResult = "David";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Student.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Student instance = new Student("David", 12);
        String expResult = "Name: David\n" +
"Spend: 12.0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Student.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Student o = new Student("Louis", 25);
        Student instance = new Student("David", 12);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertNotEquals(expResult, result);
    }
    
}
