Miniproject
=====================


Classes
-------
- Student: studentId, name, email
- Course: courseCode, name, coordinator, numEnrollment, col students



Manager Attributes
------------------
- col students
- col courses



Methods
-------
```java
void registerCourse(int courseCode, String name, String coordinator)
                        throws IllegalArgumentException     // courseCode, name, coordinator   (L)
void registerStudent(int studentId, String name, String email)   (L)
                        throws IllegalArgumentException     // studentId, name, email     (L)
        boolean isStudent()     // Aux
        boolean isCourse()      // Aux
        boolean getCourse(courseCode).isFull()      //Aux
        boolean isEnrolled()    // Aux
void enroll(int studentID, int courseCode)  
                        throws NoSuchStudentException
                        throws NoSuchCourseException
                        throws FullCourseException
                        throws AlreadyEnrolledException     // (L)
- Lista<String[]> getEnrollments(int courseCode)    // Sorted studentID     (G)
- void disenrollment(int studentID, int courseCode)    
                        throws NoSuchStudentException
                        throws NoSuchCourseException
                        throws NoSuchEnrollmentException    // (G)
- void  restartCourse(int courseCode)   // (G)
- List<String[]> getStudents()  // Sorted studentID     (G)
- List<String[]> getCourses()   // Sorted courseCode     (G)
```



Exceptions
----------
- IllegalArgumentException   
- NoSuchStudentException
- NoSuchCourseException
- FullCourseException
- NoSuchEnrollmentException
- AlreadyEnrolledException
