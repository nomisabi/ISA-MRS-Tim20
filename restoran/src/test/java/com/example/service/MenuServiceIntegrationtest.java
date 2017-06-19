package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.Food;
import com.example.domain.Menu;
import com.example.domain.MenuItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceIntegrationtest {

	@Autowired
	MenuService service;
	
	Menu dm;
	MenuItem dmi;
	Food d;
	
	
	@Test
	public void findAllFood() {
		assertNotEquals(0, service.findAllFood().size());
	}
	
	@Test
	public void findAllMenuItem() {

		assertNotEquals(0, service.findAllMenuItem().size());
	}
	
	@Test
	public void findAllMenu() {

		assertNotEquals(0, service.findAllMenu().size());
	}
	
	@Test
	public void createFood() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		assertEquals(d1, saved);
	}
	
	@Test
	public void createMenuItem() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		assertEquals(dm1, savedD);
	}
	
	@Test
	public void createMenu() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
		assertEquals(dm, saveddm);
	}
	
	
	@Test
	public void deleteFood() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		service.deleteFood(saved.getId());
		assertNull(service.findFood(saved.getId()));
	}
	
	@Test
	public void deleteMenuItem() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		service.deleteMenuItem(savedD.getId());
		assertNull(service.findMenuItem(savedD.getId()));
	}
	
	@Test
	public void deleteMenu() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
		service.deleteMenu(saveddm.getId());
		assertNull(service.findMenu(saveddm.getId()));
	}
	
	
	
	@Test
	public void findFood() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		assertEquals(saved.getId(), service.findFood(saved.getId()).getId());
	}
	
	@Test
	public void findMenuItem() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		assertEquals(savedD.getId(), service.findMenuItem(savedD.getId()).getId());
	}
	
	@Test
	public void findMenu() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
		assertEquals(saveddm.getId(), service.findMenu(saveddm.getId()).getId());
	}
	
	@Test
	public void isFoodExist() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		assertEquals(true, service.isFoodExist(d1.getId()));
	}
	
	@Test
	public void isMenuItemExist() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		assertEquals(true, service.isMenuItemExist(dm1.getId()));
	}
	
	@Test
	public void isMenuExist() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
		assertEquals(true, service.isMenuExist(dm.getId()));
	}
	
	@Test
	public void updateFood() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		d1.setName("new");
		service.updateFood(d1);
		Food find= service.findFood(d1.getId());
		assertEquals(find.getName(), "new");
	}

	@Test
	public void updateMenuItem() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		dm1.setPrice(2.3);
		service.updateMenuItem(dm1);
		MenuItem find= service.findMenuItem(dm1.getId());
		assertEquals(2,3, find.getPrice());
	}
	
	@Test
	public void updateMenu() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
		Date d= new Date();
		dm.setDateUpdate(d);
		service.updateMenu(dm);
		Menu find= service.findMenu(dm.getId());
		assertEquals(d, find.getDateUpdate());
	}
	
	@Test
	public void insertFoodNewItem() {
		Food d1= new Food();
		Food saved= service.createFood(d1);
		MenuItem dm1= new MenuItem();
		dm1.setFood(d1);
		MenuItem savedD= service.createMenuItem(dm1);
		Set<MenuItem> dmis= new HashSet<MenuItem>();
		dmis.add(savedD);
		Menu dm= new Menu();
		dm.setItems(dmis);
		Menu saveddm = service.createMenu(dm);
			
		// inser new
		Food d2= new Food();
		Food saved2= service.createFood(d2);
		MenuItem dm2= new MenuItem();
		dm2.setFood(d2);
		MenuItem savedD2= service.createMenuItem(dm2);
		Set<MenuItem> dmis2= new HashSet<MenuItem>();
		dmis2.add(savedD2);
		dm.setItems(dmis2);
		
		service.insertNewItem(dm);
	}
	
	
}
