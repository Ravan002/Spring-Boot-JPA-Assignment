package az.edu.ada.wm2.assignment1.controller;

import az.edu.ada.wm2.assignment1.model.Course;
import az.edu.ada.wm2.assignment1.model.Student;
import az.edu.ada.wm2.assignment1.service.impl.CourseService;
import az.edu.ada.wm2.assignment1.service.impl.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller class responsible for handling requests related to courses.
 * Manages CRUD operations for courses, including listing, creating, updating, and deleting courses.
 * Allows students to view available courses, enroll in courses, and drop courses.
 */

@Controller
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;
    StudentService studentService;

    /**
     * Constructor for the CourseController class.
     * Initializes CourseService and StudentService.
     * @param courseService An instance of CourseService.
     * @param studentService An instance of StudentService.
     */
    public CourseController(CourseService courseService, StudentService studentService){
        this.courseService=courseService;
        this.studentService=studentService;
    }

    /**
     * Retrieves a list of courses and their details.
     * Populates the model with course data and returns the view name for displaying the course list.
     * @param model The model to be populated with course data.
     * @return The view name for displaying the course list.
     */
    @GetMapping({"","/","/list"})
    public String getCourses(Model model){
        List<Course> courses= courseService.list();
        for(Course course: courses){
            course.setNumberOfStudents(course.getStudents().size());
            courseService.save(course);
        }
        model.addAttribute("courses",courses);
        return "courses/index";
    }

    /**
     * Displays the form for creating a new course.
     * Populates the model with necessary data and returns the view name for creating a new course.
     * @param model The model to be populated with necessary data.
     * @return The view name for creating a new course.
     */
    @GetMapping("/new")
    public String createNewCourse(Model model){
        model.addAttribute("course",new Course());
        return "courses/new";
    }

    /**
     * Displays the form for updating an existing course.
     * Retrieves the course by its ID and adds it to the model, then returns the view name for updating the course.
     * @param id The ID of the course to be updated.
     * @return A ModelAndView object containing the view name and the course object.
     */
    @GetMapping("/update/{id}")
    public ModelAndView updateCourse(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("courses/update");
        mv.addObject("course", courseService.getById(id));
        return mv;
    }

    /**
     * Saves a new or updated course.
     * Redirects to the course list view after saving.
     * @param course The course object to be saved or updated.
     * @return The redirect URL for displaying the course list.
     */
    @PostMapping("/")
    public String save(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:/course/";
    }

    /**
     * Displays detailed information about a specific course.
     * Retrieves the course by its ID and adds it to the model, then returns the view name for displaying course details.
     * @param model The model to be populated with course data.
     * @param id The ID of the course to be displayed.
     * @return The view name for displaying course details.
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        model.addAttribute("course", courseService.getById(id));
        return "courses/info";
    }

    /**
     * Deletes a course and removes its enrollment records.
     * Redirects to the course list view after deletion.
     * @param id The ID of the course to be deleted.
     * @return The redirect URL for displaying the course list.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseService.deleteStudentsFromCourse(id);
        courseService.deleteById(id);
        return "redirect:/course/";
    }

    /**
     * Displays available courses for a student to enroll.
     * Retrieves the list of courses and adds it to the model along with the student ID.
     * @param studentId The ID of the student who wants to enroll.
     * @param model The model to be populated with course data.
     * @return The view name for displaying available courses for enrollment.
     */
    @GetMapping("/takeCourse/{studentId}")
    public String listAvailableCourses(@PathVariable Long studentId, Model model){
        List<Course> courses=courseService.list();
        model.addAttribute("courses",courses);
        model.addAttribute("studentId",studentId);
        return "courses/take_course";
    }

    /**
     * Enrolls a student in a selected course.
     * Checks if the student is not already enrolled in the course, then adds the course to the student's enrollment list.
     * Increments the number of students enrolled in the course and saves the changes.
     * Redirects to the course enrollment page after enrollment.
     * @param courseId The ID of the course to be enrolled in.
     * @param studentId The ID of the student who wants to enroll.
     * @return The redirect URL for displaying the course enrollment page.
     */
    @GetMapping("/takeCourse/{studentId}/selectedCourse/{courseId}")
    public String addCourseToStudent(@PathVariable Long courseId,@PathVariable Long studentId){
        Student student = studentService.getById(studentId);
        Course course = courseService.getById(courseId);

        if(!student.getCourses().contains(course)){
            student.getCourses().add(course);
            studentService.save(student);

            // Increment numberOfStudents property of the course
            course.setNumberOfStudents(course.getNumberOfStudents() + 1);
            courseService.save(course);
            return "redirect:/course/takeCourse/{studentId}";
        }
        return "redirect:/student/info/{studentId}";
    }
}