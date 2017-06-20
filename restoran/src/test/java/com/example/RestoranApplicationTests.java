package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.domain.Drink;
import com.example.domain.DrinkMenu;
import com.example.domain.DrinkMenuItem;
import com.example.domain.Employee;
import com.example.domain.EmployeeSchedule;
import com.example.domain.Food;
import com.example.domain.Friendship;
import com.example.domain.Guest;
import com.example.domain.Manager;
import com.example.domain.Menu;
import com.example.domain.MenuItem;
import com.example.domain.Offer;
import com.example.domain.Offer_status;
import com.example.domain.Region;
import com.example.domain.Restaurant;
import com.example.domain.Supplier;
import com.example.domain.Supply;
import com.example.domain.System_manager;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.domain.DTOs.DrinkRestaurant;
import com.example.domain.DTOs.EmployeeRestaurant;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.domain.DTOs.InputTime;
import com.example.domain.DTOs.ManagerRestaurant;
import com.example.domain.DTOs.MenuRestaurant;
import com.example.domain.DTOs.OfferSupply;
import com.example.domain.DTOs.SupplierRestaurant;
import com.example.domain.DTOs.TableRegion;
import com.example.respository.DrinkItemRepository;
import com.example.respository.DrinkMenuRepository;
import com.example.respository.DrinkRepository;
import com.example.respository.EmployeeRepository;
import com.example.respository.EmployeeScheduleRepository;
import com.example.respository.FoodRepository;
import com.example.respository.FriendshipRepository;
import com.example.respository.GuestRepository;
import com.example.respository.ManagerRepository;
import com.example.respository.MenuItemRepository;
import com.example.respository.MenuRepository;
import com.example.respository.OfferRepository;
import com.example.respository.RegionRepository;
import com.example.respository.RestaurantRepository;
import com.example.respository.SupllierRepository;
import com.example.respository.SupplyRepository;
import com.example.respository.SystemManagerRepository;
import com.example.respository.TableOfRestaurantRepository;
import com.example.respository.UserRepository;
import com.example.service.OfferSupplyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestoranApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Autowired
	UserRepository userRepository;
	@Autowired
	GuestRepository guestRepository;
	@Autowired
	FriendshipRepository friendshipRepository;
	Guest guest;

	@Autowired
	SystemManagerRepository sysmanRepository;
	@Autowired
	RestaurantRepository restRepository;
	@Autowired
	ManagerRepository manRepository;
	@Autowired
	SupllierRepository supRepository;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	SupplyRepository supplyRepository;
	@Autowired
	EmployeeRepository emplRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	MenuItemRepository menuItemRepository;
	@Autowired
	DrinkMenuRepository dmenuRepository;
	@Autowired
	DrinkItemRepository dmenuItemRepository;
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	DrinkRepository drinkRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	TableOfRestaurantRepository tableRepository;
	@Autowired
	EmployeeScheduleRepository esRepository;
	@Autowired
	OfferSupplyService osService;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		User user = new User("noviUser@gmail.com", "noviUser", TypeOfUser.GUEST);
		userRepository.save(user);
		guest = new Guest("noviUser@gmail.com", "noviUser");
		guest = guestRepository.save(guest);
	}

	@Test
	public void test() throws Exception {

		this.mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testRegistration() throws Exception {

		this.mvc.perform(get("#/registration")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllGuests() throws Exception {

		this.mvc.perform(get("/api/guests")).andExpect(status().isOk());
	}

	@Test
	public void createGuest() throws Exception {

		GuestRegister g = new GuestRegister("nevena5695@email.com", "password1234", "password1234", "name", "name");
		this.mvc.perform(post("/api/guests").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(g))).andExpect(status().isCreated());
		
		Guest savedGuest = guestRepository.findByEmail("nevena5695@email.com");
		assertNotNull(savedGuest);
		assertEquals(g.getFirstName(), savedGuest.getFirstName());
		assertEquals(g.getLastName(), savedGuest.getLastName());
	}

	@Test
	public void sendRequest() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		guestRepository.save(friend);
		FriendRequest fq = new FriendRequest();
		fq.setIdFriend(friend.getId());
		fq.setIdGuest(guest.getId());

		this.mvc.perform(post("/api/friendship/sendRequest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(fq))).andExpect(status().isOk());

		this.mvc.perform(post("/api/friendship/getRequest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(friend))).andExpect(status().isOk());
		
		Collection<Guest> req = guestRepository.getRequests(friend.getId());
		assertEquals(1, req.size());

	}

	@Test
	public void getRequest() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		friend = guestRepository.save(friend);
		Friendship fr = new Friendship(guest, friend.getId(), false);
		fr = friendshipRepository.save(fr);

		this.mvc.perform(post("/api/friendship/getRequest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(friend))).andExpect(status().isOk());

	}
	
	@Test
	public void addFriend() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		friend = guestRepository.save(friend);
		Friendship fr = new Friendship(guest, friend.getId(), false);
		fr = friendshipRepository.save(fr);

		FriendRequest fq = new FriendRequest();
		fq.setIdFriend(guest.getId());
		fq.setIdGuest(friend.getId());
		this.mvc.perform(post("/api/friendship/addFriend").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(fq))).andExpect(status().isOk());

	}
	
	@Test
	public void deleteFriend() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		friend = guestRepository.save(friend);
		Friendship fr = new Friendship(guest, friend.getId(), true);
		fr = friendshipRepository.save(fr);

		FriendRequest fq = new FriendRequest();
		fq.setIdFriend(guest.getId());
		fq.setIdGuest(friend.getId());
		this.mvc.perform(post("/api/friendship/deleteFriend").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(fq))).andExpect(status().isOk());
		
		boolean exist = friendshipRepository.exists(fr.getId());

		assertEquals(false, exist);
	}
	
	@Test
	public void getRestaurants() throws Exception {
		this.mvc.perform(get("/api/restaurants")).andExpect(status().isOk());
	}
	
	@Test
	public void getVisitedRestaurants() throws Exception {
		this.mvc.perform(post("/api/visitedRestaurants").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(guest))).andExpect(status().isOk());
	}
	
	
	@Test
	public void getSysMan() throws Exception {
		System_manager sm = new System_manager("email@gmail.com", "pass", "fn", "ln");
		sysmanRepository.save(sm);
		this.mvc.perform(get("/api/sysman").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	
	@Test
	public void getSystem_manager() throws Exception {
		System_manager sm= sysmanRepository.findOne(1L);
		this.mvc.perform(get("/api/sysman/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.email", is(sm.getEmail())))
		.andExpect(jsonPath("$.id", is(1)));
	}
	
	
	@Test
	public void signUp() throws Exception {
		System_manager sm = new System_manager("email@gmail.com", "pass", "fn", "ln");
		this.mvc.perform(post("/api/sysman/createSysman").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk()).andExpect(jsonPath("$.email", is(sm.getEmail())))
		.andExpect(jsonPath("$.password", is(sm.getPassword())));
		
	}
	
	@Test
	public void getRestaurantsSysman() throws Exception {
		this.mvc.perform(get("/api/sysman/restaurants")).andExpect(status().isOk());
		
	}
	
	@Test
	public void getRest() throws Exception {
		Restaurant r= restRepository.findOne(1L);
		this.mvc.perform(get("/api/sysman/restaurant/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(r.getName())))
		.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void addRest() throws Exception {
		Restaurant r= new Restaurant("1", "23");
		ManagerRestaurant mr = new ManagerRestaurant(r, null);
		this.mvc.perform(post("/api/sysman/addRest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(mr))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(r.getName())));
		
	}
	
	@Test
	public void updateSysMan() throws Exception {
		System_manager sm= sysmanRepository.findOne(1L);
		sm.setFirstName("123");
		this.mvc.perform(post("/api/sysman/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		System_manager find= sysmanRepository.findOne(1L);
		assertEquals("123", find.getFirstName());
		
	}
	
	@Test
	public void updatePassSysMan() throws Exception {
		System_manager sm= sysmanRepository.findOne(1L);
		sm.setPassword("pw");
		this.mvc.perform(post("/api/sysman/updatePass").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		System_manager find= sysmanRepository.findOne(1L);
		assertEquals("pw", find.getPassword());
		
	}

	@Test
	public void addManToRest() throws Exception {
		Restaurant r= new Restaurant();
		r.setId(3L);
		Manager m=new Manager();
		m.setEmail("");
		m.setPassword("");
		m=manRepository.save(m);
		
		ManagerRestaurant mr= new ManagerRestaurant(r, m);
		this.mvc.perform(post("/api/sysman/addManToRest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(mr))).andExpect(status().isOk());
		
	}

	
	@Test
	public void getSuppliers() throws Exception {
		this.mvc.perform(get("/api/suppliers")).andExpect(status().isOk());
		
	}
	
	@Test
	public void getSupplier() throws Exception {
		Supplier sup = new Supplier();
		sup.setEmail("123");
		supRepository.save(sup);
		Supplier s= supRepository.findOne(2L);
		this.mvc.perform(get("/api/suppliers/2").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(s.getName())))
		.andExpect(jsonPath("$.id", is(2)));
	}
	
	@Test
	public void changePassSuppliers() throws Exception {
		Supplier sm= supRepository.findOne(1L);
		sm.setPassword("pw");
		this.mvc.perform(post("/api/suppliers/changePass").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		Supplier find= supRepository.findOne(1L);
		assertEquals("pw", find.getPassword());
		
	}
	
	@Test
	public void updateSupplier() throws Exception {
		Supplier sm= supRepository.findOne(1L);
		sm.setName("123");
		this.mvc.perform(post("/api/suppliers/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		Supplier find= supRepository.findOne(1L);
		assertEquals("123", find.getName());
		
	}
	
	@Test
	public void getRestSupplier() throws Exception {
		Supplier sm= supRepository.findOne(1L);
		this.mvc.perform(post("/api/suppliers/restaurant").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		
	}
	
	
	@Test
	public void getSuppliersRest() throws Exception {
		Restaurant sm= restRepository.findOne(2L);
		sm.setManager(null);
		sm.setEmployee(null);
		sm.setSuppliers(null);
		this.mvc.perform(post("/api/suppliers/getSuppliersRest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		
	}
	
	@Test
	public void getSuppliesWitMyOffer() throws Exception {
		Supplier sm= supRepository.findOne(1L);
		this.mvc.perform(post("/api/suppliers/getSuppliesWitMyOffer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		
	}
	
	@Test
	public void Supply_not_choosed() throws Exception {
		Supplier sm= supRepository.findOne(1L);
		this.mvc.perform(post("/api/suppliers/supply_not_choosed").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().is4xxClientError()); //jer ne postoji nijednu takva
		
	}
	
	@Test
	public void sendOffer() throws Exception {
		Offer o= offerRepository.findOne(1L);

		this.mvc.perform(post("/api/suppliers/sendOffer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(o))).andExpect(status().isOk());
		
		o= offerRepository.findOne(1L);
		assertEquals(Offer_status.SEND, o.getStatus());
	}
	
	@Test
	public void endOffer() throws Exception {
		Offer o= offerRepository.findOne(1L);

		this.mvc.perform(post("/api/suppliers/endOffer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(o))).andExpect(status().isOk());
		
		o= offerRepository.findOne(1L);
		assertEquals(Offer_status.END, o.getStatus());
	}
	
	@Test
	public void updateOffer() throws Exception {
		Offer o= offerRepository.findOne(1L);
		o.setQuality(3);
		
		Supply s =supplyRepository.findOne(1L);
		s.setRestaurant(null);
		OfferSupply os= new OfferSupply(o, s);

		this.mvc.perform(post("/api/suppliers/updateOffer").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(os))).andExpect(status().isOk());
		
		o= offerRepository.findOne(1L);
		assertEquals(3, o.getQuality());
	}
	
	@Test
	public void getMan() throws Exception {
		//System_manager sm = new System_manager("email@gmail.com", "pass", "fn", "ln");
		//sysmanRepository.save(sm);
		this.mvc.perform(get("/api/manager").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	
	@Test
	public void getManager() throws Exception {
		Manager sm= manRepository.findOne(1L);
		this.mvc.perform(get("/api/manager/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.email", is(sm.getEmail())))
		.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void getRestCollManager() throws Exception {
		Manager sm= manRepository.findOne(1L);
		Collection<Manager> man= new ArrayList<Manager>();
		man.add(sm);
		this.mvc.perform(post("/api/manager/getRestColl").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(man))).andExpect(status().isOk());
	}
	
	@Test
	public void getRestManager() throws Exception {
		Manager sm= manRepository.findOne(1L);
		Long find= manRepository.getRest(1L);

		this.mvc.perform(post("/api/manager/getRest").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(sm)))
			.andExpect(status().isOk()).andExpect(jsonPath("$.id", is( (int) (long) find)));		
	}
	
	@Test
	public void createSupplier() throws Exception {
		Collection<Supplier> e = (Collection<Supplier>) supRepository.findAll();
		int size= e.size();
		Restaurant r= restRepository.findOne(2L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		Supplier s= new Supplier();
		s.setName("123");
		s.setEmail("12");
		s.setPassword("");
		SupplierRestaurant sr = new SupplierRestaurant(r, s);
		this.mvc.perform(post("/api/manager/createSupplier").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sr))).andExpect(status().isOk());
		Collection<Supplier> find = (Collection<Supplier>) supRepository.findAll();
		assertEquals(size+1, find.size());
		
	}
	
	@Test
	public void createEmployee() throws Exception {
		Collection<Employee> e = (Collection<Employee>) emplRepository.findAll();
		int size= e.size();
		Restaurant r= restRepository.findOne(2L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		Employee s= new Employee();
		s.setEmail("12@fd");
		s.setPassword("");
		EmployeeRestaurant sr = new EmployeeRestaurant(r, s);
		this.mvc.perform(post("/api/manager/createEmployee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sr))).andExpect(status().isOk());
		Collection<Employee> find = (Collection<Employee>) emplRepository.findAll();
		assertEquals(size+1, find.size());
		
	}
	
	@Test
	public void changePassMan() throws Exception {
		Manager sm= manRepository.findOne(1L);
		sm.setPassword("pw");
		this.mvc.perform(post("/api/manager/changePass").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		Manager find= manRepository.findOne(1L);
		assertEquals("pw", find.getPassword());
		
	}
	
	@Test
	public void updateMan() throws Exception {
		Manager sm= manRepository.findOne(1L);
		sm.setFirstName("123");
		this.mvc.perform(post("/api/manager/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sm))).andExpect(status().isOk());
		Manager find= manRepository.findOne(1L);
		assertEquals("123", find.getFirstName());
		
	}
	
	
	@Test
	public void addMenuItem() throws Exception {
		Collection<MenuItem> menuitems = (Collection<MenuItem>) menuItemRepository.findAll();
		int size= menuitems.size();
		Restaurant r= restRepository.findOne(3L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		Menu m= new Menu();
		MenuItem mi = new MenuItem();
		Food f = new Food();
		mi.setFood(f);
		Set<MenuItem> items  = new HashSet<MenuItem>();
		items.add(mi);
		m.setItems(items);
		MenuRestaurant sr = new MenuRestaurant(m, r);
		this.mvc.perform(post("/api/manager/addMenuItem").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sr))).andExpect(status().isOk());
		Collection<MenuItem> find = (Collection<MenuItem>) menuItemRepository.findAll();
		assertEquals(size+1, find.size());
		
	}
	
	@Test
	public void updateMenuItem() throws Exception {
		
		Menu m= menuRepository.findOne(2L);
		m.setItems(null);
		Date old= m.getDateUpdate();
		m.setDateUpdate(new Date());
		this.mvc.perform(post("/api/manager/updateMenu").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(m))).andExpect(status().isOk());
		Menu find= menuRepository.findOne(1L);
		assertNotEquals(old, find.getDateUpdate());
	}
	
	
	@Test
	public void deleteMenuItem() throws Exception {
		
		MenuItem m= new MenuItem();
		Food f= new Food();
		f= foodRepository.save(f);
		m.setFood(f);
		
		m= menuItemRepository.save(m);
		this.mvc.perform(post("/api/manager/deleteMenuItem").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(m))).andExpect(status().isOk());
		MenuItem find= menuItemRepository.findOne(m.getId());
		assertNull(find);
	}
	

	@Test
	public void addDrinkMenuItem() throws Exception {
		Collection<DrinkMenuItem> menuitems = (Collection<DrinkMenuItem>) dmenuItemRepository.findAll();
		int size= menuitems.size();
		Restaurant r= restRepository.findOne(4L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		DrinkMenu m= new DrinkMenu();
		DrinkMenuItem mi = new DrinkMenuItem();
		Drink f = new Drink();
		mi.setDrink(f);
		Set<DrinkMenuItem> items  = new HashSet<DrinkMenuItem>();
		items.add(mi);
		m.setItems(items);
		DrinkRestaurant sr = new DrinkRestaurant(m, r);
		this.mvc.perform(post("/api/manager/addDrinkMenuItem").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(sr))).andExpect(status().isOk());
		Collection<DrinkMenuItem> find = (Collection<DrinkMenuItem>) dmenuItemRepository.findAll();
		assertEquals(size+1, find.size());
		
	}
	
	@Test
	public void updateDrinkMenu() throws Exception {
		
		DrinkMenu m= dmenuRepository.findOne(1L);
		m.setItems(null);
		Date old= m.getDateUpdate();
		m.setDateUpdate(new Date());
		this.mvc.perform(post("/api/manager/updateDrinkMenu").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(m))).andExpect(status().isOk());
		DrinkMenu find= dmenuRepository.findOne(1L);
		assertNotEquals(old, find.getDateUpdate());
		
	}
	
	
	@Test
	public void deleteDrinkMenuItem() throws Exception {
		
		DrinkMenuItem m= new DrinkMenuItem();
		Drink f= new Drink();
		f= drinkRepository.save(f);
		m.setDrink(f);
		
		m= dmenuItemRepository.save(m);
		this.mvc.perform(post("/api/manager/deleteDrinkMenuItem").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(m))).andExpect(status().isOk());
		DrinkMenuItem find= dmenuItemRepository.findOne(m.getId());
		assertNull(find);
	}
	
	@Test
	public void regions() throws Exception {
		Restaurant r= restRepository.findOne(1L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		
		this.mvc.perform(post("/api/manager/regions").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(r))
			).andExpect(status().isOk());
	}
	
	TableOfRestaurant t;
	@Test
	public void newTable() throws Exception {
		Collection<TableOfRestaurant> e = (Collection<TableOfRestaurant>) tableRepository.findAll();
		int size= e.size();
		
		Region r= regionRepository.findOne(1L);
		r.setTables(null);
		r.setRestaurant(null);
		t = new TableOfRestaurant();
		t.setNumber(4);
		TableRegion tr= new TableRegion(t, r);
		this.mvc.perform(post("/api/manager/newTable").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(tr))).andExpect(status().isOk());
		Collection<TableOfRestaurant> find = (Collection<TableOfRestaurant>) tableRepository.findAll();
		assertEquals(size+1, find.size());
	}
	

	@Test
	public void deleteTable() throws Exception {
		TableOfRestaurant tr= tableRepository.findOne(10L);
		Restaurant r= tr.getRestaurant();
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		tr.setNumber(4);
		this.mvc.perform(post("/api/manager/deleteTable").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(tr))).andExpect(status().isOk());
	}
	
	@Test
	public void supply() throws Exception {
		Restaurant r= restRepository.findOne(1L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		
		this.mvc.perform(post("/api/manager/supply").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(r))
			).andExpect(status().isOk());
		
		this.mvc.perform(post("/api/manager/supply_hist").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(r))
				).andExpect(status().isOk());
	}
	
	@Test
	public void getSupplyChoosed() throws Exception {
		Supplier s= supRepository.findOne(1L);
		
		Collection<Supply> supply=(Collection<Supply>) osService.getWaitingSupply(s.getId());
		if (supply.size()==0)
			this.mvc.perform(post("/api/manager/supply_choosed").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(s))
					).andExpect(status().is4xxClientError());
		else
		this.mvc.perform(post("/api/manager/supply_choosed").contentType(MediaType.APPLICATION_JSON_VALUE).content(IntegrationTestUtils.convertObjectToJsonBytes(s))
			).andExpect(status().isOk());
	}
	
	@Test
	public void addSupply() throws Exception {
		Collection<Supply> e = (Collection<Supply>) supplyRepository.findAll();
		int size= e.size();
		
		Supply s= new Supply();
		s.setFrom_date("");
		s.setName("");
		s.setTo_date("");
		s.setChosed(false);
		Restaurant r= restRepository.findOne(1L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		s.setRestaurant(r);
		
		this.mvc.perform(post("/api/manager/addSupply").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(s))).andExpect(status().isOk());
		
		Collection<Supply> find = (Collection<Supply>) supplyRepository.findAll();
		assertEquals(size+1, find.size());
	}
	
	
	@Test
	public void createOffer() throws Exception {
		Collection<Offer> e = (Collection<Offer>) offerRepository.findAll();
		int size= e.size();
		Offer o= new Offer();
		o.setStatus(Offer_status.WAITING);
		Supply s= supplyRepository.findOne(1L);
		OfferSupply os = new OfferSupply(o, s);
		
		this.mvc.perform(post("/api/manager/createOffer").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(os))).andExpect(status().isOk());
		Collection<Offer> find = (Collection<Offer>) offerRepository.findAll();
		assertEquals(size+1, find.size());
	}
	
	@Test
	public void chooseOffer() throws Exception {
		Supply s= supplyRepository.findOne(4L);
		Restaurant r= s.getRestaurant();
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		s.setRestaurant(r);
		Offer o = (Offer) s.getOffer().toArray()[0];
		OfferSupply os = new OfferSupply(o, s);
		
		this.mvc.perform(post("/api/manager/chooseOffer").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(os))).andExpect(status().isOk());
		Offer find= offerRepository.findOne(o.getId());
		assertEquals(Offer_status.CHOOSED, find.getStatus());
	}
	@Test
	public void addSuppToRest() throws Exception {
		Supplier s= supRepository.findOne(1L);
		Restaurant r= restRepository.findOne(2L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		SupplierRestaurant sr= new SupplierRestaurant(r, s);
		
		this.mvc.perform(post("/api/manager/addSuppToRest").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(sr))).andExpect(status().isOk());
	}
	
	@Test
	public void employeeSchedule() throws Exception {
		EmployeeSchedule es = new EmployeeSchedule();
		esRepository.save(es);
		
		this.mvc.perform(post("/api/manager/employeeSchedule").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(es))).andExpect(status().isOk());
	}
	@Test
	public void addEmployeeSchedule() throws Exception {
		Collection<EmployeeSchedule> e = (Collection<EmployeeSchedule>) esRepository.findAll();
		int size= e.size();
		EmployeeSchedule es = new EmployeeSchedule();
		
		this.mvc.perform(post("/api/manager/addEmployeeSchedule").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(es))).andExpect(status().isOk());
		Collection<EmployeeSchedule> find = (Collection<EmployeeSchedule>) esRepository.findAll();
		assertEquals(size+1, find.size());
	}
	@Test
	public void updateEmployeeSchedule() throws Exception {
		EmployeeSchedule es = new EmployeeSchedule();
		es = esRepository.save(es);
		es.setC1("");
		
		this.mvc.perform(post("/api/manager/updateEmployeeSchedule").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(es))).andExpect(status().isOk());
		EmployeeSchedule find= esRepository.findOne(es.getId());
		assertEquals(find.getC1(), "");
	}
	@Test
	public void deleteEmployeeSchedule() throws Exception {
		EmployeeSchedule es = new EmployeeSchedule();
		es = esRepository.save(es);
		EmployeeSchedule find= esRepository.findOne(1L);
		
		this.mvc.perform(post("/api/manager/deleteEmployeeSchedule").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(find))).andExpect(status().isOk());
		
		assertNull(esRepository.findOne(1L));
	}
	
	@Test
	public void Scores() throws Exception {
		Restaurant r= restRepository.findOne(1L);
		r.setManager(null);
		r.setEmployee(null);
		r.setSuppliers(null);
		r.setMenu(null);
		r.setDrinkMenu(null);
		InputTime it= new InputTime("05/05/1000", "05/05/2200", r);
		
		this.mvc.perform(post("/api/manager/getAvgRest").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(r))).andExpect(status().isOk()).andExpect(jsonPath("$", is(0.0)));
		this.mvc.perform(post("/api/manager/getAvgFood").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(r))).andExpect(status().isOk());
		this.mvc.perform(post("/api/manager/getAvgWaiter").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(r))).andExpect(status().isOk());
		this.mvc.perform(post("/api/manager/getVisits").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(r))).andExpect(status().isOk());
		this.mvc.perform(post("/api/manager/getIncomes").contentType(MediaType.APPLICATION_JSON_VALUE).
				content(IntegrationTestUtils.convertObjectToJsonBytes(it))).andExpect(status().isOk());
		
	}

}