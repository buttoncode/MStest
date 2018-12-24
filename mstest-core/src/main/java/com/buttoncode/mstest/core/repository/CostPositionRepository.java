package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.CostPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CostPositionRepository extends JpaRepository<CostPosition, Integer>, JpaSpecificationExecutor<CostPosition> {

//    List<CostPosition> findByIdDepartament (Integer departament_id);

    CostPosition findOneByNamempk(String name_mpk);

}
