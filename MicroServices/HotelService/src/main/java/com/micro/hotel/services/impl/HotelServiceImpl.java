package com.micro.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micro.hotel.entities.Hotel;
import com.micro.hotel.exceptions.ResourceNotFoundException;
import com.micro.hotel.repositories.HotelRepository;
import com.micro.hotel.services.HotelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

	private final HotelRepository hotelRepo;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepo.save(hotel);
	}

	@Override
	public Hotel getOne(String id) {
		return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found!"));
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepo.findAll();
	}

	

}
