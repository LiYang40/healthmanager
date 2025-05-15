package com.example.healthmanager.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminders")
public class ReminderEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String category; // Category of the reminder
    private String time; // Time as a string (e.g., "08:30 AM")
    private boolean isActive; // Whether the reminder is active

    // Fields not returned by the query but used in the app
    private String displayText;
    private boolean isDaily;
    private String days;
    private int hour;
    private int minute;

    // Default constructor (required by Room)
    public ReminderEntity() {
    }

    // Constructor for inserting reminders (ignored by Room)
    @Ignore
    public ReminderEntity(String displayText, boolean isDaily, String days, int hour, int minute, String category) {
        this.displayText = displayText;
        this.isDaily = isDaily;
        this.days = days;
        this.hour = hour;
        this.minute = minute;
        this.category = category;
    }

    // Getters and setters for all fields
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}