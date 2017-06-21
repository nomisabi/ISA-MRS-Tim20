package com.example.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.RateMenuItem;
import com.example.domain.DTOs.FoodRate;

@Repository
public interface RateMenuItemRepository extends JpaRepository<RateMenuItem, Long> {

	@Query(value="Select f.name, avg(rate) from (((rate_menu_item  rmi inner join menu_item mi on rmi.menu_item_id=mi.id) "
			+"inner join menu_items its on its.items_id=mi.id) inner join restaurant r on r.menu_id=its.menu_id) inner join food f "+
			"on f.id=mi.food_id where r.id=?1 group by f.name", nativeQuery=true)
	Collection<Object[]> getAvgByFood(Long id);
}
