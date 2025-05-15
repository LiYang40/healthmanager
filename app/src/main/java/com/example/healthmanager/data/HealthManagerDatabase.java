package com.example.healthmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthmanager.model.BMIRecord;
import com.example.healthmanager.model.SleepRecord;

@Database(entities = {BMIRecord.class, SleepRecord.class}, version = 2,exportSchema = false) // Increment version to 2
public abstract class HealthManagerDatabase extends RoomDatabase {

    private static volatile HealthManagerDatabase INSTANCE;

    public abstract BMIDao bmiDao();
    public abstract SleepDao sleepDao(); // Add SleepDao

    public static HealthManagerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HealthManagerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    HealthManagerDatabase.class,
                                    "health_manager_database"
                            )
                            .fallbackToDestructiveMigration() // Handle migration for version upgrade
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}