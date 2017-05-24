package com.example.service;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.respository.ReservationRepository;
import com.example.respository.TableOfRestaurantRepository;
import com.example.respository.TableReservationRepository;

@Service
@Transactional(readOnly = true)
public class TableOfRestaurantServiceImp implements TableOfRestaurantService {
	
	@Autowired
	private TableOfRestaurantRepository repository;
	
	@Autowired
	private TableReservationRepository reservationRepository;
	
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
	public boolean deleteTable(TableOfRestaurant t) {
		TableOfRestaurant table=repository.getByNum(t.getNumber(), t.getRestaurant().getId());
		Collection<TableReservation> tables=reservationRepository.getByTable(table.getId());
		if (tables.size()!=0)
			return false;
		repository.delete(table.getId());
		return true;
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
