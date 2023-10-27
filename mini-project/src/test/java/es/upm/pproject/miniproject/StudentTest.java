package es.upm.pproject.miniproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import es.upm.pproject.pojos.Student;

class StudentTest {

	Student student1 = new Student(1, "Roman", "roman@upm.es");
	Student student2 = new Student(2, "Roman", "roman@upm.es");

	@Test
	@DisplayName("student1 is lower than student2")
	void compareTo1() {
		assertEquals(-1, student1.compareTo(student2));
	}

	@Test
	@DisplayName("student1 higher than student2")
	void compareTo2() {
		assertEquals(1, student2.compareTo(student1));
	}

	@Test
	@DisplayName("student1 is equal to student1")
	void compareTo3() {
		assertEquals(0, student1.compareTo(student1));
	}

	@Test
	@DisplayName("student1 is equal student2")
	void equals1() {
		assertEquals(student1, student1);
	}

	@Test
	@DisplayName("student1 is not equal student2")
	void equals3() {
		assertNotEquals(null, student1);
	}

}