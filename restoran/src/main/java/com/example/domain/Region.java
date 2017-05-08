package com.example.domain;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private int x;
	@Column
	private int y;
	//@OneToMany
	private Set<Table> tables;
	@Column 
	private TypeRegion type;
	
	public Region(){
		
	}
	
	public Region(int x, int y, TypeRegion type){
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Set<Table> getTables() {
		return tables;
	}

	public void setTables(Set<Table> tables) {
		this.tables = tables;
	}

	public TypeRegion getType() {
		return type;
	}

	public void setType(TypeRegion type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", x=" + x + ", y=" + y + ", tables=" + tables + ", type=" + type + "]";
	}
	
	
}
