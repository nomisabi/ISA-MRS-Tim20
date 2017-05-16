package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.respository.RegionRepository;

@Service
public class RegionServiceImp implements RegionService{
	
	@Autowired
	private RegionRepository repository;
	
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

}
