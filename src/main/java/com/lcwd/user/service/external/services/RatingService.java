package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/v1/api/ratings")
	public Rating createRating(@RequestBody Rating values);
	
	@PutMapping(name = "/v1/api/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId, Rating values);

	@DeleteMapping(name = "/v1/api/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	
}
