package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findOneByShortname(String short_name);
    Department findOneByName(String name);
    Department findById(Integer id);

    List<Department> findAllByShortname(String Name);

}
