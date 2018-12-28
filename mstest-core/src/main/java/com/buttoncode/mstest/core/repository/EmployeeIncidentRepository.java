package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.EmployeeIncident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeIncidentRepository extends JpaRepository <EmployeeIncident, Integer>{

    EmployeeIncident findOneEmployeeIncidentByName(String name);

}
