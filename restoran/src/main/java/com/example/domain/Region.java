package com.example.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="region")
	private Set<TableOfRestaurant> tables;

	public Region() {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Set<TableOfRestaurant> getTables() {
		return tables;
	}

	public void setTables(Set<TableOfRestaurant> tables) {
		this.tables = tables;
	}

	
	@Override
	public String toString() {
		return "Region [id=" + id + ", tables=" + tables + ", name="+name+"]";
	}

}
