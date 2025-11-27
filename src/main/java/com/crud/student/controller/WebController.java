package com.crud.student.controller;

import com.crud.student.dto.CourseDTO;
import com.crud.student.dto.StudentDTO;
import com.crud.student.service.CourseService;
import com.crud.student.service.EnrollmentService;
import com.crud.student.service.StudentService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Hidden
public class WebController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private EnrollmentService enrollmentService;
    
    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }
    
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
    
    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<CourseDTO> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    
    @GetMapping("/enroll")
    public String showEnrollmentForm(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        List<CourseDTO> courses = courseService.getAllCourses();
        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "enroll";
    }
    
    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam String studentMatricule, 
                               @RequestParam String courseCode,
                               RedirectAttributes redirectAttributes) {
        try {
            com.crud.student.dto.EnrollmentDTO enrollmentDTO = 
                new com.crud.student.dto.EnrollmentDTO(studentMatricule, courseCode);
            enrollmentService.enrollStudentToCourse(enrollmentDTO);
            redirectAttributes.addFlashAttribute("success", "Inscription réussie !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/enroll";
    }
}

