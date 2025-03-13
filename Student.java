import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String facNumber;
    public List<Course> courses;
    private static final int MAX_Courses = 10;
    
    public Student(int id, String firstName, String lastName, String facNumber){
        super(id,firstName,lastName);
        this.facNumber = facNumber;
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course)
    {
        if (courses.size() < MAX_Courses){
            courses.add(course);
          return true;
        }else{
            System.out.println("The Course is full");
            return false;
        }
    }

    public boolean removeCourse(Course course){
        return courses.remove(course);
    }

    public String getFacNumber(){
        return facNumber;
    }

    public List<Course> getCourses(){
        return courses;
    }
}
