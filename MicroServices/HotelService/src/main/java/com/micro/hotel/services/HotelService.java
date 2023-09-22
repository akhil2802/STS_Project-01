package com.micro.hotel.services;

import java.util.List;

import com.micro.hotel.entities.Hotel;

public interface HotelService {
	
//	Create:
	Hotel create(Hotel hotel);
	
//	Get One:
	Hotel getOne(String id);
	
//	Get All:
	List<Hotel> getAll();
}
