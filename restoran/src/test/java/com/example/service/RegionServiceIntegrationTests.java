package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.Employee;
import com.example.domain.EmployeeSchedule;
import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.TableOfRestaurant;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.OfferRepository;
import com.example.respository.RegionRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;
import com.example.respository.TableOfRestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceIntegrationTests {
	@Autowired
	RegionService service;
	
	@Autowired
	RegionRepository rep;
	@Autowired
	RestaurantRepository rrep;
	@Autowired
	TableOfRestaurantRepository trep;


	Region dm;

	@Before
	public void setUp() {
		dm= new Region();
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		dm.setRestaurant(r);
		dm = service.addRegion(dm);
	}


	@Test
	public void findAllRegion() {
		assertNotEquals(0, service.getAllRegion().size());
	}	
	
	@Test
	public void createRegion() {
		Region d1= new Region();
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		d1.setRestaurant(r);
		Region saved= service.addRegion(d1);
		assertEquals(d1, saved);
	}
	
	
	@Test
	public void deleteRegion() {
		Region d1= new Region();
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		d1.setRestaurant(r);
		Region saved= service.addRegion(d1);
		service.deleteRegion(saved);
		assertNull(rep.findOne(saved.getId()));
	}

	
	@Test
	public void updateRegion() {
		Region d1= new Region();
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		d1.setRestaurant(r);
		Region saved= service.addRegion(d1);
		d1.setName("123");
		service.updateName(d1);
		Region find= rep.findOne(d1.getId());
		assertEquals( "123", find.getName());
	}
	
	@Test
	public void getRegion() {
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		Region d1= new Region();
		d1.setRestaurant(r);
		Region saved= service.addRegion(d1);
		assertEquals( r.getId(), saved.getRestaurant().getId());
	}
	
	@Test
	public void getTables() {
		TableOfRestaurant tab = new TableOfRestaurant();
		tab= trep.save(tab);
		Region d1= new Region();
		Restaurant r = new Restaurant();
		r.setName("");
		r= rrep.save(r);
		d1.setRestaurant(r);
		Set<TableOfRestaurant> t = new HashSet<TableOfRestaurant>();
		t.add(tab);	
		d1.setTables(t);
		Region saved= service.addRegion(d1);
		assertEquals( 1, saved.getTables().size());
	}

}
