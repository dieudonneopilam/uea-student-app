package com.crud.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;

@Schema(description = "Données d'un étudiant")
public class StudentDTO {
    private Long id;
    private String nom;
    private String postNom;
    private String matricule;
    private String faculte;
    private String email;
    private Set<String> courseCodes;
    
    public StudentDTO() {
    }
    
    public StudentDTO(Long id, String nom, String postNom, String matricule, String faculte, String email) {
        this.id = id;
        this.nom = nom;
        this.postNom = postNom;
        this.matricule = matricule;
        this.faculte = faculte;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPostNom() {
        return postNom;
    }
    
    public void setPostNom(String postNom) {
        this.postNom = postNom;
    }
    
    public String getMatricule() {
        return matricule;
    }
    
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
    public String getFaculte() {
        return faculte;
    }
    
    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Set<String> getCourseCodes() {
        return courseCodes;
    }
    
    public void setCourseCodes(Set<String> courseCodes) {
        this.courseCodes = courseCodes;
    }
}

