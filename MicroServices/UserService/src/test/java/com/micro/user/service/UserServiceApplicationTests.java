package com.micro.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.micro.user.entities.Rating;
import com.micro.user.external.services.RatingService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceApplicationTests {
	
	private final RatingService ratingService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(7).userId("").hotelId("").feedback("This is created using feign client decalrative approach").build();
		ResponseEntity<Rating> savedRating = ratingService.createRating(rating);
		savedRating.getStatusCode(); 
		System.out.println("New rating created:" + savedRating);
	}

}
