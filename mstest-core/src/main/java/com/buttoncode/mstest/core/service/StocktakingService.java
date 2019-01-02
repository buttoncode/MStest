package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.*;
import com.buttoncode.mstest.core.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class StocktakingService implements Serializable{

    @Autowired
    StocktakingRepository stocktakingRepository;
    @Autowired
    StocktakingSoftwareRepository stocktakingSoftwareRepository;
    @Autowired
    StocktakingHardwareRepository stocktakingHardwareRepository;
    @Autowired
    StocktakingSoftwarePeriodicRepository stocktakingSoftwarePeriodicRepository;
    @Autowired
    ProviderRepository providerRepository;

    public List<Stocktaking> getAll (){return stocktakingRepository.findAll();}

    public List<StocktakingHardware> getAllStocktakingHardware() {return  stocktakingHardwareRepository.findAll();}
    public StocktakingHardware getOneByIdStocktakingHardware(Integer id) {return  stocktakingHardwareRepository.findOne(id);}

    public List<StocktakingSoftware> getAllStocktakingSoftware() {return  stocktakingSoftwareRepository.findAll();}
    public StocktakingSoftware getOneByIdStocktakingSoftware(Integer id) {return  stocktakingSoftwareRepository.findOne(id);}

    public List<StocktakingSoftwarePeriodic> getAllStocktakingSoftwarePeriodic() {return stocktakingSoftwarePeriodicRepository.findAll();}

    public List<Provider> getAllProviders() {return providerRepository.findAll();}

}
