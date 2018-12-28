package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Employeedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeedataRepository extends JpaRepository <Employeedata, Integer>, JpaSpecificationExecutor<Employeedata> {


    @Query("SELECT ed FROM Employeedata ed WHERE ed.employee.id = ?1 ORDER BY ed.beginningofvalidity ASC")
    List<Employeedata> findAllByIdEmployee(Integer employeeid);


}
