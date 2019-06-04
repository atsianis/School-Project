
package individual.project;

import java.time.LocalDate;

public class Assignment {
    private String title;
    private String description;
    private LocalDate subDateTime;
    private double oralMark;
    private double totalMark;

    public Assignment() {
    }

    public Assignment(String title, String description, LocalDate subDateTime, double oralMark, double totalMark) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }
    
    
    
    private Course course;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDate subDateTime) {
        this.subDateTime = subDateTime;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }
    
    public void setCourse(Course course){
        this.course=course;
    }
    
    public void printCourse(){
        System.out.println("The assignment "+this.title+" belongs to this course:");
        System.out.println(this.course);
    }
    
//    public void setAll(Scanner s, int assignmentCounter){
//        System.out.println("Insert the name of the assignment #" + assignmentCounter);
//        this.setTitle(s.nextLine());
//        System.out.println("Insert the description of the assignment #" + assignmentCounter);
//        this.setDescription(s.nextLine());
//
//        System.out.println("Start Date of assignment #" + assignmentCounter + " ?");
//        System.out.println("Please insert the date in the following form:");
//        System.out.println("MONTH(space)DD,(space)YYYY");
//        String input = s.nextLine();
//        Date subDate = format.parse(input);
//        System.out.println("You have set the start date at: " + subDate);
//        this.setSubDateTime(subDate);
//
//        System.out.println("What's the oral mark of assignment #" + assignmentCounter + " ?");
//        this.setOralMark(getPositiveNumber(s));
//
//        System.out.println("What's the total mark of assignment #" + assignmentCounter + " ?");
//        this.setTotalMark(getPositiveNumber(s));
//    }
    
    
    
}
