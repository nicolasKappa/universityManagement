import java.util.ArrayList;
import java.util.List;
public class Lector extends User{
    private LectorType lectorType;
    private List<Course> courses;
    private static final int MAX_COURSES = 4;

    public Lector(int id, String firstName, String lastName,LectorType lectorType){
        super(id,firstName,lastName);
        this.lectorType = lectorType;
        this.courses = new ArrayList<>();
    }
    public LectorType getLectorType() {return lectorType;}
    public List<Course> getCourses() {return courses;}

    public boolean addCourse(Course course){
        if (courses.size() < MAX_COURSES) {
            courses.add(course);
            return true;
        } else {
            System.out.println("Lector reached maximum course take");
            return false;
        }
    }
    public boolean removeCourse(Course course){
        return courses.remove(course);
    }
    
    
}
