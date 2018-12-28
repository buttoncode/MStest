package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.StatusEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEmployeeRepository extends JpaRepository<StatusEmployee, Integer> {

        StatusEmployee findOneByName(String name);
}
