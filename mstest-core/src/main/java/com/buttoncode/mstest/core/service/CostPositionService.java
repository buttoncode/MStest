package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.CostPosition;
import com.buttoncode.mstest.core.repository.CostPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CostPositionService {

    @Autowired
    CostPositionRepository costPositionRepository;

    public List<CostPosition> getAllCostPosition (){return costPositionRepository.findAll();}

    public CostPosition getOneCostPostiionByName (String name) {return costPositionRepository.findOneByNamempk(name);}

}
