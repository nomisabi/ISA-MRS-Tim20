package com.example.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;

@Service
public class SupplierServiceImp implements SupplierService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SupllierRepository supRepository;
	
	@Autowired
	private RestaurantRepository restRepository;


	@Override
	public Collection<Supplier> findAll() {
		logger.info("> findAll");
		Collection<Supplier> supps = (Collection<Supplier>) supRepository.findAll();
		logger.info("< findAll");
		return supps;
	}

	@Override
	public Supplier findOne(Long id) {
		logger.info("> findOne id:{}", id);
		Supplier sup = supRepository.findOne(id);
		logger.info("< findOne id:{}", id);
		return sup;
	}


	@Override
	public boolean isSupplierExist(Long id){
		return supRepository.exists(id);
		
	}
	
	@Override
	public void update(Supplier s) {
		logger.info("> update ", s);
		supRepository.updatePass(s.getId(),  s.getEmail(),s.getPassword(), s.getName(), s.isActive());
		logger.info("< update :{}", s);
	}

	@Override
	public Collection<Restaurant> getRest(Long id) {

		Collection<Restaurant> r=restRepository.getRest(id);
		System.out.println("rest: "+r.size());
		return r;
	}

	@Override
	public Collection<Supplier> getSupp(Long id) {
		return supRepository.getSuppByRest(id);
	}
	


}
