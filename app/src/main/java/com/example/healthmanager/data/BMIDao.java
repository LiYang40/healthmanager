package com.example.healthmanager.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.BMIRecord;

import java.util.List;

@Dao
public interface BMIDao {

    @Insert
    void insert(BMIRecord record);

    @Query("SELECT * FROM bmi_records ORDER BY id DESC")
    LiveData<List<BMIRecord>> getAllRecords();
}