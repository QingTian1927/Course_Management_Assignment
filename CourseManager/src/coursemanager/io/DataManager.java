package coursemanager.io;

import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;
import coursemanager.model.StudentList;

public final class DataManager {
    private static DataManager instance = null;
    private static CourseList courseList = null;
    private static RegisterList registerList = null;
    private static StudentList studentList = null;

    public static final String COURSE_SAVE_FILE = "courses.txt";
    public static final String REGISTER_SAVE_FILE = "registers.txt";
    public static final String STUDENT_SAVE_FILE = "students.txt";

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            return instance;
        }
        return instance;
    }

    public static void setCourseList(CourseList courseList) {
        DataManager.courseList = courseList;
    }

    public static void setRegisterList(RegisterList registerList) {
        DataManager.registerList = registerList;
    }

    public static void setStudentList(StudentList studentList) {
        DataManager.studentList = studentList;
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
