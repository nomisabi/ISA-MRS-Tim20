package com.example.respository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Supplier;

@Repository
public class SupplierRepositoryImp implements SupllierRepository {
	private static AtomicLong counter = new AtomicLong();

	private Supplier supplier=null;
	private final ConcurrentMap<String, Supplier> supps = new ConcurrentHashMap<String, Supplier>();


	@Override
	public Collection<Supplier> findAll() {
		return this.supps.values();
	}

	@Override
	public Supplier findOne(Long id) {
		return this.supps.get(id);
	}
	
	@Override
	public Supplier findByEmail(String email){
		supps.put("1", new Supplier("1","1", "1"));
		for (Supplier s : supps.values()) {
			if (s.getEmail().equalsIgnoreCase(email)){
				return s;
			}
		}
		return null;
	}
	
	@Override
	public boolean isSupplierExist(Supplier sup){
		return findByEmail(sup.getEmail()) != null;
	}

	@Override
	public boolean changeData(Supplier s) {
			if (supps.containsKey(s.getEmail())){
				supps.replace(s.getEmail(), s);
				if (this.supplier.getEmail().equals(s.getEmail()))
					this.supplier=s;
				return true;
				}
			return false;
		}

	@Override
	public void setLogedIn(Supplier sm) {
		this.supplier=sm;
		
	}

	@Override
	public Supplier getLogedIn() {
		//System.out.println("get->"+ this.supplier.toString());
		return this.supplier;
	}



}
