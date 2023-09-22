package com.micro.user.services.impl;

	import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.user.entities.Hotel;
import com.micro.user.entities.Rating;
import com.micro.user.entities.User;
import com.micro.user.exceptions.ResourceNotFoundException;
import com.micro.user.external.services.HotelService;
import com.micro.user.repositories.UserRepository;
import com.micro.user.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final RestTemplate restTemplate;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final HotelService hotelService;
	
	@Override
	public User saveUser(User user) {
//		generate unique UUID:
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not found on server: " + userId));

		//		fetch rating of above user from Rating Service:
		Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		List<Rating> ratings = Arrays.stream(userRatings).toList();
		logger.info("{}", ratings);
		
		List<Rating> userRatingList = ratings.stream().map(rating -> {

			// api call to hotel service to get hotel:
//			ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
//			set the hotel to rating:
			rating.setHotel(hotel);
			
//			return the rating with hotel info:
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(userRatingList);
		logger.info("{}", user);
		return user;
	}

//	TODO: updateUser()

	@Override
	public User updateUser(String userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
//  TODO: deleteUser()	

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

}
