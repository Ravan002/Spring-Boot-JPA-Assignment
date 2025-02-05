/**
 * Service class implementing functionality for managing students.
 * Implements BaseService interface.
 */
package az.edu.ada.wm2.assignment1.service.impl;

import az.edu.ada.wm2.assignment1.model.Student;
import az.edu.ada.wm2.assignment1.repository.StudentRepository;
import az.edu.ada.wm2.assignment1.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<Student> {

    private final StudentRepository studentRepo;

    /**
     * Retrieves a list of all students.
     * @return A list of all students.
     */
    @Override
    public List<Student> list() {
        return studentRepo.findAll();
    }

    /**
     * Deletes a student by their ID.
     * @param id The ID of the student to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        studentRepo.deleteById(id);
    }

    /**
     * Retrieves a student by their ID.
     * @param id The ID of the student to be retrieved.
     * @return The student with the specified ID.
     * @throws RuntimeException if the student with the specified ID is not found.
     */
    @Override
    public Student getById(Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    /**
     * Saves or updates a student.
     * @param entity The student object to be saved or updated.
     */
    @Override
    public void save(Student entity) {
        studentRepo.save(entity);
    }

    /**
     * Finds students by name, ignoring case.
     * @param name The name to search for.
     * @return A list of students with names containing the specified name, ignoring case.
     */
    public List<Student> findByNameIgnoreCase(String name) {
        return studentRepo.findByNameContainingIgnoreCase(name);
    }

    /**
     * Finds students by major.
     * @param major The major to search for.
     * @return A list of students with the specified major.
     */
    public List<Student> findStudentsByMajor(String major){
        return studentRepo.findStudentsByMajor(major);
    }

    /**
     * Finds students by surname, ignoring case.
     * @param surname The surname to search for.
     * @return A list of students with surnames containing the specified surname, ignoring case.
     */
    public List<Student> findBySurname(String surname) {
        return studentRepo.findBySurnameContainingIgnoreCase(surname);
    }

    /**
     * Sorts students by a specified property.
     * @param isAsc Boolean indicating whether to sort in ascending order.
     * @param property The property by which to sort (name, surname, major).
     * @return A list of students sorted by the specified property.
     */
    public List<Student> sortStudentByProperty(boolean isAsc,String property){
        return switch (property) {
            case "name" -> isAsc ? studentRepo.findByNameOrderByNameAsc() : studentRepo.findByNameOrderByNameDesc();
            case "surname" ->
                    isAsc ? studentRepo.findBySurnameOrderBySurnameAsc() : studentRepo.findBySurnameOrderBySurnameDesc();
            case "major" ->
                    isAsc ? studentRepo.findByMajorOrderByMajorAsc() : studentRepo.findByMajorOrderByMajorDesc();
            default -> list();
        };
    }
}