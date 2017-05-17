package com.example.domain.DTOs;

import com.example.domain.Region;
import com.example.domain.TableOfRestaurant;

public class TableRegion {
	private TableOfRestaurant t;
	private Region r;
	
	public TableRegion(TableOfRestaurant t, Region r) {
		super();
		this.t = t;
		this.r = r;
	}
	
	public TableRegion() {
		super();
	}

	public TableOfRestaurant getT() {
		return t;
	}

	public void setT(TableOfRestaurant t) {
		this.t = t;
	}

	public Region getR() {
		return r;
	}

	public void setR(Region r) {
		this.r = r;
	}
	
}
