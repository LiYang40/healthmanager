package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sleep_records")
public class SleepRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private float targetSleepHours; // User's target sleep hours
    private float actualSleepHours; // Actual sleep hours
    private String date;            // Record date (formatted as yyyy/MM/dd)

    public SleepRecord(float targetSleepHours, float actualSleepHours, String date) {
        this.targetSleepHours = targetSleepHours;
        this.actualSleepHours = actualSleepHours;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTargetSleepHours() {
        return targetSleepHours;
    }

    public void setTargetSleepHours(float targetSleepHours) {
        this.targetSleepHours = targetSleepHours;
    }

    public float getActualSleepHours() {
        return actualSleepHours;
    }

    public void setActualSleepHours(float actualSleepHours) {
        this.actualSleepHours = actualSleepHours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}