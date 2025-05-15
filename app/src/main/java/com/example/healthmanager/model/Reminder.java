package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

<<<<<<< HEAD
@Entity
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String time;
    private String repeat;

    public Reminder(String title, String time, String repeat) {
        this.title = title;
        this.time = time;
        this.repeat = repeat;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public String getTime() { return time; }
    public String getRepeat() { return repeat; }

    public void setTitle(String title) { this.title = title; }
    public void setTime(String time) { this.time = time; }
    public void setRepeat(String repeat) { this.repeat = repeat; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Reminder)) return false;
        Reminder other = (Reminder) obj;
        return id == other.id && title.equals(other.title)
                && time.equals(other.time) && repeat.equals(other.repeat);
    }
}
=======
@Entity(tableName = "reminders")
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String category; // e.g., "Daily Log Reminder"
    public String time; // Format: HH:mm
    public boolean isActive;

    public Reminder(String category, String time, boolean isActive) {
        this.category = category;
        this.time = time;
        this.isActive = isActive;
    }
}
>>>>>>> 98b3c21 (Update project with latest changes)
