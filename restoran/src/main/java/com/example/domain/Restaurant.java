package com.example.domain;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Restaurant {
	private static final long serialVersionUID =  1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
	private String name;
    @Column
	private String location;
    
	@OneToMany(cascade={CascadeType.MERGE})
	private Set<Manager> manager;

	public Restaurant() {
	}

	public Restaurant(String name, String location, Set<Manager> manager) {
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

	public Set<Manager> getManager() {
		return manager;
	}

	public void setManager(Set<Manager> manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", location=" + location + ", manager=" + manager + "]";
	}

}
