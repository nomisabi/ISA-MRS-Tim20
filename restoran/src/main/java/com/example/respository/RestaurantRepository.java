package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Guest;
import com.example.domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
	@Query(value="SELECT r.id, r.description, r.location, r.name, r.species, r.drink_menu_id, r.menu_id FROM restaurant r INNER JOIN restaurant_suppliers rs ON rs.restaurant_id=r.id WHERE rs.suppliers_id=?1", nativeQuery=true)
	public Collection<Restaurant> getRest(Long id);
	
	@Modifying
	@Transactional
    @Query(value="INSERT INTO restaurant_suppliers (restaurant_id, suppliers_id) VALUES (?1,?2)", nativeQuery=true)
    int insertSup(Long id_rest, Long id_sup);
	

	@Modifying
	@Transactional
    @Query(value="INSERT INTO restaurant_employee (restaurant_id, employee_id) VALUES (?1,?2)", nativeQuery=true)
    int insertEmpl(Long id_rest, Long id_emp);


	@Modifying
	@Transactional
    @Query(value="UPDATE Restaurant SET menu_id=?2 WHERE id = ?1", nativeQuery=true)
    int updateMenu(Long id, Long man_id);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE Restaurant SET drink_menu_id=?2 WHERE id = ?1", nativeQuery=true)
    int updateDrinkMenu(Long id, Long man_id);



}
