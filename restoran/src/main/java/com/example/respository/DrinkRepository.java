package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Drink;
import com.example.domain.Food;
import com.example.domain.TableOfRestaurant;


public interface DrinkRepository extends CrudRepository<Drink, Long>{

	@Modifying
	@Transactional
    @Query(value="UPDATE Drink SET name = ?2, description=?3 WHERE id = ?1", nativeQuery=true)
    int update(Long id, String name, String desc);

}
