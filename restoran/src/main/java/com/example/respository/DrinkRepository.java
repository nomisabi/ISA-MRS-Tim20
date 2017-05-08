package com.example.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Drink;

public interface DrinkRepository extends CrudRepository<Drink, Long> {

}
