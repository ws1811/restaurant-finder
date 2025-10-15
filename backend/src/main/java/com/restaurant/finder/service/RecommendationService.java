package com.restaurant.finder.service;

import com.restaurant.finder.dto.KakaoApiResponse;
import com.restaurant.finder.dto.Restaurant;
import com.restaurant.finder.dto.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    
    private final KakaoApiService kakaoApiService;
    private final WeatherService weatherService;
    private final GeminiService geminiService;
    
    public RecommendationService(KakaoApiService kakaoApiService, 
                                  WeatherService weatherService,
                                  GeminiService geminiService) {
        this.kakaoApiService = kakaoApiService;
        this.weatherService = weatherService;
        this.geminiService = geminiService;
    }
    
    public String getRecommendation(double latitude, double longitude, 
                                     int peopleCount, String menuPreference) {
        // 1. 주변 식당 조회
        KakaoApiResponse restaurants = kakaoApiService.searchNearbyRestaurants(latitude, longitude, 1000);
        
        // 2. 날씨 조회
        WeatherResponse weather = weatherService.getWeather(latitude, longitude);
        
        // 3. 식당 목록 문자열로 변환
        String restaurantList = restaurants.getDocuments().stream()
                .limit(10)  // 상위 10개만
                .map(r -> String.format("- %s (%s, 거리: %sm)", 
                        r.getPlaceName(), r.getCategoryName(), r.getDistance()))
                .collect(Collectors.joining("\n"));
        
        // 4. Gemini에게 보낼 프롬프트 생성
        String prompt = String.format(
                "다음 조건을 고려해서 식당 3곳을 추천해주세요:\n\n" +
                "인원: %d명\n" +
                "날씨: %s, 온도 %.1f°C\n" +
                "선호 메뉴: %s\n\n" +
                "주변 식당 목록:\n%s\n\n" +
                "추천 이유와 함께 3곳을 추천해주세요.",
                peopleCount,
                weather.getWeather().get(0).getDescription(),
                weather.getMain().getTemp(),
                menuPreference,
                restaurantList
        );
        
        // 5. Gemini로 추천 받기
        return geminiService.generateRecommendation(prompt);
    }
}