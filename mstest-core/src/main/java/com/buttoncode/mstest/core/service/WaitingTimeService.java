package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.WaitingTime;
import com.buttoncode.mstest.core.repository.WaitingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WaitingTimeService {

    @Autowired
    WaitingTimeRepository waitingTimeRepository;

    public WaitingTime getByTime (String time) {return waitingTimeRepository.findByTime(time);}
}
