package com.example.service;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Manager;
import com.example.domain.System_manager;
import com.example.respository.ManRepository;
import com.example.respository.SystemManRepository;

@Service
public class ManagerServiceImpl implements ManagerService{
	
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
