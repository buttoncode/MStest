package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.SectionTop;
import com.buttoncode.mstest.core.repository.SectionTopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SectionTopService {

    @Autowired
    SectionTopRepository sectionTopRepository;

    public List<SectionTop> getAllSections(){return sectionTopRepository.findAll();}
}
