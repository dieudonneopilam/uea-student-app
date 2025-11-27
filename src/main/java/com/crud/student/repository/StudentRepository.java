package com.crud.student.repository;

import com.crud.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByMatricule(String matricule);
    Optional<Student> findByEmail(String email);
    boolean existsByMatricule(String matricule);
    boolean existsByEmail(String email);
}

