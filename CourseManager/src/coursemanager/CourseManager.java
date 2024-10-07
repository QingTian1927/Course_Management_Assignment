package coursemanager;

import coursemanager.io.DataManager;
import coursemanager.model.*;
import coursemanager.ui.Menu;
import coursemanager.util.Validation;

import java.io.IOException;

@SuppressWarnings("CallToPrintStackTrace")
public class CourseManager {

    public static void main(String[] args) {
        // Singleton cái chết
        DataManager dataManager = DataManager.getInstance();

        try {
            dataManager.getCourseList().load();
        } catch (IOException e) {
            System.out.println("[FATAL] Failed to read courses.txt");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            dataManager.getStudentList().load();
        } catch (IOException e) {
            System.out.println("[FATAL] Failed to read students.txt");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            dataManager.getRegisterList().load();
        } catch (IOException e) {
            System.out.println("[FATAL] Failed to read registers.txt");
            e.printStackTrace();
            System.exit(1);
        }

        boolean isRunning = true;
        while (isRunning) {
            Menu.displayMainMenu();
            char choice = Validation.getChar();
            System.out.println();

            switch (choice) {
                case '1':
                    manageCourses(dataManager);
                    break;
                case '2':
                    manageStudents(dataManager);
                    break;
                case '3':
                    manageRegisters(dataManager);
                    break;
                case '0':
                    isRunning = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }
    }

    public static void manageCourses(DataManager dataManager) {
        CourseList courseList = dataManager.getCourseList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayCourseMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    Course newCourse1 = courseList.getCourseDetailsFromUser();
                    if (newCourse1 != null) {
                        courseList.addLast(newCourse1);
                        System.out.println("Course registered successfully");
                    }
                    break;
                case "2":
                    courseList.display();
                    break;
                case "3":
                    try {
                        courseList.save();
                        System.out.println("Courses saved successfully.");
                    } catch (IOException e) {
                        System.out.println("[ERROR] Failed to save courses.");
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.print("Enter course code to search: ");
                    String ccodetoSearch = Validation.getString().toUpperCase();
                    Node<Course> foundCourseNode = courseList.searchByCcode(ccodetoSearch);

                    if (foundCourseNode != null) {
                        // If the course is found, print its information using the toString method (so I
                        // want to declare data to be public)
                        System.out.println(foundCourseNode.data.toString());
                    }
                    break;
                case "5":
                    System.out.print("Enter course code to delete: ");
                    String ccodetoDelete = Validation.getString().toUpperCase();
                    courseList.deleteByCcode(ccodetoDelete);
                    break;
                case "6":
                    CourseList sortedList = courseList.sort();
                    sortedList.display();
                    System.out.println("Courses sorted by code successfully.");
                    break;
                case "7":
                    Course newCourse2 = courseList.getCourseDetailsFromUser();
                    courseList.addFirst(newCourse2);
                    System.out.println("Course added successfully.");
                    break;
                case "8":
                    if (courseList.isEmpty()) {
                        System.out.println("List is empty.");
                        break;
                    }

                    int k = Validation.getInteger("Enter a position to add after: ", "Your index must be lower than the numbre of course or in valid type.", 0, courseList.size() - 1);
                    Course afterCourse = courseList.getCourseDetailsFromUser();
                    courseList.insertAfterIndex(k, afterCourse);
                    break;
                case "9":
                    if (courseList.isEmpty()) {
                        System.out.println("List is empty.");
                        break;
                    }

                    int deletePosition = Validation.getInteger("Enter a position to delete to: ", "Position must be lower than the number of course in valid type.", 0, courseList.size() - 1);
                    courseList.deleteToIndex(deletePosition);
                    System.out.println("Course deleted successfully.");
                    break;
                case "10":
                    System.out.print("Enter subject name to search: ");
                    String subjectName = Validation.getString();
                    CourseList searchResults = courseList.searchByName(subjectName);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Courses found:");
                        searchResults.display();
                    } else {
                        System.out.println("No courses found with subject name: " + subjectName);
                    }
                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }
    }

    public static void manageStudents(DataManager dataManager) {
        StudentList studentList = dataManager.getStudentList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayStudentMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    //Student student = studentList.getStudentDetailsFromUser();
                    Student newStudent = studentList.getStudentDetailsFromUser();
                    if (newStudent != null) {
                        studentList.addLast(newStudent);
                        System.out.println("Add new student successfully");
                    }
                    break;
                case "2":
                    studentList.display();
                    break;
                case "3":
                    try {
                        studentList.save();
                        System.out.println("Student saved successfully.");
                    } catch (IOException e) {
                        System.out.println("[ERROR] Failed to save courses.");
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    String scodetoSearch = studentList.inputScode().toUpperCase();
                    Node<Student> foundStudentNode1 = studentList.searchByScode(scodetoSearch);

                    if (foundStudentNode1 != null) {
                        // If the student is found, print its information using the toString method (so
                        // I
                        // want to declare data to be public)
                        foundStudentNode1.data.displayStudentInfo();
                    } else {
                        System.out.println("No course found with code: " + foundStudentNode1);
                    }
                    break;
                case "5":
                    studentList.deleteByScode();

                    break;
                case "6":
                    String studName = studentList.inputName();
                    StudentList foundStudent = studentList.searchByName(studName);

                    if (!foundStudent.isEmpty()) {
                        System.out.println("Students found: ");
                        foundStudent.display();
                    } else {
                        System.out.println("No student found with input name: " + studName);
                    }
                    break;
                case "7":
                    CourseList courseListRegisteredByStudent = studentList.searchRegisteredCoursesByScode(dataManager);
                    if (!courseListRegisteredByStudent.isEmpty()) {
                        System.out.println("List of Courses Registered by Student:");
                        courseListRegisteredByStudent.display();
                    }

                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }
    }

    public static void manageRegisters(DataManager dataManager) {
        RegisterList registerList = dataManager.getRegisterList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayRegisterMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    System.out.print("Enter course code to register: ");
                    String ccode = Validation.getString().toUpperCase();
                    System.out.print("Enter student code to register: ");
                    String scode = Validation.getString().toUpperCase();
                    registerList.registerCourse(ccode, scode);
                    break;
                case "2":
                    registerList.display();
                    break;
                case "3":
                    try {
                        registerList.save();
                        System.out.println("Save registered student successfully.");
                    } catch (IOException e) {
                        System.out.println("[ERROR] Failed to save courses registration");
                    }
                    break;
                case "4":
                    CommonList<Register> sortedList = (CommonList<Register>) registerList.sort();
                    System.out.println("After Sorted: ");
                    sortedList.display();
                    break;
                case "5":
                    System.out.print("Enter course code to update mark: ");
                    String ccodeUpdate = Validation.getString().toUpperCase();
                    System.out.print("Enter student code to update mark: ");
                    String scodeUpdate = Validation.getString().toUpperCase();
                    double mark = Validation.getDouble("Enter mark you want to update: ", "Mark must be in range 0 to 10", 0, 10);
                    registerList.updateMark(scodeUpdate, ccodeUpdate, mark);
                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
            System.out.println();
        }
    }
}
