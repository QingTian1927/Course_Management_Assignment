/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coursemanager.model;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import coursemanager.io.DataManager;
import coursemanager.io.DataParser;
import coursemanager.util.Formatter;
import coursemanager.util.Validation;


/**
 * @author NgocHien-PC
 */
public class StudentList extends CommonList<Student> {
    private Scanner sc = new Scanner(System.in);

    public Student getStudentDetailsFromUser() {
        // Prompt user for course details
        System.out.println("Please enter the following student details:");

        String scode = inputScode();

        String sname = inputName();

        int sbyear = inputByear();

        return new Student(scode, sname, sbyear);

    }

    public String inputScode() {
        System.out.print("Enter student code: ");
        String scode = sc.nextLine();
        return Formatter.normalizeId(scode);
    }

    public String inputName() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        return Formatter.normalizeName(name);
    }

    public int inputByear() {
        System.out.print("Enter birth year (positive integer): ");
        while (true) {
            try {
                int byear = sc.nextInt();
                sc.nextLine();
                if (byear <= 0) {
                    throw new InputMismatchException();
                }
                return byear; // Return the valid birth year
            } catch (InputMismatchException e) {
                System.out.println("Input a positive integer.");
                sc.nextLine(); // Clear the invalid input
            }
        }
    }


    public void addStudent() {
        String scode = inputScode();
        String name = inputName();
        int byear = inputByear();
        if (searchByScode(scode) == null) {
            Student newStudent = new Student(scode, name, byear);
            super.addLast(newStudent);
        } else {
            System.out.println("Duplicated Student Code");
        }
    }

    public void addStudent(Student newStudent) {
        if (searchByScode(newStudent.getScode()) == null) {
            super.addLast(newStudent);
        } else {
            System.out.println("Duplicated Student Code");
        }
    }

    @Override
    public CommonList<Student> sort() {
        for (Node<Student> p = head; p != null; p = p.next) {
            for (Node<Student> q = p.next; q != null; q = q.next) {
                if (p.data.getScode().compareTo(q.data.getScode()) < 0) {
                    Student t = p.data;
                    p.data = q.data;
                    q.data = t;
                }
            }
        }
        return this;
    }

    @Override
    public void display() {
        if (!this.isEmpty()) {
            Node<Student> p = head;

            while (p != null) {
                p.data.displayStudentInfo();
                System.out.println("---------------------");
                p = p.next;
            }
        }
    }

    public Node<Student> searchByScode() {
        String scode = inputScode();
        Node<Student> result = null;
        if (!this.isEmpty()) {
            Node<Student> p = head;
            while (p != null) {
                if (p.data.getScode().equals(scode)) {
                    result = p;
                    break;
                }
                p = p.next;
            }
        }
        return result;
    }

    public Node<Student> searchByScode(String scode) {
        Node<Student> result = null;
        if (!this.isEmpty()) {
            Node<Student> p = head;
            while (p != null) {
                if (p.data.getScode().equals(scode)) {
                    result = p;
                    break;
                }
                p = p.next;
            }
        }
        return result;
    }

    public void deleteByScode() {
        String scode = inputScode();
        if (searchByScode(scode) != null) {
            if (head.data.getScode().equals(scode)) {
                Node<Student> p = head;
                head = head.next;
                p.next = null;
                return;
            }
            Node<Student> p = head;
            while (p.next != null) {
                if (p.next.data.getScode().equals(scode)) {
                    Node<Student> temp = p.next;
                    p.next = p.next.next;
                    temp.next = null;
                }
            }
        }
    }

    public Node<Student> searchStudentByName() {
        if (isEmpty()) {
            return null;
        }
        String name = inputName();
        Node<Student> p = head;
        while (p != null) {
            if (p.data.getName().equals(name)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public Node<Student> searchRegisteredCoursesByScode(DataManager manager) {
        String scode = inputScode();
        Node<Student> foundStudent = searchByScode(scode);

        if (foundStudent != null) {
            CourseList courseList = manager.getCourseList();
            RegisterList registerList = manager.getRegisterList();

            for (Node<Register> p = registerList.head; p != null; p = p.next) {
                if (p.data.getScode().equals(scode)) {
                    Node<Course> course = manager.getCourseList().searchByCcode(p.data.getCcode());
                    System.out.println(course.toString());
                }
            }
            return foundStudent;
        }

        return null;
    }

    public Node<Student> searchByName(String sname) {
        Node<Student> result = null;
        if (!this.isEmpty()) {
            Node<Student> p = head;
            while (p != null) {
                if (p.data.getName().equals(sname)) {
                    result = p;
                    break;
                }
                p = p.next;
            }
        }
        return result;
    }

    public void load() throws IOException {
        DataParser<Student> dataParser = new DataParser<>() {
            @Override
            public Student parse(String data) {
                String[] properties = data.split(DataParser.PROPERTY_SEPARATOR);
                if (properties.length != 3) {   // số thuộc tính trong Student
                    return null;
                }

                String scode = properties[0].trim();
                String name = properties[1].trim();
                int byear = Validation.parseInt(properties[2].trim());

                return new Student(scode, name, byear);
            }
        };

        File file = new File(DataManager.STUDENT_SAVE_FILE);
        this.readFile(file, dataParser);
    }

    public void save() throws IOException {
        this.saveFile(new File(DataManager.STUDENT_SAVE_FILE), Student::toDataString);
    }
}
