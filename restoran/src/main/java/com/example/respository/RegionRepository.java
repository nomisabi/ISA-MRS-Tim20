package com.example.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Region;


public interface RegionRepository extends CrudRepository<Region, Long> {

}
