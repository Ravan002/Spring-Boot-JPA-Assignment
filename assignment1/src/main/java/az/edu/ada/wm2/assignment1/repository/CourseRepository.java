package az.edu.ada.wm2.assignment1.repository;

import az.edu.ada.wm2.assignment1.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The repository interface for accessing and managing course entities in the database.
 * This interface extends JpaRepository to inherit basic CRUD operations.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds courses taken by a specific student.
     *
     * @param studentId the ID of the student
     * @return a list of courses taken by the specified student
     */
    @Query(value = "SELECT c.* FROM courses c LEFT JOIN student_course sc ON c.id = sc.course_id WHERE sc.student_id = :studentId",
            nativeQuery = true)
    List<Course> findCoursesTakenByStudent(@Param("studentId") Long studentId);

}