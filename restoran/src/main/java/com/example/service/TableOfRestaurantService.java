package com.example.service;

import java.util.Collection;

import com.example.domain.TableOfRestaurant;

public interface TableOfRestaurantService {
	
	Collection<TableOfRestaurant> getAllTableOfRestaurant(Long id);
	
	TableOfRestaurant addTable(TableOfRestaurant table, Long id_reg);
	
	boolean deleteTable(TableOfRestaurant t);

	TableOfRestaurant getByNumber(int num, Long id);
	
	void updateTable(Long id_table, Long id_region);
}
