package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.Periodic;
import com.buttoncode.mstest.core.repository.PeriodicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeriodicService {

    @Autowired
    PeriodicRepository periodicRepository;

    public List<Periodic> getAlllPeriodic(){return periodicRepository.findAll();}
}
