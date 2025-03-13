import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniManagement management = new UniManagementImplement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("University Management System");
        
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Create Student");
            System.out.println("2. Create Course");
            System.out.println("3. Add Student to Course");
            System.out.println("4. Delete Student");
            System.out.println("5. Delete Course");
            System.out.println("6. Assign Lector to Course");
            System.out.println("7. Remove Student from Course");
            System.out.println("8. Update Course Name");
            System.out.println("9. Exit");
            System.out.print("Choice: ");
            
            String choiceStr = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 9.");
                continue;
            }
            
            try {
                switch (choice) {
                    case 1:
                        // Create Student
                        System.out.print("Enter Student ID: ");
                        int sId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter First Name: ");
                        String sFirst = scanner.nextLine().trim();
                        System.out.print("Enter Last Name: ");
                        String sLast = scanner.nextLine().trim();
                        System.out.print("Enter Faculty Number: ");
                        String facNum = scanner.nextLine().trim();
                        Student student = management.createStudent(sId, sFirst, sLast, facNum);
                        if (student != null) {
                            System.out.println("Student created: " + student.getFirstName() + " " + student.getLastName());
                        }
                        break;
                    case 2:
                        // Create Course
                        System.out.print("Enter Course Name: ");
                        String cName = scanner.nextLine().trim();
                        Course course = management.createCourse(cName);
                        if (course != null) {
                            System.out.println("Course created: " + course.getName());
                        }
                        break;
                    case 3:
                        // Add Student to Course
                        System.out.print("Enter Student ID to add: ");
                        int addSId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Course Name: ");
                        String addCName = scanner.nextLine().trim();
                        
                        // Look up student from the array in the implementation
                        UniManagementImplement impl = (UniManagementImplement) management;
                        Student foundStudent = null;
                        for (int i = 0; i < impl.lastUsedStudentIndex; i++) {
                            if (impl.students[i].getId() == addSId) {
                                foundStudent = impl.students[i];
                                break;
                            }
                        }
                        if (foundStudent == null) {
                            System.out.println("Student with ID " + addSId + " not found.");
                            break;
                        }
                        
                        // Look up course from the list
                        Course foundCourse = null;
                        for (Course c : impl.courses) {
                            if (c.getName().equalsIgnoreCase(addCName)) {
                                foundCourse = c;
                                break;
                            }
                        }
                        if (foundCourse == null) {
                            System.out.println("Course '" + addCName + "' not found.");
                            break;
                        }
                        boolean added = management.addStudentToCourse(foundStudent, foundCourse);
                        System.out.println("Student added to course: " + added);
                        break;
                    case 4:
                        // Delete Student
                        System.out.print("Enter Student ID to delete: ");
                        int delSId = Integer.parseInt(scanner.nextLine().trim());
                        boolean delStudent = management.deleteStudent(delSId);
                        System.out.println("Delete student status: " + delStudent);
                        break;
                    case 5:
                        // Delete Course
                        System.out.print("Enter Course Name to delete: ");
                        String delCName = scanner.nextLine().trim();
                        boolean delCourse = management.deleteCourse(delCName);
                        System.out.println("Delete course status: " + delCourse);
                        break;
                    case 6:
                        // Assign Lector to Course
                        System.out.print("Enter Lector ID: ");
                        int lId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Lector First Name: ");
                        String lFirst = scanner.nextLine().trim();
                        System.out.print("Enter Lector Last Name: ");
                        String lLast = scanner.nextLine().trim();
                        System.out.print("Enter Lector Type (DOCENT/PROFESSOR/ASSISTANCE): ");
                        String lTypeStr = scanner.nextLine().trim().toUpperCase();
                        LectorType lType;
                        try {
                            lType = LectorType.valueOf(lTypeStr);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Lector Type.");
                            break;
                        }
                        Lector lector = management.createLector(lId, lFirst, lLast, lType);
                        if (lector == null) break;
                        System.out.print("Enter Course Name to assign: ");
                        String assignCName = scanner.nextLine().trim();
                        boolean assigned = management.assignLectorToCourse(lector, assignCName);
                        System.out.println("Assign lector status: " + assigned);
                        break;
                    case 7:
                        // Remove Student from Course
                        System.out.print("Enter Student ID to remove: ");
                        int remSId = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Course Name: ");
                        String remCName = scanner.nextLine().trim();
                        // Look up student and course as in option 3
                        UniManagementImplement impl2 = (UniManagementImplement) management;
                        Student remStudent = null;
                        for (int i = 0; i < impl2.lastUsedStudentIndex; i++) {
                            if (impl2.students[i].getId() == remSId) {
                                remStudent = impl2.students[i];
                                break;
                            }
                        }
                        if (remStudent == null) {
                            System.out.println("Student with ID " + remSId + " not found.");
                            break;
                        }
                        boolean removed = management.removeStudentFromCourse(remStudent, remCName);
                        System.out.println("Remove student from course status: " + removed);
                        break;
                    case 8:
                        // Update Course Name
                        System.out.print("Enter Old Course Name: ");
                        String oldName = scanner.nextLine().trim();
                        System.out.print("Enter New Course Name: ");
                        String newName = scanner.nextLine().trim();
                        boolean updated = management.updateCourse(oldName, newName);
                        System.out.println("Update course status: " + updated);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please choose between 1 and 9.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
