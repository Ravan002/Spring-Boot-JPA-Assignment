/**
 * Model class representing a course.
 * Contains attributes such as ID, course name, teacher name, course description, number of students, credit, and enrolled students.
 * Utilizes Lombok annotations for generating getters, setters, constructors, and default values.
 */
package az.edu.ada.wm2.assignment1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSES")
public class Course {

    /**
     * The unique identifier for the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the course.
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * The name of the teacher or instructor for the course.
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * The description of the course.
     */
    @Column(name = "course_description")
    private String courseDescription;

    /**
     * The number of students enrolled in the course.
     * Default value set to 0.
     */
    @Column(name = "number_of_students", columnDefinition = "int default 0")
    private int numberOfStudents=0;

    /**
     * The credit value associated with the course.
     */
    private int credit;

    /**
     * The list of students enrolled in the course.
     * Represents a many-to-many relationship with the Student entity.
     */
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}