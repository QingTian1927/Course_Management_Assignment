package coursemanager.io;

import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;
import coursemanager.model.StudentList;

public final class DataManager {
    private static DataManager instance = null;
    private static CourseList courseList;
    private static RegisterList registerList;
    private static StudentList studentList;

    public static final String COURSE_SAVE_FILE = "courses.txt";
    public static final String REGISTER_SAVE_FILE = "registers.txt";
    public static final String STUDENT_SAVE_FILE = "students.txt";

    private DataManager() {
        courseList = new CourseList();
        registerList = new RegisterList();
        studentList = new StudentList();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            return instance;
        }
        return instance;
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public RegisterList getRegisterList() {
        return registerList;
    }

    public StudentList getStudentList() {
        return studentList;
    }
}
