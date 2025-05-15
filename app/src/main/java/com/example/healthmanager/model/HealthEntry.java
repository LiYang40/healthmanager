package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "health_entry")
public class HealthEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public float weight;
    public float height;
    public String symptoms;
    public float bmi;
}
