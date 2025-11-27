package com.crud.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Données d'inscription d'un étudiant à un cours")
public class EnrollmentDTO {
    private String studentMatricule;
    private String courseCode;
    
    public EnrollmentDTO() {
    }
    
    public EnrollmentDTO(String studentMatricule, String courseCode) {
        this.studentMatricule = studentMatricule;
        this.courseCode = courseCode;
    }
    
    public String getStudentMatricule() {
        return studentMatricule;
    }
    
    public void setStudentMatricule(String studentMatricule) {
        this.studentMatricule = studentMatricule;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}

