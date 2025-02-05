package az.edu.ada.wm2.assignment1.controller;


import az.edu.ada.wm2.assignment1.model.Course;
import az.edu.ada.wm2.assignment1.model.Student;
import az.edu.ada.wm2.assignment1.service.impl.CourseService;
import az.edu.ada.wm2.assignment1.service.impl.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to Student entities.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;
    CourseService courseService;
    List<String> majorsList = Arrays.asList(
            "Computer Science",
            "Computer Engineering",
            "Information Technology",
            "Electrical Engineering",
            "Mathematics",
            "Law",
            "Finance",
            "Business Administration"
    );
    private boolean isAsc=true;

    /**
     * Constructor for StudentController.
     * @param studentService Service handling Student-related operations.
     * @param courseService Service handling Course-related operations.
     */
    public StudentController(StudentService studentService,CourseService courseService){
        this.studentService=studentService;
        this.courseService=courseService;
    }

    /**
     * Retrieves and displays a list of all students.
     * @param model Model to be populated with data for the view.
     * @return The view template for displaying a list of students.
     */
    @GetMapping({"","/","/list"})
    public String getStudents(Model model){
        List<Student> students= studentService.list();
        model.addAttribute("students",students);
        return "students/index";
    }

    /**
     * Displays a form for creating a new student.
     * @param model Model to be populated with data for the view.
     * @return The view template for creating a new student.
     */
    @GetMapping("/new")
    public String createNewStudent(Model model){
        model.addAttribute("majorList",majorsList);
        model.addAttribute("student",new Student());
        return "students/new";
    }

    /**
     * Handles the submission of a new student form.
     * @param student The student object to be saved.
     * @return Redirects to the list of students upon successful creation.
     */
    @PostMapping("/")
    public String save(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/student/";
    }

    /**
     * Displays information about a specific student.
     * @param model Model to be populated with data for the view.
     * @param id The ID of the student to retrieve information for.
     * @return The view template for displaying student information.
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        model.addAttribute("student", studentService.getById(id));
        return "students/info";
    }

    /**
     * Deletes a student with the specified ID.
     * @param id The ID of the student to be deleted.
     * @return Redirects to the list of students after deletion.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/student/";
    }

    /**
     * Displays a form for updating student information.
     * @param id The ID of the student to be updated.
     * @return The view template for updating student information.
     */
    @GetMapping("/update/{id}")
    public ModelAndView updateStudent(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("students/update");
        mv.addObject("student", studentService.getById(id));
        return mv;
    }

    /**
     * Displays information about a specific student.
     * @param id The ID of the student to retrieve information for.
     * @return The view template for displaying student information.
     */
    @GetMapping("/info/{id}")
    public ModelAndView infoStudent(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("students/info");
        mv.addObject("student", studentService.getById(id));
        return mv;
    }

    /**
     * Drops a course from a student's list of enrolled courses.
     * @param studentId The ID of the student.
     * @param courseId The ID of the course to be dropped.
     * @return Redirects to the student's information page after dropping the course.
     */
    @GetMapping("/drop/{studentId}/course/{courseId}")
    public String dropCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        // Retrieve student and course from the database
        Student student = studentService.getById(studentId);
        Course course = courseService.getById(courseId);

        // Remove course from student's courses property
        student.getCourses().removeIf(c -> c.getId().equals(courseId));
        studentService.save(student);

        // Decrement numberOfStudents property of the course
        course.setNumberOfStudents(course.getNumberOfStudents() - 1);
        courseService.save(course);
        return "redirect:/student/info/{studentId}";
    }

    /**
     * Searches for students by name.
     * @param name The name to search for.
     * @param model Model to be populated with search results.
     * @return The view template for displaying search results.
     */
    @GetMapping("/search")
    public String searchStudentByName(@RequestParam String name, Model model) {
        List<Student> students=studentService.list();
        if (name != null && !name.isEmpty()) {
            students = studentService.findByNameIgnoreCase(name);
        }
        model.addAttribute("students", students);
        return "students/index"; // Replace with your actual Thymeleaf template name
    }

    /**
     * Sorts students based on the specified property.
     * @param property The property by which to sort the students.
     * @param model Model to be populated with sorted student data.
     * @return The view template for displaying sorted students.
     */
    @GetMapping("/sort/{property}")
    public String sortStudents(@PathVariable String property, Model model) {
        List<Student>students=studentService.sortStudentByProperty(isAsc,property);
        isAsc=!isAsc;
        model.addAttribute("students", students);
        return "students/index";
    }
}



