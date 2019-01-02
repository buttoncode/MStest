package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.Provider;
import com.buttoncode.mstest.core.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class ProviderService implements Serializable{

    @Autowired
    ProviderRepository providerRepository;

    public List<Provider> getAllProviders() {return providerRepository.findAll();}

    public Provider getProviderById(Integer id) {return providerRepository.findOne(id);}

}
