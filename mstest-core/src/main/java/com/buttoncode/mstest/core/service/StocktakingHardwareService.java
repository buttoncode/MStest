package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.repository.StocktakingHardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StocktakingHardwareService {

    @Autowired
    StocktakingHardwareRepository stocktakingHardwareRepository;
}