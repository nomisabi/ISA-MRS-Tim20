package com.example.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.domain.RateMenuItem;
import com.example.domain.Restaurant;
import com.example.domain.DTOs.FoodRate;
import com.example.domain.DTOs.IncomesByDay;
import com.example.domain.DTOs.IncomesByWaiters;
import com.example.domain.DTOs.RateWaiter;
import com.example.domain.DTOs.Visits;
import com.example.respository.BillRepository;
import com.example.respository.RateMenuItemRepository;
import com.example.respository.RateRestaurantRepository;
import com.example.respository.RateWaiterRepository;
import com.example.respository.ReservationRepository;
import com.example.respository.RestaurantRepository;

import scala.collection.parallel.ParIterableLike.Collect;

@Service
public class RestaurantServiceImp implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ReservationRepository reservaitonRepository;
	
	@Autowired
	RateRestaurantRepository rateRepository;
	
	@Autowired
	RateMenuItemRepository rateMenuRepository;
	
	@Autowired
	RateWaiterRepository rateWaiterRepository;

	@Autowired
	BillRepository billRepository;
	
	@Override
	public Restaurant getRestaurant(Long id) {
		return restaurantRepository.findOne(id);
	}

	@Override
	public Collection<Restaurant> getAllRestaurants() {
		return (Collection<Restaurant>) restaurantRepository.findAll();
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		Assert.notNull(restaurant, "Restaurant could not be null.");
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void updateMenu(Restaurant r) {
		restaurantRepository.updateMenu(r.getId(), r.getMenu().getId());
		
	}
	
	@Override
	public void updateDrinkMenu(Restaurant r) {
		restaurantRepository.updateDrinkMenu(r.getId(), r.getDrinkMenu().getId());
		
	}


	@Override
	public void insertEmpl(Long id_r, Long id_e) {
		restaurantRepository.insertEmpl(id_r, id_e);
		
	}

	@Override
	public double getAvg(Long id) {
		try{
			return rateRepository.getAvgRest(id);
		} catch(Exception e) {
			return 0;
		}
	}
	
	@Override
	public Collection<FoodRate> getAvgByFood(Long id) {
		//try{
		Collection<FoodRate> foodrate = new ArrayList<FoodRate>();
		for (Object[] employee: rateMenuRepository.getAvgByFood(id)){
	         String name = (String) employee[0];
	         double rate = ((BigDecimal) employee[1]).doubleValue();
	         FoodRate fr= new FoodRate(name, rate);
	         foodrate.add(fr);
		}
	    return foodrate;
	}
	
	@Override
	public Collection<RateWaiter> getAvgByWaiter(Long id) {
		//try{
		Collection<RateWaiter> waiterrate = new ArrayList<RateWaiter>();
		for (Object[] employee: rateWaiterRepository.getAvgByWaiter(id)){
	         String first_name = (String) employee[0];
	         String last_name = (String) employee[1];
	         double rate = ((BigDecimal) employee[2]).doubleValue();
	         RateWaiter wr= new RateWaiter(first_name,last_name, rate);
	         waiterrate.add(wr);
		}
	    return waiterrate;
					
	}
	
	@Override
	public Collection<Visits> getVisits(Long id) {
		//try{
		Collection<Visits> visits = new ArrayList<Visits>();
		for (Object[] visit: billRepository.getVisits(id)){
	         String day = (String) visit[0];
	         int numb = ((BigInteger) visit[1]).intValue();
	         //System.out.println(day);
	         Visits v= new Visits(day,numb);
	         visits.add(v);
		}
	    return visits;
				
	}
	
	@Override
	public Collection<IncomesByWaiters> getIncomes(Long id) {
		//try{
		Collection<IncomesByWaiters> incomes = new ArrayList<IncomesByWaiters>();
		for (Object[] income: billRepository.getIncomesByWaiter(id)){
			 String first_name = (String) income[0];
	         String last_name = (String) income[1];
	         double price = ((Double) income[2]).doubleValue();
	         IncomesByWaiters v= new IncomesByWaiters(first_name, last_name, price);
	         incomes.add(v);
		}
	    return incomes;
				
	}

	@Override
	public Collection<IncomesByDay> getIncomes(String begin, String end, Long id) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date begin_date = format.parse(begin);
		Date end_date = format.parse(end);
		
		Collection<IncomesByDay> incomes = new ArrayList<IncomesByDay>();
		for (Object[] income: billRepository.getIncomes( id)){
			 String day = (String) income[0];
			 Date day_date = format.parse(day);
			 
	         double price = ((Double) income[1]).doubleValue();
	         IncomesByDay v= new IncomesByDay(day, price);
	         if (day_date.after(begin_date) && day_date.before(end_date))
	        	 incomes.add(v);
		}
	    return incomes;
	}
	@Override
	public Collection<Restaurant> searchRestaurants(String search){
		return restaurantRepository.searchRestaurants(search);
	}
	
	

}
