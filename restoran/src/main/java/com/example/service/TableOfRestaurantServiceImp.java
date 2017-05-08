package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.TableOfRestaurant;
import com.example.respository.TableOfRestaurantRepository;

@Service
public class TableOfRestaurantServiceImp implements TableOfRestaurantService {
	
	@Autowired
	private TableOfRestaurantRepository repository;
	
	@Override
	public Collection<TableOfRestaurant> getAllTableOfRestaurant(Long id){
		return repository.getRequests(id);
	}
	
	@Override
	public TableOfRestaurant addTable(TableOfRestaurant table){
		return repository.save(table);
	}
	

}
