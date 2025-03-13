public interface UniManagement {
    Course createCourse(String courseName);
    boolean deleteCourse(String courseName) throws CourseNotFoundException;
    boolean updateCourse(String oldCourseName, String newCourseName);

    Student createStudent(int id, String firstName, String lastName, String facNumber);
    boolean deleteStudent(int id) throws StudentNotFoundException;
    boolean addStudentToCourse(Student student, Course course);
    boolean removeStudentFromCourse(Student student, String courseName);



    Lector createLector(int id, String firstName, String lastName,LectorType lectorType);
    boolean assignLectorToCourse(Lector lector, String courseName);


}
