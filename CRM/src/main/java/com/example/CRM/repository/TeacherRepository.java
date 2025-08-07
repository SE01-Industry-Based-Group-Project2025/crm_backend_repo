package com.example.CRM.repository;

import com.example.CRM.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Additional query methods can be defined here if needed
}