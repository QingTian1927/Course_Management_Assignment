/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coursemanager.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import coursemanager.io.DataManager;
import coursemanager.io.DataParser;
import coursemanager.util.Formatter;
import coursemanager.util.Validation;

/**
 * @author NgocHien-PC
 */
public class StudentList extends CommonList<Student> {
	private Scanner sc = new Scanner(System.in);

//	public Student getStudentDetailsFromUser() {
//		// Prompt user for course details
//		System.out.println("Please enter the following student details:");
//
//		String scode = inputScode().toUpperCase();
//
//		String sname = inputName();
//
//		int sbyear = inputByear();
//
//		return new Student(scode, sname, sbyear);
//
//	}

	public String inputScode() {
		System.out.print("Enter student code: ");
		String scode = Validation.getString();
		return Formatter.normalizeId(scode);
	}

	public String inputName() {
		System.out.print("Enter student name: ");
		String name = Validation.getString();
		return Formatter.normalizeName(name);
	}

	public int inputByear() {
                int currentYear = LocalDate.now().getYear();
		int byear = Validation.getInteger("Enter birth year (positive integer): ",
				"Student's age must be from 18 to 99", currentYear - 99, currentYear - 18);

		return byear; 

	}

	public void addStudent() {
		String scode = inputScode();
		
		if (searchByScode(scode) == null) {
                    String name = inputName();
                    int byear = inputByear();
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
		String scode = inputScode().toUpperCase();
		if (searchByScode(scode) != null) {
			if (head.data.getScode().equals(scode)) {
				Node<Student> p = head;
				head = head.next;
				p.next = null;
				System.out.println("Student Code deleted successfully.");
				return;
			}
			Node<Student> p = head;
			while (p.next != null) {
				if (p.next.data.getScode().equals(scode)) {
					Node<Student> temp = p.next;
					p.next = p.next.next;
					temp.next = null;
					System.out.println("Student Code deleted successfully.");
					return;
				} p = p.next;
			}
		}
		System.out.println("Student not found.");
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

	public CourseList searchRegisteredCoursesByScode(DataManager manager) {
		String scode = inputScode();
		Node<Student> foundStudent = searchByScode(scode);
                CourseList courseList = new CourseList();
                
		if (foundStudent != null) {
			
			RegisterList registerList = manager.getRegisterList();

			for (Node<Register> p = registerList.head; p != null; p = p.next) {
				if (p.data.getScode().equals(scode)) {
					Node<Course> course = manager.getCourseList().searchByCcode(p.data.getCcode());
					courseList.addLast(course.data);
				}
			}if(courseList.isEmpty()){
                            System.out.println("No course registered by student with " + scode);
                        }			
		}else System.out.println("No registered student found with code: " + scode);

		return courseList;
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
		DataParser<Student> dataParser = (String data) -> {
                    String[] properties = data.split(DataParser.PROPERTY_SEPARATOR);
                    if (properties.length != 3) { // số thuộc tính trong Student
                        return null;
                    }
                    
                    String scode = properties[0].trim();
                    String name = properties[1].trim();
                    int byear = Validation.parseInt(properties[2].trim());
                    
                    return new Student(scode, name, byear);
                };

		File file = new File(DataManager.STUDENT_SAVE_FILE);
		this.readFile(file, dataParser);
	}

	public void save() throws IOException {
		this.saveFile(new File(DataManager.STUDENT_SAVE_FILE), Student::toDataString);
	}
}
