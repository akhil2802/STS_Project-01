package com.micro.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micro.rating.entities.Rating;
import com.micro.rating.repositories.RatingRepository;
import com.micro.rating.services.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

	private final RatingRepository ratingRepo;
	
	@Override
	public Rating create(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		return ratingRepo.findByHotelId(hotelId);
	}

}
