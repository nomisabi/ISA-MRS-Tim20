package com.example.domain.DTOs;

public class Visits {

	private String day;
	private int numb;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public Visits(String day, int numb) {
		super();
		this.day = day;
		this.numb = numb;
	}
	
	
}
