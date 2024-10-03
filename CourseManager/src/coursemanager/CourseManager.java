package coursemanager;

import coursemanager.io.DataManager;
import coursemanager.model.Course;
import coursemanager.model.CourseList;
import coursemanager.model.Node;
import coursemanager.model.Register;
import coursemanager.model.RegisterList;
import coursemanager.model.Student;
import coursemanager.model.StudentList;
import coursemanager.ui.Menu;
import coursemanager.util.Validation;

import java.io.IOException;

@SuppressWarnings("CallToPrintStackTrace")
public class CourseManager {

	public static void main(String[] args) {
		// Singleton cái chết
		DataManager dataManager = DataManager.getInstance();

		try {
			dataManager.getCourseList().load();
		} catch (IOException e) {
			System.out.println("[FATAL] Failed to read courses.txt");
			e.printStackTrace();
			System.exit(1);
		}

		try {
			dataManager.getStudentList().load();
		} catch (IOException e) {
			System.out.println("[FATAL] Failed to read students.txt");
			e.printStackTrace();
			System.exit(1);
		}

		try {
			dataManager.getRegisterList().load();
		} catch (IOException e) {
			System.out.println("[FATAL] Failed to read registers.txt");
			e.printStackTrace();
			System.exit(1);
		}

		boolean isRunning = true;
		while (isRunning) {
			Menu.displayMainMenu();
			char choice = Validation.getChar();

			switch (choice) {
			case '1':
				manageCourses(dataManager);
				break;
			case '2':
				manageStudents(dataManager);
				break;
			case '3':
				manageRegisters(dataManager);
				break;
			case '0':
				isRunning = false;
				System.out.println("Exiting the program.");
				break;
			default:
				System.out.println("Invalid option. Try again!");
			}
		}
	}

	public static void manageCourses(DataManager dataManager) {
		CourseList courseList = dataManager.getCourseList();
		boolean isManaging = true;

		while (isManaging) {
			Menu.displayCourseMenu();
			String choice = Validation.getString();

			switch (choice) {
			case "1":
				Course newCourse1 = courseList.getCourseDetailsFromUser();
				courseList.addLast(newCourse1);
				System.out.println("Course added successfully.");
				break;
			case "2":
				courseList.display();
				break;
			case "3":
				try {
					courseList.save();
					System.out.println("Courses saved successfully.");
				} catch (IOException e) {
					System.out.println("[ERROR] Failed to save courses.");
					e.printStackTrace();
				}
				break;
			case "4":
				System.out.print("Enter course code to search: ");
				String ccodetoSearch = Validation.getString();
				Node<Course> foundCourseNode = courseList.searchByCcode(ccodetoSearch);

				if (foundCourseNode != null) {
					// If the course is found, print its information using the toString method (so I
					// want to declare data to be public)
					System.out.println(foundCourseNode.data.toString());
				}
				break;
			case "5":
				System.out.print("Enter course code to delete: ");
				String ccodetoDelete = Validation.getString();
				courseList.deleteByCcode(ccodetoDelete);
				break;
			case "6":
				courseList.sort();
				System.out.println("Courses sorted by code successfully.");
				break;
			case "7":
				Course newCourse2 = courseList.getCourseDetailsFromUser();
				courseList.addFirst(newCourse2);
				System.out.println("Course added successfully.");
				break;
			case "8":
				System.out.print("Enter a position to add after: ");
				int k = Validation.getInteger(0, courseList.size() - 1);
				Course afterCourse = courseList.getCourseDetailsFromUser();
				courseList.insertAfterIndex(k, afterCourse);
				break;
			case "9":
				System.out.print("Enter a position to delete: ");
				int deletePosition = Validation.getInteger(0, courseList.size() - 1);
				courseList.deleteToIndex(deletePosition);
				System.out.println("Course at position " + deletePosition + " deleted successfully.");
				break;
			case "10":
				System.out.print("Enter subject name to search:");
				String subjectName = Validation.getString();
				CourseList searchResults = courseList.searchByName(subjectName);
				if (!searchResults.isEmpty()) {
					System.out.println("Courses found:");
					searchResults.display();
				} else {
					System.out.println("No courses found with subject name: " + subjectName);
				}
				break;
			case "0":
				isManaging = false;
				break;
			default:
				System.out.println("Invalid option. Try again!");
			}
		}
	}

