package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.StocktakingHardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocktakingHardwareRepository extends JpaRepository<StocktakingHardware, Integer>{

}
