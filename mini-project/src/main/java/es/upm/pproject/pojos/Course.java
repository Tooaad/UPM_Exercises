package es.upm.pproject.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Comparable<Course>{

	private int courseCode;
	private String name;
	private String coordinator;
	private Map<Integer, Student> enrollments;
	
	public Course(int courseCode, String name, String coordinator) {		
		this.courseCode = courseCode;
		this.name = name;
		this.coordinator = coordinator;
		enrollments = new HashMap<>();
	}
	
	public int getCourseCode() {
		return courseCode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCoordinator() {
		return coordinator;
	}
	
	public void disenroll(int studentId) {
		enrollments.remove(studentId);
	}
	
	public int size() {
		return enrollments.size();
	}
	
	public boolean isEnrolled(int studentId) {
		return (enrollments.get(studentId) != null);
	}

	public void enroll(Student student) {
		enrollments.put(student.getStudentId(), student);
	}	
	
	public List<String> listEnrollments() {
		List<Student> enrollmentsList = new ArrayList<>(enrollments.values());
		Collections.sort(enrollmentsList);
		List<String> sortedEnrollmentsList = new ArrayList<>();
		for(Student student : enrollmentsList) {
			sortedEnrollmentsList.add(student.toString());
		}
		return sortedEnrollmentsList;
	}
	
	public void restartCourse() {
			enrollments.clear();
	}

	
	
	@Override
    public int compareTo(Course other) {
        if(other.courseCode > this.courseCode)
            return -1;
        if(other.courseCode == this.courseCode)
            return 0;
        return 1;
    }
	
	@Override
	public String toString() {
		return String.valueOf(courseCode)+ ", " + name + ", " + coordinator;
	}
	
}