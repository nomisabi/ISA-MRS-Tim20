package com.example.domain;

import java.util.ArrayList;

public class Restaurant {
	private String name;
	private String location;
	private ArrayList<Manager> manager = new ArrayList<Manager>();

	public Restaurant() {
	}

	public Restaurant(String name, String location, ArrayList<Manager> manager) {
		super();
		this.name = name;
		this.location = location;
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Manager> getManager() {
		return manager;
	}

	public void setManager(ArrayList<Manager> manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", location=" + location + ", manager=" + manager + "]";
	}
	
	

}
