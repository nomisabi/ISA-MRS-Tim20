package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.EmployeeSchedule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrinkMenuServiceIntegrationTest {

	@Autowired
	DrinkMenuService service;
	
	DrinkMenu dm;
	DrinkMenuItem dmi;
	Drink d;
	
	
	@Test
	public void findAllDrink() {
		assertEquals(19, service.findAllDrink().size());
	}
	
	@Test
	public void findAllDrinkMenuItem() {

		assertEquals(5, service.findAllDrinkMenuItem().size());
	}
	
	@Test
	public void findAllDrinkMenu() {

		assertEquals(2, service.findAllDrinkMenu().size());
	}
	
	@Test
	public void createDrink() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		assertEquals(d1, saved);
	}
	
	@Test
	public void createDrinkMenuItem() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		assertEquals(dm1, savedD);
	}
	
	@Test
	public void createDrinkMenu() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
		assertEquals(dm, saveddm);
	}
	
	
	@Test
	public void deleteDrink() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		service.deleteDrink(saved.getId());
		assertNull(service.findDrink(saved.getId()));
	}
	
	@Test
	public void deleteDrinkMenuItem() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		service.deleteDrinkMenuItem(savedD.getId());
		assertNull(service.findDrinkMenuItem(savedD.getId()));
	}
	
	@Test
	public void deleteDrinkMenu() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
		service.deleteDrinkMenu(saveddm.getId());
		assertNull(service.findDrinkMenu(saveddm.getId()));
	}
	
	
	
	@Test
	public void findDrink() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		assertEquals(saved.getId(), service.findDrink(saved.getId()).getId());
	}
	
	@Test
	public void findDrinkMenuItem() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		assertEquals(savedD.getId(), service.findDrinkMenuItem(savedD.getId()).getId());
	}
	
	@Test
	public void findDrinkMenu() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
		assertEquals(saveddm.getId(), service.findDrinkMenu(saveddm.getId()).getId());
	}
	
	@Test
	public void isDrinkExist() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		assertEquals(true, service.isDrinkExist(d1.getId()));
	}
	
	@Test
	public void isDrinkMenuItemExist() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		assertEquals(true, service.isDrinkMenuItemExist(dm1.getId()));
	}
	
	@Test
	public void isDrinkMenuExist() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
		assertEquals(true, service.isDrinkMenuExist(dm.getId()));
	}
	
	@Test
	public void updateDrink() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		d1.setName("new");
		service.updateDrink(d1);
		Drink find= service.findDrink(d1.getId());
		assertEquals(find.getName(), "new");
	}

	@Test
	public void updateDrinkMenuItem() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		dm1.setPrice(2.3);
		service.updateDrinkMenuItem(dm1);
		DrinkMenuItem find= service.findDrinkMenuItem(dm1.getId());
		assertEquals(2,3, find.getPrice());
	}
	
	@Test
	public void updateDrinkMenu() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
		Date d= new Date();
		dm.setDateUpdate(d);
		service.updateDrinkMenu(dm);
		DrinkMenu find= service.findDrinkMenu(dm.getId());
		assertEquals(d, find.getDateUpdate());
	}
	
	@Test
	public void insertDrinkNewItem() {
		Drink d1= new Drink();
		Drink saved= service.createDrink(d1);
		DrinkMenuItem dm1= new DrinkMenuItem();
		dm1.setDrink(d1);
		DrinkMenuItem savedD= service.createDrinkMenuItem(dm1);
		Set<DrinkMenuItem> dmis= new HashSet<DrinkMenuItem>();
		dmis.add(savedD);
		DrinkMenu dm= new DrinkMenu();
		dm.setItems(dmis);
		DrinkMenu saveddm = service.createDrinkMenu(dm);
			
		// inser new
		Drink d2= new Drink();
		Drink saved2= service.createDrink(d2);
		DrinkMenuItem dm2= new DrinkMenuItem();
		dm2.setDrink(d2);
		DrinkMenuItem savedD2= service.createDrinkMenuItem(dm2);
		Set<DrinkMenuItem> dmis2= new HashSet<DrinkMenuItem>();
		dmis2.add(savedD2);
		dm.setItems(dmis2);
		
		service.insertDrinkNewItem(dm);
	}
	
	

}
