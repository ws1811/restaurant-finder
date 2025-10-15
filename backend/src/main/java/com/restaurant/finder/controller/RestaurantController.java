package com.restaurant.finder.controller;

import com.restaurant.finder.dto.KakaoApiResponse;
import com.restaurant.finder.dto.RecommendationRequest;
import com.restaurant.finder.dto.WeatherResponse;
import com.restaurant.finder.service.KakaoApiService;
import com.restaurant.finder.service.RecommendationService;
import com.restaurant.finder.service.WeatherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final KakaoApiService kakaoApiService;
    private final WeatherService weatherService;
    private final RecommendationService recommendationService;
    
    RestaurantController(KakaoApiService kakaoApiService, 
            WeatherService weatherService,
            RecommendationService recommendationService) {
		this.kakaoApiService = kakaoApiService;
		this.weatherService = weatherService;
		this.recommendationService = recommendationService;
	}
	
	@GetMapping("/test")
    public String test() {
        return "Restaurant API Test Success!";
    }
	
	@GetMapping("/nearby")
	public KakaoApiResponse getNearbyRestaurants(
	        @RequestParam(name = "latitude") double latitude,
	        @RequestParam(name = "longitude") double longitude,
	        @RequestParam(name = "radius", defaultValue = "1000") int radius) {
	    return kakaoApiService.searchNearbyRestaurants(latitude, longitude, radius);
	}
	
	@GetMapping("/weather")
    public WeatherResponse getWeather(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude) {
        return weatherService.getWeather(latitude, longitude);
    }
	
	@PostMapping("/recommend")
    public String recommendRestaurants(@RequestBody RecommendationRequest request) {
        return recommendationService.getRecommendation(
                request.getLatitude(),
                request.getLongitude(),
                request.getPeopleCount(),
                request.getMenuPreference()
        );
    }
}