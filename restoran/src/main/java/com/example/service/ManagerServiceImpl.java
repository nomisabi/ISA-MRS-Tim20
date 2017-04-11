package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Manager;
import com.example.respository.ManRepository;

public class ManagerServiceImpl implements ManagerService {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ManRepository mRepository;
    

	@Override
	public void createManager(Manager m) {
		logger.info("> create manager");
        mRepository.createManager(m);
        logger.info("< create manager");
	}

}
