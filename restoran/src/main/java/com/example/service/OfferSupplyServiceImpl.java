package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Offer;
import com.example.domain.Offer_status;
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
	public Offer createOffer(Offer offer, Long id) {
		Offer o= offerRepository.save(offer);
		offerRepository.updateName(o.getId(), id);
		return o;
	}

	@Override
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
	public void deleteOffer(Long id) {
		offerRepository.delete(id);
		
	}

	@Override
	public Collection<Supply> getSupplyByRest(Long id) {
		/*String now="";
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR)-2;
		if (month<10)
			if (day<10)
				now+=year+"-0"+month+"-0"+day+"T"+hours+":"+date.getMinutes()+":"+date.getSeconds();
			else
				now+=year+"-0"+month+"-"+day+"T"+hours+":"+date.getMinutes()+":"+date.getSeconds();
		else
			if (day<10)
				now+=year+"-"+month+"-0"+day+"T"+hours+":"+date.getMinutes()+":"+date.getSeconds();
			else
				now+=year+"-"+month+"-"+day+"T"+dhours+":"+date.getMinutes()+":"+date.getSeconds();
		System.out.println("Datum (2017-05-23T17:25:22.186Z):"+now);*/
		return (Collection<Supply>) supplyRepository.getSupplyByRest(id);
	}

	@Override
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
		/*String now="";
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month<10)
			if (day<10)
				now+=year+"-0"+month+"-0"+day+"T"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			else
				now+=year+"-0"+month+"-"+day+"T"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		else
			if (day<10)
				now+=year+"-"+month+"-0"+day+"T"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			else
				now+=year+"-"+month+"-"+day+"T"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		System.out.println("Datum (2017-05-23T17:25:22.186Z):"+now);*/
		return (Collection<Supply>) supplyRepository.getSupplyByRestChoosed(id);
	}

	@Override
	public Collection<Supply> getWaitingSupply(Long id) {
		return  (Collection<Supply>) supplyRepository.getWatingSupply(id);
	}

	@Override
	public void updateOfferQualityAndPrice(Offer o) {
		offerRepository.update(o.getId(), o.getQuality(), o.getPrice());  
		
	}

}
