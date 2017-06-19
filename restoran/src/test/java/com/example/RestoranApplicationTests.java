package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.example.domain.Friendship;
import com.example.domain.Guest;
import com.example.domain.TypeOfUser;
import com.example.domain.User;
import com.example.domain.DTOs.FriendRequest;
import com.example.domain.DTOs.GuestRegister;
import com.example.respository.FriendshipRepository;
import com.example.respository.GuestRepository;
import com.example.respository.UserRepository;

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
				.content(IntegrationTestUtils.convertObjectToJsonBytes(fq))).andExpect(status().isOk());

	}

	@Test
	public void getRequest() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		guestRepository.save(friend);
		Friendship fr = new Friendship(guest, friend.getId(), false);
		friendshipRepository.save(fr);

		this.mvc.perform(post("/api/friendship/getRequest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(friend))).andExpect(status().isOk());

	}
	
	@Test
	public void addFriend() throws Exception {

		Guest friend = new Guest("email@email.com", "password1234", "name", "name");
		guestRepository.save(friend);
		Friendship fr = new Friendship(guest, friend.getId(), false);
		friendshipRepository.save(fr);

		FriendRequest fq = new FriendRequest();
		fq.setIdFriend(guest.getId());
		fq.setIdGuest(friend.getId());
		this.mvc.perform(post("/api/friendship/addFriend").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(IntegrationTestUtils.convertObjectToJsonBytes(fq))).andExpect(status().isOk());

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

}
