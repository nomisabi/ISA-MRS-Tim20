package com.example.service;

import java.util.Collection;
import com.example.domain.Region;
import com.example.domain.Restaurant;

public interface RegionService {

	Collection<Region> getAllRegion();

	Region addRegion(Region region);

	Collection<Region> getRegion(Restaurant rest);
}
