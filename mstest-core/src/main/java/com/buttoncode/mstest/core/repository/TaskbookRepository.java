package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Taskbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskbookRepository extends JpaRepository<Taskbook, Integer> {

    @Query("SELECT tb FROM Taskbook tb WHERE tb.employee.id = ?1 ORDER BY tb.taskbookdate ASC")
    List<Taskbook> findAllByIdEmployee(Integer employeeid);

}
