package com.example.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DrinkMenuItem;
import com.example.domain.DrinkMenuItemReservation;
import com.example.domain.Guest;
import com.example.domain.GuestReservation;
import com.example.domain.MenuItem;
import com.example.domain.MenuItemReservation;
import com.example.domain.RateMenuItem;
import com.example.domain.RateRestaurant;
import com.example.domain.Region;
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.domain.User;
import com.example.domain.DTOs.Area;
import com.example.domain.DTOs.ConfirmInvite;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.domain.DTOs.InviteFriends;
import com.example.domain.DTOs.ItemsReservation;
import com.example.domain.DTOs.Rate;
import com.example.domain.DTOs.ReservationDetails;
import com.example.domain.DTOs.RestaurantReservation;
import com.example.domain.DTOs.Table;
import com.example.service.EmailService;
import com.example.service.GuestService;
import com.example.service.RegionService;
import com.example.service.ReservationService;
import com.example.service.RestaurantService;
import com.example.service.UserService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private HttpSession session;

	/*** Get all guests ***/
	@RequestMapping(value = "/api/guests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getGuests() {
		logger.info("> getGuests");
		Collection<Guest> guests = guestService.getAllGuests();
		logger.info("< getGuests");
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Guest log in ***/
	@RequestMapping(value = "/api/guest/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuestLog(@Valid @RequestBody User user) {
		logger.info("> Guest log in");
		System.out.println(user);
		if (user == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest guest = guestService.findByEmailAndPass(user.getEmail(), user.getPassword());
		System.out.println("Guest from base: " + guest);
		if (!guest.isAccepted()){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		session.setAttribute("guest", guest);

		logger.info("< Guest log in");
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/*** Get guest with {id} ***/
	@RequestMapping(value = "/api/guests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id) {
		logger.info("> getGuest id:{}", id);
		Guest guest = guestService.getGuest(id);
		if (guest == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getGuest id:{}", id);
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/*** Guest registration ***/
	@RequestMapping(value = "/api/guests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> createGuest(@Valid @RequestBody GuestRegister guest) throws Exception {
		logger.info("> createGuest");
		System.out.println(guest);
		if (guest.getPassword().equals(guest.getPassword2())) {
			Guest g = new Guest(guest.getEmail(), guest.getPassword(), guest.getFirstName(), guest.getLastName());
			Guest savedGuest = guestService.addGuest(g);
			emailService.sendMail(savedGuest);
			logger.info("< createGuest");
			return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
		}
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
	}

	/*** Guest update ***/
	@RequestMapping(value = "/api/guests/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> updateUser(@PathVariable("id") long id, @RequestBody Guest guest) throws Exception {
		System.out.println("Updating User " + id);
		System.out.println("Guest" + guest);

		Guest currentGuest = guestService.getGuest(id);
		System.out.println("Current guest: " + currentGuest);

		if (currentGuest == null) {
			System.out.println("Guest with id " + id + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		User currentUser = userService.getLogin();
		if (currentUser == null) {
			System.out.println("User not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		if (!currentUser.getEmail().equals(guest.getEmail())) {
			userService.updateEmail(currentUser.getId(), guest.getEmail());
			userService.logout();
		}
		Guest updatedGuest = guestService.updateGuest(id, guest);

		// updatedGuest = guestService.getGuest(id);
		session.setAttribute("guest", updatedGuest);
		System.out.println("Updating guest" + updatedGuest);
		return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
	}

	/*** Send friendship request ***/
	@RequestMapping(value = "/api/friendship/sendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> sendRequest(@RequestBody FriendRequest friendRequest) throws Exception {
		System.out.println(friendRequest);
		Guest currentGuest = guestService.getGuest(friendRequest.getIdGuest());

		if (currentGuest == null) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest sessionGuest = (Guest) session.getAttribute("guest");

		if (currentGuest.getId() != sessionGuest.getId()) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not log in");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		Guest friendGuest = guestService.getGuest(friendRequest.getIdFriend());

		if (friendGuest == null) {
			System.out.println("Guest with id " + friendRequest.getIdGuest() + " not found");
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		guestService.sendFriendRequest(currentGuest, friendGuest);

		return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK);
	}

	/*** Get all requests of guest ***/
	@RequestMapping(value = "/api/friendship/getRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getRequests(@RequestBody Guest guest) throws Exception {
		System.out.println(guest);
		Collection<Guest> guests = guestService.getRequests(guest.getId());
		for (Guest guest2 : guests) {
			System.out.println(guest2);
		}

		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Add friend ***/
	@RequestMapping(value = "/api/friendship/addFriend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> addFriend(@RequestBody FriendRequest friendRequest) throws Exception {
		System.out.println(friendRequest);
		guestService.addFriend(friendRequest.getIdGuest(), friendRequest.getIdFriend());

		return new ResponseEntity<Collection<Guest>>(HttpStatus.OK);
	}

	/*** Delete friend ***/
	@RequestMapping(value = "/api/friendship/deleteFriend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> deleteFriend(@RequestBody FriendRequest friendRequest) throws Exception {
		System.out.println(friendRequest);
		System.out.println("delete");
		boolean flag = guestService.deleteFriend(friendRequest.getIdGuest(), friendRequest.getIdFriend());
		if (!flag) {
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<Guest>>(HttpStatus.OK);
	}

	/***
	 * Return all guest with firstName or lastName that match the search
	 * criteria
	 ***/
	@RequestMapping(value = "/api/friendship/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> searchGuests(@RequestBody Guest guest) {
		logger.info("> getGuests");
		System.out.println(guest);
		Guest cuurrentGuest = (Guest) session.getAttribute("guest");
		System.out.println(cuurrentGuest);
		Collection<Guest> guests = guestService.searchGuest(cuurrentGuest.getId(), guest.getFirstName());

		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/***
	 * Return all friends of guest with firstName or lastName that match the
	 * search criteria
	 ***/
	@RequestMapping(value = "/api/friendship/searchFriends", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> searchFriends(@RequestBody Guest guest) {
		logger.info("> getFriends");
		System.out.println(guest);
		Guest cuurrentGuest = (Guest) session.getAttribute("guest");
		System.out.println(cuurrentGuest);
		Collection<Guest> guests = guestService.searchFriends(cuurrentGuest.getId(), guest.getFirstName());
		for (Guest guest2 : guests) {
			System.out.println(guest2);
		}

		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}

	/*** Get friends ***/
	@RequestMapping(value = "/api/friends", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Guest>> getFriends(@RequestBody Guest guest) {
		logger.info("> getFriends");
		Guest currentGuest = (Guest) session.getAttribute("guest");

		if (currentGuest == null) {
			System.out.println("Guest with id " + guest.getId() + " not found");
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NOT_FOUND);
		}

		Collection<Guest> friends = guestService.findFriends(currentGuest.getId());
		logger.info("< getFriends");
		return new ResponseEntity<Collection<Guest>>(friends, HttpStatus.OK);
	}

	/*** Get all restaurants ***/
	@RequestMapping(value = "/api/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Restaurant>> getRestaurants() {
		logger.info("> getRestaurants");
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		logger.info("< getRestaurants");
		return new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK);
	}

	/*** Get all visited restaurants ***/
	@RequestMapping(value = "/api/visitedRestaurants", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Reservation>> getVisitedRestaurants(@RequestBody Guest guest) {
		logger.info("> getVisitedRestaurants");
		System.out.println(guest);
		Collection<Reservation> restaurants = reservationService.getVisitedRestaurants(guest.getId());
		for (Reservation restaurant : restaurants) {
			System.out.println(restaurant);
		}
		logger.info("< getVisitedRestaurants");
		return new ResponseEntity<Collection<Reservation>>(restaurants, HttpStatus.OK);
	}

	/*** Return all tables ***/
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/restaurant/tables", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Area>> getAvaliableTables(@RequestBody RestaurantReservation reservation) {
		logger.info("> getAvaliableTables");
		System.out.println(reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");

		String startTimeStr = sdf.format(reservation.getDateAndTime());
		System.out.println(startTimeStr);

		Date endTime = reservation.getDateAndTime();
		endTime.setHours(endTime.getHours() + reservation.getDuration());
		String endTimeStr = sdf.format(endTime);
		System.out.println(endTimeStr);

		Collection<TableReservation> reservations = reservationService
				.getAllReservationTable(reservation.getRestaurant().getId(), startTimeStr, endTimeStr);

		HashMap<Long, TableOfRestaurant> tableMap = new HashMap<Long, TableOfRestaurant>();

		HashMap<Long, Area> areas = new HashMap<Long, Area>();

		for (TableReservation tableReservation : reservations) {
			System.out.println(tableReservation);
			tableMap.put(tableReservation.getTable().getId(), tableReservation.getTable());
			System.out.println(tableReservation.getTable().getId());
		}

		Collection<Region> regions = regionService.getRegion(reservation.getRestaurant());

		for (Region region : regions) {
			HashMap<Long, Table> tables = new HashMap<Long, Table>();
			System.out.println(region.getName());
			for (TableOfRestaurant table : region.getTables()) {
				System.out.println(table);
				if (tableMap.containsKey(table.getId())) {
					tables.put(table.getId(), new Table(table, true));
				} else {
					tables.put(table.getId(), new Table(table, false));
				}
			}
			areas.put(region.getId(), new Area(region.getName(), tables.values()));

		}

		return new ResponseEntity<Collection<Area>>(areas.values(), HttpStatus.OK);
	}

	/*** Make reservation ***/
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/restaurant/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> makeReservation(@RequestBody RestaurantReservation reservation) {
		logger.info("> makeReservation");
		System.out.println(reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");

		Date startTime = reservation.getDateAndTime();
		String startTimeStr = sdf.format(startTime);

		Date endTime = reservation.getDateAndTime();
		endTime.setHours(endTime.getHours() + reservation.getDuration());
		String endTimeStr = sdf.format(endTime);

		HashMap<Long, TableReservation> trs = new HashMap<>();

		for (TableOfRestaurant t : reservation.getTables()) {
			System.out.println(t);
			TableReservation tableReservation = new TableReservation(t, startTimeStr, endTimeStr);
			tableReservation = reservationService.saveTable(tableReservation);
			if (tableReservation == null) {
				for (Long id : trs.keySet()) {
					reservationService.deleteTableReservation(id);
				}
				return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
			}
			trs.put(tableReservation.getId(), tableReservation);
		}

		Reservation makeReservation = new Reservation(reservation.getRestaurant(), startTimeStr, endTimeStr);

		Reservation savedReservation = reservationService.createReservation(makeReservation);
		System.out.println(savedReservation);
		if (savedReservation == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}

		for (TableReservation tr : trs.values()) {
			System.out.println(tr);
			reservationService.setReservation(tr.getId(), savedReservation);

		}

		GuestReservation guestReservation = new GuestReservation(reservation.getGuest(), savedReservation, true);
		reservationService.saveGuestReservation(guestReservation);
		logger.info("< makeReservation");

		return new ResponseEntity<Reservation>(savedReservation, HttpStatus.OK);
	}

	/*** Invite friends ***/
	@RequestMapping(value = "/api/restaurant/friends", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> inviteFriends(@RequestBody InviteFriends invite) {
		logger.info("> inviteFriends");
		System.out.println(invite);

		for (Guest guest : invite.getFriends()) {
			GuestReservation guestReservation = new GuestReservation(guest, invite.getReservation());
			guestReservation = reservationService.saveGuestReservation(guestReservation);
			System.out.println(guest);
			try {
				emailService.sendMail(guestReservation, invite.getGuest(), guest);
			} catch (MailException | InterruptedException e) {
				logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}

		Restaurant restaurant = invite.getReservation().getRestaurant();
		System.out.println(restaurant);

		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	/*** Ordering food and drink ***/
	@RequestMapping(value = "/api/restaurant/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Reservation>> orderFoodAndDrink(@RequestBody ItemsReservation itemsReservation) {
		logger.info("> orderingFoodAndDrink");
		System.out.println(itemsReservation);

		for (MenuItemReservation menuItem : itemsReservation.getMenuItems()) {
			System.out.println(menuItem);
			reservationService.saveMenuItem(menuItem);
		}

		for (DrinkMenuItemReservation drinkMenuItem : itemsReservation.getDrinkMenuItems()) {
			System.out.println(drinkMenuItem);
			reservationService.saveDrinkMenuItem(drinkMenuItem);
		}

		return new ResponseEntity<Collection<Reservation>>(HttpStatus.OK);
	}

	/*** Confirm ***/
	@RequestMapping(value = "/api/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfirmInvite> getRequest(@RequestBody ConfirmInvite confirm) {
		logger.info("> confirm");
		System.out.println(confirm);

		Long id = reservationService.getGuestReservationId(confirm.getToken());
		if (id == null){
			return new ResponseEntity<ConfirmInvite>(HttpStatus.NOT_FOUND);
		}
		confirm.setId(id);

		Reservation reservation = reservationService.getReservationOfGuest(id);
		System.out.println(reservation);
		confirm.setReservation(reservation);

		Guest guest = reservationService.getGuestOfGuestReservation(id);

		session.setAttribute("guest", guest);

		User u = userService.findByEmail(guest.getEmail());
		userService.login(u);
		System.out.println(guest);
		confirm.setGuest(guest);
		
		Collection<Guest> guests = reservationService.getFriends(reservation.getId(), guest.getId());
			
		for (Guest guest2 : guests) {
			System.out.println(guest2);
		}
		confirm.setFriends(guests);
	

		

		return new ResponseEntity<ConfirmInvite>(confirm, HttpStatus.OK);
	}

	/*** Confirm ***/
	@RequestMapping(value = "/api/reservation/confirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfirmInvite> confirmationInvite(@RequestBody ConfirmInvite confirm) {
		logger.info("> confirm");
		System.out.println(confirm);
		reservationService.confirmReservation(confirm.getId());
		Long verificationId = reservationService.getVerificationId(confirm.getId());
		reservationService.deleteToken(verificationId);

		return new ResponseEntity<ConfirmInvite>(confirm, HttpStatus.OK);
	}

	/*** Delete ***/
	@RequestMapping(value = "/api/reservation/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfirmInvite> deleteInvite(@RequestBody ConfirmInvite confirm) {
		logger.info("> delete");
		System.out.println(confirm);
		Long verificationId = reservationService.getVerificationId(confirm.getId());
		if (verificationId != null) {
			reservationService.deleteToken(verificationId);
		}
		reservationService.deleteGuestReservation(confirm.getId());

		return new ResponseEntity<ConfirmInvite>(confirm, HttpStatus.OK);
	}

	/*** Get reservation with {id} ***/
	@RequestMapping(value = "/api/reservation/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDetails> getReservation(@PathVariable("id") Long id, @RequestBody Guest guest) {
		logger.info("> getReservation id:{}", id);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(id);
		System.out.println(guest);
		Reservation reservation = reservationService.getReservation(id);
		String startTime = reservation.getStartTime();
		String endTime = reservation.getEndTime();
		boolean flag = false;
		boolean flagRate = false;

		int rateRestaurant = 0;
		int rateFood = 0;

		LocalDateTime startTimeD = LocalDateTime.parse(startTime, sdf);
		LocalDateTime endTimeD = LocalDateTime.parse(endTime, sdf);
		System.out.println(startTimeD);
		if (now.isAfter(endTimeD)) {
			if (!reservation.isRate()) {
				flagRate = true;
			} else {
				RateRestaurant rateR = reservationService.getrate(reservation.getId(), guest.getId());
				if (rateR != null) {
					rateRestaurant = rateR.getRate();
					rateFood = rateR.getRateFood();
				}

			}
		}

		System.out.println(now);

		now = now.plusMinutes(30);
		System.out.println(now);
		if (now.isBefore(startTimeD)) {
			flag = true;
		}

		System.out.println(flag);
		System.out.println(reservation);
		Collection<TableOfRestaurant> tables = reservationService.getAllTableOfReservation(id);
		for (TableOfRestaurant tableOfRestaurant : tables) {
			System.out.println(tableOfRestaurant);
		}
		Collection<Guest> guests = reservationService.getFriends(id, guest.getId());
		for (Guest guest2 : guests) {
			System.out.println(guest2);
		}
		Collection<MenuItemReservation> menuItems = reservationService.getAllMenuItemReservation(id, guest.getId());
		for (MenuItemReservation menuItem : menuItems) {
			System.out.println(menuItem);
		}

		Collection<DrinkMenuItemReservation> drinkMenuItems = reservationService.getAllDrinkMenuItemReservation(id,
				guest.getId());
		for (DrinkMenuItemReservation drinkMenuItem : drinkMenuItems) {
			System.out.println(drinkMenuItem);
		}

		Long guestReservationId = reservationService.getGuestReservationId(id, guest.getId());
		ReservationDetails details = new ReservationDetails(reservation, guestReservationId, tables, guests, menuItems,
				drinkMenuItems, flag, flagRate, rateRestaurant, rateFood);

		return new ResponseEntity<ReservationDetails>(details, HttpStatus.OK);
	}

	/*** Delete reservation ***/
	@RequestMapping(value = "/api/reservation/guest/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDetails> deleteGuestReservation(@RequestBody ReservationDetails details) {
		logger.info("> delete");
		System.out.println(details);

		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		String startTime = details.getReservation().getStartTime();
		boolean flag = false;

		LocalDateTime startTimeD = LocalDateTime.parse(startTime, sdf);
		System.out.println(startTimeD);

		System.out.println(now);

		now = now.plusMinutes(30);
		System.out.println(now);
		if (now.isBefore(startTimeD)) {
			flag = true;
			reservationService.deleteGuestReservation(details.getGuestReservationId());
			return new ResponseEntity<ReservationDetails>(details, HttpStatus.OK);
		}

		details.setFlag(flag);

		return new ResponseEntity<ReservationDetails>(details, HttpStatus.NOT_FOUND);
	}

	/*** Get order ***/
	@RequestMapping(value = "/api/reservation/order/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationDetails> getOrder(@PathVariable("id") Long id, @RequestBody Guest guest) {
		logger.info("> orderingFoodAndDrink");
		System.out.println(id);
		System.out.println(guest);
		Reservation reservation = reservationService.getReservationId(id);
		System.out.println(reservation.getId());
		HashMap<Long, MenuItemReservation> menuReservation = new HashMap<Long, MenuItemReservation>();

		Collection<MenuItemReservation> menuItemReservation = reservationService
				.getAllMenuItemReservation(reservation.getId(), guest.getId());
		for (MenuItemReservation menuItemReservation2 : menuItemReservation) {
			System.out.println(menuItemReservation2);
			menuReservation.put(menuItemReservation2.getMenuItem().getId(), menuItemReservation2);
		}

		HashMap<Long, DrinkMenuItemReservation> drinkReservation = new HashMap<Long, DrinkMenuItemReservation>();
		Collection<DrinkMenuItemReservation> drinkItemReservation = reservationService
				.getAllDrinkMenuItemReservation(reservation.getId(), guest.getId());
		for (DrinkMenuItemReservation drinkMenuItemReservation : drinkItemReservation) {
			System.out.println(drinkMenuItemReservation);
			drinkReservation.put(drinkMenuItemReservation.getDrinkMenuItem().getId(), drinkMenuItemReservation);
		}

		Restaurant restaurant = reservationService.getRestaurant(reservation.getId());
		System.out.println(restaurant);

		for (MenuItem item : restaurant.getMenu().getItems()) {
			System.out.println(item);
			if (!menuReservation.containsKey(item.getId())) {
				menuReservation.put(item.getId(), new MenuItemReservation(item, guest, reservation));
			}
		}

		for (DrinkMenuItem item : restaurant.getDrinkMenu().getItems()) {
			System.out.println(item);
			if (!drinkReservation.containsKey(item.getId())) {
				drinkReservation.put(item.getId(), new DrinkMenuItemReservation(item, guest, reservation));
			}
		}

		ReservationDetails details = new ReservationDetails(menuReservation.values(), drinkReservation.values());

		return new ResponseEntity<ReservationDetails>(details, HttpStatus.OK);
	}

	/*** Ordering food and drink ***/
	@RequestMapping(value = "/api/reservation/changeOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Reservation>> changeOrder(@RequestBody ItemsReservation itemsReservation) {
		logger.info("> orderingFoodAndDrink");
		System.out.println(itemsReservation);
		System.out.println(itemsReservation.getGuest());
		System.out.println(itemsReservation.getReservation());

		Collection<MenuItemReservation> reservedItems = reservationService.getAllMenuItemReservation(
				itemsReservation.getReservation().getId(), itemsReservation.getGuest().getId());
		HashMap<Long, MenuItemReservation> hashReservedItems = new HashMap<>();

		for (MenuItemReservation menuItemReservation : reservedItems) {
			hashReservedItems.put(menuItemReservation.getId(), menuItemReservation);

		}

		Collection<DrinkMenuItemReservation> reservedDrink = reservationService.getAllDrinkMenuItemReservation(
				itemsReservation.getReservation().getId(), itemsReservation.getGuest().getId());

		HashMap<Long, DrinkMenuItemReservation> hashReservedDrink = new HashMap<>();

		for (DrinkMenuItemReservation drinkMenuItemReservation : reservedDrink) {
			hashReservedDrink.put(drinkMenuItemReservation.getId(), drinkMenuItemReservation);
		}

		for (MenuItemReservation menuItem : itemsReservation.getMenuItems()) {
			System.out.println(menuItem);
			if (menuItem.getId() == null) {
				reservationService.saveMenuItem(menuItem);
			} else {
				reservationService.updateMenuItem(menuItem.getId(), menuItem.getQuantity());
				if (hashReservedItems.containsKey(menuItem.getId())) {
					hashReservedItems.remove(menuItem.getId());
				}
			}
		}

		for (DrinkMenuItemReservation drinkMenuItem : itemsReservation.getDrinkMenuItems()) {
			System.out.println(drinkMenuItem);
			if (drinkMenuItem.getId() == null) {
				reservationService.saveDrinkMenuItem(drinkMenuItem);
			} else {
				reservationService.updateDrinkItem(drinkMenuItem.getId(), drinkMenuItem.getQuantity());
				if (hashReservedDrink.containsKey(drinkMenuItem.getId())) {
					hashReservedDrink.remove(drinkMenuItem.getId());
				}
			}
		}

		for (DrinkMenuItemReservation drinkMenuItemReservation : hashReservedDrink.values()) {
			System.out.println(drinkMenuItemReservation);
			reservationService.deleteDrinkItem(drinkMenuItemReservation.getId());
		}

		for (MenuItemReservation menuItemReservation : hashReservedItems.values()) {
			System.out.println(menuItemReservation);
			reservationService.deleteMenuItem(menuItemReservation.getId());
		}
		return new ResponseEntity<Collection<Reservation>>(HttpStatus.OK);
	}

	/*** Rate restaurant and food ***/
	@RequestMapping(value = "/api/restaurant/rate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Reservation>> rateReservation(@RequestBody Rate rate) {
		logger.info("> rateRestaurant");
		System.out.println(rate);

		RateRestaurant rateRestaurant = new RateRestaurant(rate.getGuest(), rate.getReservation().getRestaurant(),
				rate.getReservation(), rate.getRateRestaurant(), rate.getRateMenu());
		reservationService.saveRateRestaurant(rateRestaurant);

		Collection<MenuItemReservation> menuItems = reservationService
				.getAllMenuItemReservation(rate.getReservation().getId(), rate.getGuest().getId());
		for (MenuItemReservation menuItemReservation : menuItems) {
			RateMenuItem rateMenuItem = new RateMenuItem(rate.getGuest(), menuItemReservation.getMenuItem(),
					rate.getReservation(), rate.getRateMenu());
			reservationService.saveRateMenuItem(rateMenuItem);
		}

		reservationService.setRate(rate.getReservation().getId());

		return new ResponseEntity<Collection<Reservation>>(HttpStatus.OK);
	}
	
	/*** Guest registration ***/
	@RequestMapping(value = "/api/guest/changePass", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> changePassword(@Valid @RequestBody GuestRegister guest) throws Exception {
		logger.info("> createGuest");
		System.out.println(guest);
		Guest findGuest = guestService.getGuest(guest.getId());
		
		if (!findGuest.getPassword().equals(guest.getOldPassword())){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		
		if (!guest.getPassword().equals(guest.getPassword2())){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		
		guestService.setPassword(guest.getId(), guest.getPassword());
		
		User u = userService.getLogin();
		userService.changePassword(u.getId(), guest.getPassword());
		
		return new ResponseEntity<Guest>(HttpStatus.OK);
	}
	
	/***
	 * Return all friends of guest with firstName or lastName that match the
	 * search criteria
	 ***/
	@RequestMapping(value = "/api/restaurant/searchRestaurants", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Restaurant>> searchRestaurants(@RequestBody Restaurant restaurant) {
		logger.info("> getRestaurants");
		System.out.println(restaurant);
		
		Collection<Restaurant> restaurants = restaurantService.searchRestaurants(restaurant.getName());

		return new ResponseEntity<Collection<Restaurant>>(restaurants,HttpStatus.OK);
	}
	
	/*** Guest log in****/
	@RequestMapping(value = "/api/guest/accept", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> registrationAccept(@RequestBody GuestRegister guestRegister) {
		logger.info("> Guest log in");
		System.out.println(guestRegister);
		
		Guest guest = guestService.getGuestId(guestRegister.getToken());
		System.out.println(guest);
		if (guest == null){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		
		guestService.setRegistrationAccept(guest.getId());
		
		User u = userService.findByEmail(guest.getEmail());
		
		userService.login(u);
		
		session.setAttribute("guest", guest);
		
		guestService.deleteToken(guestRegister.getToken());
		
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
		
	}

}
