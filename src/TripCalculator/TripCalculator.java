/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TripCalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This program is used to calculate the expenses for a group of students who 
 * like to go on a road trip.
 * 
 * Three LinkedList objects are used:
 *      studentList - keeps a list of all the student objects.
 *      underspendList - keeps a list of students that paid less than the 
 *                      average share.
 *      overspendList - keeps a list of students that paid more than the 
 *                      average share.
 * 
 * @author Ali Milaninia  Dec 26, 2017
 */
public class TripCalculator {

    List<Student> studentList = new LinkedList<>();
    List<Student> underspendList = new LinkedList<>();
    List<Student> overspendList = new LinkedList<>();

    double individualExpense = 0;
    
    /**
     * Constructor method for the class.
     * 
     * @param studentList list of students to be processed
     */
    public TripCalculator(List<Student> studentList) {
        this.studentList = studentList;
    }
    
    /**
     * Processes the expenses of the students and calculates the average share,
     * and the amounts that need to be exchanged between students, if any.
     * 
     * @return the result string to be displayed to the user.
     */
    public String processStudentExpenses() {
        
        individualExpense = calculatePerShare();
        setupUnderspendOverspendLists();
        
        return resolveExpenses();
    }
    
    
    /**
     * Calculates the equal share that all students must each pay. That is the 
     * total expenses divided by the number of students, rounded down to the
     * nearest cent.
     * 
     * @return Per student share
     */
    private double calculatePerShare() {
        double totalExpensesCent = 0;
        
        // Calculate the total expenses in cents.
        for (Student each : studentList) {
            totalExpensesCent = totalExpensesCent + (each.getSpend() * 100);
        }
        
        // Value of each share in cents, rounded down to the nearest cent.
        double perShareExpenseCent = Math.floor(totalExpensesCent / studentList.size());

        // value of each share in dollars and cents.
        double perShareExpense = perShareExpenseCent / 100;
                
        return perShareExpense;
    }
    
    /**
     * Generates the list of students that have underspent 
     * and the list of students that have overspent.
     * The lists are then sorted to minimize the number of exchanges.
     * 
     * @return none
     */
    private void setupUnderspendOverspendLists() {
        for (Student each : studentList) {
            if (each.getSpend() < individualExpense) {
                underspendList.add(each);
            } else if (each.getSpend() > individualExpense) {
                overspendList.add(each);
            }
        }
        
        // Sort the 2 lists to minimize number of exchanges.
        
        // Sort underspendList in ascending order, 
        // starting from the student who spent the least, and therefore owes the most.
        Collections.sort(underspendList);
        
        // Sort overspendList in descending order, 
        // starting from the student who spent the most, and therefore is owed the most.
        Collections.sort(overspendList);
        Collections.reverse(overspendList);
    }
    
    /**
     * Resolves how much each student owes other students, if any.
     * Generates the output indicating the exchanges to take place.
     * 
     * @return Formatted results string
     */
    private String resolveExpenses() {
        double mustPay = 0;
        StringBuilder results = new StringBuilder();
        
        for (Student under : underspendList) {
            double owes = individualExpense - under.getSpend();
            int index = -1;
            for (Student over : overspendList) {
                double overpaid = over.getSpend() - individualExpense;
                boolean removeFlag = false;
                if (owes < overpaid) {
                    // Under student owes no more.
                    // Over student is not payed off yet.
                    mustPay = owes;
                    over.setSpend(over.getSpend() - mustPay);
                    results.append(String.format("%s owes %s $%.2f\n", under.getName(), over.getName(), mustPay));
                    break;
                } else if (owes > overpaid) {
                    // Paying off the over student
                    mustPay = overpaid;
                    owes -= mustPay;
                    results.append(String.format("%s owes %s $%.2f\n", under.getName(), over.getName(), mustPay));
                    index++;
                } else {
                    // Pay off the over student.
                    // Under student owes no more.
                    mustPay = overpaid;
                    results.append(String.format("%s owes %s $%.2f\n", under.getName(), over.getName(), mustPay));
                    index++;                  
                    break;
                }                                                
            }
            
            // If index is not -1, it means all over students up to index 
            // have been paid off.
            // we will remove all 'over' students up to index.
            if (index > -1) {
                overspendList.subList(0, index+1).clear();
            }
            
        }
        
        return results.toString();
    }
        
}
