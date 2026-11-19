package studentgradetracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Student {

    int rollNo;
    String name;
    double marks;
  

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
       
    }

    void display() {
        System.out.println(rollNo + "\t" + name + "\t" + marks);
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();

    static BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

    // Add Student
    static void addStudent() throws Exception {

        System.out.print("Enter Roll Number: ");
        int roll = Integer.parseInt(br.readLine());

        System.out.print("Enter Student Name: ");
        String name = br.readLine();

        System.out.print("Enter Marks: ");
        double marks = Double.parseDouble(br.readLine());

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid Marks!");
            return;
        }

        students.add(new Student(roll, name, marks));

        System.out.println("Student Added Successfully!");
    }

    // Display Students
    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Found!");
            return;
        }

        System.out.println("\nROLL NO\tNAME\tMARKS");

        for (Student s : students) {
            s.display();
        }
    }

    // Calculate Results
    static void calculateResults() {

        if (students.isEmpty()) {
            System.out.println("No Records Available!");
            return;
        }

        double total = 0;
        double highest = students.get(0).marks;
        double lowest = students.get(0).marks;

        String topper = students.get(0).name;
        String lowestStudent = students.get(0).name;

        for (Student s : students) {

            total += s.marks;

            if (s.marks > highest) {
                highest = s.marks;
                topper = s.name;
            }

            if (s.marks < lowest) {
                lowest = s.marks;
                lowestStudent = s.name;
            }
        }

        double average = total / students.size();

        System.out.println("\n===== RESULT SUMMARY =====");

        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Topper: " + topper);
        System.out.println("Lowest Marks: " + lowest);
        System.out.println("Lowest Scorer: " + lowestStudent);
    }

    	//Display Student Report
    	static void displayCompleteReport() {

    	    if (students.isEmpty()) {
    	        System.out.println("No Student Records Found!");
    	        return;
    	    }

    	    double total = 0;
    	    double highest = students.get(0).marks;
    	    double lowest = students.get(0).marks;

    	    System.out.println("\n========== COMPLETE STUDENT REPORT ==========");

    	    System.out.printf("%-10s %-15s %-10s %-10s %-10s%n",
    	            "Roll No", "Name", "Marks", "Grade", "Result");

    	    System.out.println("----------------------------------------------------");

    	    for (Student s : students) {

    	        total += s.marks;

    	        if (s.marks > highest) {
    	            highest = s.marks;
    	        }

    	        if (s.marks < lowest) {
    	            lowest = s.marks;
    	        }

    	        String grade;
    	        String result;

    	        if (s.marks >= 90) {
    	            grade = "A+";
    	            result = "Pass";
    	        }
    	        else if (s.marks >= 80) {
    	            grade = "A";
    	            result = "Pass";
    	        }
    	        else if (s.marks >= 70) {
    	            grade = "B";
    	            result = "Pass";
    	        }
    	        else if (s.marks >= 60) {
    	            grade = "C";
    	            result = "Pass";
    	        }
    	        else if (s.marks >= 40) {
    	            grade = "D";
    	            result = "Pass";
    	        }
    	        else {
    	            grade = "F";
    	            result = "Fail";
    	        }

    	        System.out.printf("%-10d %-15s %-10.2f %-10s %-10s%n",
    	                s.rollNo, s.name, s.marks, grade, result);
    	    }

    	    double average = total / students.size();

    	    System.out.println("----------------------------------------------------");

    	    System.out.println("Total Students: " + students.size());
    	    System.out.println("Class Average: " + average);
    	    System.out.println("Highest Marks: " + highest);
    	    System.out.println("Lowest Marks: " + lowest);

    	    System.out.println("==============================================");

    }

    // Main Method
    public static void main(String[] args) throws Exception {

        while (true) {

            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Calculate Results");
            System.out.println("4. Display Complete Report");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice : ");

            int choice = Integer.parseInt(br.readLine());

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    calculateResults();
                    break;

                case 4:
                	displayCompleteReport();
                    System.out.println("Thank You!");
                    break;
                    
                case 5:
                	System.out.println("Thank You!");
                	return;
                

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
