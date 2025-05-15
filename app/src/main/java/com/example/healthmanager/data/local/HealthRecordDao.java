package com.example.healthmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.healthmanager.model.HealthRecord;

import java.util.List;

@Dao
public interface HealthRecordDao {

    @Insert
    void insert(HealthRecord record);

    @Delete
    void delete(HealthRecord record);

    @Update
    void update(HealthRecord record);

    @Query("SELECT * FROM health_records ORDER BY date DESC")
    LiveData<List<HealthRecord>> getAllRecords();

    @Query("SELECT SUM(coinsEarned) FROM health_records")
    LiveData<Integer> getTotalCoins();

    @Query("SELECT * FROM health_records ORDER BY date DESC LIMIT 1")
    LiveData<HealthRecord> getLatestRecord();

    // Fetch records based on a specific category (e.g., Sleep, Water, etc.)
    @Query("SELECT * FROM health_records WHERE activityLevel = :category ORDER BY date DESC")
    LiveData<List<HealthRecord>> getRecordsByCategory(String category);
}
