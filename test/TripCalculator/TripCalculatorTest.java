/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TripCalculator;

import java.util.LinkedList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ali
 */
public class TripCalculatorTest {
    List<Student> studentList = new LinkedList<>();

    public TripCalculatorTest() {
    }
    
    /**
     * Test of processStudentExpenses method, of class TripCalculator.
     */
    @Test
    public void testProcessStudentExpenses() {
        Student testStudent = new Student("David", 12);
        studentList.add(testStudent);
        
        testStudent = new Student("Louis", 25);
        studentList.add(testStudent);

        System.out.println("processStudentExpenses");
        TripCalculator instance = new TripCalculator(studentList);
        String expResult = "David owes Louis $6.50\n";
        String result = instance.processStudentExpenses();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calculatePerShare method, of class TripCalculator.
     */
    @Test
    public void testCalculatePerShare() {
        Student testStudent = new Student("David", 12);
        studentList.add(testStudent);
        
        testStudent = new Student("Louis", 25);
        studentList.add(testStudent);
        double totalExpensesCent = 0;
        
        // Calculate the total expenses in cents.
        for (Student each : studentList) {
            System.out.println("spend = " + each.getSpend());
            totalExpensesCent = totalExpensesCent + (each.getSpend() * 100);
        }
        
        // Value of each share in cents, rounded down to the nearest cent.
        double perShareExpenseCent = Math.floor(totalExpensesCent / studentList.size());

        // value of each share in dollars and cents.
        double result = perShareExpenseCent / 100;
        System.out.println("result = " + result);
        double expResult = 18.50;
        assertTrue(expResult==result);

    }

}
