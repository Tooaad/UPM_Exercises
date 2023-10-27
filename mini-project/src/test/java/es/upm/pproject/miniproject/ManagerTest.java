package es.upm.pproject.miniproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.pproject.exceptions.AlreadyEnrolledException;
import es.upm.pproject.exceptions.FullCourseException;
import es.upm.pproject.exceptions.IllegalArgumentException;
import es.upm.pproject.exceptions.NoSuchCourseException;
import es.upm.pproject.exceptions.NoSuchEnrollmentException;
import es.upm.pproject.exceptions.NoSuchStudentException;
import es.upm.pproject.pojos.Course;
import es.upm.pproject.pojos.Student;

import java.util.ArrayList;
import java.util.List;

class ManagerTest {

	private Manager manager;

	@BeforeEach
	void init() {
		manager = new Manager();
	}

	@Test
	void constructorTest() {
		assertEquals(0, manager.getNumStudents());
		assertEquals(0, manager.getNumStudents());
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when courseCode is negative")
	void registerCourseTest1() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerCourse(-5, "AED", "Roman"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when name is blank")
	void registerCourseTest2() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerCourse(1, "", "Roman"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when coordinator is blank")
	void registerCourseTest3() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerCourse(1, "AED", ""));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when name is null")
	void registerCourseTest4() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerCourse(1, null, "Roman"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when coordinator is null")
	void registerCourseTest5() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerCourse(1, "AED", null));
	}

	@Test
	@DisplayName("The course is registed correctly")
	void registerCourseTest6() throws IllegalArgumentException {
		manager.registerCourse(1, "AED", "Roman");
		assertEquals(1, manager.getNumCourses());
		Course course = manager.getCourse(1);
		assertEquals(1, course.getCourseCode());
		assertEquals("AED", course.getName());
		assertEquals("Roman", course.getCoordinator());
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when studentId is negative")
	void registerStudentTest1() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(-5, "Roman", "roman@upm.es"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when name is blank")
	void registerStudentTest2() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, "", "roman@upm.es"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when email is blank")
	void registerStudentTest3() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, "Roman", ""));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when name is null")
	void registerStudentTest4() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, null, "roman@upm.es"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when email is null")
	void registerStudentTest5() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, "Roman", null));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when email does not contain @")
	void registerStudentTest6() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, "Roman", "romanupm.es"));
	}

	@Test
	@DisplayName("Throws IllegalArgumentException when email ends in .")
	void registerStudentTest7() {
		assertThrows(IllegalArgumentException.class, () -> 
		manager.registerStudent(1, "Roman", "roman@upm."));
	}

	@Test
	@DisplayName("The student is registed correctly")
	void registerStudentTest8() throws IllegalArgumentException {
		manager.registerStudent(1, "Roman", "roman@upm.es");
		assertEquals(1, manager.getNumStudents());
		Student student = manager.getStudent(1);
		assertEquals(1, student.getStudentId());
		assertEquals("Roman", student.getName());
		assertEquals("roman@upm.es", student.getEmail());
	}

	@Test
	@DisplayName("Throws NoSuchStudentException when studentID is not registered")
	void enrollTest1() throws IllegalArgumentException {
		manager.registerCourse(1, "AED", "Roman");
		assertThrows(NoSuchStudentException.class, () -> 
		manager.enroll(1, 1));
	}

	@Test
	@DisplayName("Throws NoSuchCourseException when courseCode is not registered")
	void enrollTest2() throws IllegalArgumentException {
		manager.registerStudent(1, "Roman", "roman@upm.es");
		assertThrows(NoSuchCourseException.class, () -> 
		manager.enroll(1, 1));
	}

	@Test
	@DisplayName("Throws FullCourseException when the course >= 50")
	void enrollTest3() throws IllegalArgumentException, NoSuchStudentException, 
	NoSuchCourseException, FullCourseException, AlreadyEnrolledException {
		manager.registerCourse(1, "AED", "Roman");
		for(int i = 1; i <= 50; ++i) {
			manager.registerStudent(i, "Roman", "roman@upm.es");
			manager.enroll(i, 1);
		}
		manager.registerStudent(51, "Roman", "roman@upm.es");
		assertThrows(FullCourseException.class, () -> 
		manager.enroll(51, 1));
	}

	@Test
	@DisplayName("Throws AlreadyEnrolledException when studentID is registered twice")
	void enrollTest4() throws IllegalArgumentException, NoSuchStudentException,
	NoSuchCourseException, FullCourseException, AlreadyEnrolledException {
		manager.registerCourse(1, "AED", "Roman");
		manager.registerStudent(1, "Roman", "roman@upm.es");
		manager.enroll(1, 1);
		assertThrows(AlreadyEnrolledException.class, () -> 
		manager.enroll(1, 1));
	}

	@Test
	@DisplayName("Enrollment executed correctly")
	void enrollTest5() 
			throws NoSuchStudentException, NoSuchCourseException, 
			FullCourseException, AlreadyEnrolledException, IllegalArgumentException {
		manager.registerCourse(1, "AED", "Roman");
		manager.registerStudent(1, "Roman", "roman@upm.es");
		manager.enroll(1, 1);
		assertEquals(1, manager.getCourse(1).size());
		assertTrue(manager.isEnrolled(1, manager.getCourse(1)));
	}

	@Test
	@DisplayName("Course Enrollments executed correctly in order")
	void listEnrollmentTest1()
			throws IllegalArgumentException, NoSuchStudentException, 
			NoSuchCourseException, FullCourseException, AlreadyEnrolledException {
		List<String> l1 = new ArrayList<>();
		String st1 = new Student(1, "Roman", "roman@upm.es").toString();
		String st2 = new Student(2, "Gonzalo", "gonzalo@upm.es").toString();
		String st3 = new Student(3, "Luis", "luis@upm.es").toString();
		l1.add(st1);
		l1.add(st2);
		l1.add(st3);
		manager.registerCourse(1, "AED", "Roman");
		manager.registerStudent(2, "Gonzalo", "gonzalo@upm.es");
		manager.registerStudent(3, "Luis", "luis@upm.es");
		manager.registerStudent(1, "Roman", "roman@upm.es");
		manager.enroll(1, 1);
		manager.enroll(2, 1);
		manager.enroll(3, 1);
		assertEquals(3, manager.getCourse(1).size());
		assertEquals(l1, manager.listEnrollments(1));
	}

	@Test
	@DisplayName("Student does not exist on disenrollment")
	void disenrollmentTest1() 
			throws NoSuchStudentException, NoSuchCourseException,
			NoSuchEnrollmentException, IllegalArgumentException {
		manager.registerCourse(1, "AED", "Roman");
		assertThrows(NoSuchStudentException.class, () -> 
		manager.disenroll(1, 1));
	}

	@Test
	@DisplayName("Course does not exist on disenrollment")
	void disenrollmentTest2() 
			throws NoSuchStudentException, NoSuchCourseException,
			NoSuchEnrollmentException, IllegalArgumentException {
		manager.registerStudent(1, "Roman", "roman@upm.es");
		assertThrows(NoSuchCourseException.class, () -> 
		manager.disenroll(1, 1));
	}

	@Test
	@DisplayName("There is not Student enrolled on the Course")
	void disenrollmentTest3() 
			throws NoSuchStudentException, NoSuchCourseException,
			NoSuchEnrollmentException, IllegalArgumentException {
		manager.registerStudent(1, "Gonzalo", "gonzalo@upm.es");
		manager.registerCourse(1, "PP", "Roman");
		assertThrows(NoSuchEnrollmentException.class, () -> 
		manager.disenroll(1, 1));
	}

	@Test
	@DisplayName("Student was succesfully disenrolled")
	void disenrollmentTest4() 
			throws NoSuchStudentException, NoSuchCourseException,
			NoSuchEnrollmentException, IllegalArgumentException,
			FullCourseException, AlreadyEnrolledException {
		manager.registerStudent(1, "Gonzalo", "gonzalo@upm.es");
		manager.registerStudent(2, "Luis", "luis@upm.es");
		manager.registerCourse(1, "PP", "Roman");
		manager.enroll(1, 1);
		manager.enroll(2, 1);
		manager.disenroll(1, 1);
		assertEquals(1, manager.getCourse(1).size());
	}

	@Test
	@DisplayName("Course restarted with more than 0 students")
	void restartCourseTest1() 
			throws IllegalArgumentException {
		manager.registerCourse(1, "PP", "Roman");
		manager.registerStudent(1, "Gonzalo", "gonzalo@upm.es");
		manager.registerStudent(2, "Luis", "luis@upm.es");
		manager.restartCourse(1);
		assertEquals(0, manager.getCourse(1).size());
	}

	@Test
	@DisplayName("Course restarted with 0 students")
	void restartCourseTest2() 
			throws IllegalArgumentException {
		manager.registerCourse(1, "PP", "Roman");
		manager.restartCourse(1);
		assertEquals(0, manager.getCourse(1).size());
	}

	@Test
	@DisplayName("List of All the Students registered")
	void listStudentsTest1()
			throws IllegalArgumentException, NoSuchStudentException,
			NoSuchCourseException, FullCourseException, AlreadyEnrolledException {
		List<String> l1 = new ArrayList<>();
		String st1 = new Student(1, "Roman", "roman@upm.es").toString();
		String st2 = new Student(2, "Gonzalo", "gonzalo@upm.es").toString();
		String st3 = new Student(3, "Luis", "luis@upm.es").toString();
		l1.add(st1);
		l1.add(st2);
		l1.add(st3);
		manager.registerStudent(2, "Gonzalo", "gonzalo@upm.es");
		manager.registerStudent(1, "Roman", "roman@upm.es");
		manager.registerStudent(3, "Luis", "luis@upm.es");
		assertEquals(3, manager.listStudents().size());
		assertEquals(l1, manager.listStudents());
	}

	@Test
	@DisplayName("List of All the Students registered")
	void listCoursesTest1()
			throws IllegalArgumentException, NoSuchStudentException,
			NoSuchCourseException, FullCourseException, AlreadyEnrolledException {
		List<String> l1 = new ArrayList<>();
		String st1 = new Course(1, "PP", "Guillermo").toString();
		String st2 = new Course(2, "AOP", "Clara").toString();
		l1.add(st1);
		l1.add(st2);

		manager.registerCourse(2, "AOP", "Clara");
		manager.registerCourse(1, "PP", "Guillermo");
		assertEquals(2, manager.listCourses().size());
		assertEquals(l1, manager.listCourses());
	}

}