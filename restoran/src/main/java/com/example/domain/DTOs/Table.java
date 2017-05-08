package com.example.domain.DTOs;

import java.io.Serializable;

import com.example.domain.TableOfRestaurant;

public class Table implements Serializable {
	private static final long serialVersionUID = 1L;

	private TableOfRestaurant tableOfRestaurant;
	private boolean reserved;

	public Table() {
	}

	public Table(TableOfRestaurant tableOfRestaurant, boolean reserved) {
		super();
		this.tableOfRestaurant = tableOfRestaurant;
		this.reserved = reserved;
	}

	public TableOfRestaurant getTableOfRestaurant() {
		return tableOfRestaurant;
	}

	public void setTableOfRestaurant(TableOfRestaurant tableOfRestaurant) {
		this.tableOfRestaurant = tableOfRestaurant;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "Table [tableOfRestaurant=" + tableOfRestaurant + ", reserved=" + reserved + "]";
	}

}
