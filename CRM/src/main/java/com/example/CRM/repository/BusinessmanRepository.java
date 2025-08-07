package com.example.CRM.repository;

import com.example.CRM.entity.Businessman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessmanRepository extends JpaRepository<Businessman, Long> {
}