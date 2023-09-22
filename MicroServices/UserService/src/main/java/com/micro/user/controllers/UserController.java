package com.micro.user.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.user.entities.User;
import com.micro.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	Create:
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
//	Get One:
	@GetMapping("/{userId}")
//  @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//  @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
  @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
  public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
      logger.info("Get Single User Handler: UserController");
      User user = userService.getUser(userId);
      return ResponseEntity.ok(user);
  }
	
//	creating ratingHotel Fallback method impl for resilience4j circuit breaker:
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//      logger.info("Fallback is executed because service is down : ", ex.getMessage());

      ex.printStackTrace();

      User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
      return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
  }
	
//	Get All:
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
}
