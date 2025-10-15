package com.restaurant.finder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {
    private List<Weather> weather;
    private Main main;
    
    public List<Weather> getWeather() {
        return weather;
    }
    
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    
    public Main getMain() {
        return main;
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public static class Weather {
        private String main; // Clear, Rain, Snow 등
        private String description;
        
        public String getMain() {
            return main;
        }
        
        public void setMain(String main) {
            this.main = main;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
    }
    
    public static class Main {
        private double temp;
        private double feels_like;
        
        public double getTemp() {
            return temp;
        }
        
        public void setTemp(double temp) {
            this.temp = temp;
        }
        
        public double getFeelsLike() {
            return feels_like;
        }
        
        public void setFeelsLike(double feels_like) {
            this.feels_like = feels_like;
        }
    }
}