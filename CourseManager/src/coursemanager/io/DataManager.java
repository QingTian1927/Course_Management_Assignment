package coursemanager.io;

import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;
import coursemanager.model.StudentList;

// Có lẽ class này không nên tồn tại

public final class DataManager {
    private static DataManager instance = null;
    private static CourseList courseList = null;
    private static RegisterList registerList = null;
    private static StudentList studentList = null;

    public static final String COURSE_SAVE_FILE = "C:\\Users\\USER\\Documents\\GitHub\\Course_Management_Assignment\\CourseManager\\data\\courses.txt";
    public static final String REGISTER_SAVE_FILE = "C:\\Users\\USER\\Documents\\GitHub\\Course_Management_Assignment\\CourseManager\\data\\registers.txt";
    public static final String STUDENT_SAVE_FILE = "C:\\Users\\USER\\Documents\\GitHub\\Course_Management_Assignment\\CourseManager\\data\\students.txt";

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            instance.setCourseList(new CourseList());
            instance.setStudentList(new StudentList());
            instance.setRegisterList(new RegisterList());
            return instance;
        }
        return instance;
    }

    private void setCourseList(CourseList courseList) {
        DataManager.courseList = courseList;
    }

    private void setRegisterList(RegisterList registerList) {
        DataManager.registerList = registerList;
    }

    private void setStudentList(StudentList studentList) {
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
