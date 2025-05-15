package com.example.healthmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ReminderEntity.class}, version = 1, exportSchema = false) // Set exportSchema to false to suppress schema export warnings
public abstract class ReminderDatabase extends RoomDatabase {

    private static ReminderDatabase instance;

    public abstract ReminderDao reminderDao();

    public static synchronized ReminderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ReminderDatabase.class,
                            "reminder_database"
                    )
                    // Use destructive migration for simplicity (not recommended for production apps)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}