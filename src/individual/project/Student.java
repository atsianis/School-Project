
package individual.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int tuitionFees;
    
    private List<Assignment> assignments = new ArrayList(); // πρεπει να φταξω getter/settet
    private List<Course> courses = new ArrayList();

    public Student() {
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, int tuitionFees) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }
    
    public void addCourse(Course course){ //Adds course !!!AND!!! its assignment to student
        this.courses.add(course);
        for (int i=0;i<course.getAssignments().size();i++){
            this.assignments.add(course.getAssignments().get(i));
        }
    }
    
    
    public void addAssignment(Assignment assignment){ //In case you want to give an individual assignment
        this.assignments.add(assignment);
    }
    
    public void printAssignments(){
        System.out.println("The student "+this.firstName+" "+this.lastName+ " has the following assignments: ");
        for(Assignment a:this.assignments){
            System.out.println(""+((this.assignments.indexOf(a)+1))+". "+a.getTitle()+" Deadline: "+a.getSubDateTime());
        }
    }
    
//    public void setAll(Scanner s, int studentCounter) throws ParseException {
//        System.out.println("Give the first name of student #" + studentCounter);
//        this.setFirstName(s.nextLine());
//        System.out.println("Give the last name of student #" + studentCounter);
//        this.setLastName(s.nextLine());
//
//        System.out.println("Date of birth of student #" + studentCounter + " ?");
//        System.out.println("Please insert the date in the following form:");
//        System.out.println("MONTH(space)DD,(space)YYYY");
//        String input = s.nextLine();
//        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
//        Date birthday = format.parse(input);
//        System.out.println("You have set the date of birth at: " + birthday);
//        this.setDateOfBirth(birthday);
//
//        System.out.println("Insert the tuition fees of student #" + studentCounter);
//        this.setTuitionFees(getPositiveNumber(s));
//    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Course> getCourses() {
        return courses;
    }
    
    
    
    
}
