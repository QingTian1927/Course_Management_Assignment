package coursemanager;

import coursemanager.io.DataManager;
import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;
import coursemanager.model.StudentList;
import coursemanager.ui.Menu;
import coursemanager.util.Validation;

import java.io.IOException;

@SuppressWarnings("CallToPrintStackTrace")
public class CourseManager {

    public static void main(String[] args) {
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

            switch (choice) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    break;
                case '0':
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    public static void manageCourses(DataManager dataManager) {
        CourseList courseList = dataManager.getCourseList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayCourseMenu();
            String choice = Validation.getString();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    public static void manageStudents(DataManager dataManager) {
        StudentList studentList = dataManager.getStudentList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayStudentMenu();
            String choice = Validation.getString();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    public static void manageRegisters(DataManager dataManager) {
        RegisterList registerList = dataManager.getRegisterList();
        boolean isManaging = true;

        while (isManaging) {
            Menu.displayRegisterMenu();
            String choice = Validation.getString();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "0":
                    isManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }
}
