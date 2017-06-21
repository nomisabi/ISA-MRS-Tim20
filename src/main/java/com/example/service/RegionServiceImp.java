package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.respository.RegionRepository;
import com.example.respository.TableOfRestaurantRepository;

@Service
public class RegionServiceImp implements RegionService{
	
	@Autowired
	private RegionRepository repository;
	
	@Autowired
	private TableOfRestaurantRepository tableRepository;
	
	@Override
	public Collection<Region> getAllRegion (){
		return repository.findAll();
	}
	
	@Override
	public Region addRegion(Region region){
		return repository.save(region);
	}
	
	@Override
	public Collection<Region> getRegion(Restaurant rest){
		return (Collection<Region>) repository.getRequests(rest.getId());
	}

	@Override
	public void updateName(Region r) {
		System.out.println(r.getName()+", id:"+r.getId());
		repository.updateName(r.getId(), r.getName());	
	}

	@Override
	public void deleteRegion(Region r) {
		repository.delete(r.getId());
		
	}

	@Override
	public Collection<TableOfRestaurant> getTables(Long id) {
		return tableRepository.getTablesByRegion(id);
	}

}
