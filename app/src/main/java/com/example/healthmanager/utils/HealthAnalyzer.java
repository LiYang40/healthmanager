package com.example.healthmanager.utils;

import com.example.healthmanager.model.HealthRecord;

import java.util.ArrayList;
import java.util.List;

public class HealthAnalyzer {

    public static List<String> generateAdvice(HealthRecord record) {
        List<String> advice = new ArrayList<>();

        // Example logic to generate advice
        if (record.getCalories() > 2500) {
            advice.add("Consider reducing your calorie intake.");
        } else {
            advice.add("Your calorie intake is within a healthy range.");
        }

        if (record.getWater() < 2000) {
            advice.add("You should drink more water.");
        }

        if (record.getSleep() < 7) {
            advice.add("Aim for more than 7 hours of sleep.");
        }

        // Add more logic based on other fields such as weight, activity level, etc.

        return advice;
    }
}
