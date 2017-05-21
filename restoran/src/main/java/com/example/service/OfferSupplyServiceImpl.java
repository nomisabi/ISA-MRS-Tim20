package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Offer;
import com.example.domain.Supply;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuRepository;
import com.example.respository.OfferRepository;
import com.example.respository.SupplyRepository;

@Service
public class OfferSupplyServiceImpl  implements OfferSupplyService{

    @Autowired
    private OfferRepository offerRepository;
    
    @Autowired
    private SupplyRepository supplyRepository;
    
	@Override
	public Supply createSupply(Supply supply) {
		return supplyRepository.save(supply);
	}

	@Override
	public void updateSupply(Supply supply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSupplyExist(Long id) {
		return supplyRepository.exists(id);
	}

	@Override
	public Supply findSupply(Long id) {
		return supplyRepository.findOne(id);
	}

	@Override
	public Collection<Supply> findAllSupply() {
		return (Collection<Supply>) supplyRepository.findAll();
	}

	@Override
	public void deleteSupply(Long id) {
		supplyRepository.delete(id);
		
	}

	@Override
	public Offer createOffer(Offer offer) {
		return offerRepository.save(offer);
	}

	@Override
	public void updateOffer(Offer offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOfferExist(Long id) {
		return offerRepository.exists(id);
	}

	@Override
	public Offer findOffer(Long id) {
		return offerRepository.findOne(id);
	}

	@Override
	public Collection<Offer> findAllOffer() {
		return (Collection<Offer>) offerRepository.findAll();
	}

	@Override
	public void deleteOffer(Long id) {
		offerRepository.delete(id);
		
	}

}
