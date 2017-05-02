package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;
import com.example.respository.ManRepository;
import com.example.respository.SystemManagerRepository;

@Service
public class ManagerServiceImp implements ManagerService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ManRepository mRepository;
 
    @Override
	public void createManager(Manager m) {
		logger.info("> create manager");
        mRepository.createManager(m);
        logger.info("< create manager");
	}

	@Override
	public Employee createEmployee(Employee e) {
		
		logger.info("> create employee");
		Employee ret= mRepository.createEmployee(e);
        logger.info("< create employee");
		return ret;
	}

	@Override
	public Supplier createSupplier(Supplier s) {
		logger.info("> create supplier");
		Supplier ret= mRepository.createSupplier(s);
        logger.info("< create supplier");
		return ret;
	}
	
	@Override
	public Collection<Manager> findAll() {
		logger.info("> findAll");
		Collection<Manager> manager = mRepository.findAll();
		logger.info("< findAll");
		return manager;
	}

	@Override
	public Manager findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Manager manager = mRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return manager;
	}
	
	@Override
	public Manager findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		Manager manager = mRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return manager;
		
	}

	@Override
	public boolean isManagerExist(Manager m){
		return mRepository.isManagerExist(m);
		
	}

	@Override
	public boolean changePassword(String newP, String oldP, Manager m) {
		logger.info("> changePass ", m);
		boolean val = mRepository.changePassword(newP, oldP, m);
		logger.info("< changePass :{}", m);
		return val;
	}

	@Override
	public Manager getLogedIn() {
		return mRepository.getLogedIn();
	}

	@Override
	public void setLogedIn(Manager m) {
		mRepository.setLogedIn( m);
		
	}

}
