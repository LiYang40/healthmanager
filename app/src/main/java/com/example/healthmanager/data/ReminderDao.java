package com.example.healthmanager.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDao {
    @Insert
    void insertReminder(ReminderEntity reminder);

    @Delete
    void deleteReminder(ReminderEntity reminder);

    @Query("SELECT * FROM reminders")
    List<ReminderEntity> getAllReminders();
}