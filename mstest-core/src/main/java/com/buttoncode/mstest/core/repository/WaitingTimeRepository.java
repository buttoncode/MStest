package com.buttoncode.mstest.core.repository;

import com.buttoncode.mstest.core.entities.WaitingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingTimeRepository extends JpaRepository <WaitingTime, Integer> {

    WaitingTime findByTime(String time);
}
