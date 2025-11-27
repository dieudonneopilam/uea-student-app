package com.crud.student.service;

import com.crud.student.dto.EnrollmentDTO;
import com.crud.student.exception.ResourceNotFoundException;
import com.crud.student.model.Course;
import com.crud.student.model.Student;
import com.crud.student.repository.CourseRepository;
import com.crud.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnrollmentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    public void enrollStudentToCourse(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findByMatricule(enrollmentDTO.getStudentMatricule())
            .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "matricule", enrollmentDTO.getStudentMatricule()));
        
        Course course = courseRepository.findByCode(enrollmentDTO.getCourseCode())
            .orElseThrow(() -> new ResourceNotFoundException("Cours", "code", enrollmentDTO.getCourseCode()));
        
        if (student.getCourses().contains(course)) {
            throw new IllegalArgumentException("L'étudiant est déjà inscrit à ce cours");
        }
        
        student.addCourse(course);
        studentRepository.save(student);
    }
}

