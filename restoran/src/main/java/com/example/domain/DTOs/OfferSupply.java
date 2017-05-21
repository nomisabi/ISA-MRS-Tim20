package com.example.domain.DTOs;

import com.example.domain.Offer;
import com.example.domain.Supply;

public class OfferSupply {
	private Offer o;
	private Supply s;
	
	public Offer getO() {
		return o;
	}
	public void setO(Offer o) {
		this.o = o;
	}
	public Supply getS() {
		return s;
	}
	public void setS(Supply s) {
		this.s = s;
	}
	
	public OfferSupply(Offer o, Supply s) {
		super();
		this.o = o;
		this.s = s;
	}
	
	public OfferSupply() {
		super();
	}
	
}
