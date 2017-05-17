package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.TableOfRestaurant;
import com.example.respository.TableOfRestaurantRepository;

@Service
@Transactional(readOnly = true)
public class TableOfRestaurantServiceImp implements TableOfRestaurantService {
	
	@Autowired
	private TableOfRestaurantRepository repository;
	
	@Override
	public Collection<TableOfRestaurant> getAllTableOfRestaurant(Long id){
		return repository.getRequests(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public TableOfRestaurant addTable(TableOfRestaurant table, Long id_reg){
		TableOfRestaurant t= repository.save(table);
		repository.insertReg(table.getId(), id_reg);
		return t;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTable(Long id) {
		repository.delete(id);
		System.out.println("izbrisao: "+id);
	}
	
	@Override
	public TableOfRestaurant getByNumber(int num, Long id) {
		return repository.getByNum(num, id);
	}

}
