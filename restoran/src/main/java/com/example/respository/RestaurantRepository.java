package com.example.respository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
	
	@Modifying
	@Transactional
    @Query(value="INSERT INTO restaurant_suppliers (restaurant_id, suppliers_id) VALUES (?1,?2)", nativeQuery=true)
    int insertSup(Long id_rest, Long id_sup);

	@Modifying
	@Transactional
    @Query(value="UPDATE Restaurant SET menu_id=?2 WHERE id = ?1", nativeQuery=true)
    int updateMenu(Long id, Long man_id);

}
