package com.micro.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
	
//	Custom finding methods:
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
