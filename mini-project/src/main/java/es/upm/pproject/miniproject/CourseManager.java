package es.upm.pproject.miniproject;

import java.util.List;

import es.upm.pproject.exceptions.IllegalArgumentException;
import es.upm.pproject.exceptions.AlreadyEnrolledException;
import es.upm.pproject.exceptions.FullCourseException;
import es.upm.pproject.exceptions.NoSuchCourseException;
import es.upm.pproject.exceptions.NoSuchEnrollmentException;
import es.upm.pproject.exceptions.NoSuchStudentException;

public interface CourseManager {

	/*
	 * Registers a new course
	 * @throws IllegalArgumentException if courseCode < 1, 
	 * or name is null or blank, or coordinator is null or blank
	 */
	public void registerCourse(int courseCode, String name, String coordinator)
			throws IllegalArgumentException;


	/*
	 * Registers a new student
	 * @throws IllegalArgumentException if studentId < 1, 
	 * or name is null or blank, or email is null or blank,
	 * or email does not contain @ or it ends in .
	 */
	public void registerStudent(int studentId, String name, String email)
			throws IllegalArgumentException;


	/*
	 * Enrolls a student into a course
	 * @throws NoSuchStudentException if studentId doesn't exist, 
	 * @throws NoSuchCourseException if courseCode doesn't exist
	 * @throws FullCourseException if the course identified by courseCode has 50 students
	 * @throws AlreadyEnrolledException if the student identify by studentId is already
	 * enrolled in the course identified by courseCode,
	 */
	public void enroll(int studentID, int courseCode)
			throws NoSuchStudentException, NoSuchCourseException, FullCourseException, AlreadyEnrolledException;


	/*
	 * Returns the info of all students registered in course, sorted by student ID
	 */
	public List<String> listEnrollments(int courseCode);


	/*
	 * Removes a students in a course
	 * @throws NoSuchStudentException if studentId doesn't exist, 
	 * @throws NoSuchCourseException if courseCode doesn't exist
	 * @throws NoSuchEnrollmentException if the student identified by studentId
	 * is not enrolled in the course identified by courseCode
	 */
	public void disenroll(int studenId, int courseCode)
			throws NoSuchStudentException, NoSuchCourseException, NoSuchEnrollmentException;


	/*
	 * Removes all students in a course
	 */
	public void restartCourse(int courseCode);


	/*
	 * Returns the info of all students registered, sorted by student ID
	 */
	public List<String> listStudents();


	/*
	 * Returns the info of all courses registered, sorted by student ID
	 */
	public List<String> listCourses(); 

}