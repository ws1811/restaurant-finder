package com.restaurant.finder.controller;

import com.restaurant.finder.service.KakaoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final KakaoApiService kakaoApiService;

    RestaurantController(KakaoApiService kakaoApiService) {
        this.kakaoApiService = kakaoApiService;
    }
	
	@GetMapping("/test")
    public String test() {
        return "Restaurant API Test Success!";
    }
	
	@GetMapping("/nearby")
	public String getNearbyRestaurants(
	        @RequestParam(name = "latitude") double latitude,
	        @RequestParam(name = "longitude") double longitude,
	        @RequestParam(name = "radius", defaultValue = "1000") int radius) {
	    return kakaoApiService.searchNearbyRestaurants(latitude, longitude, radius);
	}
}
