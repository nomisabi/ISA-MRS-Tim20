package com.example.respository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DrinkMenu;
import com.example.domain.Menu;

public interface DrinkMenuRepository  extends CrudRepository<DrinkMenu, Long>  {

	@Modifying
	@Transactional
    @Query(value="UPDATE drink_menu SET date_update = ?2 WHERE id = ?1", nativeQuery=true)
    int update(Long id, Date date);
	
	@Modifying
	@Transactional
    @Query(value="INSERT INTO drink_menu_items (drink_menu_id, items_id) VALUES (?1,?2)", nativeQuery=true)
    int insertNewItem(Long id_m, Long id_it);
	
	@Modifying
	@Transactional
    @Query(value="DELETE FROM drink_menu_items WHERE items_id=?1", nativeQuery=true)
    int deleteItem(Long id_it);

}
