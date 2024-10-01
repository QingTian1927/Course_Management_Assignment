package coursemanager.ui;

import coursemanager.model.*;
import coursemanager.util.Validation;
import java.io.File;
import java.io.IOException;

public final class Menu{
    Menu() {}

    public static void displayMainMenu(){
        System.out.println("========== CSD201 CourseManager ==========\n");
        System.out.println("1. Manage Course List");
        System.out.println("2. Manage Student List");
        System.out.println("3. Manage Register List");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void displayCourseMenu() {
        System.out.println("---------- Manage Course List ----------\n");
        System.out.println("1. Input & add to the end");
        System.out.println("2. Display data");
        System.out.println("3. Save course list to file");
        System.out.println("4. Search by ccode");
        System.out.println("5. Delete by ccode");
        System.out.println("6. Sort by ccode");
        System.out.println("7. Input & add to beginning");
        System.out.println("8. Add after position k");
        System.out.println("9. Delete position k");
        System.out.println("10. Search by name");
        System.out.println("11. Search by code");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printCourseMenu() throws IOException {
        while (true){
            CourseList courseList = new CourseList();
            Course course = new Course();
            displayCourseMenu();
            int choice = Validation.getInteger(0,11);
            switch (choice){
                case 1:
                    courseList.addLast(course);
                    break;
                case 2:
                    courseList.display();
                    break;
                case 3:
                    courseList.save();
                    break;
                case 4:
                    System.out.print("Enter cCode to search: ");
                    String cCodeSearch = Validation.getString();
                    courseList.searchByCcode(cCodeSearch);
                    break;
                case 5:
                    System.out.print("Enter cCode to delete: ");
                    String cCodeDel = Validation.getString();
                    courseList.deleteByCcode(cCodeDel);
                    break;
                case 6:
                    courseList.sort();
                    break;
                case 7:
                    courseList.addFirst(course);
                    break;
                case 8:
                    System.out.print("Enter position to add: ");
                    int positionAdd = Validation.getInteger(0, Integer.MAX_VALUE);
                    courseList.insertAfterIndex(positionAdd, course);
                    break;
                case 9:
                    System.out.print("Enter position to delete: ");
                    int positionDel = Validation.getInteger(0, Integer.MAX_VALUE);
                    courseList.deleteToIndex(positionDel);
                    break;
                case 10:
                    System.out.print("Enter name to search: ");
                    String nameSearch = Validation.getString();
                    courseList.searchByName(nameSearch);
                    break;
                case 11:
                    System.out.print("Enter code to search: ");
                    String codeSearch = Validation.getString();
                    courseList.searchByCcode(codeSearch);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayStudentMenu() {
        System.out.println("---------- Manage Student List ----------\n");
        System.out.println("1. Input & add to the end");
        System.out.println("2. Display data");
        System.out.println("3. Save course list to file");
        System.out.println("4. Search by ccode");
        System.out.println("5. Delete by ccode");
        System.out.println("6. Search by course name");
        System.out.println("7 .Search registered code by scode");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printStudentMenu() throws IOException {
        while (true){
            StudentList studentList = new StudentList();
            displayStudentMenu();
            int choice = Validation.getInteger(0,7);
            switch (choice){
                case 1:
                    studentList.addStudent();
                    break;
                case 2:
                    studentList.display();
                    break;
                case 3:
                    studentList.save();
                    break;
                case 4:
                    studentList.searchByScode();
                    break;
                case 5:
                    studentList.deleteByScode();
                    break;
                case 6:
                    //chua co searchByCourseName
                    break;
                case 7:
                    System.out.print("Enter sCode to search: ");
                    String sCode = Validation.getString();
                    studentList.searchByScode(sCode);
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }


    public static void displayRegisterMenu() {
        System.out.println("---------- Manage Register List ----------\n");
        System.out.println("1. Input & add to the end");
        System.out.println("2. Display data");
        System.out.println("3. Save course list to file");
        System.out.println("4. Sort by scode and ccode");
        System.out.println("5. Update mark by scode and ccode");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printRegisterMenu() throws IOException {
        while (true){
            RegisterList registerList = new RegisterList();
            Register register = new Register();
            //tu dien pathFile nha
            File file = new File("");
            displayRegisterMenu();
            int choice = Validation.getInteger(0,5);
            switch (choice){
                case 1:
                    registerList.addLast(register);
                    break;
                case 2:
                    registerList.display();
                    break;
                case 3:
                    registerList.saveData(file);
                    break;
                case 4:
                    registerList.sort();
                    break;
                case 5:
                    System.out.print("Enter sCode to update mark: ");
                    String sCode = Validation.getString();
                    System.out.print("Enter cCode to update mark: ");
                    String cCode = Validation.getString();
                    System.out.print("Enter mark to update mark: ");
                    double mark = Validation.getDouble();
                    registerList.updateMark(sCode,cCode,mark);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void printMenu() throws IOException {
        while(true){
            displayMainMenu();
            int choice = Validation.getInteger(0,3);
            switch (choice){
                case 1:
                    printCourseMenu();
                    break;
                case 2:
                    printStudentMenu();
                    break;
                case 3:
                    printRegisterMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
}
