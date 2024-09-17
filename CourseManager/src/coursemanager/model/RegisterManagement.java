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

	RegisterNode register_Head;
	RegisterNode register_Tail;

	public RegisterManagement() {
		register_Head = null;
		register_Tail = null;
	}

	// Add new register at First (if we concern about recent entries)
	public void addFirstRegister(Register register) {
		RegisterNode new_RegisterNode = new RegisterNode(register);
		if (register_Head == null) {
			register_Head = new_RegisterNode;
			register_Tail = new_RegisterNode;
		} else {
			new_RegisterNode.next = register_Head;
			register_Head = new_RegisterNode;
		}
	}

	// Add new register at Last
	public void addLastRegister(Register register) {
		RegisterNode new_RegisterNode = new RegisterNode(register);
		if (register_Head == null) {
			register_Head = new_RegisterNode;
			register_Tail = new_RegisterNode;
		} else {
			register_Tail.next = new_RegisterNode;
			register_Tail = new_RegisterNode;
		}
	}
	
	
	// Load data from file (Assume txt file has format split each atribute with " ,
	// "
//	public void loadRegisteringFromFile(String filename, int mode) {
//		try (Scanner scanner = new Scanner(new FileReader(filename))) {
//			while (scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				if (line.length() != 5) {
//					System.out.println("Cannot format this line: " + line);
//					continue;
//				}
//				String[] content = line.split(",");
//				String ccode = content[0];
//				String scode = content[1];
//				Date bdate = new SimpleDateFormat("dd-MMM-yyyy").parse(content[2]);
//				double mark = Double.parseDouble(content[3]);
//				int state = Integer.parseInt(content[4]);
//				/*
//				 * I prefer using addFirst in here but i'll declare a mode in here 
//				 * 1: Add First
//				 * 2: Add Last
//				 */
//
//				if (mode == 1) {
//					registeringlist.addFirstRegister(new Register(ccode, scode, bdate, mark, state));
//				} else {
//					registeringlist.addLastRegister(new Register(ccode, scode, bdate, mark, state));
//				}
//
//			}
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
//	}

	public void registerCourse(String ccode, String scode) {
		// Get the course code in course Linked List
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

	public void displayRegistration() {
		if (register_Head == null) {
			System.out.println("No registrations available.");
			return;
		}
		RegisterNode start = register_Head;
		while (start != null) {
			start.data.displayRegistrationInfor();
			System.out.println("---------------------");
			start = start.next;
		}
	}

	public void saveRegisterToFile(String filename) {

	}

	// Sort ascending based on ccode, if they are the same, sort by scode
	public void sortRegistrationList() {
		if (register_Head == null) {
			return;
		}

		for (RegisterNode i = register_Head; i != null; i = i.next) {
			RegisterNode minNode = i;
			for (RegisterNode j = i.next; j != null; j = j.next) {
				if (minNode.data.getCcode().compareTo(j.data.getCcode()) > 0
						|| (minNode.data.getCcode().equals(j.data.getCcode())
								&& minNode.data.getScode().compareTo(j.data.getScode()) > 0)) {
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
	
	
	// Update mark
	public void updateMark(String scode, String ccode, double newmark) {
		RegisterNode start = register_Head;
		while(start != null) {
			if(scode.equals(start.data.getScode()) && ccode.equals(start.data.getCcode())) {
				start.data.setMark(newmark);
				break;
			}
			start = start.next;
		}
		System.out.println("Registration not found. Update mark fail!!");
	}

}
