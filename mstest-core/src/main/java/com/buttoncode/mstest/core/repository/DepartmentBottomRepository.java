package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.DepartmentBottom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentBottomRepository extends JpaRepository<DepartmentBottom, Integer> {

    DepartmentBottom findById(Integer id);
    DepartmentBottom findOneByName(String name);
    DepartmentBottom findOneByShortname(String short_name);

}
