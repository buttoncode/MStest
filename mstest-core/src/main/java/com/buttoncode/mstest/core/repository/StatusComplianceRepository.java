package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.StatusCompliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusComplianceRepository extends JpaRepository <StatusCompliance, Integer> {

    StatusCompliance findOneStatusComplianceByName(String name);
}
