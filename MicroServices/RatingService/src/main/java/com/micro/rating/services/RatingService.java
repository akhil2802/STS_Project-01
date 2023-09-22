package com.micro.rating.services;

import java.util.List;

import com.micro.rating.entities.Rating;

public interface RatingService {
	
//	Create:
	Rating create(Rating rating);
	
//	Get All Ratings:
	List<Rating> getRatings();
	
//	Get ALL Ratings by userId:
	List<Rating> getRatingsByUserId(String userId);
	
//	Get ALl Ratings by hotelId:
	List<Rating> getRatingsByHotelId(String hotelId);
}
