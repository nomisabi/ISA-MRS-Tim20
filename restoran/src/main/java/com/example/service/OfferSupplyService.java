package com.example.service;

import java.util.Collection;

import com.example.domain.Menu;
import com.example.domain.Offer;
import com.example.domain.Supply;

public interface OfferSupplyService {

	Supply createSupply(Supply supply);
	
	void updateSupply(Supply supply);
	
	boolean isSupplyExist(Long id);
	
	Supply findSupply(Long id);
	
	Collection<Supply> findAllSupply();
	
	void deleteSupply(Long id);
	
	Offer createOffer(Offer offer);
	
	void updateOffer(Offer offer);
	
	boolean isOfferExist(Long id);
	
	Offer findOffer(Long id);
	
	Collection<Offer> findAllOffer();
	
	void deleteOffer(Long id);
	
}
