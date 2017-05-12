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
    @Query(value="UPDATE Food SET name = ?2, description=?3 WHERE id = ?1", nativeQuery=true)
    int update(Long id, String name, String desc);
}
