package com.crud.student.controller;

import com.crud.student.dto.CourseDTO;
import com.crud.student.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "API pour la gestion des cours")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping
    @Operation(summary = "Créer un cours", description = "Crée un nouveau cours")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Lister tous les cours", description = "Récupère la liste de tous les cours")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un cours par ID", description = "Récupère un cours par son identifiant")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping("/code/{code}")
    @Operation(summary = "Obtenir un cours par code", description = "Récupère un cours par son code")
    public ResponseEntity<CourseDTO> getCourseByCode(@PathVariable String code) {
        CourseDTO course = courseService.getCourseByCode(code);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping("/without-students")
    @Operation(summary = "Lister les cours sans étudiants", description = "Récupère tous les cours qui n'ont aucun étudiant inscrit")
    public ResponseEntity<List<CourseDTO>> getCoursesWithoutStudents() {
        List<CourseDTO> courses = courseService.getCoursesWithoutStudents();
        return ResponseEntity.ok(courses);
    }
}

