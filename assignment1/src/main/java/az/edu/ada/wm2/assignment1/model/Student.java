/**
 * Model class representing a student.
 * Contains attributes such as ID, name, surname, major, date of birth, and enrolled courses.
 * Utilizes Lombok annotations for generating getters, setters, constructors, and builder methods.
 */
package az.edu.ada.wm2.assignment1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "STUDENTS")
public class Student {
    /**
     * The unique identifier for the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the student.
     */
    private String name;

    /**
     * The surname of the student.
     */
    private String surname;

    /**
     * The major or field of study of the student.
     */
    private String major;

    /**
     * The date of birth of the student.
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * The list of courses in which the student is enrolled.
     * Represents a many-to-many relationship with the Course entity.
     */
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}