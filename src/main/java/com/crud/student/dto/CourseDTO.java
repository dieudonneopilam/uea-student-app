package com.crud.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

@Schema(description = "Données d'un cours")
public class CourseDTO {
    private Long id;
    private String code;
    private String nom;
    private Integer credits;
    private String enseignant;
    private Set<String> studentMatricules;
    
    public CourseDTO() {
    }
    
    public CourseDTO(Long id, String code, String nom, Integer credits, String enseignant) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.credits = credits;
        this.enseignant = enseignant;
    }

    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Integer getCredits() {
        return credits;
    }
    
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    
    public String getEnseignant() {
        return enseignant;
    }
    
    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }
    
    public Set<String> getStudentMatricules() {
        return studentMatricules;
    }
    
    public void setStudentMatricules(Set<String> studentMatricules) {
        this.studentMatricules = studentMatricules;
    }
}

