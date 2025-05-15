package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trophies")
public class Trophy {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title; // e.g., "First Day Streak"
    public String description;
    public String dateEarned;

    public Trophy(String title, String description, String dateEarned) {
        this.title = title;
        this.description = description;
        this.dateEarned = dateEarned;
    }
}