package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.TableOfRestaurant;

public interface TableOfRestaurantRepository extends JpaRepository<TableOfRestaurant, Long> {

	@Query("SELECT Object(t) FROM TableOfRestaurant t WHERE t.restaurant.id = ?1")
	public Collection<TableOfRestaurant> getRequests(Long id);

}
