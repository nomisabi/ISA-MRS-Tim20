package com.example.respository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Menu;
import com.example.domain.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

	@Modifying
	@Transactional
    @Query(value="UPDATE menu_item SET price = ?2 WHERE id = ?1", nativeQuery=true)
    int update(Long id, double price);
}
