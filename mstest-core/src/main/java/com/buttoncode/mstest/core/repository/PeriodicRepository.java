package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Periodic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicRepository extends JpaRepository<Periodic, Integer>{

}
