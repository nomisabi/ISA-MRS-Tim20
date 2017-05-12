package com.example.service;

import java.util.Collection;

import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.Drink;

public interface DrinkMenuService {
	
	DrinkMenu createDrinkMenu(DrinkMenu menu);
	
	void updateDrinkMenu(DrinkMenu menu);
	
	boolean isDrinkMenuExist(Long id);
	
	DrinkMenu findDrinkMenu(Long id);
	
	Collection<DrinkMenu> findAllDrinkMenu();
	
	void deleteDrinkMenu(Long id);
	
	void insertDrinkNewItem(DrinkMenu m);
	
	DrinkMenuItem createDrinkMenuItem(DrinkMenuItem menu);
	
	void updateDrinkMenuItem(DrinkMenuItem menu);
	
	boolean isDrinkMenuItemExist(Long id);
	
	DrinkMenuItem findDrinkMenuItem(Long id);
	
	Collection<DrinkMenuItem> findAllDrinkMenuItem();
	
	void deleteDrinkMenuItem(Long id);
	
	Drink createDrink(Drink d);
	
	void updateDrink(Drink d);
	
	boolean isDrinkExist(Long id);
	
	Drink findDrink(Long id);
	
	Collection<Drink> findAllDrink();
	
	void deleteDrink(Long id);
	
}
