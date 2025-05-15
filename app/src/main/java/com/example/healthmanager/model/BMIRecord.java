package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bmi_records")
public class BMIRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private float weight;   // User's weight in kg
    private float height;   // User's height in meters
    private float bmi;      // Calculated BMI
    private String date;    // Record date (formatted as yyyy/MM/dd)

    public BMIRecord(float weight, float height, float bmi, String date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}