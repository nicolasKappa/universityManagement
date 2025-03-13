# University Management System

This is a console-based Java application for managing university courses, students, and lectors. It was developed as part of the DOITGEORGIA Java Course Certification project to help practice and apply concepts such as methods, classes, inheritance, exceptions, arrays, lists, loops, and conditionals.

## Project Overview

The University Management System provides the following functionalities:
- **Adding a New Course:** Create a new course with a unique course name.
- **Deleting an Existing Course:** Remove a course by its name (throws an exception if not found).
- **Assigning a Lector to a Course:** Assign a lector (Professor, Docent, or Assistance) to a course.
- **Adding a Student to a Course:** Enroll a student in a course (with a maximum of 30 students per course).
- **Deleting a Student from a Course:** Remove a student from a course.
- **Updating a Course:** Change a course's name (by replacing the old course with a new one while preserving its assigned lector, assistance, and enrolled students).

### Data Storage

- **Students:** Stored in a fixed-size array with a maximum capacity of 1000. A counter (`lastUsedStudentIndex`) is used to track the current number of students.
- **Courses:** Stored in a list with a limit of 10 courses.
- **Lectors:** Stored in a list. Each student, course, and lector is expected to have a unique identifier.

## Technologies & Concepts Used

- **Java SE:** Core Java language features including classes, interfaces, and exceptions.
- **Collections & Arrays:** Usage of fixed-size arrays for students and dynamic lists for courses and lectors.
- **Object-Oriented Programming:** Inheritance, encapsulation, and polymorphism.
- **Exception Handling:** Custom exception classes (`CourseNotFoundException` and `StudentNotFoundException`).
- **Console-Based User Interface:** Command-line parsing with the `Scanner` class and a menu-driven interface.

## Project Structure

