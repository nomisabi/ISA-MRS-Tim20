package com.example.respository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Menu;

public interface MenuRepository  extends CrudRepository<Menu, Long>  {

	@Modifying
	@Transactional
    @Query(value="UPDATE Menu m SET m.date_update = ?2 WHERE m.id = ?1", nativeQuery=true)
    int update(Long id, Date date);
	
	@Modifying
	@Transactional
    @Query(value="INSERT INTO menu_items (menu_id, items_id) VALUES (?1,?2)", nativeQuery=true)
    int insertNewItem(Long id_m, Long id_it);

}
