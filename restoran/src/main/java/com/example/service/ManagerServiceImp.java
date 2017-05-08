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
import com.example.respository.EmployeeRepository;
import com.example.respository.ManagerRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SystemManagerRepository;

@Service
public class ManagerServiceImp implements ManagerService{
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ManagerRepository mRepository;
    @Autowired
    private RestaurantRepository rRepository;
    @Autowired
    private EmployeeRepository eRepository;
    @Autowired
    private SupllierRepository sRepository;
    
    @Override
	public Manager createManager(Manager m) {
		logger.info("> create manager");
        return mRepository.save(m);
       // logger.info("< create manager");
	}

	/*@Override
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
	*/
	@Override
	public Collection<Manager> findAll() {
		logger.info("> findAll");
		Collection<Manager> manager = (Collection<Manager>) mRepository.findAll();
		for (Manager m:manager)
			if (mRepository.getRest(m.getId())!=null){
				Restaurant r=rRepository.findOne(mRepository.getRest(m.getId()));
				//m.setRestaurant(r);
			}
		logger.info("< findAll");
		return manager;
	}
	
	public Restaurant findRest(Long id) {
		logger.info("> /nfindRestSERVICE/n");
		Restaurant r=null;
		Long id_r= mRepository.getRest(id);
		logger.info("> /n id:"+id_r);
		if (id_r!=null){
			r=rRepository.findOne(mRepository.getRest(id));		
			logger.info(r.toString());
		}
		logger.info("<  /nfindRestSERVICE/n");
		return r;
	}
	

	@Override
	public Manager findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Manager manager = mRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return manager;
	}
	/*
	@Override
	public Manager findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		Manager manager = mRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return manager;
		
	}
*/
	@Override
	public boolean isManagerExist(Long id){
		return mRepository.exists(id);
		
	}
/*
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
		
	}*/

	//@Override
	//public int update(Manager man, Restaurant r) {
		//return mRepository.updateRestaurant(man.getId(), r);
		
	//}
	
	@Override
	public void update(Manager man) {
		mRepository.updatePass(man.getId(), man.getPassword(), man.getEmail(), man.getFirstName(), man.getLastName(), man.isActive());
	
	}

	@Override
	public Employee createEmployee(Employee e) {	
		return eRepository.save(e);
	}

	@Override
	public Supplier createSupplier(Supplier s) {
		return sRepository.save(s);
	}

	@Override
	public void updateRest(Long id_r, Long id_s) {
		rRepository.insertSup(id_r, id_s);
	}

}
