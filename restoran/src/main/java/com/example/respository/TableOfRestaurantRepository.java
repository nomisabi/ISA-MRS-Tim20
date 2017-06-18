package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.TableOfRestaurant;

public interface TableOfRestaurantRepository extends JpaRepository<TableOfRestaurant, Long> {

	@Query("SELECT Object(t) FROM TableOfRestaurant t WHERE t.restaurant.id = ?1")
	public Collection<TableOfRestaurant> getTableOfRestaurant(Long id);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE table_of_restaurant SET region_id=?2 WHERE id=?1", nativeQuery=true)
    int insertReg(Long id_table, Long id_reg);
	
	@Query(value="SELECT Object(t) FROM TableOfRestaurant t WHERE t.number = ?1 and t.restaurant.id=?2", nativeQuery=false)
	public TableOfRestaurant getByNum(int num, Long id);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM table_of_restaurant t WHERE t.id = ?1", nativeQuery=true)
	void deleteTable(Long id);
	
	@Query(value="SELECT Object(t) FROM TableOfRestaurant t WHERE t.region.id = ?1", nativeQuery=false)
	public Collection<TableOfRestaurant> getTablesByRegion(Long id);
}
