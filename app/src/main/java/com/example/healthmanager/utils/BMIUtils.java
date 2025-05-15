package com.example.healthmanager.utils;

public class BMIUtils {

    // Calculate BMI based on weight (kg) and height (m)
    public static double calculateBMI(double weightKg, double heightM) {
        if (heightM <= 0) throw new IllegalArgumentException("Height must be greater than zero.");
        return weightKg / (heightM * heightM);
    }

    // Provide health advice based on BMI
    public static String getHealthAdvice(double bmi) {
        if (bmi < 18.5) return "Underweight: Consider gaining some weight.";
        else if (bmi < 24.9) return "Normal: Maintain your current weight.";
        else if (bmi < 29.9) return "Overweight: Consider a healthy diet and exercise.";
        else return "Obese: Consult a healthcare professional.";
    }
}