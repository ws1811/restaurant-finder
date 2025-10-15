package com.restaurant.finder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.restaurant.finder.dto.WeatherResponse;

@Service
public class WeatherService {
    
    @Value("${openweather.api.key}")
    private String apiKey;
    
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    
    @Cacheable(value = "weather", key = "#latitude + '_' + #longitude")
    public WeatherResponse getWeather(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric&lang=kr",
                WEATHER_API_URL, latitude, longitude, apiKey);
        
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
        
        return response.getBody();
    }
}