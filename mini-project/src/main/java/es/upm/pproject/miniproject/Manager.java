package es.upm.pproject.miniproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.upm.pproject.exceptions.IllegalArgumentException;
import es.upm.pproject.exceptions.AlreadyEnrolledException;
import es.upm.pproject.exceptions.FullCourseException;
import es.upm.pproject.exceptions.NoSuchCourseException;
import es.upm.pproject.exceptions.NoSuchEnrollmentException;
import es.upm.pproject.exceptions.NoSuchStudentException;
import es.upm.pproject.pojos.Course;
import es.upm.pproject.pojos.Student;

public class Manager implements CourseManager {

	private Map<Integer, Student> students;
	private Map<Integer, Course> courses;

	public Manager() {
		students = new HashMap<>();
		courses = new HashMap<>();
	}

	public void registerCourse(int courseCode, String name, String coordinator)
			throws IllegalArgumentException {
		if(courseCode < 1) {
			throw new IllegalArgumentException("The course code is not a positive integer");
		}
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("The course name is empty");
		}
		if(coordinator == null || coordinator.isBlank()) {
			throw new IllegalArgumentException("The course coordinator's name is empty");
		}
		courses.put(courseCode, new Course(courseCode, name, coordinator));
	}

	public void registerStudent(int studentId, String name, String email)
			throws IllegalArgumentException{
		if(studentId < 1) {
			throw new IllegalArgumentException("The student code is not a positive integer");
		}
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("The student's name is empty");
		}
		if(email == null || email.isBlank()) {
			throw new IllegalArgumentException("The student's email is empty");
		}
		if(!email.contains("@") || email.endsWith(".")) {
			throw new IllegalArgumentException("The student's email is not valid");
		}
		students.put(studentId, new Student(studentId, name, email));
	}

	public void enroll(int studentId, int courseCode)
			throws NoSuchStudentException, NoSuchCourseException,
			FullCourseException, AlreadyEnrolledException {
		if(students.get(studentId) == null) {
			throw new NoSuchStudentException("The student code is not registered");
		}
		Course course = courses.get(courseCode);
		if(course == null) {
			throw new NoSuchCourseException("The course code is not registered");
		}
		if(course.size() >= 50) {
			throw new FullCourseException("Course is already full");
		}
		if(course.isEnrolled(studentId)) {
			throw new AlreadyEnrolledException("The student is already enrolled");
		}
		courses.get(courseCode).enroll(students.get(studentId));
	}

	public List<String> listEnrollments(int courseCode) {		
		return courses.get(courseCode).listEnrollments();
	}

	public void disenroll(int studentId, int courseCode)
			throws NoSuchStudentException, NoSuchCourseException,
			NoSuchEnrollmentException {
		if(students.get(studentId) == null) {
			throw new NoSuchStudentException("The student code is not registered");
		}
		if(courses.get(courseCode) == null) {
			throw new NoSuchCourseException("The course code is not registered");
		}
		if(!courses.get(courseCode).isEnrolled(studentId)) {
			throw new NoSuchEnrollmentException("The student is not registered on that course");
		}
		courses.get(courseCode).disenroll(studentId);
	}

	public void restartCourse(int courseCode) {
		courses.get(courseCode).restartCourse();
	}

	public List<String> listStudents() {
		List<Student> enrollmentsList = new ArrayList<>(students.values());
		Collections.sort(enrollmentsList);
		List<String> sortedEnrollmentsList = new ArrayList<>();
		for(Student student : enrollmentsList) {
			sortedEnrollmentsList.add(student.toString());
		}
		return sortedEnrollmentsList;
	}

	public List<String> listCourses() {
		List<Course> enrollmentsList = new ArrayList<>(courses.values());
		Collections.sort(enrollmentsList);
		List<String> sortedEnrollmentsList = new ArrayList<>();
		for(Course student : enrollmentsList) {
			sortedEnrollmentsList.add(student.toString());
		}
		return sortedEnrollmentsList;
	}

	public int getNumStudents() {
		return students.size();
	}

	public int getNumCourses() {
		return courses.size();
	}

	public Student getStudent(int studenId) {
		return students.get(studenId);
	}

	public Course getCourse(int courseCode) {
		return courses.get(courseCode);
	}

	public boolean isEnrolled(int studentID, Course course) {
		return course.isEnrolled(studentID);
	}

}