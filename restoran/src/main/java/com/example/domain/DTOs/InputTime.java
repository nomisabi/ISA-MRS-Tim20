package com.example.domain.DTOs;

import com.example.domain.Restaurant;

public class InputTime {

	private String begin;
	private String end;
	private Restaurant r;
	
	public InputTime(String begin, String end, Restaurant r) {
		super();
		this.begin = begin;
		this.end = end;
		this.r = r;
	}
	
	public InputTime() {
		super();

	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Restaurant getR() {
		return r;
	}
	public void setR(Restaurant r) {
		this.r = r;
	}

	
}
