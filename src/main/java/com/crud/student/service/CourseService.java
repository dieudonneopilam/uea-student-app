package com.crud.student.service;

import com.crud.student.dto.CourseDTO;
import com.crud.student.exception.ResourceNotFoundException;
import com.crud.student.model.Course;
import com.crud.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByCode(courseDTO.getCode())) {
            throw new IllegalArgumentException("Un cours avec ce code existe déjà");
        }
        
        Course course = new Course(
            courseDTO.getCode(),
            courseDTO.getNom(),
            courseDTO.getCredits(),
            courseDTO.getEnseignant()
        );
        
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }
    
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cours", "id", id));
        return convertToDTO(course);
    }
    
    public CourseDTO getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Cours", "code", code));
        return convertToDTO(course);
    }
    
    public List<CourseDTO> getCoursesWithoutStudents() {
        return courseRepository.findCoursesWithoutStudents().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO(
            course.getId(),
            course.getCode(),
            course.getNom(),
            course.getCredits(),
            course.getEnseignant()
        );
        
        Set<String> studentMatricules = course.getStudents().stream()
            .map(com.crud.student.model.Student::getMatricule)
            .collect(Collectors.toSet());
        dto.setStudentMatricules(studentMatricules);
        
        return dto;
    }
}