	public static void manageStudents(DataManager dataManager) {
		StudentList studentList = dataManager.getStudentList();
		RegisterList registerList = dataManager.getRegisterList();
		boolean isManaging = true;

		while (isManaging) {
			Menu.displayStudentMenu();
			String choice = Validation.getString();

			switch (choice) {
			case "1":
				Student student = studentList.getStudentDetailsFromUser();
				studentList.addStudent(student);
				System.out.println("Student added successfully.");
				break;
			case "2":
				studentList.display();
				break;
			case "3":
				try {
					studentList.save();
					System.out.println("Student saved successfully.");
				} catch (IOException e) {
					System.out.println("[ERROR] Failed to save courses.");
					e.printStackTrace();
				}
				break;
			case "4":
				String scodetoSearch = studentList.inputScode();
				Node<Student> foundStudentNode1 = studentList.searchByScode(scodetoSearch);

				if (foundStudentNode1 != null) {
					// If the student is found, print its information using the toString method (so
					// I
					// want to declare data to be public)
					System.out.println(foundStudentNode1.data.toString());
				} else {
					System.out.println("No course found with code: " + foundStudentNode1);
				}
				break;
			case "5":
				studentList.deleteByScode();
				break;
			case "6":
				String studName = studentList.inputName();
				Node<Student> foundStudentNode2 = studentList.searchByName(studName);

				if (foundStudentNode2 != null) {
					// If the student is found, print its information using the toString method (so
					// I
					// want to declare data to be public)
					System.out.println(foundStudentNode2.data.toString());
				} else {
					System.out.println("No course found with code: " + foundStudentNode2);
				}
				break; 
			case "7":
				String stuCode = studentList.inputScode(); 
				Node<Register> registeredStudent = registerList.findRegisteredStudent(stuCode);
				if (registeredStudent != null) {
					// If the student is found, print its information using the toString method (so
					// I
					// want to declare data to be public)
					System.out.println(registeredStudent.data.toString());
				} else {
					System.out.println("No registered student found with code: " + stuCode);
				}
				break;
			case "0":
				isManaging = false;
				break;
			default:
				System.out.println("Invalid option. Try again!");
			}
		}
	}

	public static void manageRegisters(DataManager dataManager) {
		RegisterList registerList = dataManager.getRegisterList();
		boolean isManaging = true;

		while (isManaging) {
			Menu.displayRegisterMenu();
			String choice = Validation.getString();

			switch (choice) {
			case "1":
				System.out.print("Enter course code to register: ");
				String ccode = Validation.getString();
				System.out.print("Enter student code to register: ");
				String scode = Validation.getString();
				registerList.registerCourse(ccode, scode);
				break;
			case "2":
				registerList.display();
				break;
			case "3":
				try {
					registerList.save();
					System.out.println("Save registered student successfully.");
				} catch (IOException e) {
					System.out.println("[ERROR] Failed to save courses registration");
				}
				break;
			case "4":
				registerList.sort();
				System.out.println("Registration sorted by code successfully.");
				break;
			case "5":
				System.out.print("Enter course code to update mark: ");
				String ccodeUpdate = Validation.getString();
				System.out.print("Enter student code to update mark: ");
				String scodeUpdate = Validation.getString();
				System.out.print("Enter mark you want to update: ");
				double mark = Validation.getDouble();
				registerList.updateMark(scodeUpdate, ccodeUpdate, mark);
				break;
			case "0":
				isManaging = false;
				break;
			default:
				System.out.println("Invalid option. Try again!");
			}
		}
	}
}
