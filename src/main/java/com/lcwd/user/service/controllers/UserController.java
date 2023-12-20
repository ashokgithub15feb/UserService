package com.lcwd.user.service.controllers;

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

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

	private static Logger looger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

	int retryCount = 1;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User saveUser = userService.saveUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}

	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackMethod")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallbackMethod")
	//@RateLimiter(name = "userRaateLimiter", fallbackMethod = "ratingHotelFallbackMethod")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		looger.info(String.format("Get single user object : %s ", userId));
		looger.info(String.format("Retry Count : %s ", retryCount));
		retryCount++;
		return ResponseEntity.ok(user);
	}
	
	
	
	public ResponseEntity<User> ratingHotelFallbackMethod(String userId, Exception ex) {
		
		//looger.info(String.format("Fallback is executed beacuse service is down : %s ", ex.getMessage()));
		User user = User.builder()
				.name("Dummy")
				.about("I am dummy account")
				.email("dummy@gmail.com")
				.userId("123456789")
				.build();
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();

		return ResponseEntity.ok(allUser);
	}
	
	
}
