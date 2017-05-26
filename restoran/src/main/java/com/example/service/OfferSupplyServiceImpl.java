package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Supply;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuRepository;
import com.example.respository.OfferRepository;
import com.example.respository.SupplyRepository;

@Service
@Transactional(readOnly = true)
public class OfferSupplyServiceImpl  implements OfferSupplyService{

    @Autowired
    private OfferRepository offerRepository;
    
    @Autowired
    private SupplyRepository supplyRepository;
    
	@Override
	@Transactional(readOnly = false)
	public Supply createSupply(Supply supply) {
		return supplyRepository.save(supply);
	}

	@Override
	@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
	public void deleteSupply(Long id) {
		supplyRepository.delete(id);
		
	}

	
	/*    EY KELL - PLUSZ LEELLENORIZNI A SUPPLY,AKTIVE-E   */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Offer createOffer(Offer offer, Long id) {
		Supply s= supplyRepository.findOne(id);
		if (s.isChosed())
			return null;
		Offer o= offerRepository.save(offer);
		offerRepository.updateName(o.getId(), id);
		return o;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateOffer(Offer offer) {
		offerRepository.updateStatus(offer.getId(), 3);
		
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteOffer(Long id) {
		offerRepository.delete(id);
		
	}

	@Override
	public Collection<Supply> getSupplyByRest(Long id) {
		
		return (Collection<Supply>) supplyRepository.getSupplyByRest(id);
	}

	
	
	
	@Override
	@Transactional(readOnly = false)
	public void update(Supply s, Offer o) {
		for (Offer offer : s.getOffer()) {
			if (o.getId()==offer.getId())
				offerRepository.updateStatus(offer.getId(), 2);
			else	
				offerRepository.updateStatus(offer.getId(), 1);
		}
		supplyRepository.updateStatus(s.getId(), true);
		
	}

	@Override
	public Collection<Supply> getSupplyByRestChoosed(Long id) {
	
		return (Collection<Supply>) supplyRepository.getSupplyByRestChoosed(id);
	}

	@Override
	public Collection<Supply> getWaitingSupply(Long id) {
		return  (Collection<Supply>) supplyRepository.getWatingSupply(id);
	}

	
	
	/*    EY KELL - PLUSZ LEELLENORIZNI A SUPPLY,AKTIVE-E   */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Offer updateOfferQualityAndPrice(Offer o, Supply sup) {
		//System.out.println(o.toString());
		Supply s= supplyRepository.findOne(sup.getId());
		if (s.isChosed())
			return null;
		offerRepository.update(o.getId(), o.getQuality(), o.getPrice());
		return o;
	}

	@Override
	public Collection<Supply> getSupplyWithMyOffer(Long id) {
		// TODO Auto-generated method stub
		return supplyRepository.getSupplyWithMyOffer(id);
	}

}
