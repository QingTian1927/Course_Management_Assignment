package coursemanager.model;

import java.util.Date;

public class RegisterList extends CommonList<Register> {
    private CourseList courseList; // Custom linked list for courses
    private StudentList studentList; // Custom linked list for students


    // Add new register at the beginning (if we care about recent entries)
    public void addFirst(Register register) {
        super.addFirst(register);
    }

    // Add new register at the end
    public void addLast(Register register) {
        super.addLast(register);
    }

    // Method to register a course for a student
    public void registerCourse(String ccode, String scode) {
        Node<Course> courseNode = courseList.searchByCcode(ccode); // Search for the course by course code
        Node<Student> studentNode = studentList.searchByScode(scode); // Search for the student by student code (ID)

        if (courseNode == null) {
            System.out.println("Course does not exist.");
            return;
        }

        if (studentNode == null) {
            System.out.println("Student does not exist.");
            return;
        }

        Course course = courseNode.data;
        Student student = studentNode.data;

        // Check if the course has available seats
        if (course.getSeats() <= 0) {
            System.out.println("No seats available for the course.");
            return;
        }

        Date today = new Date();
        Register newRegistration = new Register(ccode, scode, today, 0, 0);
        addFirst(newRegistration);

        course.updateSeatAndRegister(-1, 1);

        System.out.println("Course successfully registered for student: " + scode);
    }

    // Update the mark of a registration
    public void updateMark(String scode, String ccode, double newmark) {
        Node<Register> current = head;
        while (current != null) {
            if (scode.equals(current.data.getScode()) && ccode.equals(current.data.getCcode())) {
                current.data.setMark(newmark);
                return;
            }
            current = current.next;
        }
        System.out.println("Registration not found. Update mark failed!");
    }

    @Override
    public CommonList<Register> sort() {
        if (head == null) {
            return null;
        }

        for (Node<Register> i = head; i != null; i = i.next) {
            Node<Register> minNode = i;

            for (Node<Register> j = i.next; j != null; j = j.next) {
                if (shouldSwap(minNode, j)) {
                    minNode = j;
                }
            }

            if (minNode != i) {
                swap(i, minNode);
            }
        }

        return this;
    }

    private boolean shouldSwap(Node<Register> a, Node<Register> b) {
        int ccodeComparison = a.data.getCcode().compareTo(b.data.getCcode());
        if (ccodeComparison > 0) {
            return true;
        }
        if (ccodeComparison == 0) {
            return a.data.getScode().compareTo(b.data.getScode()) > 0;
        }
        return false;
    }

    @Override
    public void display() {
        if (head == null) {
            System.out.println("No registrations available.");
            return;
        }

        Node<Register> current = head;
        while (current != null) {
            current.data.displayRegistrationInfo();
            System.out.println("---------------------");
            current = current.next;
        }
    }
}
