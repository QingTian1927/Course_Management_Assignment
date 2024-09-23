import java.util.Scanner;

import coursemanager.model.Course;
import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;
import coursemanager.util.Validation;

public class Menu{

    CourseList courseList = new CourseList();
    Course course = new Course();
    ReaderList readerList = new ReaderList();
    RegisterList registerList = new RegisterList();

    public void displayInfoToStore(){
        System.out.println("==========List Data To Store==========");
        System.out.println("1.Course List");
        System.out.println("2.Reader List");
        System.out.println("3.Registering List");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

    public void printInfoToStore(){
        Scanner sc = new Scanner(System.in);
        int choice = Validation.getInteger(0,3);
        displayInfoToStore();
        switch (choice){
            case 1:
                printMenuCourseList();
                break;
            case 2:
                printMenuReaderList();
                break;
            case 3:
                System.out.println("Registering List");
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public static void displayMenuCourseList() {
        System.out.println("\nCourse List Menu:");
        System.out.println("1. Load data from file");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save course list to file");
        System.out.println("5. Search by ccode");
        System.out.println("6. Delete by ccode");
        System.out.println("7. Sort by ccode");
        System.out.println("8. Input & add to beginning");
        System.out.println("9. Add after position k");
        System.out.println("10. Delete position k");
        System.out.println("11. Search by name");
        System.out.println("12. Search booked by rcode");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void printMenuCourseList(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            int choice = Validation.getInteger(0,12);
            displayMenuCourseList();
            switch (choice){
                case 1:
                    courseList.loadDataFromFile();
                    break;
                case 2:
                    courseList.addLast(course);
                    break;
                case 3:
                    courseList.displayCourse();
                    break;
                case 4:
                    courseList.saveDataToFile();
                    break;
                case 5:
                    String cCode = Validation.getString();
                    courseList.searchByCcode(cCode);
                    break;
                case 6:
                    String cCode1 = Validation.getString();
                    courseList.deleteByCcode(cCode1);
                    break;
                case 7:
                    courseList.sort();
                    break;
                case 8:
                    courseList.addFirst(course);
                    break;
                case 9:
                    int k = Validation.getInteger(1,10);
                    courseList.insertAfterIndex(k, course);
                    break;
                case 10:
                    int k1 = Validation.getInteger(1,10);
                    courseList.deleteToIndex(k1);
                    break;
                case 11:
                    String sName = Validation.getString();
                    courseList.searchByName(sName);
                    break;
                case 12:
                    String rCode = Validation.getString();
                    courseList.searchByRcode(rCode);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenuReaderList() {
        System.out.println("\nReader List Menu:");
        System.out.println("1. Load data from file");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save course list to file");
        System.out.println("5. Search by ccode");
        System.out.println("6. Delete by ccode");
        System.out.println("7. Search by cousre name");
        System.out.println("8 .Search registered code by scode");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void printMenuReaderList(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            int choice = Validation.getInteger(0,8);
            displayMenuReaderList();
            switch (choice){
                case 1:
                    readerList.loadDataFromFile();
                    break;
                case 2:
                    readerList.addLast(course);
                    break;
                case 3:
                    readerList.displayCourse();
                    break;
                case 4:
                    readerList.saveDataToFile();
                    break;
                case 5:
                    String cCode = Validation.getString();
                    readerList.searchByCcode(cCode);
                    break;
                case 6:
                    String cCode1 = Validation.getString();
                    readerList.deleteByCcode(cCode1);
                    break;
                case 7:
                    readerList.searchByName();
                    break;
                case 8:
                    readerList.searchByRegisteredCode();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void displayMenuRegisterList() {
        System.out.println("\nRegister List Menu:");
        System.out.println("1. Load data from file");
        System.out.println("2. Input & add to the end");
        System.out.println("3. Display data");
        System.out.println("4. Save course list to file");
        System.out.println("5. Sort by scode and ccode");
        System.out.println("6. Update mark by scode and ccode");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void printMenuRegisterList(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            int choice = Validation.getInteger(0,8);
            displayMenuRegisterList();
            switch (choice){
                case 1:
                    registerList.loadDataFromFile();
                    break;
                case 2:
                    registerList.addFirst(registerList);
                    break;
                case 3:
                    registerList.display();
                    break;
                case 4:
                    registerList.saveDataToFile();
                    break;
                case 5:
                    registerList.sort();
                    break;
                case 6:
                    String scode = Validation.getString();
                    String ccode = Validation.getString();
                    double mark = Validation.getDouble();
                    registerList.updateMark(scode, ccode,mark);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
}
