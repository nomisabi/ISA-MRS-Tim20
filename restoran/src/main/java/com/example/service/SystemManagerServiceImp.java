package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.System_manager;
import com.example.respository.ManagerRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SystemManagerRepository;

@Service
public class SystemManagerServiceImp implements SystemManagerService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ManagerRepository mRepository;
    
    @Autowired
    private SystemManagerRepository smRepository;
    
    @Autowired
    private RestaurantRepository rRepository;
    
   /* @Override
	public System_manager signUP(System_manager sm){
        logger.info("> sing up");
        return smRepository.signUP(sm);
	}

	@Override
	public Manager createManager(Manager m) {
		logger.info("> create manager");
        Manager retVal = smRepository.createManager(m);
        logger.info("< create manager");
        return retVal;
	}

	@Override
	public Boolean login(System_manager m) {
		
		logger.info("> login");
		Boolean value= smRepository.login(m);
        logger.info("< login");
		return value;
	}
*/
	
	@Override
	public Collection<System_manager> findAll() {
		logger.info("> findAll");
		Collection<System_manager> manager = (Collection<System_manager>) smRepository.findAll();
		logger.info("< findAll");
		return manager;
	}

	@Override
	public System_manager findOne(Long id) {
		logger.info("> findOne id:{}", id);
		System_manager manager = smRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return manager;
	}
	/*
	@Override
	public System_manager findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		System_manager manager = smRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return manager;
		
	}
*/
	@Override
	public boolean isSysManagerExist(Long id){
		return smRepository.exists(id);
		
	}
	@Override
	public System_manager addSysMan(System_manager sm){
		return smRepository.save(sm);		
	}
	
	@Override
	public Collection<Restaurant> findAllRest(){
		return (Collection<Restaurant>) rRepository.findAll();
	}

	@Override
	public Restaurant findOneRest(Long id){
		return rRepository.findOne(id);	
	}

	@Override
	public boolean isRestaurantExist(Long id){
		return rRepository.exists(id);	
	}
	
	@Override
	public Restaurant addRestaurant(Restaurant r){
		Restaurant res= rRepository.save(r);
		return res;	
	}
	
	@Override
	public Restaurant update(Restaurant r){
		rRepository.delete(r.getId());
		rRepository.save(r);
		return r;	
	}

	@Override
	public void update(System_manager man) {
		logger.info("> update ");
		smRepository.updatePass(man.getId(), man.getEmail(), man.getPassword(), man.getFirstName(), man.getLastName());
		logger.info("< update :{}", man);
		
	}

	@Override
	public void insertManager(Long id_r, Long id_m) {
		rRepository.insertMan(id_r,id_m);
		
	}
	
	
}
