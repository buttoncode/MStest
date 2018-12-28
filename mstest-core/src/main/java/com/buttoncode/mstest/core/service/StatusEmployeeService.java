package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.StatusEmployee;
import com.buttoncode.mstest.core.repository.StatusEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusEmployeeService {

    @Autowired
    StatusEmployeeRepository statusEmployeeRepository;

    public List<StatusEmployee> getAllStatusEmployee() {return statusEmployeeRepository.findAll();}

    public StatusEmployee getStatusEmployeeByName(String name) {return statusEmployeeRepository.findOneByName(name);}

}
