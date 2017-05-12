package com.example.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.Drink;
import com.example.respository.DrinkItemRepository;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.FoodRepository;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuRepository;

@Service
public class DrinkMenuServiceImpl implements DrinkMenuService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DrinkMenuRepository menuRepository;
    
    @Autowired
    private DrinkItemRepository itemRepository;
    
    @Autowired
    private DrinkRepository drinkRepository;
    
	@Override
	public DrinkMenu createDrinkMenu(DrinkMenu menu) {
		DrinkMenu m= menuRepository.save(menu);
		return m;
	}

	@Override
	public void updateDrinkMenu(DrinkMenu menu) {
		menuRepository.update(menu.getId(), menu.getDateUpdate());
	}

	@Override
	public boolean isDrinkMenuExist(Long id) {
		return menuRepository.exists(id);
	}

	@Override
	public DrinkMenu findDrinkMenu(Long id) {
		return menuRepository.findOne(id);
	}

	@Override
	public Collection<DrinkMenu> findAllDrinkMenu() {
		return (Collection<DrinkMenu>) menuRepository.findAll();
	}

	@Override
	public void deleteDrinkMenu(Long id) {
		menuRepository.delete(id);
	}

	@Override
	public DrinkMenuItem createDrinkMenuItem(DrinkMenuItem menu) {
		DrinkMenuItem mi = itemRepository.save(menu);
		return mi;
	}

	@Override
	public void updateDrinkMenuItem(DrinkMenuItem menu) {
		itemRepository.update(menu.getId(), menu.getPrice());
	}

	@Override
	public boolean isDrinkMenuItemExist(Long id) {
		return itemRepository.exists(id);
	}

	@Override
	public DrinkMenuItem findDrinkMenuItem(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Collection<DrinkMenuItem> findAllDrinkMenuItem() {
		return (Collection<DrinkMenuItem>) itemRepository.findAll();
	}

	@Override
	public void deleteDrinkMenuItem(Long id) {
		menuRepository.deleteItem(id);
		itemRepository.delete(id);
	}

	@Override
	public Drink createDrink(Drink d) {
		Drink drink = drinkRepository.save(d);
		return drink;
	}

	@Override
	public void updateDrink(Drink d) {
		drinkRepository.update(d.getId(), d.getName(), d.getDescription());
	}

	@Override
	public boolean isDrinkExist(Long id) {
		return drinkRepository.exists(id);
	}

	@Override
	public Drink findDrink(Long id) {
		return drinkRepository.findOne(id);
	}

	@Override
	public Collection<Drink> findAllDrink() {
		return (Collection<Drink>) drinkRepository.findAll();
	}

	@Override
	public void deleteDrink(Long id) {
		drinkRepository.delete(id);
		
	}

	@Override
	public void insertDrinkNewItem(DrinkMenu m) {
		for (DrinkMenuItem mi: m.getItems())
			menuRepository.insertNewItem(m.getId(), mi.getId());
	}

}
