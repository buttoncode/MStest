package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.Stocktaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StocktakingRepository extends JpaRepository<Stocktaking, Integer>, JpaSpecificationExecutor<Stocktaking> {

}