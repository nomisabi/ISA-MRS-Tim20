package com.example.service;

import java.util.Collection;

import com.example.domain.Food;
import com.example.domain.Menu;
import com.example.domain.MenuItem;

public interface MenuService {
	
	Menu createMenu(Menu menu);
	
	void updateMenu(Menu menu);
	
	boolean isMenuExist(Long id);
	
	Menu findMenu(Long id);
	
	Collection<Menu> findAllMenu();
	
	void deleteMenu(Long id);
	
	void insertNewItem(Menu m);
	
	MenuItem createMenuItem(MenuItem menu);
	
	void updateMenuItem(MenuItem menu);
	
	boolean isMenuItemExist(Long id);
	
	MenuItem findMenuItem(Long id);
	
	Collection<MenuItem> findAllMenuItem();
	
	void deleteMenuItem(Long id);
	
	Food createFood(Food f);
	
	void updateFood(Food f);
	
	boolean isFoodExist(Long id);
	
	Food findFood(Long id);
	
	Collection<Food> findAllFood();
	
	void deleteFood(Long id);
	
}
