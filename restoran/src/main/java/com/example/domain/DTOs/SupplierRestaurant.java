package com.example.domain.DTOs;

import com.example.domain.Restaurant;
import com.example.domain.Supplier;

public class SupplierRestaurant {
	private Restaurant r;
	private Supplier s;
	
	public Restaurant getR() {
		return r;
	}
	
	public void setR(Restaurant r) {
		this.r = r;
	}
	
	public Supplier getS() {
		return s;
	}
	
	public void setS(Supplier s) {
		this.s = s;
	}
	
	public SupplierRestaurant(Restaurant r, Supplier s) {
		super();
		this.r = r;
		this.s = s;
	}
	
	public SupplierRestaurant() {
		super();
	}
	
}
