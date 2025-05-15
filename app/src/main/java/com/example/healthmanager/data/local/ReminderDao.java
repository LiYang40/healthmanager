package com.example.healthmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert
    void insert(Reminder reminder);  // <- Room annotation

    @Delete
    void delete(Reminder reminder);  // <- Room annotation

    @Query("SELECT * FROM Reminder ORDER BY id DESC")
    LiveData<List<Reminder>> getAllReminders();
}
