package com.example.service;

import java.text.ParseException;
import java.util.Collection;

import com.example.domain.Restaurant;
import com.example.domain.DTOs.FoodRate;
import com.example.domain.DTOs.IncomesByDay;
import com.example.domain.DTOs.IncomesByWaiters;
import com.example.domain.DTOs.RateWaiter;
import com.example.domain.DTOs.Visits;

public interface RestaurantService {
	
	Restaurant getRestaurant(Long id);
	
	Collection<Restaurant> getAllRestaurants();
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	void updateMenu(Restaurant r);
	
	void insertEmpl(Long id_r, Long id_e);

	void updateDrinkMenu(Restaurant r);
	
	double getAvg(Long id);
	
	public Collection<FoodRate> getAvgByFood(Long id) ;
	
	public Collection<RateWaiter> getAvgByWaiter(Long id);
	
	public Collection<Visits> getVisits(Long id);
	
	public Collection<IncomesByWaiters> getIncomes(Long id);
	
	public Collection<IncomesByDay> getIncomes(String begin, String end, Long id) throws ParseException;
	
	Collection<Restaurant> searchRestaurants(String search);
	
	
}
