package com.example.healthmanager.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.HealthRecord;

import java.util.List;

@Dao
public interface HealthDao {

    @Insert
    void insert(HealthRecord healthRecord);

    @Query("SELECT * FROM health_records ORDER BY date ASC")
    List<HealthRecord> getAllRecordsOrderedByDate();

    @Query("SELECT * FROM health_records WHERE category = :category ORDER BY date ASC")
    List<HealthRecord> getRecordsByCategory(String category);
}