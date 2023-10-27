package es.upm.pproject.pojos;

public class Student implements Comparable<Student> {

	private int studentId;
	private String name;
	private String email;

	public Student(int studentId, String name, String email) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int compareTo(Student other) {
		if(other.studentId > this.studentId) {
			return -1;
		}
		if(other.studentId == this.studentId) {
			return 0;
		}
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass()!= this.getClass()) {
			return false;
		}
		Student other = (Student)obj;
		return other.studentId == this.studentId && 
				other.name.equals(this.name) && 
				other.email.equals(this.email);
	}

	@Override
	public int hashCode() {
		return studentId;
	}

	@Override
	public String toString() {
		return String.valueOf(studentId)+ ", " + name + ", " + email;
	}

}