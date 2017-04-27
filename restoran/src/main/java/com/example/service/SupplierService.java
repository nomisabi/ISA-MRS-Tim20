package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;

public interface SupplierService {

	Collection<Supplier> findAll();

	Supplier findOne(Long id);

	Supplier findByEmail(String email);

	boolean isSupplierExist(Supplier sup);
	
	boolean changeData(Supplier s);
	
	Supplier getLogedIn();
	void setLogedIn(Supplier s);

}
