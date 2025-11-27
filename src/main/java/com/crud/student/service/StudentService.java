package com.crud.student.service;

import com.crud.student.dto.StudentDTO;
import com.crud.student.exception.ResourceNotFoundException;
import com.crud.student.model.Student;
import com.crud.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByMatricule(studentDTO.getMatricule())) {
            throw new IllegalArgumentException("Un étudiant avec ce matricule existe déjà");
        }
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new IllegalArgumentException("Un étudiant avec cet email existe déjà");
        }
        
        Student student = new Student(
            studentDTO.getNom(),
            studentDTO.getPostNom(),
            studentDTO.getMatricule(),
            studentDTO.getFaculte(),
            studentDTO.getEmail()
        );
        
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }
    
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "id", id));
        return convertToDTO(student);
    }
    
    public StudentDTO getStudentByMatricule(String matricule) {
        Student student = studentRepository.findByMatricule(matricule)
            .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "matricule", matricule));
        return convertToDTO(student);
    }
    
    public List<StudentDTO> getStudentsByCourseCode(String courseCode) {
        return studentRepository.findAll().stream()
            .filter(student -> student.getCourses().stream()
                .anyMatch(course -> course.getCode().equals(courseCode)))
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public void deleteAllEnrollmentsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Étudiant", "id", studentId));
        
        Set<com.crud.student.model.Course> courses = new java.util.HashSet<>(student.getCourses());
        for (com.crud.student.model.Course course : courses) {
            student.removeCourse(course);
        }
        
        studentRepository.save(student);
    }
    
    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO(
            student.getId(),
            student.getNom(),
            student.getPostNom(),
            student.getMatricule(),
            student.getFaculte(),
            student.getEmail()
        );
        
        Set<String> courseCodes = student.getCourses().stream()
            .map(com.crud.student.model.Course::getCode)
            .collect(Collectors.toSet());
        dto.setCourseCodes(courseCodes);
        
        return dto;
    }
}

