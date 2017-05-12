package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Food;
import com.example.domain.Menu;
import com.example.domain.MenuItem;
import com.example.respository.FoodRepository;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    @Autowired
    private FoodRepository foodRepository;
    
	@Override
	public Menu createMenu(Menu menu) {
		Menu m= menuRepository.save(menu);
		return m;
	}

	@Override
	public void updateMenu(Menu menu) {
		menuRepository.update(menu.getId(), menu.getDateUpdate());
	}

	@Override
	public boolean isMenuExist(Long id) {
		return menuRepository.exists(id);
	}

	@Override
	public Menu findMenu(Long id) {
		return menuRepository.findOne(id);
	}

	@Override
	public Collection<Menu> findAllMenu() {
		return (Collection<Menu>) menuRepository.findAll();
	}

	@Override
	public void deleteMenu(Long id) {
		menuRepository.delete(id);
	}

	@Override
	public MenuItem createMenuItem(MenuItem menu) {
		MenuItem mi = menuItemRepository.save(menu);
		return mi;
	}

	@Override
	public void updateMenuItem(MenuItem menu) {
		menuItemRepository.update(menu.getId(), menu.getPrice());
	}

	@Override
	public boolean isMenuItemExist(Long id) {
		return menuItemRepository.exists(id);
	}

	@Override
	public MenuItem findMenuItem(Long id) {
		return menuItemRepository.findOne(id);
	}

	@Override
	public Collection<MenuItem> findAllMenuItem() {
		return (Collection<MenuItem>) menuItemRepository.findAll();
	}

	@Override
	public void deleteMenuItem(Long id) {
		menuRepository.deleteItem(id);
		menuItemRepository.delete(id);
	}

	@Override
	public Food createFood(Food f) {
		Food food = foodRepository.save(f);
		return food;
	}

	@Override
	public void updateFood(Food f) {
		foodRepository.update(f.getId(), f.getName(), f.getDescription());
	}

	@Override
	public boolean isFoodExist(Long id) {
		return foodRepository.exists(id);
	}

	@Override
	public Food findFood(Long id) {
		return foodRepository.findOne(id);
	}

	@Override
	public Collection<Food> findAllFood() {
		return (Collection<Food>) foodRepository.findAll();
	}

	@Override
	public void deleteFood(Long id) {
		foodRepository.delete(id);
		
	}

	@Override
	public void insertNewItem(Menu m) {
		for (MenuItem mi: m.getItems())
			menuRepository.insertNewItem(m.getId(), mi.getId());
	}

}
