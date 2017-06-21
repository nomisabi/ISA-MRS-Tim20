package com.example.respository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DrinkMenuItem;
import com.example.domain.Menu;
import com.example.domain.MenuItem;

public interface DrinkItemRepository extends CrudRepository<DrinkMenuItem, Long> {

	@Modifying
	@Transactional
    @Query(value="UPDATE drink_menu_item SET price = ?2 WHERE id = ?1", nativeQuery=true)
    int update(Long id, double price);
}
