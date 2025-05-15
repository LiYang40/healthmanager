package com.example.healthmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.data.ReminderEntity;
import com.example.healthmanager.model.Trophy;

@Database(entities = {HealthRecord.class, ReminderEntity.class, Trophy.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // Singleton instance of AppDatabase
    private static volatile AppDatabase INSTANCE;

    // DAO declarations
    public abstract HealthDao healthDao();
    public abstract ReminderDao reminderDao();
    public abstract TrophyDao trophyDao();

    // Method to get the singleton instance of AppDatabase
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "health_manager_database")
                            .fallbackToDestructiveMigration() // Optional: handles schema migrations
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}