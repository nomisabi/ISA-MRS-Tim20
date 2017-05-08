package com.example.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {

}
