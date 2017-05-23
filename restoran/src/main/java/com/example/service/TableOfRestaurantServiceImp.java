package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
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
	@Lock(LockModeType.PESSIMISTIC_READ)
	public void deleteTable(Long id) {
		repository.delete(id);
		}
	
	@Override
	public TableOfRestaurant getByNumber(int num, Long id) {
		return repository.getByNum(num, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateTable(Long id_table, Long id_region) {
		System.out.println("table: "+id_table+"region: "+id_region);
		repository.insertReg(id_table, id_region);
		
	}

}
