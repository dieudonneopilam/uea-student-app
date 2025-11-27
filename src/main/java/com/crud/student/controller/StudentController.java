package com.crud.student.controller;

import com.crud.student.dto.StudentDTO;
import com.crud.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "API pour la gestion des étudiants")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @PostMapping
    @Operation(summary = "Créer un étudiant", description = "Crée un nouvel étudiant")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Lister tous les étudiants", description = "Récupère la liste de tous les étudiants")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un étudiant par ID", description = "Récupère un étudiant par son identifiant")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    
    @GetMapping("/matricule/{matricule}")
    @Operation(summary = "Obtenir un étudiant par matricule", description = "Récupère un étudiant par son matricule")
    public ResponseEntity<StudentDTO> getStudentByMatricule(@PathVariable String matricule) {
        StudentDTO student = studentService.getStudentByMatricule(matricule);
        return ResponseEntity.ok(student);
    }
    
    @GetMapping("/course/{courseCode}")
    @Operation(summary = "Lister les étudiants d'un cours", description = "Récupère tous les étudiants inscrits à un cours donné")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourseCode(@PathVariable String courseCode) {
        List<StudentDTO> students = studentService.getStudentsByCourseCode(courseCode);
        return ResponseEntity.ok(students);
    }
    
    @DeleteMapping("/{id}/enrollments")
    @Operation(summary = "Supprimer toutes les inscriptions d'un étudiant", description = "Supprime toutes les inscriptions d'un étudiant sans supprimer l'étudiant")
    public ResponseEntity<Void> deleteAllEnrollmentsByStudentId(@PathVariable Long id) {
        studentService.deleteAllEnrollmentsByStudentId(id);
        return ResponseEntity.noContent().build();
    }
}

