package coursemanager.ui;

public final class Menu{
    private Menu() {}

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
}
