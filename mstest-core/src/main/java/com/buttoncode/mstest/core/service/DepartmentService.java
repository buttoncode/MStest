package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.Department;
import com.buttoncode.mstest.core.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {
    @Autowired private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments () {
        Sort sort = new Sort(Sort.Direction.ASC,"shortname");
        return departmentRepository.findAll(sort);
    }

    public Department getDepartmentById (Integer id) {return departmentRepository.findById(id);}
    public Department getOneDepartmentByName (String name) {return departmentRepository.findOneByName(name);}
    public Department getOneDepartmentByShortname (String shortname) {return departmentRepository.findOneByShortname(shortname);}
    public Department updateDepartment(Department department){
        return departmentRepository.save(department);
    }

}
