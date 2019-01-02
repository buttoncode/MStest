package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.StocktakingSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocktakingSoftwareRepository extends JpaRepository<StocktakingSoftware, Integer>{

}
