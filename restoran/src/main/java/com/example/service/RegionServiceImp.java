package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Region;
import com.example.respository.RegionRepository;

@Service
public class RegionServiceImp implements RegionService{
	
	@Autowired
	private RegionRepository repository;
	
	@Override
	public Collection<Region> getAllRegion (Long id){
		return repository.getRequests(id);
	}
	
	@Override
	public Region addRegion(Region region){
		return repository.save(region);
	}

}
