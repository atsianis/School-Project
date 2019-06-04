package individual.project;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndividualProject {

    public static void main(String[] args) throws ParseException {

        List<Course> courses = new ArrayList();
        List<Student> students = new ArrayList();
        List<Trainer> trainers = new ArrayList();
        List<Assignment> assignments = new ArrayList();

        System.out.println("Welcome to MySchool application");
        System.out.println("Would you rather insert data or use synthetic?\nType 'I/i' for insertion or 'S/s' for synthetic");
        Scanner s = new Scanner(System.in);
        String answer = s.next();
        //Making sure answer is either I/i or S/s
        while ((!answer.toLowerCase().equals("i")) && (!answer.toLowerCase().equals("s"))) {
            System.out.println("Please choose between 'I' ans 'S'");
            answer = s.next();
        }
        s.nextLine();
        
        /*
        The following big if-else statement serves the purpose of filling in data to all
        the entities of the school, either manualy or automatically
         */
        
        if (answer.toLowerCase().equals("i")) {     
           
            /*
            This part is for manual insertion of data
            */
            
            System.out.println("You have chosen to insert data");
            System.out.println("First of all, you have to insert the names, dates of birth and tuition fees of all the students in the school");
            System.out.println("Secondly, you are going to have to create courses. For every course you will be asked to insert:");
            System.out.println("1. the title, stream, type, start/end dates");
            System.out.println("2. the names of the trainers that teach the course");
            System.out.println("3. the assignments of the course (for every assignment you will be asked to type in");
            System.out.println("   it's title,description, submission date and marks)");
            System.out.println("4. the students who attend the course");
            System.out.println(" ");

            /*
            The user now has to fill in the list with all the students of the school
             */
            boolean giveStudent = true;
            int studentCounter = 1;
            while (giveStudent) {
                Student st = new Student();

                System.out.println("Give the first name of student #" + studentCounter);
                st.setFirstName(isAlpha(s));
                
                System.out.println("Give the last name of student #" + studentCounter);
                st.setLastName(isAlpha(s));

                System.out.println("Date of birth of student #" + studentCounter + " ?");

                st.setDateOfBirth(getValidDate(s));

                System.out.println("Insert the tuition fees ($) of student #" + studentCounter);
                st.setTuitionFees(getPositiveNumber(s));
                
                System.out.println("");
                System.out.println("You have set the following info for student #"+studentCounter);
                System.out.println("Name: "+st.getFirstName()+" "+st.getLastName());
                System.out.println("Date of birth: "+st.getDateOfBirth());
                System.out.println("Tuition fees: $"+st.getTuitionFees());
                System.out.println("");

                students.add(st); // Adding the student to the list of all students

                studentCounter++;

                System.out.println("Do you want to add another student ?");
                System.out.println("y/n ?");
                giveStudent = checkFlag(s, giveStudent);
            }

            /*
            The list of all students in the school has been filled
            Now we create the trainers
             */
            boolean giveTrainer = true;
            int trainerCounter = 1;
            while (giveTrainer) {
                Trainer t = new Trainer();
                
//                t.setAll(s, trainerCounter);
                System.out.println("Type the first name of the teacher #" + trainerCounter);
                t.setFirstName(isAlpha(s));
                System.out.println("Type the last name of the teacher #" + trainerCounter);
                t.setLastName(isAlpha(s));

                trainers.add(t); // Adding the trainer to the list of all trainers

                trainerCounter++;
                
                System.out.println("");
                System.out.println("You have set the following info for traner #"+trainerCounter);
                System.out.println("Name: "+t.getFirstName()+" "+t.getLastName());
                System.out.println("");

                System.out.println("Do you want to add another trainer to the school?");
                System.out.println("y/n ?");
                giveTrainer = checkFlag(s, giveTrainer);

            }

            /*
            Now we have all the students and all the trainers in 2 separate lists
            
            In the following section we create the courses and link every course with:
            
            1. One or more trainers
            2. One or more assignments
            3. One or more students
            
            For (1) and (3) we show the user a list of all students/trainers and he/she chooses
            which of them to link with the course
            
             */
            boolean giveCourse = true;
            int courseCounter = 1;
            while (giveCourse) {
                Course c = new Course();

                System.out.println("Name course #" + courseCounter);
                String name = s.nextLine();
                System.out.println("You have named the course: " + name);
                c.setTitle(name);

                System.out.println("Stream of course #" + courseCounter);
                String stream = isAlpha(s);
                System.out.println("You given the stream: " + stream);
                c.setStream(stream);

                System.out.println("Type of course #" + courseCounter);
                String type = isAlpha(s);
                System.out.println("You given the course the " + type);
                c.setType(type);


                System.out.println("Start Date of course #" + courseCounter + " ?");

                c.setStartDate(getValidDate(s));

                System.out.println("End Date of course #" + courseCounter + " ?");

                c.setEndDate(getValidDate(s));
                
                while (( c.getEndDate().compareTo(c.getStartDate())) <=0 ) {
                    System.out.println("Sorry, can't set end-date earlier than start-date");
                    System.out.println("Set end-date again");
                    c.setEndDate(getValidDate(s));
                }
                        

                System.out.println("\nThe course information have been inserted ");
                System.out.println("\n");
                System.out.println("Now,you have to set the trainers for this course ");
                System.out.println("\n");

                /*
                Course fields have been set
                Now it's time to set the trainers of the course
                 */
                giveTrainer = true;
                while (giveTrainer){
                    for (Trainer trainer:trainers){
                        System.out.println(""+(trainers.indexOf(trainer)+1)+". "+" "+trainer.getFirstName()+" "+trainer.getLastName());
                    }
                    int index;
                    do {
                        System.out.println("Choose the index of the trainer you would like to add to "+c.getTitle());
                        System.out.println("\n");
                        while(!s.hasNextInt()){
                            System.out.println("Enter a number");
                            s.nextLine();
                        }
                        index = s.nextInt();
                        if (c.getTrainers().contains(trainers.get(index - 1))) {
                            System.out.println("You have already added " + trainers.get(index - 1).getFirstName()+" "+trainers.get(index - 1).getLastName() + " to " + c.getTitle());
                            System.out.println("\n");
                        }
                        s.nextLine();
                    }while ( index > (trainers.size()));
                    
                    c.addTrainer(trainers.get(index-1)); //Adding the trainer to the course
                    trainers.get(index-1).addCourse(c); //Adding the course to the trainer

                    System.out.println("Do you want to add another trainer to: " + c.getTitle());
                    System.out.println("y/n ?");
                    giveTrainer = checkFlag(s, giveTrainer);

                }

                /*
                Trainers of the course have been added
                Now it's time to add the assignments of the course
                 */
                boolean giveAssignment = true;
                int assignmentCounter = 1;
                while (giveAssignment) {
                    Assignment a = new Assignment();
                    System.out.println("Insert the name of the assignment #" + assignmentCounter);
                    a.setTitle(s.nextLine());
                    System.out.println("Insert the description of the assignment #" + assignmentCounter);
                    a.setDescription(s.nextLine());

                    System.out.println("Submission Date of assignment #" + assignmentCounter + " ?");

                    a.setSubDateTime(getValidDate(s));
                    while (!((( a.getSubDateTime().getDayOfYear()) >= (c.getStartDate().getDayOfYear())) &&  ((a.getSubDateTime().getDayOfYear())<=(c.getEndDate().getDayOfYear())))) {
                        System.out.println("Sorry, can't set submission date of the assignment out of the range of course start-end dates");
                        System.out.println("");
                        System.out.println("Set submission date again");
                        System.out.println("");
                        System.out.println(c.getTitle()+" starts at "+c.getStartDate()+" and ends at "+c.getEndDate());
                        a.setSubDateTime(getValidDate(s));
                }

                    System.out.println("What's the oral mark of assignment #" + assignmentCounter + " ?");
                    a.setOralMark(getPositiveNumber(s));

                    System.out.println("What's the total mark of assignment #" + assignmentCounter + " ?");
                    a.setTotalMark(getPositiveNumber(s));
                    
                    System.out.println("\n");

                    c.addAssignment(a); // Adding the assignment to the course
                    assignments.add(a); // Adding the assignment to the list of all assignments
                    a.setCourse(c); //Adding the course to the assignment

                    assignmentCounter++;

                    System.out.println("Do you want to add another assignment to :" + c.getTitle() + "?");
                    System.out.println("y/n ?");
                    System.out.println("\n");
                    giveAssignment = checkFlag(s, giveAssignment);

                }

                /*
                Assignments have been added to the course
                Now it's time to add the students who attend this course
                 */
                giveStudent = true;
                while (giveStudent) {
                    for (Student student:students){
                        System.out.println(""+(students.indexOf(student)+1)+". "+" "+student.getFirstName()+" "+student.getLastName());
                    }
                    int index;
                    do {
                        System.out.println("Choose the index of the student you would like to add to "+c.getTitle());
                        while(!s.hasNextInt()){
                            System.out.println("Enter a number");
                            s.nextLine();
                        }
                        index = s.nextInt();
                        if (c.getStudents().contains(students.get(index - 1))) {
                            System.out.println("You have already added " + students.get(index - 1).getFirstName()+" "+students.get(index - 1).getLastName() + " to " + c.getTitle());
                        }
                        s.nextLine();
                    }while ( index > (students.size()));
                    
                    c.addStudent(students.get(index-1)); //Adding the student to the course
                    students.get(index-1).addCourse(c); //Adding the course + it's assignments to the student

                    System.out.println("Do you want to add another student to: " + c.getTitle());
                    System.out.println("y/n ?");
                    System.out.println("\n");
                    giveStudent = checkFlag(s, giveStudent);

                }

                courses.add(c);

                courseCounter++;

                System.out.println("Do you want to add another course ?");
                System.out.println("y/n ?");
                System.out.println("\n");
                giveCourse = checkFlag(s, giveCourse);
                }
            
            System.out.println("THE DATA INSERTION PROCESS HAS BEEN SUCCESSFULLY COMPLETED");
        }else{ 

        /*
            This part is for the use of fixed-synthetic data
        */
        
        String hmnia = "24/06/1995";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        LocalDate dateFromString = LocalDate.parse(hmnia, formatter);
        Student s1 = new Student("Thanos", "Tsianis", dateFromString, 15000);
        
        hmnia = "31/4/1991";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s2 = new Student("Nick","Georgiou", dateFromString, 17500);
        
        hmnia = "17/7/1989";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s3 = new Student("Peter", "Peterson", dateFromString, 9000);
        
        hmnia = "29/1/1990";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s4 = new Student("Bill","Quick", dateFromString, 17500);
        
        hmnia = "11/10/1998";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s5 = new Student("John","Williams", dateFromString, 11000);
        
        hmnia = "03/3/1992";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s6 = new Student("Sotiris","Sotiriou", dateFromString, 17500);
        
        hmnia = "15/9/1994";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s7 = new Student("Michalel","Jordan", dateFromString, 11000);
        
        hmnia = "31/12/1999";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s8 = new Student("Nicole","Kidman", dateFromString, 12000);
        
        hmnia = "14/4/1985";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s9 = new Student("Stephen","Jones", dateFromString, 16500);
        
        hmnia = "18/11/1983";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString = LocalDate.parse(hmnia, formatter);
        Student s10 = new Student("Dimitra","Liapi", dateFromString, 12000);
        
        Trainer t1 = new Trainer("Kobe","Bryant");
        Trainer t2 = new Trainer("Jonathan","Love");
        Trainer t3 = new Trainer("Damian","Allen");
        Trainer t4 = new Trainer("Paul","Griffin");
        Trainer t5 = new Trainer("Mary","Poppins");
        
        String h1 = "19/1/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        LocalDate dateFromString1 = LocalDate.parse(h1, formatter);
        String h2 = "29/3/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        LocalDate dateFromString2 = LocalDate.parse(h2, formatter);
        Course c1 = new Course("C1","Java","Full-time",dateFromString1,dateFromString2);
        
        h1 = "17/1/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString1 = LocalDate.parse(h1, formatter);
        h2 = "27/3/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString2 = LocalDate.parse(h2, formatter);
        Course c2 = new Course("C2","Java","Part-time",dateFromString1,dateFromString2);
        
        h1 = "21/1/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString1 = LocalDate.parse(h1, formatter);
        h2 = "24/6/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString2 = LocalDate.parse(h2, formatter);
        Course c3 = new Course("C3","Python","Full-time",dateFromString1,dateFromString2);
        
        h1 = "25/2/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString1 = LocalDate.parse(h1, formatter);
        h2 = "19/7/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        dateFromString2 = LocalDate.parse(h2, formatter);
        Course c4 = new Course("C4","Python","Part-time",dateFromString1,dateFromString2);
        
        hmnia = "19/2/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        LocalDate d = LocalDate.parse(hmnia, formatter);
        Assignment a11 = new Assignment ("Java_FT_OOP1","Inheritance practise",d,30,100);
        
        hmnia = "10/3/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a12 = new Assignment ("Java_PT_OOP2","Mastering Java",d,50,100);
        
        hmnia = "05/2/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a21 = new Assignment ("Java_FT_OOP1","Inheritance practise",d,30,100);
        
        hmnia = "20/3/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a22 = new Assignment ("Java_PT_OOP2","Mastering Java",d,40,100);
        
        hmnia = "23/3/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a31 = new Assignment ("Python_FT_OOP1","Inheritance practise",d,30,100);
        
        hmnia = "24/5/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a32 = new Assignment ("Pyhton_FT_OOP2","Mastering Python",d,30,100);
        
        hmnia = "02/4/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a41 = new Assignment ("Python_PT_OOP1","Inheritance practise",d,50,100);
        
        hmnia = "19/5/2020";
        formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        d = LocalDate.parse(hmnia, formatter);
        Assignment a42 = new Assignment ("Python_PT_OOP2","Mastering Python",d,20,100);
        
        //Entities have been created. Now correlate them
        //Adding assignments to courses
        c1.addAssignment(a11);
        c1.addAssignment(a12);
        c2.addAssignment(a21);
        c2.addAssignment(a22);
        c3.addAssignment(a31);
        c3.addAssignment(a32);
        c4.addAssignment(a41);
        c4.addAssignment(a42);
        //Adding trainers to courses and vice versa
        t1.addCourse(c1);
        t1.addCourse(c2);
        t2.addCourse(c2);
        t2.addCourse(c3);
        t3.addCourse(c3);
        t3.addCourse(c4);
        t4.addCourse(c4);
        t4.addCourse(c1);
        t5.addCourse(c2);
        t5.addCourse(c3);
        t5.addCourse(c4);
        
        c1.addTrainer(t1);
        c1.addTrainer(t4);
        c2.addTrainer(t1);
        c2.addTrainer(t2);
        c2.addTrainer(t5);
        c3.addTrainer(t2);
        c3.addTrainer(t3);
        c3.addTrainer(t5);
        c4.addTrainer(t3);
        c4.addTrainer(t4);
        c4.addTrainer(t5);
        
        //Adding course to student, student to course and assignment to course
        
        s1.addCourse(c1);
        s1.addCourse(c3);
        s2.addCourse(c2);
        s3.addCourse(c3);
        s4.addCourse(c4);
        s5.addCourse(c3);
        s6.addCourse(c2);
        s6.addCourse(c4);
        s7.addCourse(c2);
        s8.addCourse(c4);
        s9.addCourse(c2);
        s9.addCourse(c3);
        s10.addCourse(c1);
        
        
        c1.addStudent(s1);
        c1.addStudent(s10);
        c2.addStudent(s2);
        c2.addStudent(s6);
        c2.addStudent(s7);
        c2.addStudent(s9);
        c3.addStudent(s1);
        c3.addStudent(s3);
        c3.addStudent(s5);
        c3.addStudent(s9);
        c4.addStudent(s4);
        c4.addStudent(s6);
        c4.addStudent(s8);
        
        //////filling in the general lists
        
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);
        students.add(s9);
        students.add(s10);
        
        trainers.add(t1);
        trainers.add(t2);
        trainers.add(t3);
        trainers.add(t4);
        trainers.add(t5);
        
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);
        
        assignments.add(a11);
        assignments.add(a12);
        assignments.add(a21);
        assignments.add(a22);
        assignments.add(a31);
        assignments.add(a32);
        assignments.add(a41);
        assignments.add(a42);
        
        System.out.println("SYNTHETIC DATA READY FOR USE");
        }        
        /*
        Now the entities are filled with data
        The following part of the code gives the user the chance to print the part of informsation
        he/she is interested about
         */
        
        s = new Scanner(System.in);
        boolean more = true;
        while (more){
            System.out.println("Select one of the following options:");
            System.out.println("1.Print a list of all the students");
            System.out.println("2.Print a list of all the trainers");
            System.out.println("3.Print a list of all the courses");
            System.out.println("4.Print a list of all the assignments");
            System.out.println("5.Print a list of the students who attend a specific course");
            System.out.println("6.Print a list of the trainers who teach a specific course");
            System.out.println("7.Print a list of the assignments of a specific student");
            System.out.println("8.Print a list of the assigmnents of a specific course");
            System.out.println("9.Print a list of the students who attend multiple courses");
            System.out.println("0.Print a list of the students who need to submit an assignment\n  within a specific week");

            int choice = getValidMenuChoice(s,0,9);
            switch(choice){
                case 1:
                    System.out.println("The school has the following students:");
                    for (Student student:students){
                        System.out.println(""+(students.indexOf(student)+1)+". "+" "+student.getFirstName()+" "+student.getLastName());
                    }
                    break;
                case 2:
                    System.out.println("The school has the following trainers:");
                    for (Trainer trainer:trainers){
                        System.out.println(""+(trainers.indexOf(trainer)+1)+". "+" "+trainer.getFirstName()+" "+trainer.getLastName());
                    }
                    break;
                case 3:
                    System.out.println("The school offers the following courses:");
                    for (Course course:courses){
                        System.out.println(""+(courses.indexOf(course)+1)+". "+" Title:"+course.getTitle()+" Stream:"+course.getStream()+" Type:"+course.getType());
                    }
                    break;
                case 4:
                    System.out.println("The school has the following assignments: ");
                    for (Course course:courses){
                        course.printAssignments();
                    }
                    break;
                case 5:
                    System.out.println("Which course are you interested about?");
                    for (Course course:courses){
                        System.out.println(""+(courses.indexOf(course)+1)+". "+" Title:"+course.getTitle()+" Stream:"+course.getStream()+" Type:"+course.getType());
                    }
                    choice = getValidMenuChoice(s,1,courses.size());
                    Course x = courses.get(choice-1);
                    x.printStudents();
                    break;
                case 6:
                    System.out.println("Which course are you interested about?");
                    for (Course course:courses){
                        System.out.println(""+(courses.indexOf(course)+1)+". "+" Title:"+course.getTitle()+" Stream:"+course.getStream()+" Type:"+course.getType());
                    }
                    choice = getValidMenuChoice(s,1,courses.size());
                    x = courses.get(choice-1);
                    x.printTrainers();
                    break;
                case 7:
                    System.out.println("Which student are you interested about?");
                    for (Student student:students){
                        System.out.println(""+(students.indexOf(student)+1)+". "+" "+student.getFirstName()+" "+student.getLastName());
                    }
                    choice = getValidMenuChoice(s,1,students.size());
                    Student st = students.get(choice-1);
                    st.printAssignments();
                    break;
                case 8:
                    System.out.println("Which course are you interested about?");
                    for (Course course:courses){
                        System.out.println(""+(courses.indexOf(course)+1)+". "+" Title:"+course.getTitle()+" Stream:"+course.getStream()+" Type:"+course.getType());
                    }
                    choice = getValidMenuChoice(s,1,courses.size());
                    x = courses.get(choice-1);
                    x.printAssignments();
                    break;
                case 9:
                    System.out.println("The following students attend multiple courses");
                    boolean MesaEpeses = false;
                    for ( Student student : students ){
                        if (student.getCourses().size()>1){
                            MesaEpeses=true;
                            System.out.println(student.getFirstName()+" "+student.getLastName()+ " attends: ");
                            for (Course course:student.getCourses()){
                                System.out.println(""+(courses.indexOf(course)+1)+". "+" Title:"+course.getTitle()+" Stream:"+course.getStream()+" Type:"+course.getType());
                            }
                        }
                    }
                    if (MesaEpeses = false){
                        System.out.println("No student attends multiple courses");
                    }
                        break;
                case 0:
                    LocalDate inputDate = getValidDate(s);
                    LocalDate firstDayOfWeek = inputDate;
                    LocalDate lastDayOfWeek = inputDate;
                    while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
                        firstDayOfWeek = firstDayOfWeek.minusDays(1);
                    }
                    while( lastDayOfWeek.getDayOfWeek() != DayOfWeek.FRIDAY){
                        lastDayOfWeek = lastDayOfWeek.plusDays(1);
                    }
//                    System.out.println("First day of week is " + firstDayOfWeek);
                    /*
                    So far the user has given a random date
                    and we have found the date of the first day of that week
                    */
                    System.out.println("For the calendar week from "+firstDayOfWeek+" to "+firstDayOfWeek.plusDays(4)+"\nthe following students need to submit one or more assignments");
                    boolean EpesesMesa = false; // Για να ελεγχει μηπως η ημερομηνια που εδωσε ο χρηστης πεφτει σε ''κενη'' βδομαδα
                    for (Student student : students ){
                        int xxx = 1; // Για να τυπωνει το ονομα μονο μια φορα
                        for ( Assignment a : student.getAssignments() ){
                            if (a.getSubDateTime().getYear() == firstDayOfWeek.getYear() &&  a.getSubDateTime().getMonth() == firstDayOfWeek.getMonth()) {  
                                if ((a.getSubDateTime().getDayOfWeek().compareTo(firstDayOfWeek.getDayOfWeek()) >= 0) && (a.getSubDateTime().getDayOfWeek().compareTo(lastDayOfWeek.getDayOfWeek()) <= 4)) {
                                    EpesesMesa = true;
                                    if (xxx==1){
                                        System.out.println(student.getFirstName() + " " + student.getLastName() + " needs to submit:");
                                    }
                                    System.out.println("\t" + "Title: " + a.getTitle() + " SubDate: " + a.getSubDateTime());
                                    xxx++;
                                }
                            }
                        }
                    }
                    
                    if ( EpesesMesa == false ){
                        System.out.println("For the calendar week from "+firstDayOfWeek+" to "+firstDayOfWeek.plusDays(4)+"\nno student needs to submit an assignment");
                    }

            }
            
            System.out.println("Would you like to choose something else? y/n");
            more = checkFlag(s,more);
            
               
        }
    }

    /*
    ----------------------------------------------------------------------------------------------------
    -----------------------------------------METHODS----------------------------------------------------
    ----------------------------------------------------------------------------------------------------
    */
    
    public static int getValidMenuChoice(Scanner s,int l,int u){
        int choice;
        do{
            System.out.println("Please choose a number 0-9");
            while (!s.hasNextInt()){
                System.out.println("Please enter a number");
                s.nextLine();
            }
            choice = s.nextInt();
            s.nextLine();
        }while(!(choice>=l && choice<=u));
        return choice;
    }
    
    public static int getPositiveNumber(Scanner s) {
        int n;
        do {
            System.out.println("Please enter a positive integer");
            while (!s.hasNextInt()) {
                System.out.println("Please enter a positive integer");
                s.nextLine();
            }
            n = s.nextInt();
            s.nextLine();
        } while (n < 0);
        return n;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    public static boolean checkFlag(Scanner s, boolean flag) {
        String answer = s.nextLine();
        while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N")) {
            System.out.println("Please choose between y and n");
            answer = s.nextLine();
        }
        if (answer.toLowerCase().equals("y")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    public static LocalDate getValidDate(Scanner s){
//        int month;
//        do {
//            System.out.println("Please enter the number of the month: 1-12");
//            while (!s.hasNextInt()) {
//                System.out.println("Please enter a month index: 1-12");
//                s.nextLine();
//            }
//            month = s.nextInt();
//            s.nextLine();
//        } while (month < 1 || month>12);
//        
//        //εχουμε το Month
//        
//        int day;
//        do {
//            System.out.println("Please enter the day of the month: 1-31");
//            while (!s.hasNextInt()) {
//                System.out.println("Please enter a month index: 1-31");
//                s.nextLine();
//            }
//            day = s.nextInt();
//            s.nextLine();
//        } while (day < 1 || day>31);
//        String str_day;
//        if (day<10) {
//            str_day = "0"+day;
//        }else{
//            str_day = ""+day;
//        }
//        
//        //Εχουμε το day
//        
//        int year;
//        do {
//            System.out.println("Please enter the year: 1950-2100");
//            while (!s.hasNextInt()) {
//                System.out.println("Please enter a year between 1950 and 2100");
//                s.nextLine();
//            }
//            year = s.nextInt();
//            s.nextLine();
//        } while (year < 1950 || year>2100);
//        
//        //εχουμε το year
//        
//        String input_date = ""+str_day+"/"+month+"/"+year;
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
//        LocalDate dateFromString = LocalDate.parse(input_date, formatter);
//        System.out.println(dateFromString);
//        
//        return dateFromString;
        String day;
        String month;
        String year;
        String CorrectDate;
        LocalDate dateFromString = null;
        boolean inputIsValid=false;
        while (inputIsValid==false){
            System.out.println("Please enter the date in the following form: dd/M/yyyy\ndd<=31\tM<=12\tyyyy<2100");
            String input = s.nextLine();
            String [] inputArray = input.split("/");
            for (int i = 0 ; i< inputArray.length ;i++){
                inputArray[i]=inputArray[i].trim();
            }
            if (( inputArray.length==3 ) && ( isNumeric(inputArray[0])) && ( isNumeric(inputArray[1])) && ( isNumeric(inputArray[2]))){
                List<Integer> inputNumbers = new ArrayList();
                inputNumbers.add(0, Integer.parseInt(inputArray[0]));
                inputNumbers.add(1, Integer.parseInt(inputArray[1]));
                inputNumbers.add(2, Integer.parseInt(inputArray[2]));
                if (inputNumbers.get(0)>=1 && inputNumbers.get(0)<=31 && inputNumbers.get(1)>=1 && inputNumbers.get(1)<=12 && inputNumbers.get(2)>=1950 && inputNumbers.get(2)<=2100){
                    if (inputNumbers.get(0)<10){
                        day="0"+inputNumbers.get(0);
                    }else{
                        day=""+inputNumbers.get(0);
                    }
                    month = ""+inputNumbers.get(1);
                    year = ""+inputNumbers.get(2);
                    inputIsValid = true;
                    CorrectDate=day+"/"+month+"/"+year;
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
                    dateFromString = LocalDate.parse(CorrectDate, formatter);
                }
            }else{
                inputIsValid=false;    
            }
        }  
    return dateFromString;
    }
    
    
    public static boolean isNumeric(String str) { 
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /*
    Το χρησιμοποιω μόνο στα ονόματα. Θα μπορούσα και στα description και τα Titles 
    των μαθηματων και των assignments, αλλα ισως καποιος να θελει να βαλει νουμερα σε τιτλους
    η περιγραφες μαθηματων,
    πχ Μαθηματικα 1 (τιτλος)
    πχ "Make 2 classes that..." (περιγραφη assignment)
    */
    public static String isAlpha(Scanner s) {
        String name = null;
        boolean flag = true;
        while (flag){
            name = s.nextLine().trim();
            char[] chars = name.toCharArray();
            boolean foundError = false;
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    System.out.println("Sorry, can't contain numbers,symbols or spaces.Try again:");
                    foundError = true;
                    break;
                }
            }
            if (!foundError){
                flag=false;
            }
        }
        return name;
    }
}

    
    
    


    

    
