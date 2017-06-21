package com.example.service;

import java.util.Collection;

import com.example.domain.Menu;
import com.example.domain.Offer;
import com.example.domain.Supply;

public interface OfferSupplyService {
	
	void updateSupp(Supply s) ;

	Supply createSupply(Supply supply);
	
	Collection<Supply> getSupplyByRest(Long id);
	
	Collection<Supply> getSupplyByRestChoosed(Long id);
	
	Collection<Supply> getWaitingSupply(Long id);
	
	Collection<Supply> getNotChosedSupply(Long id);
	
	void updateSupply(Supply supply);
	
	boolean isSupplyExist(Long id);
	
	Supply findSupply(Long id);
	
	Collection<Supply> getSupplyWithMyOffer(Long id);
	
	Collection<Supply> findAllSupply();
	
	void deleteSupply(Long id);
	
	Offer createOffer(Offer offer, Supply sup);
	
	void updateOffer(Offer offer);
	
	boolean isOfferExist(Long id);
	
	Offer findOffer(Long id);
	
	Collection<Offer> findAllOffer();
	
	void deleteOffer(Long id);
	
	void update(Supply s, Offer o);
	
	Offer updateOfferQualityAndPrice(Offer o, Supply s);
	
	void updateOfferToEnd(Offer o) ;
}
