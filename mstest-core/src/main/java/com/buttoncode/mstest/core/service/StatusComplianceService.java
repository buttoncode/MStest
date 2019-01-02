package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.StatusCompliance;
import com.buttoncode.mstest.core.repository.StatusComplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusComplianceService {

    @Autowired
    StatusComplianceRepository statusComplianceRepository;

    public List<StatusCompliance> getAllStatusCompliance() {return statusComplianceRepository.findAll();}

    public StatusCompliance getStatusComplianceByName(String name) {return statusComplianceRepository.findOneStatusComplianceByName(name);}

    public StatusCompliance getStatusComplianceById(Integer id) {return statusComplianceRepository.findOne(id);}
}
