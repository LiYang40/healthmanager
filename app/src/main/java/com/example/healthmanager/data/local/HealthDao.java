package com.example.healthmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.healthmanager.model.HealthRecord;
import java.util.List;

@Dao
public interface HealthDao {

    @Insert
    void insert(HealthRecord healthRecord);

    @Query("SELECT * FROM health_records ORDER BY date ASC")  // Ensure you're ordering by 'date'
    LiveData<List<HealthRecord>> getAllRecords();  // Get all health records sorted by date
}
