package com.example.controller;

import java.text.SimpleDateFormat;
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
import com.example.domain.Reservation;
import com.example.domain.Restaurant;
import com.example.domain.TableOfRestaurant;
import com.example.domain.TableReservation;
import com.example.domain.User;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.domain.DTOs.InviteFriends;
import com.example.domain.DTOs.ItemsReservation;
import com.example.domain.DTOs.RestaurantReservation;
import com.example.domain.DTOs.Table;
import com.example.service.GuestService;
import com.example.service.ReservationService;
import com.example.service.RestaurantService;
import com.example.service.TableOfRestaurantService;
import com.example.service.UserService;

@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private TableOfRestaurantService tableService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private UserService userService;
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
		Collection<Reservation> restaurants = reservationService.getVisitedRestaurant(guest.getId());
		for (Reservation restaurant : restaurants) {
			System.out.println(restaurant);
		}
		logger.info("< getVisitedRestaurants");
		return new ResponseEntity<Collection<Reservation>>(restaurants, HttpStatus.OK);
	}

	/*** Return all tables ***/
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/restaurant/tables", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Table>> getAvaliableTables(@RequestBody RestaurantReservation reservation) {
		logger.info("> getAvaliableTables");
		System.out.println(reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");

		String startTimeStr = sdf.format(reservation.getDateAndTime());
		System.out.println(startTimeStr);

		Date endTime = reservation.getDateAndTime();
		endTime.setHours(endTime.getHours() + reservation.getDuration());
		String endTimeStr = sdf.format(endTime);
		System.out.println(endTimeStr);

		Collection<TableReservation> reservations = reservationService
				.getAllReservationOfRestaurantInTime(reservation.getRestaurant().getId(), startTimeStr, endTimeStr);

		HashMap<Long, Table> tableSS = new HashMap<Long, Table>();
		// for (Reservation reservation2 : reservations) {
		// System.out.println(reservation2.getTable());
		// tableSS.put(reservation2.getTable().getId(), new
		// Table(reservation2.getTable(), true));
		//
		// }

		for (TableReservation tableReservation : reservations) {
			System.out.println(tableReservation);
			tableSS.put(tableReservation.getTable().getId(), new Table(tableReservation.getTable(), true));
		}

		Collection<TableOfRestaurant> tables = tableService
				.getAllTableOfRestaurant(reservation.getRestaurant().getId());
		for (TableOfRestaurant tableOfRestaurant : tables) {
			System.out.println(tableOfRestaurant);
			if (!tableSS.containsKey(tableOfRestaurant.getId())) {
				tableSS.put(tableOfRestaurant.getId(), new Table(tableOfRestaurant, false));
			}
		}

		for (Table tab : tableSS.values()) {
			System.out.println(tab);
		}

		return new ResponseEntity<Collection<Table>>(tableSS.values(), HttpStatus.OK);
	}

	/*** Make reservation ***/
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/api/restaurant/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> makeReservation(@RequestBody RestaurantReservation reservation) {
		logger.info("> makeReservation");
		System.out.println(reservation);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");

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

		GuestReservation guestReservation = new GuestReservation(reservation.getGuest(), savedReservation);
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
			reservationService.saveGuestReservation(guestReservation);
			System.out.println(guest);
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

		for (MenuItem menuItem : itemsReservation.getMenuItems()) {
			System.out.println(menuItem);
			MenuItemReservation menuItemReservation = new MenuItemReservation(menuItem, itemsReservation.getGuest(),
					itemsReservation.getReservation());
			reservationService.saveMenuItem(menuItemReservation);
		}

		for (DrinkMenuItem drinkMenuItem : itemsReservation.getDrinkMenuItems()) {
			System.out.println(drinkMenuItem);
			DrinkMenuItemReservation drinkMenuItemReservation = new DrinkMenuItemReservation(drinkMenuItem,
					itemsReservation.getGuest(), itemsReservation.getReservation());
			reservationService.saveDrinkMenuItem(drinkMenuItemReservation);
		}

		return new ResponseEntity<Collection<Reservation>>(HttpStatus.OK);
	}

}
