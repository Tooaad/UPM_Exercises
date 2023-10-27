package es.upm.pproject.miniproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import es.upm.pproject.pojos.Course;

class CourseTest {

	Course course1 = new Course(1, "Roman", "roman@upm.es");
	Course course2 = new Course(2, "Roman", "roman@upm.es");

	@Test
	@DisplayName("course1 is lower than course2")
	void compareTo1() {
		assertEquals(-1, course1.compareTo(course2));
	}

	@Test
	@DisplayName("course1 higher than course2")
	void compareTo2() {
		assertEquals(1, course2.compareTo(course1));
	}

	@Test
	@DisplayName("course1 is equal to course1")
	void compareTo3() {
		assertEquals(0, course1.compareTo(course1));
	}

	@Test
	@DisplayName("course1 is equal course2")
	void equals1() {
		assertEquals(course1, course1);
	}

	@Test
	@DisplayName("course1 is not equal course2")
	void equals3() {
		assertNotEquals(null, course1);
	}

}