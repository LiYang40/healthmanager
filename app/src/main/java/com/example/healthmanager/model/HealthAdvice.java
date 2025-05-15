package com.example.healthmanager.model;

public class HealthAdvice {
    private String category;
    private boolean isPositive;
    private String suggestion;

    public HealthAdvice(String category, boolean isPositive, String suggestion) {
        this.category = category;
        this.isPositive = isPositive;
        this.suggestion = suggestion;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
