package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Drink;
import com.example.domain.TableOfRestaurant;


public interface DrinkRepository extends JpaRepository<Drink, Long> {
	/*
	@Query("SELECT Object(t) FROM Drink d WHERE d.id = ?1")
	public Collection<Drink> getRequests(Long id);
	*/

}
