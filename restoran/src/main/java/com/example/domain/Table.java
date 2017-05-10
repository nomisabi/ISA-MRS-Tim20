package com.example.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Table implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private int x;
	@Column
	private int y;
	@Column
	private int numChairs;
	@Column
	private boolean ocupied;
	
	public Table() {
	
	}
	
	public Table(int x, int y, int numChairs, boolean ocupied){
		super();
		this.x = x;
		this.y = y;
		this.numChairs = numChairs;
		this.ocupied = ocupied;
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

	public int getNumChairs() {
		return numChairs;
	}

	public void setNumChairs(int numChairs) {
		this.numChairs = numChairs;
	}

	public boolean isOcupied() {
		return ocupied;
	}

	public void setOcupied(boolean ocupied) {
		this.ocupied = ocupied;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", x=" + x + ", y=" + y + ", numChairs=" + numChairs + ", ocupied=" + ocupied + "]";
	}
	
	

}
