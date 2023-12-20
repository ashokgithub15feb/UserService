package com.lcwd.user.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;

@Service
public class UserService{

	private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	public User saveUser(User user) {

		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);

		return userRepository.save(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! "+userId));
		
		//fetch rating of the above user from Rating Service
		//http://localhost:8083/v1/api/rating/users/9970317c-50f9-48ae-8c81-010959b9ccef
		
		//calling Rating service to get the user rating
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/v1/api/ratings/users/"+user.getUserId(), Rating[].class);
		LOGGER.info(String.format("Getting rating for User %s", Arrays.toString(ratingOfUser)));
		
		List<Rating> ratingUserList = Arrays.stream(ratingOfUser).toList();
		
		user.setRatings(ratingUserList);
		
		List<Rating> ratingList = ratingUserList.stream().map(rating -> {
			
			//api call to hotel service to get the hotel
			
			//using rest template calling hotel service
			//ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/v1/api/hotels/"+rating.getHotelId(), Hotel.class);
			//Hotel respHotel = responseEntity.getBody();
			
			//using feign client calling hotel service
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			//LOGGER.info(String.format("Response status code %s", responseEntity.getStatusCode()));
			//set the hotel to rating
			rating.setHotel(hotel);	
			//return the rating
			
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

}
