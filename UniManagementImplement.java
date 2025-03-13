import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class UniManagementImplement implements UniManagement {
    Student[] students = new Student[1000];
    int lastUsedStudentIndex = 0;
    public List<Course> courses = new ArrayList<>();
    private List<Lector> lectors = new ArrayList<>();


    @Override
    public Course createCourse(String courseName){
        boolean exists = courses.stream()
        .anyMatch(course -> course.getName().equalsIgnoreCase(courseName));
        if(exists){
            System.out.println("course '" + courseName + "' already exists");
            return null;
        }

        if (courses.size() >= 10){
            System.out.println("Maximum number of courses reached");
            return null;
        }

        Course course = new Course(courseName);
        courses.add(course);
        return course;
    }

    public boolean deleteCourse(String courseName) throws CourseNotFoundException {
        Course courseToRemove = courses.stream()
            .filter(course -> course.getName().equalsIgnoreCase(courseName))
            .findFirst()
            .orElse(null);
    
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            return true;
        } else {
            throw new CourseNotFoundException("Course with name '" + courseName + "' not found!");
        }
    }
    
    @Override
    public boolean updateCourse(String oldCourseName, String newCourseName){
        Course courseToUpdate = courses.stream()
        .filter(course -> course.getName().equalsIgnoreCase(oldCourseName))
        .findFirst()
        .orElse(null);

        if (courseToUpdate != null) {
            boolean exists = courses.stream()
                .anyMatch(course -> course.getName().equalsIgnoreCase(newCourseName));
            
            if (exists) {
                System.out.println("A course with the name '" + newCourseName + "' already exists!");
                return false;
            }
        courses.remove(courseToUpdate);
        Course updatedCourse = new Course(newCourseName, courseToUpdate.getLector(), courseToUpdate.getAssistance());
        courses.add(updatedCourse);
        System.out.println("Course '" + oldCourseName + "' has been updated to '" + newCourseName + "'.");
        return true;
        } else {
        System.out.println("Course with name '" + oldCourseName + "' not found!");
        return false;
        }
    }

    @Override
    public Student createStudent(int id, String firstName, String lastName,String facNumber){
        
        boolean exists = Arrays.stream(students, 0, lastUsedStudentIndex).anyMatch(student-> student != null && student.getId()== id);
        if(exists){
            System.out.println("Student with ID " + id + " already exists!");
            return null;
        }

        Student student = new Student(id, firstName,lastName,facNumber);
        students[lastUsedStudentIndex++] = student;
        return student;
    }
    
    @Override
    public boolean deleteStudent(int id) throws StudentNotFoundException{
        int indexToRemove = -1;
        for (int i = indexToRemove; i < lastUsedStudentIndex - 1; i++){
            if (students[i] != null && students[i].getId() == id){
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1){
            throw new StudentNotFoundException("There is no Student with that Id");
        }
        for (int i = indexToRemove; i< lastUsedStudentIndex - 1; i ++){
            students[i] = students[i + 1];
        }
        students[lastUsedStudentIndex - 1] = null;
        lastUsedStudentIndex--;
        return true;
    }
    
    

    @Override
    public boolean addStudentToCourse(Student student, Course course){
        return course.addStudent(student);
    }
    @Override
    public Lector createLector(int id, String firstName, String lastName, LectorType lectorType){
        boolean exists = lectors.stream().anyMatch(lector ->lector.getId() == id );
        if(exists){
            System.out.println("Lector with ID " + id + " already exists!");
            return null;
        }
        Lector lector = new Lector(id, firstName, lastName, lectorType);
        lectors.add(lector);
        return lector;
    }

    
    @Override
    public boolean assignLectorToCourse(Lector lector, String courseName) {
        Course course = courses.stream()
            .filter(c -> c.getName().equalsIgnoreCase(courseName))
            .findFirst()
            .orElse(null);
    
        if (course != null) {
            course.setLector(lector);
            System.out.println("Lector with Id " + lector.getId() + " was assigned to course " + courseName);
            return true;
        } else {
            System.out.println("Course not found: " + courseName);
            return false;
        }
    }
    @Override
    public boolean removeStudentFromCourse(Student student, String courseName) {
    Course course = courses.stream()
        .filter(c -> c.getName().equalsIgnoreCase(courseName))
        .findFirst()
        .orElse(null);

    if (course != null) {
        boolean removed = course.removeStudent(student);
        if (removed) {
            System.out.println("Student with ID " + student.getId() + " removed from " + courseName);
        } else {
            System.out.println("Student was not in course " + courseName);
        }
        return removed;
    } else {
        System.out.println("Course not found: " + courseName);
        return false;
    }
}
    
}
