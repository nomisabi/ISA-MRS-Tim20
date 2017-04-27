package com.example.respository;

import java.util.Collection;

import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;
import com.example.domain.System_manager;

public interface SupllierRepository {

	Collection<Supplier> findAll();

	Supplier findOne(Long id);
	
	Supplier findByEmail(String email);
	
	boolean isSupplierExist(Supplier sup);
	
	boolean changeData( Supplier s);
	
	void setLogedIn(Supplier sm);
	
	Supplier getLogedIn();

}
