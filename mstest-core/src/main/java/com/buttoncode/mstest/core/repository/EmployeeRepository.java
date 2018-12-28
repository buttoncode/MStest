package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer>, JpaSpecificationExecutor<Employee> {

    Employee findByNumbersap(Integer number_sap);
    List <Employee> findListByNumbersap(Integer number_sap);

    List<Employee> findEmployeesByNumbersap(Integer number_sap);

    @Query("SELECT e FROM Employee e WHERE NOT EXISTS (SELECT c.employee.id FROM Taskbook c WHERE e.id = c.employee.id)")
    List<Employee> findAllEmployeeWithoutTaskbook();

    @Query("SELECT e FROM Employee e WHERE NOT e.statusEmployee.name = ?1")
    List<Employee> findAllEmployeeWithStatus(String status);

}
