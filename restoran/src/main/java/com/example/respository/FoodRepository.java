package com.example.respository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {

	@Modifying
	@Transactional
    @Query(value="UPDATE Food f SET f.name = ?2, f.description=?3 WHERE f.id = ?1", nativeQuery=true)
    int update(Long id, String name, String desc);
}
