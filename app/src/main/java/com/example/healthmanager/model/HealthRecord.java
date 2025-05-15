package com.example.healthmanager.model;

import androidx.room.Entity;
<<<<<<< HEAD
import androidx.room.PrimaryKey;

@Entity(tableName = "health_records")  // Ensures table name is 'health_records'
=======
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "health_records")
>>>>>>> 98b3c21 (Update project with latest changes)
public class HealthRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

<<<<<<< HEAD
    private double weight;
    private double height;
    private double calories;
    private String activityLevel;
    private double sleep;
    private double water;
    private String date;

    private int coinsEarned;

    public HealthRecord(double weight, double height, double calories, String activityLevel,
                        double sleep, double water, String date) {
        this.weight = weight;
        this.height = height;
        this.calories = calories;
        this.activityLevel = activityLevel;
        this.sleep = sleep;
        this.water = water;
        this.date = date;
        this.coinsEarned = 0;
    }

    // Getters and setters for the fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }

    public double getSleep() { return sleep; }
    public void setSleep(double sleep) { this.sleep = sleep; }

    public double getWater() { return water; }
    public void setWater(double water) { this.water = water; }

    public String getDate() { return date; }  // Getter for date
    public void setDate(String date) { this.date = date; }

    public int getCoinsEarned() { return coinsEarned; }
    public void setCoinsEarned(int coinsEarned) { this.coinsEarned = coinsEarned; }
}
=======
    private String category; // e.g., Weight, BMI, Activity
    private float value;     // e.g., 70.5 (for weight), 22.4 (for BMI)
    private long date;       // Unix timestamp for the date of the record

    // Required for Firebase (No-argument constructor)
    public HealthRecord() {}

    // Add @Ignore annotation to tell Room to ignore this constructor
    @Ignore
    public HealthRecord(String category, float value, long date) {
        this.category = category;
        this.value = value;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
>>>>>>> 98b3c21 (Update project with latest changes)
