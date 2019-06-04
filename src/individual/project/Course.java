
package individual.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
     private String title;
     private String stream;
     private String type;
     private LocalDate startDate;
     private LocalDate endDate;
     
     
     private List<Student> students = new ArrayList(); /// Κάθε Course έχει μια λίστα με τους students που το παρακολουθουν
     private List<Trainer> trainers = new ArrayList(); /// αντίστοιχα... /// getters / setters
     private List<Assignment> assignments = new ArrayList();

    public Course() {
    }

    public Course(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
     
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void addStudent(Student student){
        this.students.add(student);
    }
     
    public void addTrainer(Trainer trainer){
        this.trainers.add(trainer);
    }
     
    public void addAssignment(Assignment assignment){
        this.assignments.add(assignment);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }
    
    
     
    public void printStudents(){
        System.out.println("The following students attend the course: "+this.title);
        for(int i=0;i<this.students.size();i++){
            System.out.println(""+(i+1)+". "+this.students.get(i).getFirstName()+this.students.get(i).getLastName()+"\n");   
        }
    }
    
    public void printTrainers(){
        System.out.println("The following trainers teach the course: "+this.title);
        for (int i=0;i<this.trainers.size();i++){
            System.out.println(""+(i+1)+". "+this.trainers.get(i).getFirstName()+" "+this.trainers.get(i).getLastName());
        }
    }
    
    public void printAssignments(){
        System.out.println("The course "+this.title+" contains the following assignments: ");
        for(Assignment a:this.assignments){
            System.out.println(""+((this.assignments.indexOf(a)+1))+". "+a.getTitle()+" Deadline: "+a.getSubDateTime());
        }
    }
    
    public List<Assignment> getAssignments(){
        return this.assignments;
    }
    
//    public void setAll(Scanner s, int courseCounter) throws ParseException {
//        System.out.println("Name course #" + courseCounter);
//        String name = s.nextLine();
//        System.out.println("You have named the course: " + name);
//        this.setTitle(name);
//
//        System.out.println("Stream of course #" + courseCounter);
//        String mystream = s.nextLine();
//        System.out.println("You given the stream: " + mystream);
//        this.setStream(mystream);
//
//        System.out.println("Type of course #" + courseCounter);
//        String mytype = s.nextLine();
//        System.out.println("You given the course the " + mytype);
//        this.setType(mytype);
//
//        //Remember to search-fix what happens in case of wrong date input
//        System.out.println("Start Date of course #" + courseCounter + " ?");
//        System.out.println("Please insert the date in the following form:");
//        System.out.println("MONTH(space)DD,(space)YYYY");
//        String input = s.nextLine();
//        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
//        Date mystartDate = format.parse(input);
//        System.out.println("You have set the start date at: " + mystartDate);
//        this.setStartDate(mystartDate);
//
//        System.out.println("End Date of course #" + courseCounter + " ?");
//        System.out.println("Please insert the date in the following form:");
//        System.out.println("MONTH(space)DD,(space)YYYY");
//        input = s.nextLine();
//        Date myendDate = format.parse(input);
//        System.out.println("You have set the end date at: " + myendDate);
//        this.setStartDate(myendDate);
//
//        
//    }
     
     
     
    
}
