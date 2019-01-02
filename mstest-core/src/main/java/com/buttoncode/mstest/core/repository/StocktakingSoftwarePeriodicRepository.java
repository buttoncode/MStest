package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.StocktakingSoftwarePeriodic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocktakingSoftwarePeriodicRepository extends JpaRepository<StocktakingSoftwarePeriodic, Integer>{

}
