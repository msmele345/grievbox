package com.mitchmele.grievbox.repository;

import com.mitchmele.grievbox.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
}
