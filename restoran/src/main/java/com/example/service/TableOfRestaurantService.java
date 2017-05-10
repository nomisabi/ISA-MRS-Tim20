package com.example.service;

import java.util.Collection;

import com.example.domain.TableOfRestaurant;

public interface TableOfRestaurantService {
	
	Collection<TableOfRestaurant> getAllTableOfRestaurant(Long id);
	
	TableOfRestaurant addTable(TableOfRestaurant table);

}
