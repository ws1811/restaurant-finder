package com.restaurant.finder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant {
    private String id;
    
    @JsonProperty("place_name")
    private String placeName;
    
    @JsonProperty("category_name")
    private String categoryName;
    
    private String phone;
    
    @JsonProperty("address_name")
    private String addressName;
    
    @JsonProperty("road_address_name")
    private String roadAddressName;
    
    private String x; // 경도
    private String y; // 위도
    
    private String distance;
    
    @JsonProperty("place_url")
    private String placeUrl;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddressName() {
        return addressName;
    }
    
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    
    public String getRoadAddressName() {
        return roadAddressName;
    }
    
    public void setRoadAddressName(String roadAddressName) {
        this.roadAddressName = roadAddressName;
    }
    
    public String getX() {
        return x;
    }
    
    public void setX(String x) {
        this.x = x;
    }
    
    public String getY() {
        return y;
    }
    
    public void setY(String y) {
        this.y = y;
    }
    
    public String getDistance() {
        return distance;
    }
    
    public void setDistance(String distance) {
        this.distance = distance;
    }
    
    public String getPlaceUrl() {
        return placeUrl;
    }
    
    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }
}