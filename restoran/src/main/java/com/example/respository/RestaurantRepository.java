package com.example.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Manager;
import com.example.domain.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

}
