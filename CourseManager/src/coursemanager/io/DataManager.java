package coursemanager.io;

import coursemanager.model.CourseList;
import coursemanager.model.RegisterList;

public final class DataManager {
    private static DataManager instance = null;
    private static CourseList courseList;
    private static RegisterList registerList;
    private static StudentList studentList;

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
