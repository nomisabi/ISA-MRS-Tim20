package com.example.domain;

import java.util.HashMap;

public class Guest {
	private String email;
	private String password;
	private String password2;
	private String firstName;
	private String lastName;
	private HashMap<String, Guest> friends;
	private HashMap<String, Restaurant> visited;

	public Guest() {
	}

	public Guest(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.friends = new HashMap<>();
		this.visited = new HashMap<>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public HashMap<String, Guest> getFriends() {
		return friends;
	}

	public void setFriends(HashMap<String, Guest> friends) {
		this.friends = friends;
	}

	public HashMap<String, Restaurant> getVisited() {
		return visited;
	}

	public void setVisited(HashMap<String, Restaurant> visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "Guest [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}
