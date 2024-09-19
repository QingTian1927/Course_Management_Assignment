package coursemanager.model;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegisterManagement {
    private CourseList courses; // Custom linked list for courses
    private StudentList students; // Custom linked list for students

    private Node<Register> registerHead;
    private Node<Register> registerTail;

    public RegisterManagement() {
        registerHead = null;
        registerTail = null;
    }

    // Add new register at the beginning (if we care about recent entries)
    public void addFirstRegister(Register register) {
        Node<Register> newRegisterNode = new Node<>(register);
        if (registerHead == null) {
            registerHead = newRegisterNode;
            registerTail = newRegisterNode;
        } else {
            newRegisterNode.next = registerHead;
            registerHead = newRegisterNode;
        }
    }

    // Add new register at the end
    public void addLastRegister(Register register) {
        Node<Register> newRegisterNode = new Node<>(register);
        if (registerHead == null) {
            registerHead = newRegisterNode;
            registerTail = newRegisterNode;
        } else {
            registerTail.next = newRegisterNode;
            registerTail = newRegisterNode;
        }
    }

    // Method to register a course for a student
    public void registerCourse(String ccode, String scode) {
        Course course = courses.searchByCcode(ccode);
        Student student = students.searchByCode(scode);

        if (course == null) {
            System.out.println("Course does not exist.");
            return;
        }

        if (student == null) {
            System.out.println("Student does not exist.");
            return;
        }

        if (course.getSeats() <= 0) {
            System.out.println("No seats available for the course.");
            return;
        }

        Date today = new Date();
        Register newRegistration = new Register(ccode, scode, today, 0, 0);
        addFirstRegister(newRegistration);
        course.updateSeatAndRegister(-1, 1);
    }

    // Display the registration list
    public void displayRegistration() {
        if (registerHead == null) {
            System.out.println("No registrations available.");
            return;
        }

        Node<Register> current = registerHead;
        while (current != null) {
            current.data.displayRegistrationInfor();
            System.out.println("---------------------");
            current = current.next;
        }
    }

    // Sort the registration list (ascending by ccode, then scode)
    public void sortRegistrationList() {
        if (registerHead == null) {
            return;
        }

        for (Node<Register> i = registerHead; i != null; i = i.next) {
            Node<Register> minNode = i;
            for (Node<Register> j = i.next; j != null; j = j.next) {
                if (minNode.data.getCcode().compareTo(j.data.getCcode()) > 0 ||
                    (minNode.data.getCcode().equals(j.data.getCcode()) && minNode.data.getScode().compareTo(j.data.getScode()) > 0)) {
                    minNode = j;
                }
            }

            if (minNode != i) {
                Register temp = i.data;
                i.data = minNode.data;
                minNode.data = temp;
            }
        }
    }

    // Update the mark of a registration
    public void updateMark(String scode, String ccode, double newmark) {
        Node<Register> current = registerHead;
        while (current != null) {
            if (scode.equals(current.data.getScode()) && ccode.equals(current.data.getCcode())) {
                current.data.setMark(newmark);
                return;
            }
            current = current.next;
        }
        System.out.println("Registration not found. Update mark failed!");
    }
}
