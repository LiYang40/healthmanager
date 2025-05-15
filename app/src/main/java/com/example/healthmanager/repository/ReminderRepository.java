package com.example.healthmanager.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.healthmanager.data.local.AppDatabase;
import com.example.healthmanager.data.local.ReminderDao;
import com.example.healthmanager.model.Reminder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReminderRepository {

    private final ReminderDao reminderDao;
    private final LiveData<List<Reminder>> allReminders;
    private final ExecutorService executorService;

    public ReminderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        reminderDao = db.reminderDao();
        allReminders = reminderDao.getAllReminders();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public void insert(Reminder reminder) {
        executorService.execute(() -> reminderDao.insert(reminder));
    }

    public void delete(Reminder reminder) {
        executorService.execute(() -> reminderDao.delete(reminder));
    }
}
