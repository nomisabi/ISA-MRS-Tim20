package com.example.service;

import java.util.Collection;
import com.example.domain.Region;

public interface RegionService {

	Collection<Region> getAllRegion();

	Region addRegion(Region region);
}
