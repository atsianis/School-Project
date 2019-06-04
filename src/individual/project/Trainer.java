
package individual.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trainer {
    private String firstName;
    private String lastName;

    public Trainer() {
    }

    public Trainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    
    
    private List<Course> courses = new ArrayList() ;  /// Καθε trainer εχει μια λιστα με τα Courses του   

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
    
    public void addCourse(Course course){
        this.courses.add(course);
    }
    
    public void printCourses(){
        System.out.println("Trainer "+this.firstName+" "+this.lastName+" teaches these courses: ");
        for (int i=0;i<this.courses.size();i++){
            System.out.println(""+(i+1)+". "+this.courses.get(i));
        }
    }
    
//    public void setAll(Scanner s,int trainerCounter){
//        System.out.println("Type the first name of the teacher #" + trainerCounter);
//        this.setFirstName(s.nextLine());
//        System.out.println("Type the last name of the teacher #" + trainerCounter);
//        this.setLastName(s.nextLine());
//    }
//    
    
}
