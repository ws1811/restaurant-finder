package com.restaurant.finder.dto;

public class RecommendationRequest {
    private double latitude;
    private double longitude;
    private int peopleCount;
    private String menuPreference;  // 예: "매운 음식", "중식", "한식"
    
    // Getters and Setters
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public int getPeopleCount() {
        return peopleCount;
    }
    
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
    
    public String getMenuPreference() {
        return menuPreference;
    }
    
    public void setMenuPreference(String menuPreference) {
        this.menuPreference = menuPreference;
    }
}