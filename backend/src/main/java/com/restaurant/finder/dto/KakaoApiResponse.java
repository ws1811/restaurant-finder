package com.restaurant.finder.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoApiResponse {
    private List<Restaurant> documents;
    private Meta meta;
    
    public List<Restaurant> getDocuments() {
        return documents;
    }
    
    public void setDocuments(List<Restaurant> documents) {
        this.documents = documents;
    }
    
    public Meta getMeta() {
        return meta;
    }
    
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    
    public static class Meta {
        @JsonProperty("total_count")
        private int totalCount;
        
        public int getTotalCount() {
            return totalCount;
        }
        
        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }
}