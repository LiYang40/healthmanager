package com.example.healthmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthmanager.model.Reminder;
import com.example.healthmanager.repository.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends ViewModel {

    private final ReminderRepository repository;
    private final LiveData<List<Reminder>> allReminders;

    public ReminderViewModel(ReminderRepository repository) {
        this.repository = repository;
        this.allReminders = repository.getAllReminders(); // <- Initialize it here
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public void insertReminder(Reminder reminder) {
        repository.insert(reminder);
    }

    public void deleteReminder(Reminder reminder) {
        repository.delete(reminder);
    }
}
