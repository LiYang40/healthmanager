package com.example.healthmanager.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.SleepRecord;

import java.util.List;

@Dao
public interface SleepDao {

    @Insert
    void insert(SleepRecord record);

    @Query("SELECT * FROM sleep_records ORDER BY id DESC")
    LiveData<List<SleepRecord>> getAllRecords();
}