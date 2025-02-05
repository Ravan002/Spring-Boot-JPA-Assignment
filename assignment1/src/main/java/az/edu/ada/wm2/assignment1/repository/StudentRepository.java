package az.edu.ada.wm2.assignment1.repository;

import az.edu.ada.wm2.assignment1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository interface for accessing and managing student entities in the database.
 * This interface extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Finds students by their name containing the specified string ignoring case.
     *
     * @param name the string to search for in student names
     * @return a list of students whose names contain the specified string
     */
    List<Student> findByNameContainingIgnoreCase(String name);

    /**
     * Finds students by their surname containing the specified string ignoring case.
     *
     * @param surname the string to search for in student surnames
     * @return a list of students whose surnames contain the specified string
     */
    List<Student> findBySurnameContainingIgnoreCase(String surname);

    /**
     * Finds students by their major.
     *
     * @param major the major to search for
     * @return a list of students majoring in the specified major
     */
    @Query(value="SELECT * FROM Students WHERE major= :major", nativeQuery = true)
    List<Student> findStudentsByMajor(@Param("major") String major);

    /**
     * Finds students ordered by name in ascending order.
     *
     * @return a list of students ordered by name in ascending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY NAME ASC", nativeQuery = true)
    List<Student> findByNameOrderByNameAsc();

    /**
     * Finds students ordered by name in descending order.
     *
     * @return a list of students ordered by name in descending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY NAME DESC", nativeQuery = true)
    List<Student> findByNameOrderByNameDesc();

    /**
     * Finds students ordered by surname in ascending order.
     *
     * @return a list of students ordered by surname in ascending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY SURNAME ASC", nativeQuery = true)
    List<Student> findBySurnameOrderBySurnameAsc();

    /**
     * Finds students ordered by surname in descending order.
     *
     * @return a list of students ordered by surname in descending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY SURNAME DESC", nativeQuery = true)
    List<Student> findBySurnameOrderBySurnameDesc();

    /**
     * Finds students ordered by major in ascending order.
     *
     * @return a list of students ordered by major in ascending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY MAJOR ASC", nativeQuery = true)
    List<Student> findByMajorOrderByMajorAsc();

    /**
     * Finds students ordered by major in descending order.
     *
     * @return a list of students ordered by major in descending order
     */
    @Query(value = "SELECT * FROM Students ORDER BY MAJOR DESC", nativeQuery = true)
    List<Student> findByMajorOrderByMajorDesc();
}