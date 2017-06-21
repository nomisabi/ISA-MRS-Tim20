package com.example.domain.DTOs;

import java.util.Collection;

public class Area {
	private String name;
	private Collection<Table> tables;

	public Area() {

	}

	public Area(String name, Collection<Table> tables) {
		super();
		this.name = name;
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Table> getTables() {
		return tables;
	}

	public void setTables(Collection<Table> tables) {
		this.tables = tables;
	}

}
