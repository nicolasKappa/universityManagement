import java.util.ArrayList;
import java.util.List;

public class Course{
    private String name;
    private List<Student> students;
    private Lector assistance;
    private Lector lector;
    private static final int MAX_STUDENTS = 30;

    public Course(String name){
        this.name = name;
        this.students = new ArrayList<>();
    }

    public Course(String name, Lector lector, Lector assistance){
        this.name = name;
        this.students = new ArrayList<>();
        this.lector = lector;
        this.assistance = assistance;
    }

    public boolean addStudent(Student student){
        if (students.size() < MAX_STUDENTS) {
            students.add(student);
            student.addCourse(this);
            return true;
        } else {
            System.out.println("The maximum amount of students is reached");
            return false;
        }
    }

    public boolean removeStudent(Student student){
        if (students.remove(student)) {
            student.removeCourse(this);
            return true;
        }
        return false;
    }

    public void setAssistance(Lector assistance){
        this.assistance = assistance;
    }

    public void setLector (Lector lector){
        this.lector = lector;
    }

    public String getName(){
        return name;
    }
    public List<Student> getStudents(){
        return students;
    }

    public Lector getAssistance(){
        return assistance;
    }
    public Lector getLector(){
        return lector;
    }
}