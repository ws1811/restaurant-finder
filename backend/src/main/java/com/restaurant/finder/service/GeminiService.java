package com.restaurant.finder.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {
    
    @Value("${gemini.api.key}")
    private String apiKey;
    
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
    
    public String generateRecommendation(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new Gson();
        
        // 요청 바디 구성
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();
        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();
        
        part.addProperty("text", prompt);
        parts.add(part);
        content.add("parts", parts);
        contents.add(content);
        requestBody.add("contents", contents);
        
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String url = GEMINI_API_URL + "?key=" + apiKey;
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestBody), headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        
        // 응답 파싱
        JsonObject responseJson = gson.fromJson(response.getBody(), JsonObject.class);
        String text = responseJson
                .getAsJsonArray("candidates")
                .get(0).getAsJsonObject()
                .getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
        
        return text;
    }
}