package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.DepartmentBottom;
import com.buttoncode.mstest.core.repository.DepartmentBottomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentBottomService {

    @Autowired private DepartmentBottomRepository deparamentBottomRepository;

    public List<DepartmentBottom> getAll (){return deparamentBottomRepository.findAll();}

    public DepartmentBottom getOneByName (String name) {return deparamentBottomRepository.findOneByName(name);}
    public DepartmentBottom getOneByShortname (String shortname) {return deparamentBottomRepository.findOneByShortname(shortname);}

}
