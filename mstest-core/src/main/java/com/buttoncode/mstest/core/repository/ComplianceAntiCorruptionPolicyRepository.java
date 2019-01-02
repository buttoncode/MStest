package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.ComplianceAntiCorruptionPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceAntiCorruptionPolicyRepository extends JpaRepository<ComplianceAntiCorruptionPolicy, Integer>, JpaSpecificationExecutor<ComplianceAntiCorruptionPolicy> {

    @Query("SELECT c FROM ComplianceAntiCorruptionPolicy c WHERE c.statusCompliance.name = ?1 AND NOT c.employee.statusEmployee.name = ?2 ORDER BY c.expirydateoftraining ASC")
    List<ComplianceAntiCorruptionPolicy> findAllWithoutEmployeeStatusAndComplianceStatus(String statusCompliance, String statusEmployee);

    List<ComplianceAntiCorruptionPolicy> findAllByEmployeeId(Integer employee_id);

}
