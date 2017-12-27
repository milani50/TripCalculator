package TripCalculator;

/**
 * Contains the information for each student: student's name and the amount spent.
 * Overrides CompareTo to allow sorting of the list of students.
 * 
 * @author Ali Milaninia  Dec 26, 2017
 */
    public class Student implements Comparable<Student> {
         private String name;
         private double spend;
         
         public Student(String name, double spend)
         {
             this.name = name;
             this.spend = spend;
         }
         
         public double getSpend() {
             return spend;
         }
         
         public void setSpend(double spend) {
             this.spend = spend;
         }
         
         public String getName() {
             return name;
         }
         
         public String toString()
         {
             return "Name: " + name + "\n" + "Spend: " + spend;
             
         }
         
         @Override
         public int compareTo(Student o) {
             return (int)(this.spend - o.spend);
         }
         
    }
