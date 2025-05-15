package com.example.healthmanager.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.model.Reward;
import com.example.healthmanager.model.UserCoins;
import com.example.healthmanager.model.Reminder;

@Database(
        entities = {
                HealthRecord.class,
                Reward.class,
                UserCoins.class,
                Reminder.class
        },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;


    public abstract HealthRecordDao healthRecordDao();
    public abstract HealthDao healthDao();

    public abstract UserCoinsDao userCoinsDao();
    public abstract RewardDao rewardDao();
    public abstract ReminderDao reminderDao(); // If you're using reminders

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "health_manager_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }


}
