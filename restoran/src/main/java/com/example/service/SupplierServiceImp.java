package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.respository.SupplierRepositoryImp;

@Service
public class SupplierServiceImp implements SupplierService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SupplierRepositoryImp supRepository;


	@Override
	public Collection<Supplier> findAll() {
		logger.info("> findAll");
		Collection<Supplier> supps = supRepository.findAll();
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
	public Supplier findByEmail(String email){
		logger.info("> findByEmail email:{}", email);
		Supplier sup = supRepository.findByEmail(email);
		logger.info("< findByEmail email:{}", email);
		return sup;
		
	}

	@Override
	public boolean isSupplierExist(Supplier guest){
		return supRepository.isSupplierExist(guest);
		
	}
	
	@Override
	public boolean changeData(Supplier s) {
		logger.info("> changePass ", s);
		boolean val =supRepository.changeData( s);
		logger.info("< changePass :{}", s);
		return val;
	}
	

	@Override
	public Supplier getLogedIn() {
		return supRepository.getLogedIn();
	}

	@Override
	public void setLogedIn(Supplier m) {
		supRepository.setLogedIn( m);
		
	}

}
