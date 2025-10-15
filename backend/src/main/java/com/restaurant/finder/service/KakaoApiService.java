package com.restaurant.finder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.restaurant.finder.dto.KakaoApiResponse;

@Service
public class KakaoApiService {
    
    @Value("${kakao.api.key}")
    private String apiKey;
    
    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/category.json";
    
    public KakaoApiResponse searchNearbyRestaurants(double latitude, double longitude, int radius) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);
        
        String url = String.format("%s?category_group_code=FD6&x=%f&y=%f&radius=%d&sort=distance",
                KAKAO_API_URL, longitude, latitude, radius);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<KakaoApiResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, KakaoApiResponse.class);
        
        return response.getBody();
    }
}