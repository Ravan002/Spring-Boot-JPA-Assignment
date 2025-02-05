/**
 * Service class implementing functionality for managing courses.
 * Implements BaseService interface.
 */
package az.edu.ada.wm2.assignment1.service.impl;

import az.edu.ada.wm2.assignment1.model.Course;
import az.edu.ada.wm2.assignment1.model.Student;
import az.edu.ada.wm2.assignment1.repository.CourseRepository;
import az.edu.ada.wm2.assignment1.repository.StudentRepository;
import az.edu.ada.wm2.assignment1.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {

    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    /**
     * Retrieves a list of all courses.
     * @return A list of all courses.
     */
    @Override
    public List<Course> list() {
        return courseRepo.findAll();
    }

    /**
     * Deletes a course by its ID.
     * @param id The ID of the course to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        courseRepo.deleteById(id);
    }

    /**
     * Retrieves a course by its ID.
     * @param id The ID of the course to be retrieved.
     * @return The course with the specified ID.
     * @throws RuntimeException if the course with the specified ID is not found.
     */
    @Override
    public Course getById(Long id) {
        return courseRepo.findById(id).orElseThrow();
    }

    /**
     * Saves or updates a course.
     * @param entity The course object to be saved or updated.
     */
    @Override
    public void save(Course entity) {
        courseRepo.save(entity);
    }

    /**
     * Deletes all enrollments of a course.
     * Removes the course from the list of courses for each student enrolled in it.
     * @param courseId The ID of the course whose enrollments are to be deleted.
     */
    public void deleteStudentsFromCourse(Long courseId) {
        // Retrieve the course
        Course course = getById(courseId);
        // Remove the course from the list of courses for each student enrolled in it
        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
            studentRepo.save(student);
        }
    }
}