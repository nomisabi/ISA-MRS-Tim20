package com.example.service;

import java.util.Collection;
import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;

public interface RegionService {

	Collection<Region> getAllRegion();

	Region addRegion(Region region);

	Collection<Region> getRegion(Restaurant rest);
	
	void updateName(Region r);
	
	void deleteRegion(Region r);
	
	Collection<TableOfRestaurant> getTables(Long id);
}
