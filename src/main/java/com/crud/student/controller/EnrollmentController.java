package com.crud.student.controller;

import com.crud.student.dto.EnrollmentDTO;
import com.crud.student.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollments", description = "API pour la gestion des inscriptions")
public class EnrollmentController {
    
    @Autowired
    private EnrollmentService enrollmentService;
    
    @PostMapping
    @Operation(summary = "Inscrire un étudiant à un cours", description = "Inscrit un étudiant à un cours en utilisant le matricule et le code du cours")
    public ResponseEntity<String> enrollStudentToCourse(@RequestBody EnrollmentDTO enrollmentDTO) {
        enrollmentService.enrollStudentToCourse(enrollmentDTO);
        return new ResponseEntity<>("Étudiant inscrit au cours avec succès", HttpStatus.CREATED);
    }
}

