package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.EmployeeIncident;
import com.buttoncode.mstest.core.repository.EmployeeIncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIncidentService {
    @Autowired
    EmployeeIncidentRepository employeeIncidentRepository;

    EmployeeIncident getOneEmployeeIncident (String name) {return employeeIncidentRepository.findOneEmployeeIncidentByName(name);}

}
