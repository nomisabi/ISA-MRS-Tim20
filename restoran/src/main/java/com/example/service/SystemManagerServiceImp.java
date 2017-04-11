package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Manager;
import com.example.domain.System_manager;
import com.example.respository.SystemManRepository;
import com.example.respository.SystemManagerRepository;

@Service
public class SystemManagerServiceImp implements SystemManagerService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SystemManRepository smRepository;
    
    @Override
	public void signUP(System_manager sm){
        logger.info("> sing up");
        smRepository.signUP(sm);
        logger.info("< sing up");
	}

	@Override
	public void createManager(Manager m, Restaurant r) {
		logger.info("> create manager");
        smRepository.createManager(m, r);
        logger.info("< create manager");
	}

    
    
}
