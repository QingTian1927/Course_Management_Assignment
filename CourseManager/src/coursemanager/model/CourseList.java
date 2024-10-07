package coursemanager.model;

import coursemanager.io.DataManager;
import coursemanager.io.DataParser;
import coursemanager.util.Validation;

import java.io.File;
import java.io.IOException;

public class CourseList extends CommonList<Course> {
    public Course getCourseDetailsFromUser() {
        System.out.println("Please enter the following course details:");
        System.out.print("Enter course code: ");
        String ccode = Validation.getString().toUpperCase();
        if (searchByCcode(ccode) != null) {
            System.out.println("this course has been registered");
            return null;
        }

        System.out.print("Enter course short code: ");
        String scode = Validation.getString();

        System.out.print("Enter course name: ");
        String sname = Validation.getString();

        System.out.print("Enter semester: ");
        String semester = Validation.getString();

        System.out.print("Enter year: ");
        String year = Validation.getStringYear();

        int seats = Validation.getInteger("Enter seat: ", "Seat must be greater than 0", 0, Integer.MAX_VALUE);
        int registered = Validation.getInteger("Enter number of registered student for this course: ", "Registered number of student must be greater than 0 and lower than the number of seats.", 0, seats);
        double price = Validation.getDouble("Enter price of the course: ", "Price should be from 0 to 1000000 or in valid type.", 0, 1000000);

        return new Course(ccode, scode, sname, semester, year, seats, registered, price);
    }


    public void addLast(Course course) {
        if (course == null) {
            return;
        }

        if (searchByCcode(course.getCcode()) != null) {
            System.out.println("this course has been registered");
            return;
        }
        super.addLast(course);
    }

    public void addFirst(Course course) {
        if (course == null) {
            return;
        }

        if (searchByCcode(course.getCcode()) != null) {
            System.out.println("this course has been registered");
            return;
        }
        super.addFirst(course);
    }

    public void insertAfterIndex(int k, Course course) {
        if (searchByCcode(course.getCcode()) != null) {
            System.out.println("this course has been registered");
            return;
        }
        this.insert(k + 1, course);
    }

    public void deleteToIndex(int k) {
        if (k >= this.size()) {
            this.clear();
        } else {
            this.head = this.getByIndex(k);
        }
    }
    
    public CourseList findCourseList(Node<Course> courseFound) {
    	CourseList courseList = new CourseList();
    	courseList.addLast(courseFound.data);
    	return courseList.sort();
    }

    public Node<Course> searchByCcode(String Ccode) {
        Node<Course> temp = this.head;
        while (temp != null) {
            if (temp.data.getCcode().equals(Ccode)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public CourseList searchByName(String sname) {
        CourseList a = new CourseList();
        Node<Course> temp = this.head;
        while (temp != null) {
            if (temp.data.getSname().toLowerCase().contains(sname.toLowerCase())) {
                a.addLast(temp.data);
            }
            temp = temp.next;
        }
        return a.sort();
    }

    public void save() throws IOException {
        this.saveFile(new File(DataManager.COURSE_SAVE_FILE), Course::toDataString);
    }

    public void deleteByCcode(String Ccode) {
        if (this.head == null) {
            System.out.println("No course to delete.");
            return;
        }
        Node<Course> courseFound = this.searchByCcode(Ccode);

        if (courseFound == null) {
            System.out.println("Course not found.");
            return;
        }

        if (courseFound.data.getRegistered() == 0) {
            this.delete(courseFound);
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("This course has been registered and cannot be deleted.");
        }
    }

    public CourseList sort() {
        CourseList a = new CourseList();
        if (this.head == null) {
            return a;
        }
        Node<Course> temp = this.head;
        while (temp != null) {
            a.addLast(temp.data);
            temp = temp.next;
        }
        Node<Course> p = a.head;
        while (p != null) {
            Node<Course> q = p.next;
            while (q != null) {
                if (p.data.getCcode().compareTo(q.data.getCcode()) > 0) {
                    this.swap(p, q);
                }
                q = q.next;
            }
            p = p.next;
        }
        return a;
    }

    public void display() {
        if (this.head == null) {
            System.out.println("No course yet");
            return;
        }

        Node<Course> temp = this.head;
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-10s | %-10s | %-30s | %-10s | %-10s | %-7s | %-12s | %s\n",
                "CourseID", "SubjectID", "Subject Name", "Semester", "Year", "Seats", "Registered", "Price"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        while (temp != null) {
            System.out.printf(
                    "%-10s | %-10s | %-30s | %-10s | %-10s | %-7d | %-12d | %.3f\n",
                    temp.data.getCcode(),
                    temp.data.getScode(),
                    temp.data.getSname(),
                    temp.data.getSemester(),
                    temp.data.getYear(),
                    temp.data.getSeats(),
                    temp.data.getRegistered(),
                    temp.data.getPrice()
            );

            // System.out.println("---------------------");
            temp = temp.next;
        }
    }

    public void load() throws IOException {
        DataParser<Course> dataParser = (String data) -> {
        	
            String[] properties = data.split(DataParser.PROPERTY_SEPARATOR);
            if (properties.length != 8) {
                return null;
            }

            String ccode = properties[0].trim();
            String scode = properties[1].trim();
            String sname = properties[2].trim();
            String semester = properties[3].trim();
            String year = properties[4].trim();
            int seats = Validation.parseInt(properties[5].trim());
            int registered = Validation.parseInt(properties[6].trim());
            double price = Validation.parseDouble(properties[7].trim());

            return new Course(ccode, scode, sname, semester, year, seats, registered, price);
        };


        File file = new File(DataManager.COURSE_SAVE_FILE);
        this.readFile(file, dataParser);
    }

}