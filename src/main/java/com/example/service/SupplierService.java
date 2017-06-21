package com.example.service;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;

public interface SupplierService {

	Collection<Supplier> findAll();

	Supplier findOne(Long id);

	boolean isSupplierExist(Long id);
	
	void update(Supplier s);
	
	Collection<Restaurant> getRest(Long id);

	Collection<Supplier> getSupp(Long id);
}
