package com.example.domain.DTOs;

import java.io.Serializable;
import java.util.Date;

import com.example.domain.Guest;
import com.example.domain.Restaurant;

public class RestaurantReserve implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Restaurant restaurant;
	private Date dateAndTime;
	private int duration;
	private Guest guest;

	

}
