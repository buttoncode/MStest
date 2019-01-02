package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.ComplianceCodeOfEthic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceCodeOfEthicRepository extends JpaRepository<ComplianceCodeOfEthic, Integer>, JpaSpecificationExecutor<ComplianceCodeOfEthic> {

    @Query("SELECT c FROM ComplianceCodeOfEthic c WHERE c.statusCompliance.name = ?1 AND NOT c.employee.statusEmployee.name = ?2 ORDER BY c.expirydateoftraining ASC")
    List<ComplianceCodeOfEthic> findAllWithoutEmployeeStatusAndComplianceStatus(String statusCompliance, String statusEmployee);

    List<ComplianceCodeOfEthic> findAllByEmployeeId(Integer employee_id);

}
